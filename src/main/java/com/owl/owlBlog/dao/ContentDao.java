package com.owl.owlBlog.dao;


import com.owl.owlBlog.pojo.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ContentDao extends JpaRepository<Content, String> {
    List<Content> findByCategoriesOrderByCreatedDesc(String categories);
    Page<Content> findByType(String type, Pageable pageable);

    Page<Content> findByTitleLikeAndTypeAndStatus(String title, String type, String status, Pageable pageable);

    List<Content> findByTitleLike(String title);

    Page<Content> findByTypeAndStatus(String type, String status, Pageable pageable);
    List<Content> findByTypeAndStatusOrderByCreatedDesc(String type, String status);

    //      public List<Content> findByTypeOrStatus(String type, String status, Sort sort);
//    SELECT count(*) FROM t_contents WHERE slug="1"and type="post"
    long countByTypeAndSlug(String type, String slug);

    List<Content> findBySlug(String slug);


    //        %Y 年, 数字, 4 位
//        %m 月, 数字(01……12)
    @Query(value = " SELECT FROM_UNIXTIME(created,'%Y年%m月') AS data ,count(*) AS count FROM t_contents " +
            "WHERE type='post' and `status`='publish' GROUP BY data ORDER BY `data` DESC", nativeQuery = true)
    List<Map> findbyArchive();

    @Query(value = " SELECT FROM_UNIXTIME(created,'%Y年%m月') AS data ,count(*) AS count FROM t_contents " +
            "WHERE type='post' and `status`='publish' GROUP BY data ORDER BY `data` DESC", nativeQuery = true)
    List<Object> findbyArchiveBo();

    @Query(value = " SELECT FROM_UNIXTIME(created,'%Y年%m月') AS data ,count(*) AS count FROM t_contents " +
            "WHERE type='post' and `status`='publish' GROUP BY data ORDER BY `data` DESC", nativeQuery = true)
    List<List<Object>> findbyArchiveBoList();

    @Query(value = "SELECT * FROM t_contents WHERE type =?  AND `status`=?  and created > ? and created < ? ", nativeQuery = true)
    List<Content> findByCreatedStartAndEnd(String type, String startus, Integer start, Integer end);
}