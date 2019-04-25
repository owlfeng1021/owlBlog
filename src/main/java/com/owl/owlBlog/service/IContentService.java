package com.owl.owlBlog.service;

import com.owl.owlBlog.constant.WebConst;
import com.owl.owlBlog.dao.ContentDao;
import com.owl.owlBlog.dto.Types;
import com.owl.owlBlog.exception.TipException;
import com.owl.owlBlog.pojo.Content;
import com.owl.owlBlog.util.*;
import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/3/13 013.
 */
@Service
@CacheConfig(cacheNames = "contents")
public class IContentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IContentService.class);
    //    /**
//     * 保存文章
//     * @param contentVo contentVo
//     */
//    void insertContent(ContentVo contentVo);
    @Resource
    ContentDao contentDao;
    @Resource
    IMetaService metasService;
    @Resource
    IdWorker idWorker;

    /**
     * 发布文章
     *
     * @param contents
     */
    @CacheEvict(allEntries = true)
    public String publish(Content contents) {
        if (null == contents) {
            return "文章对象为空";
        }
        if (StringUtils.isBlank(contents.getTitle())) {
            return "文章标题不能为空";
        }
        if (StringUtils.isBlank(contents.getContent())) {
            return "文章内容不能为空";
        }
        int titleLength = contents.getTitle().length();
        if (titleLength > WebConst.MAX_TITLE_COUNT) {
            return "文章标题过长";
        }
        int contentLength = contents.getContent().length();
        if (contentLength > WebConst.MAX_TEXT_COUNT) {
            return "文章内容过长";
        }
        if (null == contents.getAuthorId()) {
            return "请登录后发布文章";
        }
        if (StringUtils.isNotBlank(contents.getSlug())) {
            if (contents.getSlug().length() < 5) {
                return "路径太短了";
            }
            if (!TaleUtils.isPath(contents.getSlug())) {
                return "您输入的路径不合法";
            }
            long count = contentDao.countByTypeAndSlug(contents.getType(), contents.getSlug());
            if (count > 0) return "该路径已经存在，请重新输入";
        } else {
            contents.setSlug(null);
        }
        contents.setContent(EmojiParser.parseToAliases(contents.getContent()));

        int time = DateKit.getCurrentUnixTime();
        contents.setCreated(time);
        contents.setModified(time);
        contents.setHits(0);
        contents.setCommentsNum(0);
        contents.setCid(idWorker.nextId()+"");

        String tags = contents.getTags();
        String categories = contents.getCategories();
        contentDao.save(contents);

        String cid = contents.getCid();
        metasService.saveMetas(cid, tags, Types.TAG.getType());
        metasService.saveMetas(cid, categories, Types.CATEGORY.getType());
        return WebConst.SUCCESS_RESULT;

    }

    /**
     * 查询文章返回多条数据
     *
     * @param p     当前页
     * @param limit 每页条数
     * @return Content
     */
    @Cacheable(key = "#p0+'-'+#p1")
    public Page4Navigator<Content> getContents(Integer p, Integer limit) {
        LOGGER.debug("Enter getContents method");
        Sort sort = new Sort(Sort.Direction.DESC, "created");
        Pageable pageable = PageRequest.of(p - 1, limit, sort);
        Page<Content> contentList = contentDao.findByTypeAndStatus(Types.ARTICLE.getType(), Types.PUBLISH.getType(), pageable);
        LOGGER.debug("Exit getContents method");
        return new Page4Navigator<>(contentList, 11);
    }


    /**
     * 根据id或slug获取文章
     *
     * @param id id
     * @return ContentVo
     */
    @Cacheable(key="#p0")
    public Content getContents(String id) {
        if (StringUtils.isNotBlank(id)){
            if (Tools.isNumber(id)) {
                Content content = contentDao.findById(id).get();
                return  content;
            }
            else{
                List<Content> content = contentDao.findBySlug(id);
                if (content.size()!=1){
                    throw new TipException("查询结果不为一");
                }
                return content.get(0);
            }
        }

        return null;
    }

    /**
     * 根据主键更新
     *
     * @param contentVo contentVo
     */
    @CacheEvict(key="#p0.cid")
    public void updateContentByCid(Content content) {
        contentDao.save(content);
    }


    /**
     * 查询分类/标签下的文章归档
     *
     * @param mid   mid
     * @param page  page
     * @param limit limit
     * @return ContentVo
     */
    Page4Navigator<Content> getArticles(Integer mid, int page, int limit) {
        return null;
    }

    /**
     * 搜索、分页
     *
     * @param keyword keyword
     * @param page    page
     * @param limit   limit
     * @return ContentVo
     */

    public Page4Navigator<Content> getArticles(String keyword, Integer page, Integer limit) {
        Sort sort = new Sort(Sort.Direction.DESC, "created");
        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        Page<Content> PageData = contentDao.findByTitleLikeAndTypeAndStatus("%"+keyword+"%", Types.ARTICLE.getType(),Types.PUBLISH.getType(),pageable);
        return new Page4Navigator<Content>(PageData, limit);
    }


    /**
     * @param commentVoExample
     * @param page
     * @param limit
     * @return
     */
    @Cacheable(value = "adminpage",key = "#p0+#p1+#p3")
    public Page4Navigator<Content> getArticlesWithpage(String type, int page, int limit) {
        Sort sort = new Sort(Sort.Direction.DESC, "created");
        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        Page<Content> PageData = contentDao.findByType(type, pageable);
        return new Page4Navigator<Content>(PageData, limit);

    }

    /**
     * 根据文章id删除
     *
     * @param cid
     */

    @CacheEvict(allEntries = true)
    public  String deleteByCid(String cid) {
        contentDao.deleteById(cid);
        return WebConst.SUCCESS_RESULT;
    }

    /**
     * 编辑文章
     *
     * @param contents
     */
    @CacheEvict(allEntries = true)
    public String updateArticle(Content contents) {
        contentDao.save(contents);

        return WebConst.SUCCESS_RESULT;
    }


    /**
     * 更新原有文章的category
     *
     * @param ordinal
     * @param newCatefory
     */
    void updateCategory(String ordinal, String newCatefory) {

    }


}
