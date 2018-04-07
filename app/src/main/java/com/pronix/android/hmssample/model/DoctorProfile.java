package com.pronix.android.hmssample.model;

import java.io.Serializable;

/**
 * Created by ravi on 4/6/2018.
 */

public class DoctorProfile implements Serializable{

    public String userId;
    public String hospitalName;
    public String specialization;
    public String exp;
    public String qualification;
    public String timings;
    public String consultationFee;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public String getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(String consultationFee) {
        this.consultationFee = consultationFee;
    }
}
