package dk.kea.stud.fourplayers.restaurantstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class BusinessDetails extends BaseEntity{
    private String companyName;
    private String cvr;
    private String firstName;
    private String lastName;
    private String mobilePhone;
    private String address;
    private String zipCode;

    public BusinessDetails() {
    }

    public BusinessDetails(String companyName, String cvr, String firstName, String lastName, String mobilePhone, String address, String zipCode) {
        this.companyName = companyName;
        this.cvr = cvr;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobilePhone = mobilePhone;
        this.address = address;
        this.zipCode = zipCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCvr() {
        return cvr;
    }

    public void setCvr(String cvr) {
        this.cvr = cvr;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "BusinessDetails{" +
                "companyName='" + companyName + '\'' +
                ", cvr='" + cvr + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
