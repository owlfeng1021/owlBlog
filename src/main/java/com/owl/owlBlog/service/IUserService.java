package com.owl.owlBlog.service;

import com.owl.owlBlog.dao.UserDao;
import com.owl.owlBlog.exception.TipException;
import com.owl.owlBlog.pojo.User;
import com.owl.owlBlog.util.IdWorker;
import com.owl.owlBlog.util.TaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IUserService {
    @Resource
    UserDao userDao;
    public User findByID(String id){
        return  userDao.findByUid(id);
    }
    public User login(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new TipException("用户名和密码不能为空");
        }
        long count = userDao.countByUsername(username);
        if (count < 1) {
            throw new TipException("不存在该用户");
        }
        String pwd = TaleUtils.MD5encode(username + password);
        List<User> User = userDao.findByUsernameAndPassword(username, pwd);
        if (User.size() != 1) {
            throw new TipException("用户名或密码错误");
        }
        return  User.get(0);
    }
    public String insertUser(User user) {
        String uid = null;
        if (StringUtils.isNotBlank(user.getUsername()) && StringUtils.isNotBlank(user.getEmail())) {
            String encodePwd = TaleUtils.MD5encode(user.getUsername() + user.getPassword());
            user.setUid(new IdWorker().nextId()+"");
            user.setPassword(encodePwd);
            userDao.save(user);
        }
        return user.getUid();
    }
}
