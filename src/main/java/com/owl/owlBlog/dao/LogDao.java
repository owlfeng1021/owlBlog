package com.owl.owlBlog.dao;

import com.owl.owlBlog.pojo.Log;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LogDao extends JpaRepository<Log,String> {


//    long countByExample(LogVoExample example);
//
//    int deleteByExample(LogVoExample example);
//
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(LogVo record);
//
//    int insertSelective(LogVo record);
//
//    List<LogVo> selectByExample(LogVoExample example);
//
//    LogVo selectByPrimaryKey(Integer id);
//
//    int updateByExampleSelective(@Param("record") LogVo record, @Param("example") LogVoExample example);
//
//    int updateByExample(@Param("record") LogVo record, @Param("example") LogVoExample example);
//
//    int updateByPrimaryKeySelective(LogVo record);
//
//    int updateByPrimaryKey(LogVo record);
}