package com.owl.test;

import com.owl.owlBlog.Application;

import com.owl.owlBlog.bo.StatisticsBo;
import com.owl.owlBlog.dao.*;
import com.owl.owlBlog.dto.Types;
import com.owl.owlBlog.pojo.*;
import com.owl.owlBlog.service.ILogService;
import com.owl.owlBlog.service.IOptionService;
import com.owl.owlBlog.service.IUserService;
import com.owl.owlBlog.service.SiteService;
import com.owl.owlBlog.util.Commons;
import com.owl.owlBlog.util.IdWorker;

import com.owl.owlBlog.util.Page4Navigator;
import com.owl.owlBlog.util.TaleUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class logtest {

   @Autowired
    ILogService logService;
   @Resource
    IOptionService optionService;
   @Resource
    IUserService userService;
    @Resource
    UserDao userDao;
    @Resource
    CommentDao commentDao;
    @Resource
    ContentDao contentDao;
    @Resource
    SiteService siteService;
    @Resource
    MetaDao metaDao;
    @Resource
    Commons commons;
    @Resource
    LogDao logDao;

   @Test
    public void test() {

//       List<Comment> comments = siteService.recentComments(5);
//       List<Content> contents = siteService.recentContents(5);
//       List<Content> all1 = contentDao.findAll();
//       List<Meta> metaList = all1.get(0).getMetaList();
//
//       metaDao.findAll();
//
//       Meta meta = new Meta();
//       ArrayList<Content> objects = new ArrayList<>();
//       ArrayList<Meta> metas = new ArrayList<>();
//
//       Content content = new Content();
//       content.setCid("1");
//       metas.add(meta);
//       objects.add(content);
//       meta.setMid("10");
//       meta.setContentList(objects);
//       metaDao.save(meta);
//       content.setMetaList(metas);
//       content.setCid("3");
//       contentDao.save(content);
//       List<Meta> all = metaDao.findAll();
//
//       // 统计操作
//       StatisticsBo statistics = siteService.getStatistics();
//       long time = new Date().getTime();
//       int currentTime=(int)(time/1000L);
//       String fmtdate = Commons.fmtdate(currentTime, " yyyy-MM-dd HH:mm:ss");
//       String fmtdate2 = Commons.fmtdate(currentTime, " yyyy-MM-dd HH:mm:ss");
//       Sort sort = new Sort(Sort.Direction.DESC, "created");
//       Pageable pageable = PageRequest.of(0, 10, sort);
//       Page<Comment> byAuthorIdNot = commentDao.findByAuthorIdNot("1", pageable);
//       Page<Comment> all = commentDao.findAll(pageable);
//
//       Pageable pageable2 = PageRequest.of(1-1, 10,sort);
//       Page<Log> all2 = logDao.findAll(pageable);
//       List<Comment> content = byAuthorIdNot.getContent();
//       Sort sort = new Sort(Sort.Direction.DESC, "created");
//       Pageable pageable = PageRequest.of(1-1, 2,sort);
//       Page<Content> PageData = contentDao.findByType("post", pageable);
//       Sort sort = new Sort(Sort.Direction.DESC, "created");
//       Pageable pageable = PageRequest.of(0, 10, sort);
//       Page<Content> contentList = contentDao.findByTypeOrStatus(Types.ARTICLE.getType(), Types.PUBLISH.getType(), pageable);
//       new Page4Navigator<>(contentList, 5);
//       contentDao.countByTypeAndSlug("")
       Content content = new Content();

       for (int i=0;i<10;i++) {
           content.setCid(new IdWorker().nextId()+"");
           content.setType("post");
           content.setStatus(Types.PUBLISH.getType());
           contentDao.save(content);
       }
   }
}
