package com.sukhjit.assignment.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class RestErrorController implements ErrorController {

    private static final String PATH = "/error";


    @RequestMapping(value=PATH,method= RequestMethod.GET)
    public ResponseEntity errorGet(HttpServletRequest request, HttpServletResponse response){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Page Not Found", "URL not found");
        return new ResponseEntity("404 Page Not Found", responseHeaders, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value=PATH,method= RequestMethod.POST)
        public ResponseEntity errorPost(HttpServletRequest request, HttpServletResponse response){
            return new ResponseEntity("Invalid URL. Failed to add new Product.", HttpStatus.BAD_REQUEST);
        }

    @Override
    public String getErrorPath() {
        return PATH;
    }

}