/**
 * 
 */
package jersey.rest.bean;

import javax.xml.bind.annotation.XmlRootElement; //JAX-RS supports the use of JAXB (Java API for XML Binding) to bind a JavaBean to XML or JSON and vise versa

/**
 * @author tie_Bandana
 * @XmlRootElement is a JAXB API for XML binding
 * @XmlRootElement here binds this "Address" Java Bean to a XML/ JSON
 *
 */
@XmlRootElement
public class Address {
	
	private String city;
	private String street;
	
	public Address() {}
	
	/**
	 * @param city
	 * @param street
	 */
	public Address(String city, String street) {
		this.city = city;
		this.street = street;
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
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

}
