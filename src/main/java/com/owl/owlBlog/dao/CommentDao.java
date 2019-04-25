package com.owl.owlBlog.dao;



import com.owl.owlBlog.pojo.Comment;
import com.owl.owlBlog.pojo.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CommentDao extends JpaRepository<Comment,String> {

    Page<Comment> findByAuthorIdNot(String authorId, Pageable pageable);
    //
    Page<Comment> findByContents_CidAndParentAndStatusAndStatusNotNull(String cid, int  parent,String status,Pageable pageable);



}