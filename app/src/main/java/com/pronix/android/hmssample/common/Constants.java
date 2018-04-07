package com.pronix.android.hmssample.common;


import com.pronix.android.hmssample.model.UserDetails;

/**
 * Created by ravi on 1/2/2018.
 */

public class Constants {


    public static int DATABASE_VERSION = 1;
    public static UserDetails userDetails;


    //        public static String URLBase = "http://env-0687861.cloud.cms500.com/apssaat-audit/";
//    public static String URLBase = "http://192.168.0.104:8080//hms/doctor/";
    public static String URLBase = "http://192.168.0.133:1234/hms/patient/";


    //RequestMethods
    public static String REQUEST_REGISTER = "register";
    public static String REQUEST_LOGIN = "login";
    public static String REQUEST_PROFILE = "viewProfile";
    public static String REQUEST_UPDATEPROFILE = "updateProfile";
    public static String REQUEST_DOCTORSLOOKUP = "doctorLookup/";
    public static String REQUEST_APPOINTMENT = "bookAppointment";


    public static int PANCHAYATID = 0;
    public static int MANDALID = 0;
    public static int DISTRICTID = 0;
    public static String PANCHAYATNAME = "";


    //Service
    public static String SUCCESS = "SUCCESS";
    public static String FAILED = "FAILED";
    public static String EXCEPTION = "EXCEPTION";
    public static String SENT = "SENT";
}
