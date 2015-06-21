package kitapp.hska.de.kitapp.domain;

import java.io.Serializable;

public class Address implements Serializable {


    /*
    <======================= ATTRIBUTES =======================>
     */

    private Long id;
    private String street;
    private String city;
    private Integer zipcode;
    private String phone;
    private String email;

    /*
    <======================= CONSTRUCTORS =======================>
     */

    public Address() {

    }

    public Address(String street, String city, Integer zipcode, String phone, String email) {
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
        this.phone = phone;
        this.email = email;
    }

    /*
    <======================= OVERRIDES =======================>
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (getId() != null ? !getId().equals(address.getId()) : address.getId() != null)
            return false;
        if (getStreet() != null ? !getStreet().equals(address.getStreet()) : address.getStreet() != null)
            return false;
        if (getCity() != null ? !getCity().equals(address.getCity()) : address.getCity() != null)
            return false;
        if (getZipcode() != null ? !getZipcode().equals(address.getZipcode()) : address.getZipcode() != null)
            return false;
        if (getPhone() != null ? !getPhone().equals(address.getPhone()) : address.getPhone() != null)
            return false;
        return !(getEmail() != null ? !getEmail().equals(address.getEmail()) : address.getEmail() != null);

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getStreet() != null ? getStreet().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getZipcode() != null ? getZipcode().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zipcode=" + zipcode +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    /*
    <======================= GETS & SETS =======================>
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
