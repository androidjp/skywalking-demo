package com.swk.demo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.swk.demo.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jasper Wu
 * @date 2020/4/26
 **/
@Slf4j
@RestController
@RequestMapping("/")
public class HelloWorldController {
    @Reference
    private HelloService helloService;

    @GetMapping("hello/{words}")
    public String hello(@PathVariable("words") String words) throws Exception {
        Thread.sleep(1000L);
        log.info("traceId:{}", TraceContext.traceId());
        ActiveSpan.tag("hello-trace", words);
        String say = helloService.say(words);
        Thread.sleep(1000L);
        return say;
    }


    @GetMapping("err")
    public String err() {
        String traceId = TraceContext.traceId();
        log.info("traceId:{}", traceId);
        ActiveSpan.tag("error-trace activation", "error");
        throw new RuntimeException("err");
    }


}
