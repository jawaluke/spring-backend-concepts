package org.learn.SpringBootWorkAroundBranch.controller;

import org.learn.SpringBootWorkAroundBranch.model.Dog;
import org.learn.SpringBootWorkAroundBranch.model.DogDTO;
import org.learn.SpringBootWorkAroundBranch.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/base")
@RestController
public class BaseController {

    private BaseService baseService;

    @Autowired
    public BaseController(BaseService baseService) {
        this.baseService = baseService;
    }

    @GetMapping("/info/{no}")
    @ResponseBody
    public List<String> getInfo(@PathVariable("no") int no) {
        return baseService.getRaiders(no);
    }

    @PostMapping("/dum/{n}")
    public DogDTO saveDummies(@RequestBody Dog dog, @PathVariable("n") int n) {
        return baseService.saveDog(dog, n);
    }

}
