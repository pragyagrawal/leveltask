package com.example.pragyaagrawal.levelmoneytask.interfaces;

import com.example.pragyaagrawal.levelmoneytask.models.Arguments;
import com.example.pragyaagrawal.levelmoneytask.models.TransactionsDataModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LevelMoneyAPIInterface {

    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

    @POST("get-all-transactions")
    Call<TransactionsDataModel> getTransactionDetails(@Body Arguments arguments);

    @POST("projected-transactions-for-month")
    Call<TransactionsDataModel> getProjectedTransactionsDetails(@Body Arguments arguments);
}
