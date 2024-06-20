package com.aninfo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Resource {

    @JsonProperty("legajo")
    private Long id;
    @JsonProperty("Nombre")
    private String name;
    @JsonProperty("Apellido")
    private String surname;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
