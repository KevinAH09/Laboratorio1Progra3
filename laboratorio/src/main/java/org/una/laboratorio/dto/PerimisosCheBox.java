/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio.dto;

import java.util.List;
import javafx.scene.control.CheckBox;
import org.una.laboratorio.dto.PermisoDTO;

/**
 *
 * @author colo7
 */
public class PerimisosCheBox {
    CheckBox box ;
    PermisoDTO dTO;
    List<PermisoDTO> list;

    public List<PermisoDTO> getList() {
        return list;
    }

    public void setList(List<PermisoDTO> list) {
        this.list = list;
    }

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
