package com.owl.owlBlog.Controller.admin;

import com.owl.owlBlog.Controller.BaseController;
import com.owl.owlBlog.bo.RestResponseBo;
import com.owl.owlBlog.constant.WebConst;
import com.owl.owlBlog.dto.LogActions;
import com.owl.owlBlog.pojo.Option;
import com.owl.owlBlog.service.ILogService;
import com.owl.owlBlog.service.IOptionService;
import com.owl.owlBlog.service.SiteService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin/setting")
public class SettingController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SettingController.class);

    @Resource
    private IOptionService optionService;

    @Resource
    private ILogService logService;

    @Resource
    private SiteService siteService;

    /**
     * 系统设置
     */
    @GetMapping(value = "")
    public String setting(HttpServletRequest request) {
        List<Option> voList = optionService.getOptionList();
        Map<String, String> options = new HashMap<>();
        for (Option option:voList) {
            options.put(option.getName(), option.getValue());
        }
        if (options.get("site_record") == null) {
            options.put("site_record", "");
        }
        request.setAttribute("options", options);
        return "admin/setting";
    }
    //
    @PostMapping(value = "")
    @ResponseBody
    public RestResponseBo saveSetting(@RequestParam(required = false)String site_theme, HttpServletRequest request) {
        try {


        Map<String, String[]> parameterMap = request.getParameterMap();
        HashMap<String, String> hashMap = new HashMap<>();
        parameterMap.forEach((key,value)->{
            hashMap.put(key,join(value));
        });
            optionService.saveOption(hashMap);
            WebConst.initConfig = hashMap;
            if (StringUtils.isNotBlank(site_theme)){
             BaseController.THEME="themes/"+site_theme;
            }
            logService.insertLog(LogActions.SYS_SETTING.getAction(),"",request.getRemoteAddr(),this.getUid(request));
//        logService.insertLog(LogActions.DEL_ARTICLE.getAction(), cid + "", request.getRemoteAddr(), this.getUid(request));

        return RestResponseBo.ok();

        }catch (Exception e){
            String msg="保存设置失败";
        return  RestResponseBo.fail(msg);
        }
        }
    /**
     * 数组转字符串
     *
     * @param arr
     * @return
     */
    private String join(String[] arr) {
        StringBuilder ret = new StringBuilder();
        String[] var3 = arr;
        int var4 = arr.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            String item = var3[var5];
            ret.append(',').append(item);
        }

        return ret.length() > 0 ? ret.substring(1) : ret.toString();
    }


}
