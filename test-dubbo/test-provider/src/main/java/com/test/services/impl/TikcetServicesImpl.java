package com.test.services.impl;

import com.test.services.TicketService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Component
@Service
public class TikcetServicesImpl implements TicketService {

    public String ticket() {
        return "Provider TikcetServicesImpl";
    }
}
