package org.learn.SpringBootWorkAroundBranch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/api/base")
@Controller
public class BaseController {

    @GetMapping("/info")
    @ResponseBody
    public String getInfo() {
        return "Work around branch";
    }

}
