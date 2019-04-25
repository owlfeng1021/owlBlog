package com.owl.owlBlog.bo;

import com.owl.owlBlog.pojo.Content;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 13 on 2017/2/23.
 */
public class ArchiveBo implements Serializable {

    private String date;
    private String count;
    private List<Content> articles;

    public ArchiveBo() {
    }

    public ArchiveBo(String date, String count) {
        this.date = date;
        this.count = count;
    }

    public ArchiveBo(String date, String count, List<Content> articles) {
        this.date = date;
        this.count = count;
        this.articles = articles;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<Content> getArticles() {
        return articles;
    }

    public void setArticles(List<Content> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "ArchiveBo{" +
                "date='" + date + '\'' +
                ", count='" + count + '\'' +
                ", articles=" + articles +
                '}';
    }
}
