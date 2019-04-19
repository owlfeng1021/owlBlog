package com.owl.owlBlog.Controller;

import com.alibaba.druid.wall.violation.ErrorCode;
import com.owl.owlBlog.bo.ArchiveBo;
import com.owl.owlBlog.bo.CommentBo;
import com.owl.owlBlog.bo.RestResponseBo;
import com.owl.owlBlog.constant.WebConst;
import com.owl.owlBlog.dto.Types;
import com.owl.owlBlog.pojo.Comment;
import com.owl.owlBlog.pojo.Content;
import com.owl.owlBlog.pojo.Meta;
import com.owl.owlBlog.service.ICommentService;
import com.owl.owlBlog.service.IContentService;
import com.owl.owlBlog.service.IMetaService;
import com.owl.owlBlog.service.SiteService;
import com.owl.owlBlog.util.IPKit;
import com.owl.owlBlog.util.Page4Navigator;
import com.owl.owlBlog.util.TaleUtils;
import com.sun.jarsigner.ContentSigner;
import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.print.Pageable;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class IndexController extends BaseController{
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
    @Resource
    IContentService contentService;
    @Resource
    ICommentService commentService;
    @Resource
    IMetaService metaService;
    @Resource
    SiteService siteService;


    /**
     * 首页
     *
     * @return
     */
    @GetMapping(value = "/")
    public String index(HttpServletRequest request, @RequestParam(value = "limit", defaultValue = "12") int limit) {
        return this.index(request, 1, limit);
    }
    /**
     * 首页分页
     *
     * @param request request
     * @param p       第几页
     * @param limit   每页大小
     * @return 主页
     */
    @GetMapping(value = "page/{p}")
    public String index(HttpServletRequest request, @PathVariable int p, @RequestParam(value = "limit", defaultValue = "12") int limit) {
        p = p < 0 || p > WebConst.MAX_PAGE ? 1 : p;
        Page4Navigator<Content> articles = contentService.getContents(p, limit);
        request.setAttribute("articles", articles);
        if (p > 1) {
            this.title(request, "第" + p + "页");
        }
        return this.render("index");
    }
    /**
     * 文章页
     *
     * @param request 请求
     * @param cid     文章主键
     * @return
     */
    @GetMapping(value = {"article/{cid}", "article/{cid}.html"})
    public String getArticle(HttpServletRequest request, @PathVariable String cid) {
        Content contents = contentService.getContents(cid);
        if (null == contents || "draft".equals(contents.getStatus())) {
            return this.render_404();
        }
        request.setAttribute("article", contents);
        request.setAttribute("is_post", true);
        completeArticle(request, contents);
//        if (!checkHitsFrequency(request, cid)) {
//            updateArticleHit(contents.getCid(), contents.getHits());
//        }
        return this.render("post");

    }
    /**
     * 抽取公共方法
     *
     * @param request
     * @param contents
     */
    private void completeArticle(HttpServletRequest request, Content contents) {
        if (contents.getAllowComment()) {
            String cp = request.getParameter("cp");
            if (StringUtils.isBlank(cp)) {
                cp = "1";
            }
            request.setAttribute("cp", cp);
            Page4Navigator<Comment> commentsPaginator = commentService.getComments(contents.getCid(), Integer.parseInt(cp), 6);
            request.setAttribute("comments", commentsPaginator);
        }
    }
    /**
     * 更新文章的点击率
     *
     * @param cid
     * @param chits
     */
    private void updateArticleHit(String cid, Integer chits) {
        Integer hits = cache.hget("article" + cid, "hits");
        if (chits == null) {
            chits = 0;
        }
        hits = null == hits ? 1 : hits + 1;
        if (hits >= WebConst.HIT_EXCEED) {
            Content temp = new Content();
            temp.setCid(cid);
            temp.setHits(chits + hits);
            contentService.updateContentByCid(temp);
            cache.hset("article" + cid, "hits", 1);
        } else {
            cache.hset("article" + cid, "hits", hits);
        }
    }
    /**
     * 检查同一个ip地址是否在2小时内访问同一文章
     *
     * @param request
     * @param cid
     * @return
     */
    private boolean checkHitsFrequency(HttpServletRequest request, String cid) {
        String val = IPKit.getIpAddrByRequest(request) + ":" + cid;
        Integer count = cache.hget(Types.HITS_FREQUENCY.getType(), val);
        if (null != count && count > 0) {
            return true;
        }
        cache.hset(Types.HITS_FREQUENCY.getType(), val, 1, WebConst.HITS_LIMIT_TIME);
        return false;
    }

    /**
     * 评论操作
     */
//    @PostMapping(value = "comment")
//    @ResponseBody
//    public RestResponseBo comment(HttpServletRequest request, HttpServletResponse response,
//                                  @RequestParam Integer cid, @RequestParam Integer coid,
//                                  @RequestParam String author, @RequestParam String mail,
//                                  @RequestParam String url, @RequestParam String text, @RequestParam String _csrf_token) {
//
//        String ref = request.getHeader("Referer");
//        if (StringUtils.isBlank(ref) || StringUtils.isBlank(_csrf_token)) {
//            return RestResponseBo.fail(ErrorCode.BAD_REQUEST);
//        }
//
//        String token = cache.hget(Types.CSRF_TOKEN.getType(), _csrf_token);
//        if (StringUtils.isBlank(token)) {
//            return RestResponseBo.fail(ErrorCode.BAD_REQUEST);
//        }
//
//        if (null == cid || StringUtils.isBlank(text)) {
//            return RestResponseBo.fail("请输入完整后评论");
//        }
//
//        if (StringUtils.isNotBlank(author) && author.length() > 50) {
//            return RestResponseBo.fail("姓名过长");
//        }
//
//        if (StringUtils.isNotBlank(mail) && !TaleUtils.isEmail(mail)) {
//            return RestResponseBo.fail("请输入正确的邮箱格式");
//        }
//
//        if (StringUtils.isNotBlank(url) && !PatternKit.isURL(url)) {
//            return RestResponseBo.fail("请输入正确的URL格式");
//        }
//
//        if (text.length() > 200) {
//            return RestResponseBo.fail("请输入200个字符以内的评论");
//        }
//
//        String val = IPKit.getIpAddrByRequest(request) + ":" + cid;
//        Integer count = cache.hget(Types.COMMENTS_FREQUENCY.getType(), val);
//        if (null != count && count > 0) {
//            return RestResponseBo.fail("您发表评论太快了，请过会再试");
//        }
//
//        author = TaleUtils.cleanXSS(author);
//        text = TaleUtils.cleanXSS(text);
//
//        author = EmojiParser.parseToAliases(author);
//        text = EmojiParser.parseToAliases(text);
//
//        CommentVo comments = new CommentVo();
//        comments.setAuthor(author);
//        comments.setCid(cid);
//        comments.setIp(request.getRemoteAddr());
//        comments.setUrl(url);
//        comments.setContent(text);
//        comments.setMail(mail);
//        comments.setParent(coid);
//        try {
//            String result = commentService.insertComment(comments);
//            cookie("tale_remember_author", URLEncoder.encode(author, "UTF-8"), 7 * 24 * 60 * 60, response);
//            cookie("tale_remember_mail", URLEncoder.encode(mail, "UTF-8"), 7 * 24 * 60 * 60, response);
//            if (StringUtils.isNotBlank(url)) {
//                cookie("tale_remember_url", URLEncoder.encode(url, "UTF-8"), 7 * 24 * 60 * 60, response);
//            }
//            // 设置对每个文章1分钟可以评论一次
//            cache.hset(Types.COMMENTS_FREQUENCY.getType(), val, 1, 60);
//            if (!WebConst.SUCCESS_RESULT.equals(result)) {
//                return RestResponseBo.fail(result);
//            }
//            return RestResponseBo.ok();
//        } catch (Exception e) {
//            String msg = "评论发布失败";
//            LOGGER.error(msg, e);
//            return RestResponseBo.fail(msg);
//        }
//    }

    /**
     * 注销
     *
     * @param session
     * @param response
     */
    @RequestMapping("logout")
    public void logout(HttpSession session, HttpServletResponse response) {
        TaleUtils.logout(session, response);
    }
    /**
     * 归档页
     *
     * @return
     */
    @GetMapping(value = "archives")
    public String archives(HttpServletRequest request) {
        List<Content> archives = siteService.getArchives();
        request.setAttribute("archives", archives);
        return this.render("archives");
    }

    /**
     * 友链页
     *
     * @return
     */
    @GetMapping(value = "links")
    public String links(HttpServletRequest request) {
        List<Meta> links = metaService.getMetasByType(Types.LINK.getType());
        request.setAttribute("links", links);
        return this.render("links");
    }

}
