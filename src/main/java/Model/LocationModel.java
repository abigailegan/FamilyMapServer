package Model;

public class LocationModel {
    /**
     * String country
     */
    String country;

    /**
     * String city
     */
    String city;

    /**
     * float latitude
     */
    float latitude;

    /**
     * float longitude
     */
    float longitude;

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return this.country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    /**
     * Constructor for LocationModel
     * @param country String country name
     * @param city String city name
     * @param latitude float latitude
     * @param longitude float longitude
     */
    public LocationModel(String country, String city, float latitude, float longitude) {
        this.country = country;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
