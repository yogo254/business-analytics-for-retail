package con.yogo.main.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class LocationGeo{
@Id
private String id;
private int zipCode;
private double latitude;
private double longitude;
private String city;
private String state;

  public LocationGeo() {
  }

  public LocationGeo(int zipCode, double latitude, double longitude, String city, String state) {
    this.zipCode = zipCode;
    this.latitude = latitude;
    this.longitude = longitude;
    this.city = city;
    this.state = state;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getZipCode() {
    return zipCode;
  }

  public void setZipCode(int zipCode) {
    this.zipCode = zipCode;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }
}
