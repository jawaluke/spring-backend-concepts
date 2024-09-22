package org.learn.SpringBootWorkAroundBranch.controller;

import org.learn.SpringBootWorkAroundBranch.model.Item;
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

    @GetMapping("/info")
    public String getInfo() {
        return "Work around branch";
    }

    @GetMapping("/dealer/{id}")
    public Item getDealer(@PathVariable("id") String id) {
        return baseService.getItem(0,id);
    }

    @PostMapping("/dealer/add")
    public Item addDealer(@RequestBody Item item) {
        return baseService.add(item);
    }

    @DeleteMapping("/dealer/delete/{name}")
    public Item deleteDealer(@PathVariable("name") String name) {
        return baseService.delete(name);
    }

}
