package com.nt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserEntity {
	
    @Id
    private Integer id;
    private String name;
    private String email;
    private String country;

}
