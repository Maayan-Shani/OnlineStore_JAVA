package OnlineStore;

public class Address {
    private String country;
    private String city;
    private String street;
    private int buildingNumber;

    public Address(String country, String city, String street, int buildingNumber) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
    }
    public Address(Address other){
        this.country = other.country;
        this.city = other.city;
        this.street = other.street;
        this.buildingNumber = other.buildingNumber;
    }

    public String getCountry() {
        return country;
    }

    public boolean setCountry(String country) {
        this.country = country;
        return true;
    }

    public String getCity() {
        return city;
    }

    public boolean setCity(String city) {
        this.city = city;
        return true;
    }

    public String getStreet() {
        return street;
    }

    public boolean setStreet(String street) {
        this.street = street;
        return true;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public boolean setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
        return true;
    }

    @Override
    public String toString() {
        return  "    Country: " + country + "\n" +
                "    City: " + city + "\n" +
                "    Street: " + street + "\n" +
                "    Building Number: " + buildingNumber;
    }
}
