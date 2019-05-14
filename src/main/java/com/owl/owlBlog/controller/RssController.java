package com.owl.owlBlog.controller;

import com.owl.owlBlog.pojo.Meta;
import com.owl.owlBlog.service.IContentService;
import com.owl.owlBlog.util.Commons;
import com.rometools.rome.feed.atom.*;
import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Description;
import com.rometools.rome.feed.rss.Image;
import com.rometools.rome.feed.rss.Item;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndPerson;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.net.URL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
public class RssController {
    @Value("${default.user.ip}")
    private String ip;
    @Value("${server.port}")
    private String port;
    @Resource
    IContentService contentService;

    @GetMapping(path = "/rss")
    public Channel rss(HttpServletRequest request) throws Exception {
        List<com.owl.owlBlog.pojo.Content> rssArticles = contentService.getRssArticles();
//        String realPath = request.getServletPath();
        String rssPath = "http://" + ip + ":" + port;

        Channel channel = new Channel();
        channel.setFeedType("rss_2.0");
        channel.setTitle("owlfeng Feed");
        channel.setDescription("连枫的博客");
        channel.setLink("http://www.owlfeng.com");
        channel.setUri("http://www.owlfeng.com");
        channel.setGenerator("基本的编程");

        Image image = new Image();
        image.setUrl("/resources/assist/images/blog/b8fb228cdff44b8ea65d1e557bf05d2b.png");
        image.setTitle("owlfeng Feed");
        image.setHeight(32);
        image.setWidth(32);
        channel.setImage(image);

        Date postDate = new Date();
        channel.setPubDate(postDate);
        List<Item> itemList = new ArrayList<>();
        for (com.owl.owlBlog.pojo.Content content : rssArticles) {
            Item item = new Item();
            List<com.rometools.rome.feed.rss.Category> categoryList = new ArrayList<>();

            item.setAuthor("owlfeng");
            item.setLink(rssPath + "/article/" + content.getCid());
            item.setTitle(content.getTitle());
            item.setUri(rssPath + "/article/" + content.getCid());
            item.setComments("文章有进行" + content.getStatus());

            for (Meta meta : content.getMetaList()) {
                com.rometools.rome.feed.rss.Category category = new com.rometools.rome.feed.rss.Category();
                category.setValue(meta.getName());
                categoryList.add(category);
            }

            item.setCategories(categoryList);
            Description descr = new Description();
            descr.setValue(content.getContent());
            item.setDescription(descr);
            Commons.fmtdate(content.getCreated());
            item.setPubDate(new Date(content.getCreated() * 1000L));
            itemList.add(item);
        }
        channel.setItems(itemList);
        //Like more Entries here about different new topics
        return channel;
    }

    /**
     * 没有使用下面的部分 只是保留下面的接口
     *
     * @return
     */
//    @GetMapping(path = "/atom")
    public Feed atom() {
        Feed feed = new Feed();
        feed.setFeedType("atom_1.0");
        feed.setTitle("Leftso");
        feed.setId("http://www.leftso.com/");

        Content subtitle = new Content();
        subtitle.setType("text/plain");
        subtitle.setValue("最新技术的不同文章");
        feed.setSubtitle(subtitle);

        Date postDate = new Date();
        feed.setUpdated(postDate);

        Entry entry = new Entry();

        Link link = new Link();
        link.setHref("http://www.leftso.com/blog/64.html");
        entry.setAlternateLinks(Collections.singletonList(link));
        SyndPerson author = new Person();
        author.setName("Leftso");
        entry.setAuthors(Collections.singletonList(author));
        entry.setCreated(postDate);
        entry.setPublished(postDate);
        entry.setUpdated(postDate);
        entry.setId("http://www.leftso.com/blog/64.html");
        entry.setTitle("Spring boot 入门(一)环境搭建以及第一个应用");

        Category category = new Category();
        category.setTerm("CORS");
        entry.setCategories(Collections.singletonList(category));

        Content summary = new Content();
        summary.setType("text/plain");
        summary.setValue("两者没有必然的联系,但是spring boot可以看作为spring MVC的升级版."
                + " <a rel=\"nofollow\" href=\"http://www.leftso.com/blog/64.html/\">Spring boot 入门(一)环境搭建以及第一个应用</a>发布在 <a rel=\"nofollow\" href=\"http://www.leftso.com\">Leftso</a>.");
        entry.setSummary(summary);

        feed.setEntries(Collections.singletonList(entry));
        //参加这里关于不同的新话题
        return feed;
    }

    /**
     * 查看关于rss方面参数
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            String url = "http://localhost:8079/rss";

            try (XmlReader reader = new XmlReader(new URL(url))) {
                SyndFeed feed = new SyndFeedInput().build(reader);
                System.out.println(feed.getTitle());
                System.out.println("***********************************");
                for (SyndEntry entry : feed.getEntries()) {
                    System.out.println(entry);
                    System.out.println("***********************************");
                }
                System.out.println("Done");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
