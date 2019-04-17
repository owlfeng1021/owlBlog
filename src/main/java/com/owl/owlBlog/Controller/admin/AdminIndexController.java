package com.owl.owlBlog.Controller.admin;

import com.owl.owlBlog.Controller.BaseController;
import com.owl.owlBlog.Controller.IndexController;
import com.owl.owlBlog.bo.StatisticsBo;
import com.owl.owlBlog.pojo.Comment;
import com.owl.owlBlog.pojo.Content;
import com.owl.owlBlog.pojo.Log;
import com.owl.owlBlog.service.ILogService;
import com.owl.owlBlog.service.IUserService;
import com.owl.owlBlog.service.SiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminIndexController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
    @Resource
    SiteService siteService;

    @Resource
    ILogService logService;
    @Resource
    IUserService userService;
    /**
     * 页面跳转
     * @return
     */
    @GetMapping(value = {"","/index"})
    public String index(HttpServletRequest request){
        LOGGER.info("Enter admin index method");
        List<Comment> comments = siteService.recentComments(5);
        List<Content> contents = siteService.recentContents(5);
        // 统计操作
        StatisticsBo statistics = siteService.getStatistics(); // 要4个数据
        // 取最新的20条日志
        List<Log> logs = logService.getLogs(1, 5);
        request.setAttribute("comments", comments);
        request.setAttribute("articles", contents);
        
        request.setAttribute("statistics", statistics);
        request.setAttribute("logs", logs);
        LOGGER.info("Exit admin index method");
        return "admin/index";
    }


}
