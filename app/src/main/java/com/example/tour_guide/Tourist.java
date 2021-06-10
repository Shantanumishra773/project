package com.example.tour_guide;

public class Tourist {
    String Tourist_Name,Tourist_Email,Tourist_Password,Tourist_PhoneNumber;

    public Tourist() {
    }

    public Tourist(String tourist_Name, String tourist_Email, String tourist_Password) {
        Tourist_Name = tourist_Name;
        Tourist_Email = tourist_Email;
        Tourist_Password = tourist_Password;

    }

    public String getTourist_Name() {
        return Tourist_Name;
    }

    public void setTourist_Name(String tourist_Name) {
        Tourist_Name = tourist_Name;
    }

    public String getTourist_Email() {
        return Tourist_Email;
    }

    public void setTourist_Email(String tourist_Email) {
        Tourist_Email = tourist_Email;
    }

    public String getTourist_Password() {
        return Tourist_Password;
    }

    public void setTourist_Password(String tourist_Password) {
        Tourist_Password = tourist_Password;
    }

    public String getTourist_PhoneNumber() {
        return Tourist_PhoneNumber;
    }

    public void setTourist_PhoneNumber(String tourist_PhoneNumber) {
        Tourist_PhoneNumber = tourist_PhoneNumber;
    }
}
