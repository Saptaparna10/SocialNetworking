<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%@include file="headertag.xhtml" %>
<script>
	  $(function(){
	  		$("#frm\\:dob").datepicker({changeMonth:true,changeYear:true});
	  	});	
  </script>
</head>
<body>
<f:view>
	<h:form id="frm">
		<h:inputText id="dob" value="#{registrationBean.registration.dob}">
			<f:convertDateTime pattern="MM/dd/yyyy"/>
		</h:inputText>
		<h:commandButton value="Click Me"/>
		
		<h:outputText value="#{registrationBean.registration.dob}">
			<f:convertDateTime dateStyle="full"/>
		</h:outputText>
		
		<script>$(function(){$("#dialog").dialog(); $("a").button();});</script>
		<div id="dialog" title="Success" style="text-align: center">
			<p style="color:green;font-weight: bold">You are successfully registered...</p>
			<a href="login.xhtml">Login Now</a>
		</div>
	</h:form>
</f:view>
</body>
</html>