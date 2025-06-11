package com.rpp.api.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/")
public class RootController {

    // Root endpoint
    @GetMapping
    public Map<String, Object> root() {
        return Map.of(
                "name", "API-RPP",
                "status", "success",
                "version", "0.0.1"
        );
    }
}
