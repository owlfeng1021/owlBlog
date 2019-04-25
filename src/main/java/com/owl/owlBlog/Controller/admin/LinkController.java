package com.owl.owlBlog.Controller.admin;

import com.owl.owlBlog.bo.RestResponseBo;
import com.owl.owlBlog.dto.MetaDto;
import com.owl.owlBlog.dto.Types;
import com.owl.owlBlog.pojo.Meta;
import com.owl.owlBlog.service.IMetaService;
import com.owl.owlBlog.util.IdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("admin/links")
public class LinkController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LinkController.class);
    @Resource
    private IMetaService metaService;
    @Resource
    private IdWorker idWorker;

    @RequestMapping("")
    public String index(HttpServletRequest request){
        List<Meta> metas = metaService.getMetasByType(Types.LINK.getType());
        request.setAttribute("links", metas);
        return "admin/links";
    }
    @PostMapping(value = "save")
    @ResponseBody
    public RestResponseBo saveLink(@RequestParam String title, @RequestParam String url,
                                   @RequestParam String logo, @RequestParam String mid,
                                   @RequestParam(value = "sort", defaultValue = "0") int sort) {
        try {
            Meta metas = new Meta();
            metas.setMid(idWorker.nextId()+"");
            metas.setName(title);
            metas.setSlug(url);
            metas.setDescription(logo);
            metas.setSort(sort);
            metas.setType(Types.LINK.getType());
            if (null != mid) {
                metas.setMid(mid);
                metaService.update(metas);
            } else {
                metaService.saveMeta(metas);
            }
        } catch (Exception e) {
            String msg = "友链保存失败";
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
            String msg = "友链删除失败";
            LOGGER.error(msg, e);
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }
}
