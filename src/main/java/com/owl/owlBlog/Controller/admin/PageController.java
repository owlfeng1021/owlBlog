package com.owl.owlBlog.Controller.admin;

import com.owl.owlBlog.Controller.BaseController;
import com.owl.owlBlog.bo.RestResponseBo;
import com.owl.owlBlog.constant.WebConst;
import com.owl.owlBlog.dto.Types;
import com.owl.owlBlog.pojo.Content;
import com.owl.owlBlog.pojo.User;
import com.owl.owlBlog.service.IContentService;
import com.owl.owlBlog.service.ILogService;
import com.owl.owlBlog.util.IdWorker;
import com.owl.owlBlog.util.Page4Navigator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("admin/page")
public class PageController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PageController.class);
    @Resource
    private IContentService contentService;
    @Resource
    private ILogService logService;
    @Resource
    private IdWorker idWorker;
    @GetMapping(value = "")
    public String index(HttpServletRequest request) {
        // Types.PAGE.getType()
        Page4Navigator<Content> contentsPaginator = contentService.getArticlesWithpage(Types.PAGE.getType(),1, WebConst.MAX_POSTS);
        request.setAttribute("articles", contentsPaginator);
        return "admin/page_list";
    }
    @GetMapping(value = "new")
    public String newPage(HttpServletRequest request) {
        return "admin/page_edit";
    }

    @GetMapping(value = "/{cid}")
    public String editPage(@PathVariable String cid, HttpServletRequest request) {
        Content contents = contentService.getContents(cid);
        request.setAttribute("contents", contents);
        return "admin/page_edit";
    }
    @PostMapping(value = "publish")
    @ResponseBody
    public RestResponseBo publishPage(@RequestParam String title, @RequestParam String content,
                                      @RequestParam String status, @RequestParam String slug,
                                      @RequestParam(required = false) Integer allowComment, @RequestParam(required = false) Integer allowPing, HttpServletRequest request) {
        User user = this.user(request);
        Content content1 = new Content();
        content1.setTitle(title);
        content1.setContent(content);
        content1.setStatus(status);
        content1.setSlug(slug);
        content1.setType(Types.PAGE.getType());
        content1.setCid(idWorker.nextId()+"");
        content1.setAllowComment(true);
        content1.setAllowPing(true);
        content1.setAllowFeed(true);
        contentService.publish(content1);

        if (null != allowComment) {
            content1.setAllowComment(allowComment == 1);
        }
        if (null != allowPing) {
            content1.setAllowPing(allowPing == 1);
        }
        content1.setAuthorId(user.getUid());
        String result = contentService.publish(content1);
        if (!WebConst.SUCCESS_RESULT.equals(result)) {
            return RestResponseBo.fail(result);
        }
        return RestResponseBo.ok();
    }

    @PostMapping(value = "modify")
    @ResponseBody
    public RestResponseBo modifyArticle(@RequestParam String cid, @RequestParam String title,
                                        @RequestParam String content,
                                        @RequestParam String status, @RequestParam String slug,
                                        @RequestParam(required = false) Integer allowComment, @RequestParam(required = false) Integer allowPing, HttpServletRequest request) {
        User users = this.user(request);
        Content contents = new Content();
        contents.setCid(cid);
        contents.setTitle(title);
        contents.setContent(content);
        contents.setStatus(status);
        contents.setSlug(slug);
        contents.setType(Types.PAGE.getType());
        if (null != allowComment) {
            contents.setAllowComment(allowComment == 1);
        }
        if (null != allowPing) {
            contents.setAllowPing(allowPing == 1);
        }
        contents.setAuthorId(users.getUid());
        String result = contentService.updateArticle(contents);
        if (!WebConst.SUCCESS_RESULT.equals(result)) {
            return RestResponseBo.fail(result);
        }
        return RestResponseBo.ok();
    }


}
