package org.learn.SpringBootWorkAroundBranch.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api/v1")
public class DualController {

    private static final Logger log = LoggerFactory.getLogger(DualController.class);

    @GetMapping("/item")
    public ResponseEntity dual() {
        log.info("Security : {}",SecurityContextHolder.getContext().getAuthentication().toString());
        return ResponseEntity.ok(Arrays.asList("erol", "frogo"));
    }
}
