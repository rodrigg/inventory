package com.example.inventory.model;

import java.io.Serializable;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "category")
public class Category implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7604197844008982109L;
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Id
	private Long id;
    private String name;
    private String description;
    
}