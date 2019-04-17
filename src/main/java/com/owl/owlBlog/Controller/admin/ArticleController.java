package com.owl.owlBlog.Controller.admin;

import com.owl.owlBlog.Controller.BaseController;
import com.owl.owlBlog.dto.Types;
import com.owl.owlBlog.exception.TipException;
import com.owl.owlBlog.pojo.Content;
import com.owl.owlBlog.pojo.Meta;
import com.owl.owlBlog.service.IContentService;
import com.owl.owlBlog.service.ILogService;
import com.owl.owlBlog.service.IMetaService;
import com.owl.owlBlog.util.Page4Navigator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin/article")
@Transactional(rollbackFor = TipException.class)
public class ArticleController extends BaseController {
    private static final Logger LOGGER =  LoggerFactory.getLogger(ArticleController.class);
    @Resource
    private ILogService logService;
    @Resource
    private IMetaService metaServicel;
    @Resource
    private IContentService contentService;
    @GetMapping(value = "")
    public String index(@RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "limit", defaultValue = "15") int limit,
                        HttpServletRequest request){
        // 需要created desc排序的 content
        Page4Navigator<Content> contentsPaginator = contentService.getArticlesWithpage(Types.ARTICLE.getType(),page,limit);
        request.setAttribute("articles", contentsPaginator);
        return "admin/article_list";
    }
    @GetMapping("/publish")
    public String newArticle(HttpServletRequest request ){
        List<Meta> categories = metaServicel.getMetasByType(Types.CATEGORY.getType());
        request.setAttribute("categories",categories);
        return "admin/article_edit";
    }

}

