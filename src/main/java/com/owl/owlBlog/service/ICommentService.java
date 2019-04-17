package com.owl.owlBlog.service;


import com.owl.owlBlog.bo.CommentBo;
import com.owl.owlBlog.dao.CommentDao;
import com.owl.owlBlog.pojo.Comment;
import com.owl.owlBlog.util.Page4Navigator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by BlueT on 2017/3/16.
 */
@Service
public class ICommentService {
@Resource
    CommentDao commentDao;
    /**
     * 保存对象
     * @param commentVo
     */
    String insertComment(Comment commentVo){
        return null;
    }

    /**
     * 获取文章下的评论
     * @param cid
     * @param page
     * @param limit
     * @return CommentBo
     */
    Page4Navigator<CommentBo> getComments(Integer cid, int page, int limit){
        return  null;
    }

    /**
     * 获取文章下的评论
     * @param commentVoExample
     * @param page
     * @param limit
     * @return CommentVo
     */
    public Page4Navigator<Comment> getCommentsWithPage(String uid,int page, int limit){
        Sort sort = new Sort(Sort.Direction.DESC, "created");
        Pageable of = PageRequest.of(page, limit, sort);
        Page<Comment> byAuthorIdNot = commentDao.findByAuthorIdNot(uid, of);
        return  new Page4Navigator<>(byAuthorIdNot,5);
    }


    /**
     * 根据主键查询评论
     * @param coid
     * @return
     */
    Comment getCommentById(Integer coid){
        return  null;
    }


    /**
     * 删除评论，暂时没用
     * @param coid
     * @param cid
     * @throws Exception
     */
    void delete(Integer coid, Integer cid){
    }

    /**
     * 更新评论状态
     * @param comments
     */
    void update(Comment comments){

    }

}
