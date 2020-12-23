package com.dyl.services.impl;

import com.dyl.services.TicketService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Component
@Service
public class TikcetServicesImpl implements TicketService {

    public String ticket() {
        return "Provider TikcetServicesImpl";
    }
}
