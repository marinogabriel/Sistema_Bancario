package model;
import java.util.Calendar;

/*RF02 - O sistema deve permitir o cadastro de contas bancárias dos clientes. Existem três tipos de contas
que podem ser criadas: Conta Comum, Conta Especial e Conta Poupança. Cada conta
bancária deve estar associada a um único cliente. No entanto um cliente pode ter mais de uma
conta.
RF03 - Devem ser permitidos depósitos e saques nas contas bancárias. Devem também ser permitidas
transferências de valores entre contas bancárias.
RF04 - Uma Conta Comum possui uma data de abertura, um saldo e um limite de valor por
transação, que deve ser aplicado às operações de saque e transferência.
RF05 - Uma Conta Especial tem as mesmas características de uma Conta Comum e também possui um
limite de crédito. Esse limite de crédito é definido no momento da abertura da conta e pode ser
alterado ao longo do tempo.
RF06 - Uma Conta Poupança tem as mesmas características de uma Conta Comum e também uma data
de aniversário da conta. Essa data é apenas um número inteiro entre 1 e 31 que indicando o dia
do mês que a Conta Poupança recebe seus rendimentos*/


public class Conta {
    
    private int id;
    private Calendar dataAbertura;
    private float saldo;
    private float limTransacao;
    private String tipo; //originalmente era char mas pro tablemodel da problema
    private int idCliente;

    public Conta(int id, Calendar dataAbertura, String tipo, float saldo, float limTransacao, int idCliente) {
        this.id = id;
        this.dataAbertura = dataAbertura;
        this.tipo = tipo;
        this.saldo = saldo;
        this.limTransacao = limTransacao;
        this.idCliente = idCliente;
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

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getLimTransacao() {
        return limTransacao;
    }

    public void setLimTransacao(float limTransacao) {
        this.limTransacao = limTransacao;
    }
}
