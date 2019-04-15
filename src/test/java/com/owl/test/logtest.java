package com.owl.test;

import com.owl.owlBlog.Application;

import com.owl.owlBlog.pojo.Log;
import com.owl.owlBlog.service.ILogService;
import com.owl.owlBlog.util.IdWorker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class logtest {

   @Autowired
    ILogService logService;

   @Test
    public void test() {
        Log log = new Log();
        log.setId(new IdWorker().nextId()+"");
        log.setData("sdfasdf");
        logService.insertLog(log);
    }
}
