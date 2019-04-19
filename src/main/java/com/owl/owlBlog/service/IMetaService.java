package com.owl.owlBlog.service;

import com.owl.owlBlog.dao.MetaDao;
import com.owl.owlBlog.dto.MetaDto;
import com.owl.owlBlog.exception.TipException;
import com.owl.owlBlog.pojo.Content;
import com.owl.owlBlog.pojo.Meta;
import com.owl.owlBlog.util.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.boot.Metadata;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 分类信息service接口
 * Created by BlueT on 2017/3/17.
 */
@Service
public class IMetaService {
    @Resource
    MetaDao metaDao;
    @Resource
    IContentService contentService;
    /**
     * 根据类型和名字查询项
     *
     * @param type
     * @param name
     * @return
     */
    MetaDto getMeta(String type, String name){
        return null;
    }

    /**
     * 根据文章id获取项目个数
     * @param mid
     * @return
     */
    Integer countMeta(Integer mid){
        return null;
    }
    /**
     * 根据类型查询项目列表
     * @param types
     * @return
     */
    public List<Meta> getMetasByType(String type) {
        List<Meta> byType = metaDao.findByType(type);

        for (Meta metaDto: byType ) {
            List<Content> contentList = metaDto.getContentList();
            if (contentList!=null){
                metaDto.setCount(contentList.size());
            }else {
                metaDto.setCount(0);
            }
        }
        return byType;

    }

    /**
     * 保存多个项目
     * @param cid
     * @param names
     * @param type
     */
    void saveMetas(String cid, String names, String type){
        if (null == cid) {
            throw new TipException("项目关联id不能为空");
        }
        Content content = contentService.getContents(cid);
        ArrayList<Content> contentlist = new ArrayList<>();
        contentlist.add(content);
        if (StringUtils.isNotBlank(names) && StringUtils.isNotBlank(type)) {
            String[] nameArr = StringUtils.split(names, ",");
            for (String name : nameArr) {
                this.saveOrUpdate(contentlist, name, type);
            }
        }

    }
    /**
     * 保存项目
     * @param type
     * @param name
     * @param mid
     */
    void saveMeta(String type, String name, Integer mid){

    }
    /**
     * 根据类型查询项目列表，带项目下面的文章数
     * @return
     */
    List<MetaDto> getMetaList(String type, String orderby, int limit){
        return null;
    }
    /**
     * 删除项目
     * @param mid
     */
    void delete(int mid){

    }
    /**
     * 保存项目
     * @param metas
     */
    void saveMeta(Meta metas){

    }

    /**
     * 更新项目
     * @param metas
     */
    public void update(Meta metas){

    }
    // 保存
    private void saveOrUpdate(List<Content> contentlist, String name, String type) {
        List<Meta> metaVos = metaDao.findByTypeAndName(type,name);
        Meta metas;
        if (metaVos.size() == 1) {
            metas = metaVos.get(0);
            metas.setContentList(contentlist);
            metaDao.save(metas);
        } else if (metaVos.size() > 1) {
            throw new TipException("查询到多条数据");
        } else {
            metas = new Meta();
            metas.setMid(new IdWorker().nextId()+"");
            metas.setSlug(name);
            metas.setName(name);
            metas.setType(type);
            metas.setContentList(contentlist);
            metaDao.save(metas);
        }
//        if (mid != 0) {
//            Long count = relationshipService.countById(cid, mid);
//            if (count == 0) {
//                RelationshipVoKey relationships = new RelationshipVoKey();
//                relationships.setCid(cid);
//                relationships.setMid(mid);
//                relationshipService.insertVo(relationships);
//            }
//        }
    }

}
