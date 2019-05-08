package com.owl.owlBlog.Controller.admin;

import com.owl.owlBlog.Controller.BaseController;
import com.owl.owlBlog.bo.RestResponseBo;
import com.owl.owlBlog.constant.WebConst;
import com.owl.owlBlog.dto.LogActions;
import com.owl.owlBlog.exception.TipException;
import com.owl.owlBlog.pojo.User;
import com.owl.owlBlog.service.ILogService;
import com.owl.owlBlog.service.IUserService;
import com.owl.owlBlog.util.TaleUtils;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
@Transactional(rollbackFor = TipException.class)
public class AuthController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Resource
    private IUserService usersService;

    @Resource
    private ILogService logService;

    @GetMapping(value = "/login")
    public String login( HttpServletRequest request,
                         HttpServletResponse response) {
        //            判断是否有用户 没有就添加进去
        if (usersService.checkEmpty()) {
            // 计划添加一个配置页面
            logService.insertLog(LogActions.LOGIN.getAction(), null, request.getRemoteAddr(), null);
//            if (usersService.)
        }
        return "admin/login";
    }

    @PostMapping(value = "login")
    @ResponseBody
    public RestResponseBo doLogin(@RequestParam String username,
                                  @RequestParam String password,
                                  @RequestParam(required = false) String remeber_me,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        // 把错误次数加入缓存 可以使用其他缓存
        Integer error_count = cache.get("login_error_count");
        try {

                User user = usersService.login(username, password);
                // 把用户加入缓存
                request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, user);
                if (StringUtils.isNotBlank(remeber_me)) {
                    TaleUtils.setCookie(response, user.getUid());
                }
                // 记录登录操作
                logService.insertLog(LogActions.LOGIN.getAction(), null, request.getRemoteAddr(), user.getUid());
        } catch (Exception e) {
            error_count = null == error_count ? 1 : error_count + 1;
            if (error_count > 3) {
                return RestResponseBo.fail("您输入密码已经错误超过3次，请10分钟后尝试");
            }

            cache.set("login_error_count", error_count, 10 * 60);
            String msg = "登录失败";

            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                LOGGER.error(msg, e);
            }

            return RestResponseBo.fail(msg);
        }

        return  RestResponseBo.ok();

    }
    /**
     * 注销
     *
     * @param session
     * @param response
     */
    @RequestMapping("/logout")
    public void logout(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        session.removeAttribute(WebConst.LOGIN_SESSION_KEY);
        Cookie cookie=new Cookie(WebConst.USER_IN_COOKIE,"");
        cookie.setValue(null);
        cookie.setMaxAge(0);// 立即销毁cookie
        cookie.setPath("/");
        response.addCookie(cookie);
        try{
            response.sendRedirect("/admin/login");
        }
        catch(IOException e){
            e.printStackTrace();
            LOGGER.error("注销失败",e);
        }

    }
}
