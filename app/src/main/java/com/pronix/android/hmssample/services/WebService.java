package com.pronix.android.hmssample.services;







import com.pronix.android.hmssample.common.Constants;
import com.pronix.android.hmssample.model.WebServiceDO;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by ravi on 1/9/2018.
 */

public class WebService {

    public static WebServiceDO callWebService(String urlPath, String requestType, String parameter, WebServiceDO webServiceDO)
    {
        StringBuffer response = new StringBuffer();
        HttpURLConnection connection;

//        String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
        try {

            URL url = new URL(urlPath);
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(60000);
            connection.setConnectTimeout(60000);
            connection.setRequestMethod(requestType);
            connection.setRequestProperty("Content-Type", "application/json");
            if(requestType.equals("POST"))
            {
                connection.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                wr.writeBytes(parameter);
                wr.flush();
                wr.close();
            }
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) { //success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                webServiceDO.result = Constants.SUCCESS;
                webServiceDO.responseContent = response.toString();
                webServiceDO.responseCode = String.valueOf(responseCode);
            } else {
                webServiceDO.result = Constants.FAILED;
                webServiceDO.responseCode = String.valueOf(responseCode);
                System.out.println("POST request not worked");
            }
        }
        catch (IOException ex)
        {
            webServiceDO.result = Constants.EXCEPTION;
            webServiceDO.responseContent = ex.getMessage();
        }
        catch (IndexOutOfBoundsException e)
        {
            webServiceDO.result = Constants.EXCEPTION;
            webServiceDO.responseContent = "IndexOutOfBoundsException: " +e.getMessage();
        }

        catch (Exception e)
        {
            webServiceDO.result = Constants.EXCEPTION;
            webServiceDO.responseContent = e.getMessage();
        }

        return webServiceDO;

    }

}
