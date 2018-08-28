package com.csranger.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {

    @GetMapping(value = "/msg")
    public String msg() {
        return "this is product's msg 1";
    }
}
