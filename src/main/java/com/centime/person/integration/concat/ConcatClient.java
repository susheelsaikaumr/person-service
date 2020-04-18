package com.centime.person.integration.concat;

import com.centime.person.model.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "concat", url = "http://concatservice-env.eba-3b3prm7q.us-east-2.elasticbeanstalk.com/concat")
public interface ConcatClient {

    @PostMapping
    String concat(@RequestHeader("correlationId") String correlationId, Person person);
}
