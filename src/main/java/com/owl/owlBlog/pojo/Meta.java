package com.owl.owlBlog.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_meats")
public class Meta {
    /**
     * 项目主键
     */
    @Id
    private String mid;
//metaList
    @ManyToMany(cascade ={CascadeType.MERGE,CascadeType.DETACH},fetch = FetchType.EAGER)// CascadeType.DETACH
    @JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer", "handler,","metaList"})
    @JoinTable(name = "t_contents_metas",
            joinColumns = @JoinColumn(name = "mid"),
            inverseJoinColumns = @JoinColumn(name = "cid"))
    private List<Content> contentList;

//    @ManyToMany(targetEntity=Teacher.class)
//    // 使用JoinTabl来描述中间表，并描述中间表中外键与Student,Teacher的映射关系
//    // joinColumns它是用来描述Student与中间表中的映射关系
//    // inverseJoinColums它是用来描述Teacher与中间表中的映射关系
//    @JoinTable(name="s_t",
//            joinColumns={@JoinColumn(name="c_s_id",referencedColumnName="id")},
//            inverseJoinColumns={@JoinColumn(name="c_t_id",referencedColumnName="id")}

    /**
     * 名称
     */
    private String name;

    /**
     * 项目缩略名
     */
    private String slug;

    /**
     * 项目类型
     */
    private String type;

    /**
     * 选项描述
     */
    private String description;

    /**
     * 项目排序
     */
    private Integer sort;

    private Integer parent;

    private static final long serialVersionUID = 1L;

    @Transient
    private int count;

    public Meta() {
    }

    public Meta(String mid, String name, String slug, String type, String description, Integer sort, Integer parent) {
        this.mid = mid;
        this.name = name;
        this.slug = slug;
        this.type = type;
        this.description = description;
        this.sort = sort;
        this.parent = parent;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Content> getContentList() {
        return contentList;
    }

    public void setContentList(List<Content> contentList) {
        this.contentList = contentList;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
