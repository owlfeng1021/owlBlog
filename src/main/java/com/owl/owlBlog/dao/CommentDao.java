package com.owl.owlBlog.dao;



import com.owl.owlBlog.pojo.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentDao extends JpaRepository<Comment,String> {
    //
    Page<Comment> findByAuthorIdNot(String authorId, Pageable pageable);
//    long countByExample(CommentVoExample example);
//
//    int deleteByExample(CommentVoExample example);
//
//    int deleteByPrimaryKey(Integer coid);
//
//    int insert(CommentVo record);
//
//    int insertSelective(CommentVo record);
//
//    List<CommentVo> selectByExampleWithBLOBs(CommentVoExample example);
//
//    List<CommentVo> selectByExample(CommentVoExample example);
//
//    CommentVo selectByPrimaryKey(Integer coid);
//
//    int updateByExampleSelective(@Param("record") CommentVo record, @Param("example") CommentVoExample example);
//
//    int updateByExampleWithBLOBs(@Param("record") CommentVo record, @Param("example") CommentVoExample example);
//
//    int updateByExample(@Param("record") CommentVo record, @Param("example") CommentVoExample example);
//
//    int updateByPrimaryKeySelective(CommentVo record);
//
//    int updateByPrimaryKeyWithBLOBs(CommentVo record);
//
//    int updateByPrimaryKey(CommentVo record);
}