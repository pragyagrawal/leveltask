package com.example.pragyaagrawal.levelmoneytask.utils;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Pragya Agrawal on 1/5/2017.
 */

public class Utils {

    public static String getFormattedCurrency(Double currency){
        Locale locale = Locale.US;
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        return fmt.format(currency);
    }
}
