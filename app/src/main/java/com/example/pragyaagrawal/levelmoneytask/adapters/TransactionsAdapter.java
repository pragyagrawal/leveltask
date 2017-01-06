package com.example.pragyaagrawal.levelmoneytask.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pragyaagrawal.levelmoneytask.R;
import com.example.pragyaagrawal.levelmoneytask.models.Transactions;
import com.example.pragyaagrawal.levelmoneytask.utils.Utils;

import java.util.List;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsViewHolder> {
    private List<Transactions> transactionsList;
    private Context context;

    public TransactionsAdapter(List<Transactions> transactionsList, Context context) {
        this.transactionsList = transactionsList;
        this.context = context;
    }

    @Override
    public TransactionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View transactionView = inflater.inflate(R.layout.transaction_item, parent, false);

        //Return the a new Holder instance
        TransactionsViewHolder viewHolder = new TransactionsViewHolder(transactionView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TransactionsViewHolder holder, int position) {

        holder.tvMerchant.setText(transactionsList.get(position).getMerchant());
        //Negative amount = debit, positive amount = credit. Centocents. 20000 centocents = $2.
        Double amount = Double.valueOf(transactionsList.get(position).getAmount())/20000;
        holder.tvAmount.setText(Utils.getFormattedCurrency(amount));
        if (transactionsList.get(position).getAmount().contains("-")) {
            holder.tvAmount.setTextColor(Color.RED);
        }else {
            holder.tvAmount.setTextColor(Color.GREEN);
        }
    }


    @Override
    public int getItemCount() {
        if (transactionsList != null) {
            return transactionsList.size();
        }
        return 0;
    }

    public void setTransactionsList(List<Transactions> transactionsList) {
        this.transactionsList = transactionsList;
    }
}
