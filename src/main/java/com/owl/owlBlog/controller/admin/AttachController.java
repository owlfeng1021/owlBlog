package com.owl.owlBlog.controller.admin;

import com.owl.owlBlog.controller.BaseController;
import com.owl.owlBlog.bo.RestResponseBo;
import com.owl.owlBlog.constant.WebConst;
import com.owl.owlBlog.dto.LogActions;
import com.owl.owlBlog.dto.Types;
import com.owl.owlBlog.pojo.Attach;
import com.owl.owlBlog.pojo.Log;
import com.owl.owlBlog.pojo.User;
import com.owl.owlBlog.service.AttachService;
import com.owl.owlBlog.service.ILogService;
import com.owl.owlBlog.util.Commons;
import com.owl.owlBlog.util.Page4Navigator;
import com.owl.owlBlog.util.TaleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/attach")
public class AttachController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AttachController.class);

    public static final String CLASSPATH = TaleUtils.getUploadFilePath();

    @Resource
    AttachService attachService;
    @Resource
    ILogService logService;


    @GetMapping("")
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

    /**
     * 上传文件接口
     *
     * @param request
     * @return
     */
    @PostMapping(value = "upload")
    @ResponseBody
    public RestResponseBo upload(HttpServletRequest request, @RequestParam("file") MultipartFile[] multipartFiles) throws IOException {
        User users = this.user(request);
        String uid = users.getUid();
        List<String> errorFiles = new ArrayList<>();
        try {
            for (MultipartFile multipartFile: multipartFiles) {
                String fname = multipartFile.getOriginalFilename();

                if (multipartFile.getSize() <= WebConst.MAX_FILE_SIZE) {
                    String fkey = TaleUtils.getFileKey(fname);
                    String ftype = TaleUtils.isImage(multipartFile.getInputStream()) ? Types.IMAGE.getType() : Types.FILE.getType();
                    File file = new File(CLASSPATH + fkey);
                    try {
                        // 使用springboot内置的copy工具
                        FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(file));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    attachService.save(fname, fkey, ftype, uid);
                } else {
                    errorFiles.add(fname);
                }
            }
        } catch (Exception e) {
            return RestResponseBo.fail();
        }
        return RestResponseBo.ok(errorFiles);
    }

    /**
     * 删除文件
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public RestResponseBo delete(@RequestParam String id, HttpServletRequest request) {
        try {
            Attach attach = attachService.selectById(id);
            if (null == attach) {
                return RestResponseBo.fail("不存在该附件");
            }
            attachService.deleteById(id);
            //  file.getAbsolutePath() + "/";
            new File(CLASSPATH + attach.getFkey()).delete();
            // 添加删除日志
            logService.insertLog(LogActions.DEL_ARTICLE.getAction(), attach.getFkey(), request.getRemoteAddr(), this.getUid(request));

        } catch (Exception e) {
            String msg = "附件删除失败";
            LOGGER.error(msg, e);
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }
}
