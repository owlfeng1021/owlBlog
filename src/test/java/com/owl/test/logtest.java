package com.owl.test;

import com.owl.owlBlog.Application;

import com.owl.owlBlog.dao.UserDao;
import com.owl.owlBlog.pojo.Log;
import com.owl.owlBlog.pojo.Option;
import com.owl.owlBlog.pojo.User;
import com.owl.owlBlog.service.ILogService;
import com.owl.owlBlog.service.IOptionService;
import com.owl.owlBlog.service.IUserService;
import com.owl.owlBlog.util.IdWorker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
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
   @Test
    public void test() {

       User byID = userService.findByID("1");
       User one = userDao.getOne("1");
       List<User> all = userDao.findAll();
       
   }
}
