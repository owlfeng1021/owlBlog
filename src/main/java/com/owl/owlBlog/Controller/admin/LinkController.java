package com.owl.owlBlog.Controller.admin;

import com.owl.owlBlog.dto.MetaDto;
import com.owl.owlBlog.dto.Types;
import com.owl.owlBlog.pojo.Meta;
import com.owl.owlBlog.service.IMetaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("admin/links")
public class LinkController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LinkController.class);
    @Resource
    private IMetaService metaService;

    @RequestMapping("")
    public String index(HttpServletRequest request){
        List<Meta> metas = metaService.getMetasByType(Types.LINK.getType());
        request.setAttribute("links", metas);
        return "admin/links";
    }


}
