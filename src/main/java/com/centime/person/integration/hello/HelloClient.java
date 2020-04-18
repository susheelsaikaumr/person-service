package com.centime.person.integration.hello;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "hello", url = "HelloService-env.eba-fxvkijqe.us-east-2.elasticbeanstalk.com/hello")
public interface HelloClient {

    @GetMapping
    String hello(@RequestHeader("correlationId") String correlationId);
}