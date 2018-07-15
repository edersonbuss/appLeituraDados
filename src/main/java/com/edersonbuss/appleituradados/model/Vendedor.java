/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edersonbuss.appleituradados.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Ederson
 */
public class Vendedor implements Serializable {

    private static final long serialVersionUID = 1L;
    //001çCPFçNameçSalary
    private String cpf;
    private String name;
    private BigDecimal salary;

    public Vendedor(String cpf, String name, BigDecimal salary) {
        this.cpf = cpf;
        this.name = name;
        this.salary = salary;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

}
