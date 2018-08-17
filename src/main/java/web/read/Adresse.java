package web.read;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 

@Entity

@Table(name = "Adresse")

public class Adresse extends BaseEntity {

 

  private String street;

  private String city;

  private String province;

  private String country;

  private String postcode;

 
 
  /**

   * @return the street

   */

  public String getStreet() {

    return street;

  }

  /**

   * @param street the street to set

   */

  public Adresse setStreet(String street) {

    this.street = street;

    return this;

  }

  /**

   * @return the city

   */

  public String getCity() {

    return city;

  }

  /**

   * @param city the city to set

   */

  public Adresse setCity(String city) {

    this.city = city;

    return this;

  }

  /**

   * @return the province

   */

  public String getProvince() {

    return province;

  }

  /**

   * @param province the province to set

   */

  public Adresse setProvince(String province) {

    this.province = province;

    return this;

  }

  /**

   * @return the country

   */

  public String getCountry() {

    return country;

  }

  /**

   * @param country the country to set

   */

  public Adresse setCountry(String country) {

    this.country = country;

    return this;

  }

  /**

   * @return the postcode

   */

  public String getPostcode() {

    return postcode;

  }

  /**

   * @param postcode the postcode to set

   */

  public Adresse setPostcode(String postcode) {

    this.postcode = postcode;

    return this;

  }

}