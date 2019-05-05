package com.owl.owlBlog.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_contents")
public class Content implements Serializable {
    /**
     * post表主键
     */
    @Id
    private String cid;

    @JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer", "handler,","contentList"})
    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.DETACH},fetch = FetchType.EAGER)
    @JoinTable(name = "t_contents_metas",
            joinColumns = @JoinColumn(name = "cid"),
            inverseJoinColumns = @JoinColumn(name = "mid"))
    private List<Meta> metaList;
    /**
     * 内容标题
     */
    private String title;

    /**
     * 内容缩略名
     */
    private String slug;

    /**
     * 内容生成时的GMT unix时间戳
     */
    private Integer created;

    /**
     * 内容更改时的GMT unix时间戳
     */
    private Integer modified;

    /**
     * 内容所属用户id
     */
    private String authorId;

    /**
     * 内容类别
     */
    private String type;

    /**
     * 内容状态
     */
    private String status;

    /**
     * 标签列表
     */
    private String tags;

    /**
     * 分类列表
     */
    private String categories;

    /**
     * 点击次数
     */
    private Integer hits;

    /**
     * 内容所属评论数
     */
    private Integer commentsNum;

    /**
     * 是否允许评论
     */
    private Boolean allowComment;

    /**
     * 是否允许ping
     */
    private Boolean allowPing;

    /**
     * 允许出现在聚合中
     */
    private Boolean allowFeed;

    /**
     * 内容文字
     */
    private String content;

    @Transient
    private String date;
    @Transient
    private String count;
    @Transient
    private List<Content> articles;

    private static final long serialVersionUID = 1L;


    public Content() {
    }

    public Content(String cid, List<Meta> metaList, String title, String slug, Integer created, Integer modified, String authorId, String type, String status, String tags, String categories, Integer hits, Integer commentsNum, Boolean allowComment, Boolean allowPing, Boolean allowFeed, String content) {
        this.cid = cid;
        this.metaList = metaList;
        this.title = title;
        this.slug = slug;
        this.created = created;
        this.modified = modified;
        this.authorId = authorId;
        this.type = type;
        this.status = status;
        this.tags = tags;
        this.categories = categories;
        this.hits = hits;
        this.commentsNum = commentsNum;
        this.allowComment = allowComment;
        this.allowPing = allowPing;
        this.allowFeed = allowFeed;
        this.content = content;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public List<Meta> getMetaList() {
        return metaList;
    }

    public void setMetaList(List<Meta> metaList) {
        this.metaList = metaList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }


    public Integer getModified() {
        return modified;
    }

    public void setModified(Integer modified) {
        this.modified = modified;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(Integer commentsNum) {
        this.commentsNum = commentsNum;
    }

    public Boolean getAllowComment() {
        return allowComment;
    }

    public void setAllowComment(Boolean allowComment) {
        this.allowComment = allowComment;
    }

    public Boolean getAllowPing() {
        return allowPing;
    }

    public void setAllowPing(Boolean allowPing) {
        this.allowPing = allowPing;
    }

    public Boolean getAllowFeed() {
        return allowFeed;
    }

    public void setAllowFeed(Boolean allowFeed) {
        this.allowFeed = allowFeed;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
