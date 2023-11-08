<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<!DOCTYPE html>
	<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${product.name}</title>
	</head>


	<body>

		<!----start-Header---->
		<jsp:include page="include/homeHeader.jsp"></jsp:include>
		<!----End-Header---->

		<jsp:include page="include/detailspContent.jsp"></jsp:include>

		<!----start-Footder---->
		<jsp:include page="include/homeFooter.jsp"></jsp:include>
		<!----End-Footder---->
	</body>


	</html>