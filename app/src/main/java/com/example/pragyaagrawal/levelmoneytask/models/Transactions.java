package com.example.pragyaagrawal.levelmoneytask.models;

import org.parceler.Parcel;

@Parcel
public class Transactions {

    private String amount;

    private String account_id;

    private Boolean is_pending;

    private String merchant;

    private String clear_date;

    private String raw_merchant;

    private String transaction_id;

    private String aggregation_time;

    private String transaction_time;

    private String categorization;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public Boolean getIs_pending() {
        return is_pending;
    }

    public void setIs_pending(Boolean is_pending) {
        this.is_pending = is_pending;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getClear_date() {
        return clear_date;
    }

    public void setClear_date(String clear_date) {
        this.clear_date = clear_date;
    }

    public String getRaw_merchant() {
        return raw_merchant;
    }

    public void setRaw_merchant(String raw_merchant) {
        this.raw_merchant = raw_merchant;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getAggregation_time() {
        return aggregation_time;
    }

    public void setAggregation_time(String aggregation_time) {
        this.aggregation_time = aggregation_time;
    }

    public String getTransaction_time() {
        return transaction_time;
    }

    public void setTransaction_time(String transaction_time) {
        this.transaction_time = transaction_time;
    }

    public String getCategorization() {
        return categorization;
    }

    public void setCategorization(String categorization) {
        this.categorization = categorization;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [amount = "+amount+", account-id = "+account_id+", is-pending = "+is_pending+", merchant = "+merchant+", clear-date = "+clear_date+", raw-merchant = "+raw_merchant+", transaction-id = "+transaction_id+", aggregation-time = "+aggregation_time+", transaction-time = "+transaction_time+", categorization = "+categorization+"]";
    }

}
