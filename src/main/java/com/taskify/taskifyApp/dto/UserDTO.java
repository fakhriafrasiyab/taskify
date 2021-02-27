package com.taskify.taskifyApp.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String name;
    private String surname;
    private String email;

    public UserDTO(Integer id, String name, String surname, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}
