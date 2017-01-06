package com.example.pragyaagrawal.levelmoneytask.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.pragyaagrawal.levelmoneytask.R;
import com.example.pragyaagrawal.levelmoneytask.adapters.TransactionsAdapter;
import com.example.pragyaagrawal.levelmoneytask.models.Transactions;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreditCardPaymentsActivity extends AppCompatActivity {

    private static final String CREDIT_CARD_PAYMENTS_EXTRA = "credit_card_payments";
    private static final String BUNDLE_EXTRA = "bundle_extra";
    private static final String ACTIVITY_TITLE = "Credit Card Transactions";

    @BindView(R.id.rvCreditCardTransactions)
    RecyclerView rvCreditCardTransactions;

    public static Intent getIntent(Context context, List<Transactions> creditCardsTransaction)
    {
        Intent intent = new Intent(context, CreditCardPaymentsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(CREDIT_CARD_PAYMENTS_EXTRA, Parcels.wrap(creditCardsTransaction));
        intent.putExtra(BUNDLE_EXTRA,bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card_payments);

        ButterKnife.bind(this);

        getSupportActionBar().setTitle(ACTIVITY_TITLE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Bundle bundleExtra = intent.getBundleExtra(BUNDLE_EXTRA);
        List<Transactions> creditCardsTransaction = Parcels.unwrap(bundleExtra.getParcelable(CREDIT_CARD_PAYMENTS_EXTRA));

        LinearLayoutManager manager = new LinearLayoutManager(CreditCardPaymentsActivity.this);
        rvCreditCardTransactions.setLayoutManager(manager);

        if(creditCardsTransaction!=null)
        {
            TransactionsAdapter transactionsAdapter = new TransactionsAdapter(creditCardsTransaction,CreditCardPaymentsActivity.this);
            rvCreditCardTransactions.setAdapter(transactionsAdapter);
        }
    }
}
