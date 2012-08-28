package jersey.rest.resources;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

//import jersey.rest.bean.Address;
import jersey.rest.bean.Contact;
import jersey.rest.storage.ContactStore;

@Path("/contacts")
public class AllContactsResource {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Contact> getContacts() {		
		List<Contact> contacts = new ArrayList<Contact>();
		contacts.addAll(ContactStore.getStore().values());
		return contacts;
	}
	
	@GET
	@Path("count")  // Resources are implemented by any POJO (e.g. Contact and Address POJO) with this (@Path) annotation to compose its identifier
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count = ContactStore.getStore().size();
		return String.valueOf(count);
	}
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newContact(	@FormParam("name") String name,	@FormParam("companyName") String companyName, @FormParam("designation") String designation, @FormParam("address") String address, @FormParam("telephoneNo") String telephoneNo, @FormParam("emailId") String emailId, @Context HttpServletResponse servletResponse ) throws IOException {
		Contact contact = new Contact(name, companyName, designation, address, telephoneNo, emailId);
		ContactStore.getStore().put(name, contact);
		
		URI uri = uriInfo.getAbsolutePathBuilder().path(name).build();
		
		//@Response.created(uri).build(): Builds a new URI for the newly created contact as /contacts/{id} 
		// and set the response code as 201/created. You can access the new contact using http://localhost:8080/Jersey/rest/contacts/<id>.
		Response.created(uri).build();
		
		//servletResponse.sendRedirect("../pages/new_contact.html");
		servletResponse.sendRedirect("../pages/contact_list.html");
	}
	
	@Path("{contact}")
	public ContactResource getContact(@PathParam("contact") String contact) {
		return new ContactResource(uriInfo, request, contact);
	}

}
