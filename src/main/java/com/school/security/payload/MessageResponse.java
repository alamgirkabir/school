/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.security.payload;

import lombok.Data;

/**
 *
 * @author alamgir
 */
@Data
public class MessageResponse {
  private String message;

    public MessageResponse(String message) {
        this.message = message;
    }
  

}
