package com.centime.person.integration.concat;

import com.centime.person.infrastructure.web.RequestContext;
import com.centime.person.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.centime.person.infrastructure.web.RequestContext.CORRELATION_ID_KEY;

@Service
public class ConcatServiceImpl implements ConcatService {

    @Autowired
    private ConcatClient concatClient;

    @Override
    public String concat(Person person) {
        return concatClient.concat(RequestContext.get(CORRELATION_ID_KEY), person);
    }
}
