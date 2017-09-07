<%--

  Response Message component.

  Response Message

--%><%
%><%@include file="/libs/foundation/global.jsp"%><%
%><%@page session="false" %><%
%><%
	// TODO add you code here
%>

<% 
    HttpSession session = request.getSession();
    log.info("********************* response attribute"+ session.getAttribute("ResponseMessage"));
    if(session.getAttribute("ResponseMessage")!=null && session.getAttribute("ResponseMessage").toString().equals("Success")){
        log.info("***************************response value "+  session.getAttribute("ResponseMessage").toString());
		%>
<h1>User Registered Successfully <a href="/system/sling/logout.html"> Login Here </a> </h1>
		<%
	}
else if(session.getAttribute("ResponseMessage")!=null && session.getAttribute("ResponseMessage").toString().equals("Fail") && session.getAttribute("ErrorMessage") == null){
		%>
<h1>User already exists <a href="/content/forms/af/Form1.html">New User Registartion </a><a href="/system/sling/logout.html"> Login Here </a></h1>
<%
} else if(session.getAttribute("ResponseMessage")!=null && session.getAttribute("ResponseMessage").toString().equals("Fail") && session.getAttribute("ErrorMessage") != null){
    %>
<h1><%=session.getAttribute("ErrorMessage").toString()%> <a href="/content/forms/af/Form1.html">New User Registartion </a><a href="/system/sling/logout.html"> Login Here </a></h1>
<%
} else {
    %>
<h1> Response from Registaration Form will be displayed here </h1>
<%
}
session.removeAttribute("ResponseMessage");
session.removeAttribute("ErrorMessage");
%>