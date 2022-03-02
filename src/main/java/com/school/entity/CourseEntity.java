/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.entity;

import io.swagger.annotations.ApiModel;
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
@ApiModel
@Data
@Entity
@Table(name = "course")
public class CourseEntity implements Serializable {

    @ApiParam(hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = true, unique = true)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

}
