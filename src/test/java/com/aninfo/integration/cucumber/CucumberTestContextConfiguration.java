package com.aninfo.integration.cucumber;

import org.springframework.boot.test.context.SpringBootTest;

import com.aninfo.ProyectApp;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = ProyectApp.class)
public class CucumberTestContextConfiguration {
}
