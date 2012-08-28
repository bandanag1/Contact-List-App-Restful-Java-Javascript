/**
 * 
 */
package jersey.rest.storage;

//import java.util.ArrayList;
//import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//import jersey.rest.bean.Address;
import jersey.rest.bean.Contact;

/**
 * @author tie_Bandana
 *
 */
public class ContactStore {
	
	private static Map<String, Contact> store;
	private static ContactStore instance = null;
	
	private ContactStore() {
		store = new HashMap<String, Contact>();
		initOneContact();
	}
	
	public static Map<String, Contact> getStore() {
		if(instance == null) {
			instance = new ContactStore();
		}
		return store;
	}
	
	private static void initOneContact() {		
		Contact contact = new Contact("Bandana Banerjee", "TIE Kinetix B.V.", "Software Developer", "Antareslaan 22", "06655443322", "bandana.banerjee@tieholding.com");
		store.put(contact.getName(), contact);
	}
}
