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
        /*
        String url = UriComponentsBuilder.fromUriString("http://api.example.com/v1/tickets/{id}/max-response-time")
                .buildAndExpand(ticketId)
                .toUriString();

        Long severityDays = restTemplate.getForObject(url, Long.class);
*/      Long severityDays = 7L;
        return severityDays;
    }
}