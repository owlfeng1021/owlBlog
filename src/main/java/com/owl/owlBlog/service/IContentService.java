package com.owl.owlBlog.service;

import com.owl.owlBlog.dao.ContentDao;
import com.owl.owlBlog.pojo.Content;
import com.owl.owlBlog.util.Page4Navigator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/3/13 013.
 */
@Service
public class IContentService {

//    /**
//     * 保存文章
//     * @param contentVo contentVo
//     */
//    void insertContent(ContentVo contentVo);
@Resource
    ContentDao contentDao;
    /**
     * 发布文章
     * @param contents
     */
    public String publish(Content contents){
        return  null;

    }

    /**
     *查询文章返回多条数据
     * @param p 当前页
     * @param limit 每页条数
     * @return ContentVo
     */
    Page4Navigator<Content> getContents(Integer p, Integer limit){
        return  null;
    }


    /**
     * 根据id或slug获取文章
     *
     * @param id id
     * @return ContentVo
     */
    Content getContents(String id){
        return  null;


    }

    /**
     * 根据主键更新
     * @param contentVo contentVo
     */
    void updateContentByCid(Content contentVo){

    }


    /**
     * 查询分类/标签下的文章归档
     * @param mid mid
     * @param page page
     * @param limit limit
     * @return ContentVo
     */
    Page4Navigator<Content> getArticles(Integer mid, int page, int limit){
        return  null;
    }

    /**
     * 搜索、分页
     * @param keyword keyword
     * @param page page
     * @param limit limit
     * @return ContentVo
     */
    Page4Navigator<Content> getArticles(String keyword, Integer page, Integer limit){
        return  null;
    }


    /**
     * @param commentVoExample
     * @param page
     * @param limit
     * @return
     */

    public Page4Navigator<Content> getArticlesWithpage(String type, int page, int limit) {
        Sort sort = new Sort(Sort.Direction.DESC, "created");
        Pageable pageable = PageRequest.of(page-1, limit,sort);
        Page<Content> PageData = contentDao.findByType(type, pageable);
        return  new Page4Navigator<Content>(PageData,limit);

    }
    /**
     * 根据文章id删除
     * @param cid
     */
    String deleteByCid(Integer cid){
        return  null;
    }

    /**
     * 编辑文章
     * @param contents
     */
    String updateArticle(Content contents){
        return  null;
    }


    /**
     * 更新原有文章的category
     * @param ordinal
     * @param newCatefory
     */
    void updateCategory(String ordinal, String newCatefory){

    }


}
