package com.pronix.android.hmssample.model;

import java.io.Serializable;

/**
 * Created by ravi on 4/4/2018.
 */

public class Doctors implements Serializable{

    private String doctorName;
    public String doctorMobile;
    private DoctorProfile doctorProfile;

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorMobile() {
        return doctorMobile;
    }

    public void setDoctorMobile(String doctorMobile) {
        this.doctorMobile = doctorMobile;
    }

    public DoctorProfile getDoctorProfile() {
        return doctorProfile;
    }

    public void setDoctorProfile(DoctorProfile doctorProfile) {
        this.doctorProfile = doctorProfile;
    }
}
