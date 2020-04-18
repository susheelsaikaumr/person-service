package com.centime.person.controller;

import com.centime.person.model.Person;
import com.centime.person.service.GreetingService;
import com.centime.person.util.LogMethodParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Controller for greeting the person.
 * @author Susheel
 */
@RestController
@RequestMapping("/person")
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    @LogMethodParam
    @GetMapping
    public ResponseEntity<String> health() {
        return ResponseEntity.ok().body("UP");
    }

    @LogMethodParam
    @PostMapping(path = "/greeting", consumes = "application/json")
    public ResponseEntity<String> greetPerson(@RequestBody Person person) {
        return ResponseEntity.of(Optional.of(greetingService.greetPerson(person)));
    }
}
