package com.swk.demo.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @author Jasper Wu
 * @date 2020/3/12
 **/
@Service
@Component
public class DefaultHelloService implements HelloService {
    public String say(String name) throws Exception {
        Thread.sleep(2000L);
        return "hello" + name;
    }
}
