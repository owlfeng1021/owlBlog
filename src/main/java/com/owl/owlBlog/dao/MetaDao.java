package com.owl.owlBlog.dao;

import com.owl.owlBlog.pojo.Meta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MetaDao extends JpaRepository<Meta,String> {
      long countByType(String type);
      List<Meta> findByType(String type);
      List<Meta> findByTypeAndName(String type,String name);

}