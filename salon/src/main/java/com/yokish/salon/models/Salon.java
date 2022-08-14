package com.yokish.salon.models;

public class Salon  {
    private  String idSalon;
    private  String salonName;
    private  String salonAddress;
    private  String salonTelephone;

    public Salon(String idSalon, String salonName, String salonAddress, String salonTelephone) {
        this.idSalon = idSalon;
        this.salonName = salonName;
        this.salonAddress = salonAddress;
        this.salonTelephone = salonTelephone;
    }

    public Salon() {

    }

    public String getIdSalon() {
        return idSalon;
    }

    public void setIdSalon(String idSalon) {
        this.idSalon = idSalon;
    }

    public String getSalonName() {
        return salonName;
    }

    public void setSalonName(String salonName) {
        this.salonName = salonName;
    }

    public String getSalonAddress() {
        return salonAddress;
    }

    public void setSalonAddress(String salonAddress) {
        this.salonAddress = salonAddress;
    }

    public String getSalonTelephone() {
        return salonTelephone;
    }

    public void setSalonTelephone(String salonTelephone) {
        this.salonTelephone = salonTelephone;
    }

    @Override
    public String toString() {
        return "Salon{" +
                "idSalon='" + idSalon + '\'' +
                ", salonName='" + salonName + '\'' +
                ", salonAddress='" + salonAddress + '\'' +
                ", salonTelephone='" + salonTelephone + '\'' +
                '}';
    }
}
