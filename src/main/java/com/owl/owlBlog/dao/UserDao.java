package com.owl.owlBlog.dao;

import com.owl.owlBlog.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User,String> {
    public User findByUid(String uid);
    public List<User> findByUsernameAndPassword(String username, String  password) ;
    public long countByUsername(String username);
//    long countByExample(UserVoExample example);
//
//    int deleteByExample(UserVoExample example);
//
//    int deleteByPrimaryKey(Integer uid);
//
//    int insert(UserVo record);
//
//    int insertSelective(UserVo record);
//
//    List<UserVo> selectByExample(UserVoExample example);
//
//    UserVo selectByPrimaryKey(Integer uid);
//
//    int updateByExampleSelective(@Param("record") UserVo record, @Param("example") UserVoExample example);
//
//    int updateByExample(@Param("record") UserVo record, @Param("example") UserVoExample example);
//
//    int updateByPrimaryKeySelective(UserVo record);
//
//    int updateByPrimaryKey(UserVo record);
}