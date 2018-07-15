/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edersonbuss.appleituradados.model;

import java.io.Serializable;

/**
 *
 * @author Ederson
 */
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    //002çCNPJçNameçBusiness Area
    private String cnpj;
    private String name;
    private String businessArea;

    public Cliente(String cnpj, String name, String businessArea) {
        this.cnpj = cnpj;
        this.name = name;
        this.businessArea = businessArea;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

}
