package com.tecdesoftware.market;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludar")
public class HelloWorldController {

    @GetMapping("/hola")
    public String saludar(){
        return "Hello World Java Developer 👌! ";
    }
}
