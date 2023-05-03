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
public class ContaEspecial extends Conta {
    private int limiteCredito;

    public int getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(int limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public ContaEspecial(int limiteCredito, int id, Calendar dataAbertura, String tipo, double saldo, double limTransacao, int idCliente) {
        super(id, dataAbertura, tipo, saldo, limTransacao, idCliente);
        this.limiteCredito = limiteCredito;
    }
    
    @Override
     public boolean saca(double valor) {
        if(this.saldo - valor >= -limiteCredito) {
            this.saldo = saldo - valor;
            return true;
        } 
        else
            return false;
    }
}
