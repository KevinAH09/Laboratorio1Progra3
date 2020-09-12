/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio.dto;



/**
 *
 * @author colo7
 */
public class AuthenticationRequest {

    private String cedula;
    private String password;

    public AuthenticationRequest(String cedula, String password) {
        this.cedula = cedula;
        this.password = password;
    }
    
}
