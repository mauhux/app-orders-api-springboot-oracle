package dev.mauhux.apps.orders.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {

    @GetMapping
    public List<String> hello() {
        return List.of("Hello", "World", "Everyone");
    }
}
