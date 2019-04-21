package com.owl.owlBlog.service;


import com.owl.owlBlog.bo.CommentBo;
import com.owl.owlBlog.constant.WebConst;
import com.owl.owlBlog.dao.CommentDao;
import com.owl.owlBlog.pojo.Comment;
import com.owl.owlBlog.pojo.Content;
import com.owl.owlBlog.util.DateKit;
import com.owl.owlBlog.util.IdWorker;
import com.owl.owlBlog.util.Page4Navigator;
import com.owl.owlBlog.util.TaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BlueT on 2017/3/16.
 */
@Service
public class ICommentService {
    @Resource
    CommentDao commentDao;
    @Resource
    IContentService contentService;
    @Resource
    IdWorker idWorker;

    /**
     * 保存对象
     *
     * @param commentVo
     */
    public String insertComment(Comment comments) {
        if (null == comments) {
            return "评论对象为空";
        }
        if (StringUtils.isBlank(comments.getAuthor())) {
            comments.setAuthor("热心网友");
        }
        if (StringUtils.isNotBlank(comments.getMail()) && !TaleUtils.isEmail(comments.getMail())) {
            return "请输入正确的邮箱格式";
        }
        if (StringUtils.isBlank(comments.getContent())) {
            return "评论内容不能为空";
        }
        if (comments.getContent().length() < 5 || comments.getContent().length() > 2000) {
            return "评论字数在5-2000个字符";
        }
        if (null == comments.getContents().getCid()) {
            return "评论文章不能为空";
        }
        Content contents = contentService.getContents(String.valueOf(comments.getContents().getCid()));
        if (null == contents) {
            return "不存在的文章";
        }
        comments.setCoid(idWorker.nextId() + "");
        comments.setContents(contents);
        comments.setOwnerId(contents.getAuthorId());
        comments.setAuthorId("0");
        comments.setStatus("not_audit");
        comments.setCreated(DateKit.getCurrentUnixTime());
        commentDao.save(comments);

        contents.setCommentsNum(contents.getCommentsNum() + 1);
        contentService.updateContentByCid(contents);
        return WebConst.SUCCESS_RESULT;
    }

    /**
     * 获取文章下的评论
     *
     * @param cid
     * @param page
     * @param limit
     * @return CommentBo
     */
    public Page4Navigator<Comment> getComments(String cid, int page, int limit) {
        if (null != cid) {

            Sort sort = new Sort(Sort.Direction.DESC, "created");
            Pageable pageable = PageRequest.of(page-1, limit, sort);
            Page<Comment> parents = commentDao.findByContents_CidAndParentAndStatusAndStatusNotNull(cid, 0, "approved", pageable);
            return new Page4Navigator<>(parents, 5);
        }
        return null;
    }

    /**
     * 获取文章下的评论
     *
     * @param commentVoExample
     * @param page
     * @param limit
     * @return CommentVo
     */
    public Page4Navigator<Comment> getCommentsWithPage(String uid, int page, int limit) {
        Sort sort = new Sort(Sort.Direction.DESC, "created");
        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        Page<Comment> byAuthorIdNot = commentDao.findByAuthorIdNot(uid, pageable);
        List<Comment> content = byAuthorIdNot.getContent();
        for (Comment comment : content) {
            if (comment.getContent().length() > 20)
                comment.setContent(comment.getContent().substring(0, 20) + "...");
        }
        return new Page4Navigator<>(byAuthorIdNot, 5);
    }


    /**
     * 根据主键查询评论
     *
     * @param coid
     * @return
     */
    public Comment getCommentById(String coid) {
        Comment comment = commentDao.findById(coid).get();
        return comment;
    }


    /**
     * 删除评论，暂时没用
     *
     * @param coid
     * @param cid
     * @throws Exception
     */
    public void delete(String coid, String cid) {
        if (coid != null ) {
            commentDao.deleteById(coid);
            if (cid != null){
                Content contents = contentService.getContents(cid);
                contents.setCommentsNum(contents.getCommentsNum()-1);
                if (null != contents && contents.getCommentsNum() > 0) {
                    contentService.updateContentByCid(contents);
                }
            }
        }

    }

    /**
     * 更新评论状态
     *
     * @param comments
     */
    public void update(Comment comments) {
        commentDao.save(comments);
    }

}
