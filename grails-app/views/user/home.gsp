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
		<title>KennelPal | Home</title>
	</head>
	<body>
			<p>KennelPal</p>
			<p>Number of reservations: ${reservations.size()}</p>

			<p>Admin links:</p>
			<g:link action="list" controller="user">Users</g:link>
			<br/>
			<g:link action="list" controller="rate">Rates</g:link>
			<br/>

			<p>User links:</p>
			<g:link action="list" controller="reservation">Reservations</g:link>
			<br/>
			<g:link action="list" controller="payment">Payments</g:link>
			<br/>
			<g:link action="list" controller="owner">Owners</g:link>
			<br/>
			<g:link action="list" controller="contact">Contacts</g:link>
			<br/>

			<p>Today's Reservations:
			<g:each in="${reservations}" status = "" var="reservation">
				<g:link action="show" controller="reservation" id="${reservation.pet.id}">${fieldValue(bean:reservation,field:"pet.name")} ${fieldValue(bean:reservation,field:"pet.owner")}
				</g:link>	
			</g:each>
			</p>

	</body>
</html>