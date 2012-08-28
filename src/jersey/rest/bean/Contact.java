/**
 * 
 */
package jersey.rest.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement; //JAX-RS supports the use of JAXB (Java API for XML Binding) to bind a JavaBean to XML or JSON and vise versa
import javax.xml.bind.annotation.XmlElement;

/**
 * @author tie_Bandana
 * @XmlRootElement is a JAXB API for XML binding
 * @XmlRootElement here binds this "Contact" Java Bean to a XML/ JSON
 *
 */
@XmlRootElement
public class Contact {	
	private String name;
	private String companyName;
	private String designation;
	private String address;
	private String telephoneNo;
	private String emailId;
	
	public Contact(){}
	
	public Contact(String name, String companyName, String designation, String address, String telephoneNo, String emailId) {
		this.name = name;
		this.companyName = companyName;
		this.designation = designation;
		this.address = address;
		this.telephoneNo = telephoneNo;
		this.emailId = emailId;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	/**
	 * @return the id
	 */
	public String getDesignation() {
		return designation;
	}
	/**
	 * @param id the id to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the telephoneNo
	 */
	public String getTelephoneNo() {
		return telephoneNo;
	}

	/**
	 * @param telephoneNo the telephoneNumber to set
	 */
	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}		
}
