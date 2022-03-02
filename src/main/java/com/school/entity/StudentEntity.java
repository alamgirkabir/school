/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.entity;

import io.swagger.annotations.ApiParam;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author alamgir
 */
@Data
@Entity
@Table(name = "student")
public class StudentEntity implements Serializable {
    
    @ApiParam(hidden = true)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = true)
    private Long id;
    
    @Column(name = "name", nullable = false)
    private String name;


}
