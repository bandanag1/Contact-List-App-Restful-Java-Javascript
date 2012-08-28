package jersey.rest.client;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

import jersey.rest.bean.Address;
import jersey.rest.bean.Contact;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;

public class ContactClient {
	
	public static void main(String[] args) {
		Client c = Client.create();
		WebResource r = c.resource("http://localhost:8080/FLSContactListApp/rest/contacts");
		
		System.out.println("===== Get Bandana Banerjee =====");
		getOneContact(r, "Bandana Banerjee");
		
		System.out.println("===== Create bandana0 ====="); // Bandana: Commenting the below code as I am populating right now via form, but it's a useful method to be used in future		
		//postForm(r, "Bandana Banerjee0", "EDI-TIE B.V.0", "Software Developer0", "Antareslaan 220", "06876543210", "bandana.banerjee0@tieholding.com");		
		
		Contact cnt1 = new Contact("Bandana Banerjee1", "EDI-TIE B.V.1", "Software Developer1", "Antareslaan 221", "06876543211", "bandana.banerjee1@tieholding.com");
		
		System.out.println("===== Create Bandana Banerjee1 =====");
		putOneContact(r, cnt1);		
		
		Contact cnt2 = new Contact("Bandana Banerjee2", "EDI-TIE B.V.2", "Software Developer2", "Antareslaan 222", "06876543212", "bandana.banerjee2@tieholding.com");
			
		System.out.println("===== Create Bandana Banerjee2 =====");
		putOneContact(r, cnt2);
		
		System.out.println("===== All Contacts =====");
		getContacts(r);
		
		System.out.println("===== Delete bandana0 =====");
		deleteOneContact(r, "Bandana Banerjee0");
		
		System.out.println("===== All Contacts =====");
		getContacts(r);
	}
	
	public static void getContacts(WebResource r) {
		
		// 1, get response as plain text
		String jsonRes = r.accept(MediaType.APPLICATION_JSON).get(String.class);
		System.out.println(jsonRes);
		
		String xmlRes = r.accept(MediaType.APPLICATION_XML).get(String.class);
		System.out.println(xmlRes);
		
		// 2, get response and headers etc, wrapped in ClientResponse
		ClientResponse response = r.get(ClientResponse.class);
		System.out.println( response.getStatus() );
		System.out.println( response.getHeaders().get("Content-Type") );
		String entity = response.getEntity(String.class);
		System.out.println(entity);
		
		// 3, get JAXB response
		GenericType<List<Contact>> genericType = new GenericType<List<Contact>>() {};
		List<Contact> contacts = r.accept(MediaType.APPLICATION_XML).get(genericType);
		System.out.println("No. of Contacts: " + contacts.size());
		Contact contact = contacts.get(0);
		System.out.println(contact.getName() + ": " + contact.getCompanyName());
	}
	
	public static void getOneContact(WebResource r, String name) {
		GenericType<JAXBElement<Contact>> generic = new GenericType<JAXBElement<Contact>>() {};
		JAXBElement<Contact> jaxbContact = r.path(name).accept(MediaType.APPLICATION_XML).get(generic);
		Contact contact = jaxbContact.getValue();
		System.out.println(contact.getName() + ": " + contact.getCompanyName() + ": " + contact.getDesignation() + ": " + contact.getAddress() + ": " + contact.getTelephoneNo() + ": " + contact.getEmailId());
	}
	
	public static void postForm(WebResource r, String name, String companyName, String designation, String address, String telephoneNo, String emailId) {
		Form form = new Form();		
		form.add("name", name);
		form.add("companyName", companyName);
		form.add("designation", designation);
		form.add("address", address);
		form.add("telephoneNo", telephoneNo);
		form.add("emailId", emailId);
		ClientResponse response = r.type(MediaType.APPLICATION_FORM_URLENCODED)
								   .post(ClientResponse.class, form);
		System.out.println(response.getEntity(String.class));
	}
	
	public static void putOneContact(WebResource r, Contact c) {
		ClientResponse response = r.path(c.getName()).accept(MediaType.APPLICATION_XML)
								   .put(ClientResponse.class, c);
		System.out.println(response.getStatus());
	}
	
	public static void deleteOneContact(WebResource r, String name) {
		ClientResponse response = r.path(name).delete(ClientResponse.class);
		System.out.println(response.getStatus());
	}
}
