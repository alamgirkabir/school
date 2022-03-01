/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.controller.exception;

/**
 *
 * @author alamgir
 */
public class NoRegistrationFoundException extends Exception {

    public NoRegistrationFoundException(String message) {
        super(message);
    }

    public NoRegistrationFoundException() {
        super("No registration found.");
    }
    
    
}
