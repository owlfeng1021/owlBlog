package com.owl.owlBlog.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TargetController {
    @GetMapping("/admin/index")
    public String index(){
        return "admin/index";
    }
    @GetMapping("/admin/login")
    public String test1(){
        return "admin/login";
    }
}
