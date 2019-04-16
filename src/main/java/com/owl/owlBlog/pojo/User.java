package com.owl.owlBlog.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_users")
@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer", "handler"})
public class User {
    /**
     * user表主键
     */
    @Id
    private String uid;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户的邮箱
     */
    private String email;

    /**
     * 用户的主页
     */
    private String homeUrl;

    /**
     * 用户显示的名称
     */
    private String screenName;

    /**
     * 用户注册时的GMT unix时间戳
     */
    private Integer created;

    /**
     * 最后活动时间
     */
    private Integer activated;

    /**
     * 上次登录最后活跃时间
     */
    private Integer logged;

    /**
     * 用户组
     */
    private String groupName;

    private static final long serialVersionUID = 1L;

    public User() {
    }

    public User(String uid, String username, String password, String email, String homeUrl, String screenName, Integer created, Integer activated, Integer logged, String groupName) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.homeUrl = homeUrl;
        this.screenName = screenName;
        this.created = created;
        this.activated = activated;
        this.logged = logged;
        this.groupName = groupName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomeUrl() {
        return homeUrl;
    }

    public void setHomeUrl(String homeUrl) {
        this.homeUrl = homeUrl;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getActivated() {
        return activated;
    }

    public void setActivated(Integer activated) {
        this.activated = activated;
    }

    public Integer getLogged() {
        return logged;
    }

    public void setLogged(Integer logged) {
        this.logged = logged;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
