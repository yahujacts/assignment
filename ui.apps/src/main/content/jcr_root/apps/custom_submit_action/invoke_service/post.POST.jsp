<%@include file="/libs/fd/af/components/guidesglobal.jsp" %>
<%@include file="/libs/foundation/global.jsp"%>
<%@page import="com.day.cq.wcm.foundation.forms.FormsHelper,
             org.apache.sling.api.resource.ResourceUtil,
             org.apache.sling.api.resource.ValueMap" %>
<%@taglib prefix="sling"
                uri="http://sling.apache.org/taglibs/sling/1.0" %>
<%@taglib prefix="cq"
                uri="http://www.day.com/taglibs/cq/1.0"
%>
<cq:defineObjects/>
<sling:defineObjects/>
<%
    log.error("<------------------------------- Calling post.POST.jsp ----------------------------------------->");

    String First = request.getParameter("First");
    String Last = request.getParameter("Last");
    String Email = request.getParameter("Email");
    String Password = request.getParameter("Password");

    log.error("@@@@@@@@@@@@@@@"+Password);

    com.aem.cwr.framework.core.wcmhelper.CreateUserInterface hf = sling.getService(com.aem.cwr.framework.core.wcmhelper.CreateUserInterface.class);
	log.error(hf.toString());
    String w = hf.injestFormData(First,Last,Email,Password);
    log.error(w);

%>
