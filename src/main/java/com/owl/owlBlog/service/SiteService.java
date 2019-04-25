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
import com.owl.owlBlog.util.DateKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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
    public List<Comment> recentComments(int limit) {
        Sort sort = new Sort(Sort.Direction.DESC, "coid");
        Pageable pageable = PageRequest.of(0, limit, sort);
        Page<Comment> all = commentDao.findAll(pageable);
        return all.getContent();
    }

    /**
     * 最新发表的文章
     *
     * @param limit
     * @return
     */
    public List<Content> recentContents(int limit) {
        Sort sort = new Sort(Sort.Direction.DESC, "cid");
        Pageable pageable = PageRequest.of(0, limit, sort);
        Page<Content> all = contentDao.findAll(pageable);
        return all.getContent();
    }

    /**
     * 查询一条评论
     *
     * @param coid
     * @return
     */
    public Comment getComment(Integer coid) {

        return null;
    }

    /**
     * 系统备份
     *
     * @param bk_type
     * @param bk_path
     * @param fmt
     * @return
     */
    public BackResponseBo backup(String bk_type, String bk_path, String fmt) throws Exception {
        return null;
    }


    /**
     * 获取后台统计数据
     *
     * @return
     */
    public StatisticsBo getStatistics() {
        LOGGER.debug("Enter getStatistics method");
        StatisticsBo statistics = new StatisticsBo();
        // 要4个数据的count
        Long articles = contentDao.count();
        Long comments = commentDao.count();
        Long attachs = attachDao.count();
        Long links = metaDao.countByType(Types.LINK.getType());
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
    public List<ArchiveBo> getArchives() {
        LOGGER.debug("Enter getArchive Method");
        List<ArchiveBo> archiveBos = new ArrayList<>();
        // springdata jpa返回的属性有当前类 还有map 和object
        List<Object> objects = contentDao.findbyArchiveBo();
        if (objects!=null) {
            for (Object o : objects) {
                Object[] objs = (Object[]) o;
                String date = (String) objs[0];
                String count =  objs[1].toString();
                ArchiveBo archive = new ArchiveBo(date,count);
                archiveBos.add(archive);
            }
            archiveBos.forEach(temp->{
                String date = temp.getDate();
                // 这里的dataFormat 把从数据库里面取的数据又变成了完整的时间戳
                Date fdate = DateKit.dateFormat(date, "yyyy年MM月");
                // 所以下面的操作就是把 完整的时间戳变成只有年和月份的时间戳
                int start = DateKit.getUnixTimeByDate(fdate);
                int end =DateKit.getUnixTimeByDate(DateKit.dateAdd(DateKit.INTERVAL_MONTH,fdate,1))-1;

                List<Content> CArticles = contentDao.findByCreatedStartAndEnd(Types.ARTICLE.getType(), Types.PUBLISH.getType(), start, end);
                temp.setArticles(CArticles);
            });
        }

        LOGGER.debug("Outer getArchive Method");

        return archiveBos;
    }

    /**
     * 获取分类/标签列表
     *
     * @return
     */
    public List<MetaDto> metas(String type, String orderBy, int limit) {

        return null;
    }
}

