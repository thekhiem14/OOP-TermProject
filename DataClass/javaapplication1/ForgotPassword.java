package javaapplication1;
import java.util.*;
import java.sql.*;
public class ForgotPassword {
    private String email, otp;
    private Timestamp expiryDate;

    public createOTP(){

    }



    public ForgotPassword(String email, String otp) {
        this.email = email;
        this.otp = otp;
        this.expiryDate = new Timestamp(System.currentTimeMillis() + 180000);
    }

    public void Expired(){
        this.otp = null;
    }

    public String getEmail() {
        return email;
    }
}
