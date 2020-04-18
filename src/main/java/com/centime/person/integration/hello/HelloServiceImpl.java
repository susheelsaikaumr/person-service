package com.centime.person.integration.hello;

import com.centime.person.infrastructure.web.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.centime.person.infrastructure.web.RequestContext.CORRELATION_ID_KEY;

@Service
public class HelloServiceImpl implements HelloService {

    @Autowired
    private HelloClient helloClient;

    @Override
    public String getHello() {
        return helloClient.hello(RequestContext.get(CORRELATION_ID_KEY));
    }
}