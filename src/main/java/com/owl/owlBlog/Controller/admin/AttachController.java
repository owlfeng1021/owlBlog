package com.owl.owlBlog.Controller.admin;

import com.owl.owlBlog.constant.WebConst;
import com.owl.owlBlog.dto.Types;
import com.owl.owlBlog.pojo.Attach;
import com.owl.owlBlog.pojo.Log;
import com.owl.owlBlog.service.AttachService;
import com.owl.owlBlog.service.ILogService;
import com.owl.owlBlog.util.Commons;
import com.owl.owlBlog.util.Page4Navigator;
import com.owl.owlBlog.util.TaleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AttachController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AttachController.class);

    public static final String CLASSPATH = TaleUtils.getUploadFilePath();

    @Resource
    AttachService attachService;
    @Resource
    ILogService logService;
    @GetMapping("/admin/attach")
    public String Attach(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "limit", defaultValue = "12") int limit,
                         HttpServletRequest request){
        Page4Navigator<Attach> attachs = attachService.getAttachs(page, limit);
        request.setAttribute("attachs",attachs);
        request.setAttribute(Types.ATTACH_URL.getType(), Commons.site_option(Types.ATTACH_URL.getType(), Commons.site_url()));
        request.setAttribute("max_file_size", WebConst.MAX_FILE_SIZE / 1024);
        return "admin/attach";
    }

    @GetMapping("/admin/log")
    public List<Log> log(){
        return  logService.getLogs(1,5) ;
    }

}
