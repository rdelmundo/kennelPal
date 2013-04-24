<%@ page import="kennelpal.User" %>
<%@ page import="kennelpal.Reservation" %>
<%@ page import="kennelpal.Pet" %>


<%-- views/user/home.gsp --%>

<!-- Top KennelPal logo -->
<!-- Top Middle Admin Links -->
<!-- Left SideBar User Links -->
<!-- Center Top Number of Vacancies for today -->
<!-- Center bottom List of reservations for the day-->
<!-- Right SideBar Calendar View -->

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="kpMain">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<title>KennelPal | Home</title>
	</head>
	<body>
			<p>Number of reservations: ${reservations.size()}</p>

			<g:link action="list" controller="reservation">Reservations</g:link>
			<br/>
			<g:link action="list" controller="payment">Payments</g:link>
			<br/>
			<g:link action="list" controller="owner">Owners</g:link>
			<br/>
			<g:link action="list" controller="pet">Pets</g:link>
			<br/>
			<g:link action="list" controller="contact">Contacts</g:link>
			<br/>

			<p>Today's Reservations:
				<br/>
			<g:each in="${reservations}" status = "" var="reservation">
				<g:link action="show" controller="reservation" id="${reservation.id}">${fieldValue(bean:reservation,field:"pet.name")} ${fieldValue(bean:reservation,field:"pet.owner")}
				</g:link>
				<br/>	
			</g:each>
			</p>

	</body>
</html>