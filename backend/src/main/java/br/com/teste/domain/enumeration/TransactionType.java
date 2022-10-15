package br.com.teste.domain.enumeration;

public enum TransactionType {

    TRANSACTION_A("A"),
    TRANSACTION_B("B"),
    TRANSACTION_C("C"),
    TRANSACTION_D("D");

    private final String transactionType;

    TransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionType() {
        return transactionType;
    }
}
