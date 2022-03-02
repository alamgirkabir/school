/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.security.payload.request;

/**
 *
 * @author alamgir
 */
import javax.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class LoginRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    
}
