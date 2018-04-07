package com.pronix.android.hmssample;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.pronix.android.hmssample.common.Constants;
import com.pronix.android.hmssample.common.Utils;
import com.pronix.android.hmssample.model.WebServiceDO;
import com.pronix.android.hmssample.services.AsyncTask;
import com.pronix.android.hmssample.services.OnTaskCompleted;

import org.json.JSONObject;

public class Register extends AppCompatActivity implements View.OnClickListener, OnTaskCompleted {
    EditText passwordd, mobphone, mail, usrusr;
    TextView login, signup;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initializeControls();
//        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Lato-Light.ttf");
        /*signup.setTypeface(custom_font);
        mail.setTypeface(custom_font);
        mobphone.setTypeface(custom_font);
        passwordd.setTypeface(custom_font);
        usrusr.setTypeface(custom_font);
        login.setTypeface(custom_font);*/
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Register.this, Login.class);
                startActivity(it);
            }
        });
    }

    public void initializeControls() {
        try {
            usrusr = findViewById(R.id.usrusr);
            passwordd = (EditText) findViewById(R.id.passwrd);
            mail = (EditText) findViewById(R.id.mail);
            mobphone = (EditText) findViewById(R.id.mobphone);
            login = (TextView) findViewById(R.id.logiin);
            signup = (TextView) findViewById(R.id.sup);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.sup:
                    if (!validations()) {
                        callWebService();
                    }
                    break;
            }
        } catch (Exception e) {

        }
    }

    public void callWebService() {
        try {
            progressDialog(this);
            WebServiceDO webServiceDO = new WebServiceDO();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("fullName", usrusr.getText().toString());
            jsonObject.put("emailId", mail.getText().toString().trim());
            jsonObject.put("mobile", mobphone.getText().toString());
            jsonObject.put("password", passwordd.getText().toString());

            webServiceDO.result = Constants.SENT;
            webServiceDO.request = "REGISTER";
            new AsyncTask(Register.this, Register.this, Constants.URLBase + "" + Constants.REQUEST_REGISTER, "POST", jsonObject.toString()).execute(webServiceDO);
        } catch (Exception e) {
            e.getMessage();
            Utils.hideProgress(dialog);
        }
    }

    public boolean validations() {
        boolean status = false;
        String alert = "";
        if (mail.getText().toString().trim().equals("")) {
            alert = "Email is required";
        } else if (usrusr.getText().toString().trim().equals("")) {
            alert = "Username is required";
        } else if (passwordd.getText().toString().trim().equals("")) {
            alert = "Password is required";
        } else if (mobphone.getText().toString().trim().equals("")) {
            alert = "Mobile number is required";
        }
        if (!alert.equals("")) {
            status = true;
            Utils.showalert(this, "Alert", alert);
        }
        return status;
    }

    @Override
    public void onTaskCompletes(WebServiceDO webServiceDO) {
        try {
            Utils.hideProgress(dialog);
            if (webServiceDO.result.equals(Constants.SUCCESS)) {
                if (webServiceDO.request.equals("REGISTER")) {
                    JSONObject jsonObject = new JSONObject(webServiceDO.responseContent);
                    if(jsonObject.getString("status").toUpperCase().equals("SUCCESS"))
                    {
                        confirmationAlert();
                    }
                    else
                    {
                        Utils.showalert(Register.this, "Alert", jsonObject.getString("errorCode") + " : " + jsonObject.getString("errorDescription"));
                    }
                }
            } else {
                Utils.showalert(this, "Alert", webServiceDO.responseContent);
                Utils.hideProgress(dialog);
            }
        } catch (Exception e) {

        }
    }

    public void progressDialog(Activity activity) {
        dialog = new Dialog(activity);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progressview);
        dialog.show();
    }

    public void confirmationAlert()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Register.this);
        alertDialog.setTitle("Alert");

        alertDialog.setMessage("Profile registered successfully");

        alertDialog.setIcon(R.mipmap.ic_launcher);

        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                movetoLoginScreen();
            }
        });

        alertDialog.show();
    }

    public void movetoLoginScreen() {
        startActivity(new Intent(this, Login.class));
        finish();
    }
}