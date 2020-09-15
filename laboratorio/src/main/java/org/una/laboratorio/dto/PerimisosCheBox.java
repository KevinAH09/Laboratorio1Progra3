/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio.dto;

import javafx.scene.control.CheckBox;
import org.una.laboratorio.dto.PermisoDTO;

/**
 *
 * @author colo7
 */
public class PerimisosCheBox {
    CheckBox box ;
    PermisoDTO dTO;

    public PerimisosCheBox() {
    }

    public CheckBox getBox() {
        return box;
    }

    public void setBox(CheckBox box) {
        this.box = box;
    }

    public PermisoDTO getdTO() {
        return dTO;
    }

    public void setdTO(PermisoDTO dTO) {
        this.dTO = dTO;
    }

    public PerimisosCheBox(CheckBox box, PermisoDTO dTO) {
        this.box = box;
        this.dTO = dTO;
    }
    
}
