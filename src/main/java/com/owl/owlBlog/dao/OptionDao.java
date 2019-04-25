package com.owl.owlBlog.dao;

import com.owl.owlBlog.pojo.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionDao extends JpaRepository<Option,String> {
      Option findByName(String name);
      int countByName(String name);

}