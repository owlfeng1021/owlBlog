package com.owl.owlBlog.Controller.admin;

import com.owl.owlBlog.pojo.Option;
import com.owl.owlBlog.service.ILogService;
import com.owl.owlBlog.service.IOptionService;
import com.owl.owlBlog.service.SiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin/setting")
public class SettingController {
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
