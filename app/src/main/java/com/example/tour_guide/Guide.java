package com.example.tour_guide;

public class Guide {
    private String Guide_Name,Guide_Password,Guide_Email,Guide_PhoneNumber,Guide_TourPlace,Tour_Details;

    public Guide() {
    }

    public Guide(String guide_Name, String guide_Password, String guide_Email,String guide_PhoneNumber, String guide_TourPlace) {
        Guide_Name = guide_Name;
        Guide_Password = guide_Password;
        Guide_Email = guide_Email;
        Guide_PhoneNumber = guide_PhoneNumber;
        Guide_TourPlace = guide_TourPlace;
    }

    public Guide(String guide_Name, String guide_Password, String guide_Email,String guide_PhoneNumber, String guide_TourPlace, String tour_Details) {
        Guide_Name = guide_Name;
        Guide_Password = guide_Password;
        Guide_Email = guide_Email;
        Guide_PhoneNumber = guide_PhoneNumber;
        Guide_TourPlace = guide_TourPlace;
        Tour_Details = tour_Details;
    }
    public String getTour_Details() {
        return Tour_Details;
    }

    public void setTour_Details(String tour_Details) {
        Tour_Details = tour_Details;
    }

    public String getGuide_Email() {
        return Guide_Email;
    }

    public void setGuide_Email(String guide_Email) {
        Guide_Email = guide_Email;
    }

    public String getGuide_Name() {
        return Guide_Name;
    }

    public void setGuide_Name(String guide_Name) {
        Guide_Name = guide_Name;
    }

    public String getGuide_Password() {
        return Guide_Password;
    }

    public void setGuide_Password(String guide_Password) {
        Guide_Password = guide_Password;
    }

    public String getGuide_PhoneNumber() {
        return Guide_PhoneNumber;
    }

    public void setGuide_PhoneNumber(String guide_PhoneNumber) {
        Guide_PhoneNumber = guide_PhoneNumber;
    }

    public String getGuide_TourPlace() {
        return Guide_TourPlace;
    }

    public void setGuide_TourPlace(String guide_TourPlace) {
        Guide_TourPlace = guide_TourPlace;
    }
}
