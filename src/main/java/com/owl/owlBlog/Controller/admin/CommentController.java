package com.owl.owlBlog.Controller.admin;

import com.owl.owlBlog.Controller.BaseController;
import com.owl.owlBlog.pojo.Comment;
import com.owl.owlBlog.pojo.User;
import com.owl.owlBlog.service.ICommentService;
import com.owl.owlBlog.util.Page4Navigator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("admin/comments")
public class CommentController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);
    @Resource
    private ICommentService commentsService;
    @GetMapping(value = "")
    public String index(@RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "limit", defaultValue = "15") int limit, HttpServletRequest request) {
        User users = this.user(request);
        Page4Navigator<Comment> commentsPaginator = commentsService.getCommentsWithPage(users.getUid(),page, limit);
        request.setAttribute("comments", commentsPaginator);
        return "admin/comment_list";
    }
}
