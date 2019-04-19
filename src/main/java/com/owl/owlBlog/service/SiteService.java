package com.owl.owlBlog.service;

import com.owl.owlBlog.bo.ArchiveBo;
import com.owl.owlBlog.bo.BackResponseBo;
import com.owl.owlBlog.bo.StatisticsBo;
import com.owl.owlBlog.dao.AttachDao;
import com.owl.owlBlog.dao.CommentDao;
import com.owl.owlBlog.dao.ContentDao;
import com.owl.owlBlog.dao.MetaDao;
import com.owl.owlBlog.dto.MetaDto;
import com.owl.owlBlog.dto.Types;
import com.owl.owlBlog.pojo.Comment;
import com.owl.owlBlog.pojo.Content;
import com.owl.owlBlog.pojo.Log;
import com.owl.owlBlog.pojo.Meta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SiteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SiteService.class);

    @Resource
    CommentDao commentDao;
    @Resource
    ContentDao contentDao;
    @Resource
    AttachDao attachDao;
    @Resource
    MetaDao metaDao;


    /**
     * 最新收到的评论
     *
     * @param limit
     * @return
     */
    public List<Comment> recentComments(int limit){
        Sort sort = new Sort(Sort.Direction.DESC, "coid");
        Pageable pageable = PageRequest.of(0, limit,sort);
        Page<Comment> all = commentDao.findAll(pageable);
        return all.getContent();
    }

    /**
     * 最新发表的文章
     *
     * @param limit
     * @return
     */
    public List<Content> recentContents(int limit){
        Sort sort = new Sort(Sort.Direction.DESC, "cid");
        Pageable pageable = PageRequest.of(0, limit,sort);
        Page<Content> all = contentDao.findAll(pageable);
        return all.getContent();
    }

    /**
     * 查询一条评论
     * @param coid
     * @return
     */
    public   Comment getComment(Integer coid){
        return null;
    }

    /**
     * 系统备份
     * @param bk_type
     * @param bk_path
     * @param fmt
     * @return
     */
    public  BackResponseBo backup(String bk_type, String bk_path, String fmt) throws Exception{
        return null;
    }


    /**
     * 获取后台统计数据
     *
     * @return
     */
    public  StatisticsBo getStatistics(){
        LOGGER.debug("Enter getStatistics method");
        StatisticsBo statistics = new StatisticsBo();
        // 要4个数据的count
        Long articles =contentDao.count();
        Long comments =commentDao.count();
        Long attachs =attachDao.count();
        Long links =metaDao.countByType(Types.LINK.getType());
        statistics.setArticles(articles);
        statistics.setComments(comments);
        statistics.setAttachs(attachs);
        statistics.setLinks(links);
        LOGGER.debug("Exit getStatistics method");
        return statistics;
    }

    /**
     * 查询文章归档
     *
     * @return
     */
//    private String date;
//    private String count;
//    private List<Content> articles;
    public List<Content> getArchives(){

        return null;
    }

    /**
     * 获取分类/标签列表
     * @return
     */
    public  List<MetaDto> metas(String type, String orderBy, int limit){
        return null;
    }
}

