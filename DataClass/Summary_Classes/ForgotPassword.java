package Summary_Classes;
import java.sql.*;
public class ForgotPassword {
    private String email, otp;
    private Timestamp expiryDate;

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

    public String getOtp(){
        return otp;
    }

    public Timestamp getexpiryDate(){
        return expiryDate;
    }
}
