package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    private HelloRepository helloRepository;

    public HelloService(HelloRepository helloRepository) {
        this.helloRepository = helloRepository;
    }

    public String getHello(Long id) {
        Hello hello = helloRepository.findById(1L).orElseThrow();
        return hello.getResult();
    }
}
