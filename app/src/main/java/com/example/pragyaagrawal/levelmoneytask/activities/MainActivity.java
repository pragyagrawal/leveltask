package com.example.pragyaagrawal.levelmoneytask.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.TextView;

import com.example.pragyaagrawal.levelmoneytask.R;
import com.example.pragyaagrawal.levelmoneytask.adapters.TransactionsAdapter;
import com.example.pragyaagrawal.levelmoneytask.models.Arguments;
import com.example.pragyaagrawal.levelmoneytask.models.DataModel;
import com.example.pragyaagrawal.levelmoneytask.models.Transactions;
import com.example.pragyaagrawal.levelmoneytask.models.TransactionsDataModel;
import com.example.pragyaagrawal.levelmoneytask.network.LevelMoneyAPI;
import com.example.pragyaagrawal.levelmoneytask.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rvTransactions)
    RecyclerView rvTransactions;

    @BindView(R.id.tvIncome)
    TextView tvIncome;

    @BindView(R.id.tvSpending)
    TextView tvSpending;

    @BindView(R.id.switchTransactions)
    Switch switchTransactions;

    private TransactionsDataModel transactionsDataModel;
    private TransactionsAdapter transactionsAdapter;
    private Arguments arguments;
    private DataModel dataModel;

    private MenuItem emptyDonut;
    private MenuItem selectedDonut;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        dataModel = new DataModel();

        arguments = Arguments.getInstance();

        progressDialog = new ProgressDialog(MainActivity.this);

        getTransactionsFromServer();
        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
        rvTransactions.setLayoutManager(manager);

        transactionsAdapter = new TransactionsAdapter(new ArrayList<Transactions>(), MainActivity.this);
        rvTransactions.setAdapter(transactionsAdapter);
    }

    private void updateIncomeSpendingValues(List<Transactions> transactionsList){
        double[] incomeExpense = dataModel.aggregateTransactions(transactionsList);
        if (incomeExpense!=null && incomeExpense.length>1) {
            tvSpending.setText(Utils.getFormattedCurrency(incomeExpense[0]/20000));
            tvIncome.setText(Utils.getFormattedCurrency(incomeExpense[1]/20000));
        }
    }

    @OnCheckedChanged(R.id.switchTransactions)
    public void switchTransactions(boolean checked) {
        // check if turn on or off
        if (checked) {
            if (dataModel.getMergedTransactions() == null) {
                getProjectedTransactionsFromServer();
            } else {
                showAllTransactions(true);
            }
        } else {
            showAllTransactions(false);
        }
    }

    private void getTransactionsFromServer() {
        progressDialog.setTitle("Loading Transactions");
        progressDialog.show();
        LevelMoneyAPI.getTransactions(arguments, new Callback<TransactionsDataModel>() {
            @Override
            public void onResponse(Call<TransactionsDataModel> call, Response<TransactionsDataModel> response) {
                transactionsDataModel = response.body();
                if (progressDialog.isShowing()) {
                    progressDialog.cancel();
                }
                dataModel.setTransactions(transactionsDataModel.getTransactions());
                showAllTransactions(false);
            }

            @Override
            public void onFailure(Call<TransactionsDataModel> call, Throwable t) {
                if (progressDialog.isShowing()) {
                    progressDialog.cancel();
                }
                Snackbar.make(rvTransactions, "Network Failure", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void getProjectedTransactionsFromServer() {
        progressDialog.show();

        LevelMoneyAPI.getProjectedTransactions(Arguments.getInstanceForProjectedTransactions(), new Callback<TransactionsDataModel>() {
            @Override
            public void onResponse(Call<TransactionsDataModel> call, Response<TransactionsDataModel> response) {
                if (progressDialog.isShowing()) {
                    progressDialog.cancel();
                }
                dataModel.setMergedTransactions(response.body().getTransactions());
                showAllTransactions(true);
            }

            @Override
            public void onFailure(Call<TransactionsDataModel> call, Throwable t) {
                if (progressDialog.isShowing()) {
                    progressDialog.cancel();
                }
                Snackbar.make(rvTransactions, "Network Failure", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        emptyDonut = menu.findItem(R.id.empty_donut);
        selectedDonut = menu.findItem(R.id.selected_donut);
        emptyDonut.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.empty_donut:
                showAllTransactions(false);
                emptyDonut.setVisible(false);
                selectedDonut.setVisible(true);
                break;
            case R.id.selected_donut:
                showFilterData();
                emptyDonut.setVisible(true);
                selectedDonut.setVisible(false);
                break;
            case R.id.creditCard:
                if(dataModel.getCreditCardTransactions()!=null) {
                    startActivity(CreditCardPaymentsActivity.getIntent(MainActivity.this,dataModel.getCreditCardTransactions()));
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAllTransactions(boolean mergeFuture) {
        if (mergeFuture && dataModel.getMergedTransactions() != null) {
            transactionsAdapter.setTransactionsList(dataModel.getMergedTransactions());
            transactionsAdapter.notifyDataSetChanged();
            updateIncomeSpendingValues(dataModel.getMergedTransactions());
        } else if (dataModel.getTransactions() != null) {
            transactionsAdapter.setTransactionsList(dataModel.getTransactions());
            transactionsAdapter.notifyDataSetChanged();
            updateIncomeSpendingValues(dataModel.getTransactions());
        }
    }

    //this method will show transactions without donuts
    private void showFilterData() {
        List<Transactions> filteredTransactions = dataModel.getFilteredTransactions();
        if (filteredTransactions == null) {
            filteredTransactions = dataModel.filterTransactions();
        }

        if (filteredTransactions != null) {
            transactionsAdapter.setTransactionsList(filteredTransactions);
            transactionsAdapter.notifyDataSetChanged();
            updateIncomeSpendingValues(filteredTransactions);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
