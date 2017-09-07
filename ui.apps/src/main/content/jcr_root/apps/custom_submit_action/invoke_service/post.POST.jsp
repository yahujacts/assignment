<%@include file="/libs/fd/af/components/guidesglobal.jsp"%>
<%@include file="/libs/foundation/global.jsp"%>
<%@page
	import="com.day.cq.wcm.foundation.forms.FormsHelper,
             org.apache.sling.api.resource.ResourceUtil,
             org.apache.sling.api.resource.ValueMap"%>
<%@taglib prefix="sling" uri="http://sling.apache.org/taglibs/sling/1.0"%>
<%@taglib prefix="cq" uri="http://www.day.com/taglibs/cq/1.0"%>
<cq:defineObjects />
<sling:defineObjects />
<%
	com.aem.assignment.core.services.CreateUserInterface userCreator = sling.getService(com.aem.assignment.core.services.CreateUserInterface.class);
	HttpSession session = request.getSession();
	boolean isUserCreated = false;
	try {
		isUserCreated = userCreator.injestFormData(slingRequest);
		if (isUserCreated) {
			session.setAttribute("ResponseMessage", "Success");
		} else {
			session.setAttribute("ResponseMessage", "Fail");
		}
	} catch (Exception exception) {
		session.setAttribute("ErrorMessage", "System Error Occurred");
		session.setAttribute("ResponseMessage", "Fail");
	}
%>
