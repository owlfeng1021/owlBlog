package com.owl.owlBlog.controller.admin;

import com.owl.owlBlog.controller.BaseController;
import com.owl.owlBlog.bo.RestResponseBo;
import com.owl.owlBlog.dto.Types;
import com.owl.owlBlog.pojo.Meta;
import com.owl.owlBlog.service.IMetaService;
import com.owl.owlBlog.util.IdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("admin/category")
public class CategoryController  extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Resource
    private IMetaService metaService;
@Resource
private  IdWorker   idWorker;

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
    @PostMapping(value = "save")
    @ResponseBody
    public RestResponseBo saveCategory(@RequestParam String cname, @RequestParam String mid) {
        try {
            if (mid==null||"".equals(mid)){
                mid = idWorker.nextId()+"";
                metaService.saveMeta(Types.CATEGORY.getType(), cname,mid );
            }
            metaService.saveMeta(Types.CATEGORY.getType(), cname, mid);
        } catch (Exception e) {
            String msg = "分类保存失败";
            LOGGER.error(msg, e);
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public RestResponseBo delete(@RequestParam String mid) {
        try {
            metaService.delete(mid);
        } catch (Exception e) {
            String msg = "删除失败";
            LOGGER.error(msg, e);
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }
}
