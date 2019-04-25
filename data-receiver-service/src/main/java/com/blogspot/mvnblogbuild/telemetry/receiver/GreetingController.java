package com.blogspot.mvnblogbuild.telemetry.receiver;

import org.springframework.web.bind.annotation.RequestMapping;

public interface GreetingController {
    @RequestMapping("/greeting")
    String greeting();
}
