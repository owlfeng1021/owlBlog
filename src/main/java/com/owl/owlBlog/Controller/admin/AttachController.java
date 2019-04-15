package com.owl.owlBlog.Controller.admin;

import com.owl.owlBlog.pojo.Attach;
import com.owl.owlBlog.pojo.Log;
import com.owl.owlBlog.service.AttachService;
import com.owl.owlBlog.service.ILogService;
import com.owl.owlBlog.util.TaleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class AttachController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AttachController.class);

    public static final String CLASSPATH = TaleUtils.getUploadFilePath();

    @Resource
    AttachService attachService;
    @Resource
    ILogService logService;
    @GetMapping("/admin/attach")
    public List<Attach> Attach(){

        return  attachService.list() ;
    }

    @GetMapping("/admin/log")
    public List<Log> log(){
        return  logService.getLogs(1,5) ;
    }

}
