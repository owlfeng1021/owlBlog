package com.owl.owlBlog.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "t_comments")
@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer", "handler"})
public class Comment implements Serializable {
    /**
     * comment表主键
     */
    @Id
    private String coid;

    /**
     * post表主键,关联字段
     */
    @ManyToOne
    @JoinColumn(name = "cid")
    private Content contents;

    /**
     * 评论生成时的GMT unix时间戳
     */
    private Integer created;

    /**
     * 评论作者
     */
    private String author;

    /**
     * 评论所属用户id
     */
    private Integer authorId;

    /**
     * 评论所属内容作者id
     */
    private Integer ownerId;

    /**
     * 评论者邮件
     */
    private String mail;

    /**
     * 评论者网址
     */
    private String url;

    /**
     * 评论者ip地址
     */
    private String ip;

    /**
     * 评论者客户端
     */
    private String agent;

    /**
     * 评论类型
     */
    private String type;

    /**
     * 评论状态
     */
    private String status;

    /**
     * 父级评论
     */
    private Integer parent;

    /**
     * 评论内容
     */
    private String content;

    public Comment() {
    }

    public Comment(String coid, Content contents, Integer created, String author, Integer authorId, Integer ownerId, String mail, String url, String ip, String agent, String type, String status, Integer parent, String content) {
        this.coid = coid;
        this.contents = contents;
        this.created = created;
        this.author = author;
        this.authorId = authorId;
        this.ownerId = ownerId;
        this.mail = mail;
        this.url = url;
        this.ip = ip;
        this.agent = agent;
        this.type = type;
        this.status = status;
        this.parent = parent;
        this.content = content;
    }

    public String getCoid() {
        return coid;
    }

    public void setCoid(String coid) {
        this.coid = coid;
    }

    public Content getContents() {
        return contents;
    }

    public void setContents(Content contents) {
        this.contents = contents;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
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

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
