package com.owl.owlBlog.Controller.admin;

import com.owl.owlBlog.Controller.BaseController;
import com.owl.owlBlog.bo.RestResponseBo;
import com.owl.owlBlog.pojo.Comment;
import com.owl.owlBlog.pojo.User;
import com.owl.owlBlog.service.ICommentService;
import com.owl.owlBlog.util.Page4Navigator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "status")
    @ResponseBody
    public RestResponseBo delete(@RequestParam String coid, @RequestParam String status) {
        try {
            Comment comments = commentsService.getCommentById(coid);
            if (comments != null) {
                comments.setCoid(coid);
                comments.setStatus(status);
                commentsService.update(comments);
            } else {
                return RestResponseBo.fail("操作失败");
            }
        } catch (Exception e) {
            String msg = "操作失败";
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }
    /**
     * 删除一条评论
     *
     * @param coid
     * @return
     */
    @PostMapping(value = "delete")
    @ResponseBody
    public RestResponseBo delete(@RequestParam String coid) {
        try {
            Comment comments = commentsService.getCommentById(coid);
            if (null == comments) {
                return RestResponseBo.fail("不存在该评论");
            }
            commentsService.delete(coid, comments.getContents().getCid());
        } catch (Exception e) {
            String msg = "评论删除失败";
            LOGGER.error(msg, e);
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }
}
