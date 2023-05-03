package model;
import java.util.Calendar;

/*
RF05 - Uma Conta Especial tem as mesmas características de uma Conta Comum e também possui um
limite de crédito. Esse limite de crédito é ***definido no momento da abertura da conta e pode ser
alterado ao longo do tempo.***
*/

public class Conta {
    
    private int id;
    private Calendar dataAbertura;
    protected double saldo;
    private double limTransacao;
    private String tipo; //originalmente era char mas pro tablemodel da problema
    private int dia;
    private int limCredito;
    private int idCliente;

    public Conta(int id, Calendar dataAbertura, double saldo, double limTransacao, String tipo, int dia, int limCredito, int idCliente) {
        this.id = id;
        this.dataAbertura = dataAbertura;
        this.saldo = saldo;
        this.limTransacao = limTransacao;
        this.tipo = tipo;
        this.dia = dia;
        this.limCredito = limCredito;
        this.idCliente = idCliente;
    }

    public int getDia() {
        return dia;
    }

    public int getLimCredito() {
        return limCredito;
    }

    public void setLimCredito(int limCredito) {
        this.limCredito = limCredito;
    }

    
    public void deposita(double valor) {
        this.saldo = saldo + valor;
    }
    
    public boolean saqueEspecial(double valor) {
        if(valor > this.limTransacao) {
            return false;
        }
        else if(this.saldo - valor >= -this.limCredito) {
            this.saldo -= valor;
            return true;
        }
        else
            return false;
    }
    
    public boolean saca(double valor) {
        if(valor > this.limTransacao) {
            return false;
        }
        else if(this.saldo - valor >= 0) {
            this.saldo -= valor;
            return true;
        } 
        else {
            return false;
        }
    }
    
    public int getId() {
        return id;
    }

    public int getIdCliente() {
        return idCliente;
    }
    
     public String getTipo() {
        return tipo;
    }

    public Calendar getDataAbertura() {
        return dataAbertura;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getLimTransacao() {
        return limTransacao;
    }

    public void setLimTransacao(double limTransacao) {
        this.limTransacao = limTransacao;
    }
}
