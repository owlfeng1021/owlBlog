package com.owl.owlBlog.service;

import com.owl.owlBlog.dao.MetaDao;
import com.owl.owlBlog.dto.MetaDto;
import com.owl.owlBlog.pojo.Meta;
import org.hibernate.boot.Metadata;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 分类信息service接口
 * Created by BlueT on 2017/3/17.
 */
@Service
public class IMetaService {
    @Resource
    MetaDao metaDao;
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
    List<Meta> getMetas(String types){
        return null;
    }

    /**
     * 保存多个项目
     * @param cid
     * @param names
     * @param type
     */
    void saveMetas(Integer cid, String names, String type){

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

    public List<Meta> getMetasByType(String type) {
        return metaDao.findByType(type);

    }
}
