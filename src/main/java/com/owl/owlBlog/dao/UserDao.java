package com.owl.owlBlog.dao;

import com.owl.owlBlog.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User,String> {
    public User findByUid(String uid);
    public List<User> findByUsernameAndPassword(String username, String  password) ;
    public long countByUsername(String username);

}