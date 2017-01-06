package com.example.pragyaagrawal.levelmoneytask.network;

import com.example.pragyaagrawal.levelmoneytask.interfaces.LevelMoneyAPIInterface;
import com.example.pragyaagrawal.levelmoneytask.models.Arguments;
import com.example.pragyaagrawal.levelmoneytask.models.TransactionsDataModel;

import retrofit2.Call;
import retrofit2.Callback;

public class LevelMoneyAPI {

    public static void getTransactions(Arguments arguments, Callback<TransactionsDataModel> callback) {
        LevelMoneyAPIInterface levelMoneyAPIInterface = RetroClient.getRetrofit().create(LevelMoneyAPIInterface.class);
        Call<TransactionsDataModel> call = levelMoneyAPIInterface.getTransactionDetails(arguments);
        call.enqueue(callback);
    }

    public static void getProjectedTransactions(Arguments arguments, Callback<TransactionsDataModel> callback){
        LevelMoneyAPIInterface levelMoneyAPIInterface = RetroClient.getRetrofit().create(LevelMoneyAPIInterface.class);
        Call<TransactionsDataModel> call = levelMoneyAPIInterface.getProjectedTransactionsDetails(arguments);
        call.enqueue(callback);
    }
}
