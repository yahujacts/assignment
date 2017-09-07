package com.aem.assignment.core.services.impl;

import com.aem.assignment.core.services.CreateUserInterface;

import java.security.Principal;
import javax.jcr.PropertyType;
import javax.jcr.Session;
import javax.jcr.Value;
import javax.jcr.ValueFactory;

import org.apache.felix.scr.annotations.Component;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.jackrabbit.api.security.user.Authorizable;
import org.apache.jackrabbit.api.security.user.Group;
import org.apache.jackrabbit.api.security.user.User;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@Service
public class CreateUserImpl implements CreateUserInterface {

	@Reference
	ResourceResolverFactory resolverFactory;
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean injestFormData(SlingHttpServletRequest request) throws Exception {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");
	    String username = request.getParameter("userName");
	    String dateOfBirth = request.getParameter("dateOfBirth");
	    String occupation = request.getParameter("occupation");
	    String address1 = request.getParameter("address1");
	    String address2 = request.getParameter("address2");
	    String address3 = request.getParameter("address3");
	    String zipcode = request.getParameter("zipcode");
	    String mobile = request.getParameter("mobile");
	    
		log.info("Data posted from an AEM adaptive form - First Name is: " + firstName + " Last Name is : " + lastName
				+ " Email is : " + email + " Password is " + password);
		
		ResourceResolver adminResolver = null;
		Session adminSession = null;
		try {
			adminResolver = resolverFactory.getAdministrativeResourceResolver(null);
			adminSession = adminResolver.adaptTo(Session.class);
			log.debug("AdminResolver: " + adminResolver + " AdminSession: " + adminSession);
			final UserManager userManager = adminResolver.adaptTo(UserManager.class);
			log.debug("UserManager: " + userManager);

			Authorizable authorizable = userManager.getAuthorizable(username);
			if (authorizable == null) {
				User user = userManager.createUser(username, password);
				ValueFactory valueFactory = adminSession.getValueFactory();
				
				Value value = valueFactory.createValue(lastName, PropertyType.STRING);
				user.setProperty("./profile/familyName", value);

				value = valueFactory.createValue(firstName, PropertyType.STRING);
				user.setProperty("./profile/givenName", value);

				value = valueFactory.createValue(email, PropertyType.STRING);
				user.setProperty("./profile/email", value);
				
				value = valueFactory.createValue(dateOfBirth, PropertyType.STRING);
				user.setProperty("./profile/dateOfBirth", value);
				
				value = valueFactory.createValue(occupation, PropertyType.STRING);
				user.setProperty("./profile/occupation", value);
				
				value = valueFactory.createValue(address1, PropertyType.STRING);
				user.setProperty("./profile/address1", value);
				
				value = valueFactory.createValue(address2, PropertyType.STRING);
				user.setProperty("./profile/address2", value);

				value = valueFactory.createValue(address3, PropertyType.STRING);
				user.setProperty("./profile/address3", value);

				value = valueFactory.createValue(zipcode, PropertyType.STRING);
				user.setProperty("./profile/zipcode", value);
				
				value = valueFactory.createValue(mobile, PropertyType.STRING);
				user.setProperty("./profile/mobile", value);

				Group group = (Group) (userManager.getAuthorizable("administrators"));
				group.addMember(user);

				adminSession.save();
				return true;

			} else {
				log.error("User already exist..");
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		
		return false;

	}
}