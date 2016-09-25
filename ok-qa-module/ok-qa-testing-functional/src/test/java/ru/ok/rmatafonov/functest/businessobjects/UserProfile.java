package ru.ok.rmatafonov.functest.businessobjects;

public class UserProfile {

    private String name;
    private String surname;
    private String dayOfBirth;
    private String monthOfBirth;
    private String yearOfBirth;
    private String gender;
    private String cityOfResidence;
    private String hometown;
    private String hometownFormatted;
    private String cityOfResidenceFormatted;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getMonthOfBirth() {
        return monthOfBirth;
    }

    public void setMonthOfBirth(String monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCityOfResidence() {
        return cityOfResidence;
    }

    public void setCityOfResidence(String cityOfResidence) {
        this.cityOfResidence = cityOfResidence;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getHometownFormatted() {
        return hometownFormatted;
    }

    public void setHometownFormatted(String hometown) {
        this.hometownFormatted = hometown;
    }

    public String getCityOfResidenceFormatted() {
        return cityOfResidenceFormatted;
    }

    public void setCityOfResidenceFormatted(String cityOfResidence) {
        this.cityOfResidenceFormatted = cityOfResidence;
    }
}
