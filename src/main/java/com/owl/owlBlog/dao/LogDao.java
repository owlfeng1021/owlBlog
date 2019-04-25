package com.owl.owlBlog.dao;

import com.owl.owlBlog.pojo.Log;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LogDao extends JpaRepository<Log,String> {

}