package com.example.pragyaagrawal.levelmoneytask.models;

import java.util.List;

public class TransactionsDataModel {

    private List<Transactions> transactions;

    private String error;

    public List<Transactions> getTransactions ()
    {
        return transactions;
    }

    public void setTransactions (List<Transactions> transactions)
    {
        this.transactions = transactions;
    }

    public String getError ()
    {
        return error;
    }

    public void setError (String error)
    {
        this.error = error;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [transactions = "+transactions+", error = "+error+"]";
    }
}
