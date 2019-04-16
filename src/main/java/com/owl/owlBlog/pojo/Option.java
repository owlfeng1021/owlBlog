package com.owl.owlBlog.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_options")
@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer", "handler"})
/**
 *  这个表是添加 外来链接和相关配置的
 */
public class Option {
    /**
     * 配置名称
     */
    @Id
    private String name;

    /**
     * 配置值
     */
    private String value;

    private String description;

    private static final long serialVersionUID = 1L;

    public Option() {
    }

    public Option(String name, String value, String description) {
        this.name = name;
        this.value = value;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
