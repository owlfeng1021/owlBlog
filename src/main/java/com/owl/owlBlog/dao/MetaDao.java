package com.owl.owlBlog.dao;

import com.owl.owlBlog.pojo.Meta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MetaDao extends JpaRepository<Meta,String> {
      long countByType(String type);
      List<Meta> findByType(String type);
//    long countByExample(MetaVoExample example);
//
//    int deleteByExample(MetaVoExample example);
//
//    int deleteByPrimaryKey(Integer mid);
//
//    int insert(MetaVo record);
//
//    int insertSelective(MetaVo record);
//
//    List<MetaVo> selectByExample(MetaVoExample example);
//
//    MetaVo selectByPrimaryKey(Integer mid);
//
//    int updateByExampleSelective(@Param("record") MetaVo record, @Param("example") MetaVoExample example);
//
//    int updateByExample(@Param("record") MetaVo record, @Param("example") MetaVoExample example);
//
//    int updateByPrimaryKeySelective(MetaVo record);
//
//    int updateByPrimaryKey(MetaVo record);
//
//    List<MetaDto> selectFromSql(Map<String, Object> paraMap);
//
//    MetaDto selectDtoByNameAndType(@Param("name") String name, @Param("type") String type);
//
//    Integer countWithSql(Integer mid);
}