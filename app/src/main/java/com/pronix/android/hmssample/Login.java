package com.pronix.android.hmssample;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.pronix.android.hmssample.common.Constants;
import com.pronix.android.hmssample.common.Utils;
import com.pronix.android.hmssample.model.UserDetails;
import com.pronix.android.hmssample.model.WebServiceDO;
import com.pronix.android.hmssample.services.AsyncTask;
import com.pronix.android.hmssample.services.OnTaskCompleted;

import org.json.JSONObject;

public class Login extends AppCompatActivity implements View.OnClickListener, OnTaskCompleted{
    TextView bytes,sup,lin;
    EditText et_User,et_Password;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_User = (EditText) findViewById(R.id.et_User);
        et_Password = (EditText)findViewById(R.id.et_Password);
        lin = (TextView)findViewById(R.id.logiin);
        sup = (TextView)findViewById(R.id.sup);
        bytes = (TextView)findViewById(R.id.bytes);
//        Typeface custom_font = Typeface.createFromAsset(getAssets(),"fonts/Lato-Light.ttf");
//        bytes.setTypeface(custom_font);
//        pswd.setTypeface(custom_font);
//        sup.setTypeface(custom_font);
//        lin.setTypeface(custom_font);
//        usr.setTypeface(custom_font);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.logiin:
                if(!validation()) {
                    callCredentialsWebServices();
                }
                break;
            case R.id.sup:
                Intent in1 = new Intent(this, Register.class);
                startActivity(in1);
                break;
        }
    }

    public boolean validation()
    {
        boolean status = false;
        String alert = "";
        try
        {
            if(et_User.getText().toString().trim().equals(""))
            {
                alert = "User is required";
            }
            else if(et_Password.getText().toString().trim().equals(""))
            {
                alert = "Password is required";
            }

            if(!alert.equals("")) {
                status = true;
                Utils.showalert(Login.this, "Alert", alert);
            }

        }
        catch (Exception e)
        {

        }
        return status;
    }

    public void callCredentialsWebServices()
    {
        try {
            progressDialog(this);
            WebServiceDO webServiceDO = new WebServiceDO();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("mobile", et_User.getText().toString());
            jsonObject.put("password", et_Password.getText().toString());
            webServiceDO.result = Constants.SENT;
            webServiceDO.request = "CREDENTIALS";
            new AsyncTask(Login.this, Login.this, Constants.URLBase + "" + Constants.REQUEST_LOGIN, "POST", jsonObject.toString()).execute(webServiceDO);
        } catch (Exception e) {
            e.getMessage();
            Utils.hideProgress(dialog);
        }
    }

    @Override
    public void onTaskCompletes(WebServiceDO webServiceDO) {
        try
        {
            Utils.hideProgress(dialog);
            if (webServiceDO.result.equals(Constants.SUCCESS)) {
                JSONObject jsonObject = new JSONObject(webServiceDO.responseContent);
                JSONObject json = new JSONObject(jsonObject.getString("responseStatus"));
                if (webServiceDO.request.equals("CREDENTIALS")) {
                    if(json.getString("status").toUpperCase().equals("SUCCESS")) {
                        JSONObject userDetails = new JSONObject(jsonObject.getString("patientDetailsData"));
                        Constants.userDetails = new UserDetails();
                        Constants.userDetails.name = userDetails.getString("fullName");
                        Constants.userDetails.email = userDetails.getString("emailId");
                        Constants.userDetails.mobile = userDetails.getString("mobile");
                        Constants.userDetails.userId = userDetails.getString("userId");
                        moveToMenuScreen();
                    }
                    else {
                        Utils.showalert(Login.this, "Alert", json.getString("errorDescription"));
                    }

                }
            } else {
                Utils.showalert(this, "Alert", webServiceDO.responseContent);
                Utils.hideProgress(dialog);
            }
        }
        catch (Exception e)
        {
            e.getMessage();
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

    public void moveToMenuScreen()
    {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
    }
}
