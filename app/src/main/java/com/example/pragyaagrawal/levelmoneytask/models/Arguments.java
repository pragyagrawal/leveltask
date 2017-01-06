package com.example.pragyaagrawal.levelmoneytask.models;

/**
 * Created by Pragya Agrawal on 1/4/2017.
 */

public class Arguments {

    private static final String APP_TOKEN_FOR_INTERVIEW = "AppTokenForInterview";
    private static final String APP_TOKEN = "2EDF25538B127A3F2AE61275984F3C99";
    private static final int UID = 1110590645;

    private Args args;

    private int year;

    private int month;


    public Args getArgs ()
    {
        return args;
    }

    public void setArgs (Args args)
    {
        this.args = args;
    }

    //Since all the arguments are common, generating the object from one single place
    public static Arguments getInstance(){
        Arguments arguments = new Arguments();
        Args args = new Args();
        args.setApi_token(APP_TOKEN_FOR_INTERVIEW);
        args.setJson_strict_mode(false);
        args.setJson_verbose_response(false);
        args.setToken(APP_TOKEN);
        args.setUid(UID);

        arguments.setArgs(args);
        return arguments;
    }

    public static Arguments getInstanceForProjectedTransactions(){
        Arguments arguments = new Arguments();
        Args args = new Args();
        args.setApi_token(APP_TOKEN_FOR_INTERVIEW);
        args.setJson_strict_mode(false);
        args.setJson_verbose_response(false);
        args.setToken(APP_TOKEN);
        args.setUid(UID);
        arguments.setMonth(1);
        arguments.setYear(2017);

        arguments.setArgs(args);
        return arguments;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [args = "+args+"]";
    }
}
