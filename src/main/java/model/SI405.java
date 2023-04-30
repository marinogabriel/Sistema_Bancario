
package model;

/**
 *
 * @author gabri
 */

/*RF01 - O sistema deve permitir o cadastro de clientes no banco. Ao cadastrar um novo cliente o sistema
deve solicitar o nome, cpf e data de nascimento do cliente.
RF02 - O sistema deve permitir o cadastro de contas bancárias dos clientes. Existem três tipos de contas
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
do mês que a Conta Poupança recebe seus rendimentos

No contexto deste mini projeto não há necessidade de aplicar o rendimento da Conta Poupança automaticamente.
*/

public class SI405 {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
