package com.owl.owlBlog.Controller.admin;

import com.owl.owlBlog.Controller.BaseController;
import com.owl.owlBlog.dto.Types;
import com.owl.owlBlog.pojo.Meta;
import com.owl.owlBlog.service.IMetaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("admin/category")
public class CategoryController  extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Resource
    private IMetaService metaService;

    @GetMapping("")
    public String index(HttpServletRequest request)
    {
        // 传下去 tag 和category
        List<Meta> categories =metaService.getMetasByType(Types.CATEGORY.getType());
        List<Meta> tags=  metaService.getMetasByType(Types.TAG.getType());
        request.setAttribute("categories",categories);
        request.setAttribute("tags",tags);
        return "admin/category";
    }

}
