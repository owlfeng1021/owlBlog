package com.owl.owlBlog.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 */
@Entity
@Table(name = "t_attach")
@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer", "handler"})
public class Attach implements Serializable {
    @Id
    private String id;

    private String fname;

    private String ftype;

    private String fkey;


    private Integer authorId;

    private Integer created;

    public Attach() {
    }

    public Attach(String id, String fname, String ftype, String fkey, Integer authorId, Integer created) {
        this.id = id;
        this.fname = fname;
        this.ftype = ftype;
        this.fkey = fkey;
        this.authorId = authorId;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFtype() {
        return ftype;
    }

    public void setFtype(String ftype) {
        this.ftype = ftype;
    }

    public String getFkey() {
        return fkey;
    }

    public void setFkey(String fkey) {
        this.fkey = fkey;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }
}
