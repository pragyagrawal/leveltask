package com.example.pragyaagrawal.levelmoneytask.models;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pragya Agrawal on 1/4/2017.
 */

public class DataModel {

    private static final String KRISPY_KREME_DONUTS = "Krispy Kreme Donuts";
    private static final String DUNKIN = "DUNKIN #336784";
    private static final String CREDIT_CARD_PAYMENT = "Credit Card Payment";
    private static final String CC_PAYMENT = "CC Payment";

    private List<Transactions> transactions;
    private List<Transactions> filteredTransactions;
    private List<Transactions> mergedTransactions;
    private List<Transactions> creditCardTransactions;

    public DataModel() {
        transactions= new ArrayList<>();
        creditCardTransactions = new ArrayList<>();
    }

    public List<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transactions> transactionsWithCreditCard) {

        //lets filter the credit card transactions.
        for (int i = 0; i < transactionsWithCreditCard.size(); i++) {
            if (transactionsWithCreditCard.get(i).getMerchant().equalsIgnoreCase(CREDIT_CARD_PAYMENT)
                    || transactionsWithCreditCard.get(i).getMerchant().equalsIgnoreCase(CC_PAYMENT)) {
                creditCardTransactions.add(transactionsWithCreditCard.get(i));
            }
            else
            {
                transactions.add(transactionsWithCreditCard.get(i));
            }
        }
    }

    public List<Transactions> getFilteredTransactions() {
        return filteredTransactions;
    }

    public void setFilteredTransactions(List<Transactions> filteredTransactions) {
        this.filteredTransactions = filteredTransactions;
    }

    public List<Transactions> filterTransactions() {
        if (transactions != null) {
            filteredTransactions = new ArrayList<>();
            String merchant;
            for (int i = 0; i < transactions.size(); i++) {
                merchant = transactions.get(i).getMerchant();
                if (TextUtils.isEmpty(merchant) || (!merchant.equalsIgnoreCase(KRISPY_KREME_DONUTS) && !merchant.equalsIgnoreCase(DUNKIN))) {
                    filteredTransactions.add(transactions.get(i));
                }
            }
        }
        return filteredTransactions;
    }

    public List<Transactions> getMergedTransactions() {
        return mergedTransactions;
    }

    public void setMergedTransactions(List<Transactions> futureTransactions) {
        this.mergedTransactions = futureTransactions;
        if (mergedTransactions != null && transactions != null) {
            mergedTransactions.addAll(transactions);
        }
    }

    public List<Transactions> getCreditCardTransactions() {
        return creditCardTransactions;
    }

    public double[] aggregateTransactions(List<Transactions> transactions){
        double spending = 0;
        double income = 0;
        for (int i = 0; i < transactions.size(); i++) {
            double transactionAmount = Double.valueOf(transactions.get(i).getAmount());
            if (transactionAmount>0){
                income = income + transactionAmount;
            }else {
                spending = spending +transactionAmount;
            }
        }
        return new double[]{spending, income};
    }
}
