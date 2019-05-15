package com.owl.owlBlog.dao;

import com.owl.owlBlog.pojo.Comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface CommentDao extends JpaRepository<Comment,String> {

    Page<Comment> findByAuthorIdNot(String authorId, Pageable pageable);
    //
    Page<Comment> findByContents_CidAndParentAndStatusAndStatusNotNull(String cid, int  parent,String status,Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = " DELETE  from t_comments WHERE t_comments.cid = ? ", nativeQuery = true)
    void deleteByContent(String cid);


}