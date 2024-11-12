package org.learn.SpringBootWorkAroundBranch.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.learn.SpringBootWorkAroundBranch.service.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BaseController {

    @Autowired
    private ProxyService proxyService;

    @RequestMapping(path = "/**", method = {RequestMethod.GET, RequestMethod.HEAD, RequestMethod.PUT,
            RequestMethod.OPTIONS, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.POST})
    public ResponseEntity getInfo(HttpServletRequest httpServletRequest) {
        System.out.println("httpServletRequest = " + httpServletRequest.toString());
        ResponseEntity response = proxyService.getFromInternet(httpServletRequest);
        return response;
    }

}
