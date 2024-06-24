package com.aninfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public class TicketSeverityService {
    @Autowired
    private RestTemplate restTemplate;
    
    public Long getSeverity(Long ticketId) {

        if(ticketId == -1)
            return -1L;
        
        String url = UriComponentsBuilder.fromUriString("https://sistema-de-gestion-soporte-3.onrender.com/v1/tickets/{id}/max-response-days")
                .buildAndExpand(ticketId)
                .toUriString();
                
        Long severityDays = restTemplate.getForObject(url, Long.class);

        return severityDays;
    }
}