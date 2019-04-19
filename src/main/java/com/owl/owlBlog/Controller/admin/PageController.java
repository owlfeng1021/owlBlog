package com.owl.owlBlog.Controller.admin;

import com.owl.owlBlog.Controller.BaseController;
import com.owl.owlBlog.constant.WebConst;
import com.owl.owlBlog.dto.Types;
import com.owl.owlBlog.pojo.Content;
import com.owl.owlBlog.service.IContentService;
import com.owl.owlBlog.service.ILogService;
import com.owl.owlBlog.util.Page4Navigator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @GetMapping(value = "")
    public String index(HttpServletRequest request) {
        // Types.PAGE.getType()

        Page4Navigator<Content> contentsPaginator = contentService.getArticlesWithpage(Types.PAGE.getType(),1, WebConst.MAX_POSTS);
        request.setAttribute("articles", contentsPaginator);
        return "admin/page_list";
    }
}
