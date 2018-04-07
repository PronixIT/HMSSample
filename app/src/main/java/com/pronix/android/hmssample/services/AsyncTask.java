package com.pronix.android.hmssample.services;

import android.content.Context;

import com.pronix.android.hmssample.common.Constants;
import com.pronix.android.hmssample.model.WebServiceDO;


/**
 * Created by ravi on 1/11/2018.
 */

    public class AsyncTask extends android.os.AsyncTask<WebServiceDO, WebServiceDO, WebServiceDO> {

    OnTaskCompleted onTaskCompleted;
    String url;
    String requestType;
    String parameters;

    public AsyncTask(Context context, OnTaskCompleted onTaskCompleted, String url, String requestType, String parameters)
    {
        this.onTaskCompleted = onTaskCompleted;
        this.url = url;
        this.requestType = requestType;
        this.parameters = parameters;
    }



    @Override
    protected WebServiceDO doInBackground(WebServiceDO... webServiceDOS) {
        WebServiceDO webServiceDO = webServiceDOS[0];
        try {
            webServiceDO = WebService.callWebService(url, requestType, parameters, webServiceDOS[0]);
        }
        catch (Exception e)
        {
            e.getMessage();
            webServiceDO.result = Constants.EXCEPTION;
            webServiceDO.responseContent = "Error: " + e.getMessage();
        }

        return webServiceDO;
    }

    @Override
    protected void onPostExecute(WebServiceDO webServiceDO) {
        super.onPostExecute(webServiceDO);
        onTaskCompleted.onTaskCompletes(webServiceDO);

    }

}
