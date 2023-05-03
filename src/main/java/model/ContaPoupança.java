/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Calendar;

/**
 *
 * @author gabri
 */
public class ContaPoupança extends Conta {
    private int dia;

    public ContaPoupança(int dia, int id, Calendar dataAbertura, String tipo, double saldo, double limTransacao, int idCliente) {
        super(id, dataAbertura, tipo, saldo, limTransacao, idCliente);
        this.dia = dia;
    }

    public int getDia() {
        return dia;
    }
 
}
