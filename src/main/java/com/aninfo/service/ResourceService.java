package com.aninfo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aninfo.model.Resource;

@Service
public class ResourceService {

    private static final String API_URL = "https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/recursos-psa/1.0.0/m/api/recursos";

    @Autowired
    private RestTemplate restTemplate;

    public List<Resource> getResources() {
        Resource[] resourcesArray = restTemplate.getForObject(API_URL, Resource[].class);
        return Arrays.asList(resourcesArray);
    }

}
