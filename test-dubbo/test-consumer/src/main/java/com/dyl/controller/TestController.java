package com.dyl.controller;

import com.dyl.services.TicketService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Reference
    TicketService ticketService;

    @RequestMapping("test")
    public String test() {
        String ticket = ticketService.ticket();
        return ticket;
    }
}
