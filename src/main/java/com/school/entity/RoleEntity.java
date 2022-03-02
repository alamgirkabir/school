/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.entity;
import com.school.config.constants.ROLES;
import javax.persistence.*;
import lombok.Data;
/**
 *
 * @author alamgir
 */
@Data
@Entity
@Table(name = "roles")
public class RoleEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, updatable = true, unique = true)
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private ROLES name;

  
}