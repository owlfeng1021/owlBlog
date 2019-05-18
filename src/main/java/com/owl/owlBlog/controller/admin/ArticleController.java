package com.owl.owlBlog.controller.admin;

import com.owl.owlBlog.constant.WebConst;
import com.owl.owlBlog.controller.BaseController;
import com.owl.owlBlog.bo.RestResponseBo;
import com.owl.owlBlog.dto.LogActions;
import com.owl.owlBlog.dto.Types;
import com.owl.owlBlog.exception.TipException;
import com.owl.owlBlog.pojo.Content;
import com.owl.owlBlog.pojo.Meta;
import com.owl.owlBlog.pojo.User;
import com.owl.owlBlog.service.ICommentService;
import com.owl.owlBlog.service.IContentService;
import com.owl.owlBlog.service.ILogService;
import com.owl.owlBlog.service.IMetaService;
import com.owl.owlBlog.util.IdWorker;
import com.owl.owlBlog.util.Page4Navigator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static com.owl.owlBlog.util.Commons.returnStatus;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/article")
@Transactional(rollbackFor = TipException.class)
@Api(tags = "文章控制器")
public class ArticleController extends BaseController {
    private static final Logger LOGGER =  LoggerFactory.getLogger(ArticleController.class);
    @Resource
    private ILogService logService;
    @Resource
    private IMetaService metaServicel;
    @Resource
    private IContentService contentService;
    @Resource
    private ICommentService commentService;
    @Resource
    private IdWorker idWorker;

    @ApiOperation(value = "跳转", notes = "查询分页数据")
    @GetMapping(value = "")
    public String index(HttpServletRequest request){


//        return "admin/old_article_list";
        return "admin/article_list";
    }

    /**
     *
     * @param page
     * @param limit
     * @param request
     * @return
     */
    @ApiOperation(value = "查询关键字", notes = "查询关键字")
    @PostMapping(value = "/search")
    @ResponseBody
    public  Page4Navigator<Content> search(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "15") int limit,
            @RequestParam  Map<String, String> map, HttpServletRequest request){
        Page4Navigator<Content> byCondition = contentService.findByCondition(page, limit, map);
//        Page4Navigator<Content> contentsPaginator = contentService.getArticlesWithpage(Types.ARTICLE.getType(),page,limit);
        return byCondition;
    }
    @GetMapping("/publish")
    public String newArticle(HttpServletRequest request ){
        List<Meta> categories = metaServicel.getMetasByType(Types.CATEGORY.getType());
        request.setAttribute("categories",categories);
        return "admin/article_edit";
    }
    // 根据cid来编辑
    @GetMapping(value = "/{cid}")
    public String editArticle(@PathVariable String cid, HttpServletRequest request) {
        Content contents = contentService.getContents(cid);
        request.setAttribute("contents", contents);
        List<Meta> categories = metaServicel.getMetasByType(Types.CATEGORY.getType());
        request.setAttribute("categories", categories);
        request.setAttribute("active", "article");
        return "admin/article_edit";
    }
    // 上传文章
    // 修改文章
    @PostMapping(value = "/publish")
    @ResponseBody
    public RestResponseBo publishArticle(Content contents, HttpServletRequest request) {
        User users = this.user(request);
        contents.setAuthorId(users.getUid());

        contents.setType(Types.ARTICLE.getType());
        if (StringUtils.isBlank(contents.getCategories())) {
            contents.setCategories("默认分类");
        }
        String result = contentService.publish(contents);
        return returnStatus(result);
    }

    @PostMapping(value = "/modify")
    @ResponseBody
    public RestResponseBo modifyArticle(Content contents, HttpServletRequest request) {
        User users = this.user(request);
        contents.setAuthorId(users.getUid());
        contents.setType(Types.ARTICLE.getType());
        String result = contentService.updateArticle(contents);
        return returnStatus(result);
    }

    @DeleteMapping(value = "/delete")
    @ResponseBody
    public RestResponseBo delete(@RequestParam String cid, HttpServletRequest request) {
        commentService.delete(null,cid);
        String result = contentService.deleteByCid(cid);
        logService.insertLog(LogActions.DEL_ARTICLE.getAction(), cid + "", request.getRemoteAddr(), this.getUid(request));
        return returnStatus(result);
    }

}

