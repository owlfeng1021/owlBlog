package com.owl.owlBlog.bo;

import java.io.Serializable;

public class LinkBo implements Serializable {
    private String link;
    private String name;

    public LinkBo() {
    }

    public LinkBo(String link, String name) {
        this.link = link;
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
