package com.owl.owlBlog.dao;

import com.owl.owlBlog.pojo.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OptionDao extends JpaRepository<Option,String> {
      Option findByName(String name);
      int countByName(String name);
      //
      @Modifying
      @Transactional
      @Query(value = " UPDATE t_options t  SET t.`value`=?2 WHERE name =?1", nativeQuery = true)
      void saveOption(String name,String value);

}