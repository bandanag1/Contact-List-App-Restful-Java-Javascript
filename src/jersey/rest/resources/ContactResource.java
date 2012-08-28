package jersey.rest.resources;

import java.util.ArrayList;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import jersey.rest.bean.Address;
import jersey.rest.bean.Contact;
import jersey.rest.storage.ContactStore;
import jersey.rest.util.ParamUtil;

import com.sun.jersey.api.NotFoundException;

public class ContactResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	String contact;
	
	public ContactResource(UriInfo uriInfo, Request request, String contact) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.contact = contact;		
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Contact getContact() {
		Contact cont = ContactStore.getStore().get(contact);
		if (cont == null)
				throw new NotFoundException("No such contact");
		return cont;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML) // This method accepts the request content type of APPLICATION/ XML
	public Response putContact(JAXBElement<Contact> jaxbContact) {  // JAXB binds this input XML to the Contact object
		Contact cntct = jaxbContact.getValue();
		return putAndGetResponse(cntct);
	}
	
	@PUT
	public Response putContact(@Context HttpHeaders herders, byte[] in) {
		Map<String,String> params = ParamUtil.parse(new String(in));
		Contact c = new Contact(params.get("name"), params.get("companyName"), params.get("designation"), params.get("address"), params.get("telephoneNo"), params.get("emailId"));				
		return putAndGetResponse(c);
	}
	
	private Response putAndGetResponse(Contact c) {
		Response res;
		if(ContactStore.getStore().containsKey(c.getName())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		ContactStore.getStore().put(c.getName(), c);
		return res;
	}
	
	@DELETE
	public void deleteContact() {
		Contact c = ContactStore.getStore().remove(contact);
		if(c==null)
			throw new NotFoundException("No such Contact.");
	}

}
