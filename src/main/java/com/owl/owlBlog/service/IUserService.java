package com.owl.owlBlog.service;

import com.owl.owlBlog.dao.UserDao;
import com.owl.owlBlog.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IUserService {
    @Resource
    UserDao userDao;
    public User findByID(String id){
        return  userDao.findByUid(id);
    }
}
