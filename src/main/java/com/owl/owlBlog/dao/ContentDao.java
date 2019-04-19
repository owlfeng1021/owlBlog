package com.owl.owlBlog.dao;

import com.owl.owlBlog.pojo.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContentDao extends JpaRepository<Content,String> {
      public Page<Content> findByType(String type, Pageable pageable);

      public Page<Content> findByTypeOrStatus(String type,String status, Pageable pageable);
//    SELECT count(*) FROM t_contents WHERE slug="1"and type="post"
      public long countByTypeAndSlug(String type,String slug);

//    long countByExample(ContentVoExample example);
//
//    int deleteByExample(ContentVoExample example);
//
//    int deleteByPrimaryKey(Integer cid);
//
//    int insert(ContentVo record);
//
//    int insertSelective(ContentVo record);
//
//    List<ContentVo> selectByExampleWithBLOBs(ContentVoExample example);
//
//    List<ContentVo> selectByExample(ContentVoExample example);
//
//    ContentVo selectByPrimaryKey(Integer cid);
//
//    int updateByExampleSelective(@Param("record") ContentVo record, @Param("example") ContentVoExample example);
//
//    int updateByExampleWithBLOBs(@Param("record") ContentVo record, @Param("example") ContentVoExample example);
//
//    int updateByExample(@Param("record") ContentVo record, @Param("example") ContentVoExample example);
//
//    int updateByPrimaryKeySelective(ContentVo record);
//
//    int updateByPrimaryKeyWithBLOBs(ContentVo record);
//
//    int updateByPrimaryKey(ContentVo record);
//
//    List<ArchiveBo> findReturnArchiveBo();
//
//    List<ContentVo> findByCatalog(Integer mid);
}