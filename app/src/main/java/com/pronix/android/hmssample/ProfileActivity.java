package com.pronix.android.hmssample;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.TextView;

import com.pronix.android.hmssample.common.Constants;
import com.pronix.android.hmssample.common.Utils;
import com.pronix.android.hmssample.model.WebServiceDO;
import com.pronix.android.hmssample.services.AsyncTask;
import com.pronix.android.hmssample.services.OnTaskCompleted;

import org.json.JSONObject;

/**
 * Created by ravi on 4/4/2018.
 */

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener, OnTaskCompleted {
    TextInputEditText et_Mobile, et_Email, et_Age, et_BloodType, et_Address;
    RadioButton rb_Male, rb_Female;
    Dialog dialog;
    TextView tv_Name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initializeControls();
        callProfileWebServices();
    }

    public void initializeControls() {
        et_Mobile = (TextInputEditText) findViewById(R.id.et_Mobile);
        et_Email = (TextInputEditText) findViewById(R.id.et_Email);
        et_Address = (TextInputEditText) findViewById(R.id.et_Address);
        et_Age = (TextInputEditText) findViewById(R.id.et_Age);
        et_BloodType = (TextInputEditText) findViewById(R.id.et_BloodType);
        rb_Male = (RadioButton) findViewById(R.id.rb_Male);
        rb_Female = (RadioButton) findViewById(R.id.rb_Female);
        tv_Name = (TextView) findViewById(R.id.tv_Name);
        tv_Name.setText(Constants.userDetails.name);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_Submit:
                if (!validations()) {
                    sendProfileDataWebServices();
                }
                break;
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

    public void sendProfileDataWebServices() {
        try {
            progressDialog(this);
            WebServiceDO webServiceDO = new WebServiceDO();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userId", Constants.userDetails.userId);
            jsonObject.put("gender", rb_Male.isChecked() ? "Male" : "Female");
            jsonObject.put("age", et_Age.getText().toString().trim());
            jsonObject.put("address", et_Address.getText().toString());
            webServiceDO.result = Constants.SENT;
            webServiceDO.request = "PROFILE";
            new AsyncTask(ProfileActivity.this, ProfileActivity.this, Constants.URLBase + "" + Constants.REQUEST_UPDATEPROFILE, "POST", jsonObject.toString()).execute(webServiceDO);
        } catch (Exception e) {
            e.getMessage();
            Utils.hideProgress(dialog);
        }
    }

    public void callProfileWebServices() {
        try {
            progressDialog(this);
            WebServiceDO webServiceDO = new WebServiceDO();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userId", Constants.userDetails.userId);
            webServiceDO.result = Constants.SENT;
            webServiceDO.request = "PROFILEDATA";
            new AsyncTask(ProfileActivity.this, ProfileActivity.this, Constants.URLBase + "" + Constants.REQUEST_PROFILE, "POST", jsonObject.toString()).execute(webServiceDO);
        } catch (Exception e) {
            e.getMessage();
            Utils.hideProgress(dialog);
        }
    }

    @Override
    public void onTaskCompletes(WebServiceDO webServiceDO) {
        try {
            Utils.hideProgress(dialog);
            if (webServiceDO.result.equals(Constants.SUCCESS)) {
                if (webServiceDO.request.equals("PROFILE")) {
                    JSONObject jsonObject = new JSONObject(webServiceDO.responseContent);
                    if (jsonObject.getString("status").toUpperCase().equals("SUCCESS")) {
                        Utils.showalert(ProfileActivity.this, "Alert", "Profile Updated Successfully");
                    } else {
                        Utils.showalert(ProfileActivity.this, "Alert", jsonObject.getString("errorDescription"));
                    }

                } else if (webServiceDO.request.equals("PROFILEDATA")) {
                    JSONObject jsonObject = new JSONObject(webServiceDO.responseContent);

                    setFields(jsonObject);

                }

            } else {
                Utils.showalert(ProfileActivity.this, "Alert", webServiceDO.responseContent);
            }
        } catch (Exception e) {
            e.getMessage();
            Utils.hideProgress(dialog);
        }
    }

    public boolean validations() {
        boolean status = false;
        String alert = "";
        if (et_Email.getText().toString().trim().equals("")) {
            alert = "Email is required";
        } else if (et_BloodType.getText().toString().trim().equals("")) {
            alert = "Blood type is required";
        } else if (et_Age.getText().toString().trim().equals("")) {
            alert = "Age is required";
        } else if (et_Address.getText().toString().equals("")) {
            alert = "Address is required";
        }

        if (!alert.equals("")) {
            status = true;
            Utils.showalert(this, "Alert", alert);
        }

        return status;
    }

    public void setFields(JSONObject jsonObject) {
        try {
            if(jsonObject.getString("gender").toUpperCase().equals("MALE"))
            {
                rb_Male.setChecked(true);
            }
            else
                rb_Female.setChecked(true);
            et_Age.setText(jsonObject.getString("age"));
            et_Address.setText(jsonObject.getString("address"));

        } catch (Exception e) {

        }
    }
}
