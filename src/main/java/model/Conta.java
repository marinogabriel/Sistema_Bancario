package model;
import java.util.Calendar;

/*RF03 - Devem ser permitidos depósitos e saques nas contas bancárias. Devem também ser permitidas
transferências de valores entre contas bancárias.
RF04 - Uma Conta Comum possui uma data de abertura, um saldo e um limite de valor por
transação, que deve ser aplicado às operações de saque e transferência.
RF05 - Uma Conta Especial tem as mesmas características de uma Conta Comum e também possui um
limite de crédito. Esse limite de crédito é ***definido no momento da abertura da conta e pode ser
alterado ao longo do tempo.***
RF06 - Uma Conta Poupança tem as mesmas características de uma Conta Comum e também uma data
de aniversário da conta. Essa data é apenas um número inteiro entre 1 e 31 que indicando o dia
do mês que a Conta Poupança recebe seus rendimentos OK*/

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
