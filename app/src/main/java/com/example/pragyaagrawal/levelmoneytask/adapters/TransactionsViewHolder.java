package com.example.pragyaagrawal.levelmoneytask.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.pragyaagrawal.levelmoneytask.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvMerchant)
    TextView tvMerchant;

    @BindView(R.id.tvAmount)
    TextView tvAmount;

    public TransactionsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
