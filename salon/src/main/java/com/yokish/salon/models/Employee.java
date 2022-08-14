package com.yokish.salon.models;

public class Employee {
    private String idEmployee;
    private String nameEmployee;
    private String surnameEmployee;
    private String patronymicEmployee;
    private String addressEmployee;
    private String telephoneEmployee;
    private String positionEmployee;

    public Employee(String idEmployee, String nameEmployee, String surnameEmployee, String patronymicEmployee,
                    String addressEmployee, String telephoneEmployee, String positionEmployee) {
        this.idEmployee = idEmployee;
        this.nameEmployee = nameEmployee;
        this.surnameEmployee = surnameEmployee;
        this.patronymicEmployee = patronymicEmployee;
        this.addressEmployee = addressEmployee;
        this.telephoneEmployee = telephoneEmployee;
        this.positionEmployee = positionEmployee;
    }

    public Employee() {

    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getSurnameEmployee() {
        return surnameEmployee;
    }

    public void setSurnameEmployee(String surnameEmployee) {
        this.surnameEmployee = surnameEmployee;
    }

    public String getPatronymicEmployee() {
        return patronymicEmployee;
    }

    public void setPatronymicEmployee(String patronymicEmployee) {
        this.patronymicEmployee = patronymicEmployee;
    }

    public String getAddressEmployee() {
        return addressEmployee;
    }

    public void setAddressEmployee(String addressEmployee) {
        this.addressEmployee = addressEmployee;
    }

    public String getTelephoneEmployee() {
        return telephoneEmployee;
    }

    public void setTelephoneEmployee(String telephoneEmployee) {
        this.telephoneEmployee = telephoneEmployee;
    }

    public String getPositionEmployee() {
        return positionEmployee;
    }

    public void setPositionEmployee(String positionEmployee) {
        this.positionEmployee = positionEmployee;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "idEmployee='" + idEmployee + '\'' +
                ", nameEmployee='" + nameEmployee + '\'' +
                ", surnameEmployee='" + surnameEmployee + '\'' +
                ", patronymicEmployee='" + patronymicEmployee + '\'' +
                ", addressEmployee='" + addressEmployee + '\'' +
                ", telephoneEmployee='" + telephoneEmployee + '\'' +
                ", positionEmployee='" + positionEmployee + '\'' +
                '}';
    }
}

