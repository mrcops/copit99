/**
 * Created by smoligor on 29.03.2017.
 */
public class AddressProfile {
    String firstName;
    String lastName;
    String country;
    String StreetAddress;
    String aptSuitUnit;
    String city;
    String stateAndRegion;
    String zipPostalCode;
    String telephone;

    public AddressProfile() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreetAddress() {
        return StreetAddress;
    }

    public void setStreetAdress(String streetAddress) {
        StreetAddress = streetAddress;
    }

    public String getAptSuitUnit() {
        return aptSuitUnit;
    }

    public void setAptSuitUnit(String aptSuitUnit) {
        this.aptSuitUnit = aptSuitUnit;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateAndRegion() {
        return stateAndRegion;
    }

    public void setStateAndRegion(String stateAndRegion) {
        this.stateAndRegion = stateAndRegion;
    }

    public String getZipPostalCode() {
        return zipPostalCode;
    }

    public void setZipPostalCode(String zipPostalCode) {
        this.zipPostalCode = zipPostalCode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public void setStreetAddress(String streetAddress) {
        StreetAddress = streetAddress;
    }
}
