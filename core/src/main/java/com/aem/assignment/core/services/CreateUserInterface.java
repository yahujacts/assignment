package com.aem.assignment.core.services;

import org.apache.sling.api.SlingHttpServletRequest;

public interface CreateUserInterface {

	boolean injestFormData(SlingHttpServletRequest request) throws Exception;

}
