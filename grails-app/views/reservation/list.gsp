
<%@ page import="kennelpal.Reservation" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'reservation.label', default: 'Reservation')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-reservation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/user/home')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-reservation" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="reservation.owner.label" default="Owner" /></th>
					
						<th><g:message code="reservation.pet.label" default="Pet" /></th>
					
						<th><g:message code="reservation.rate.label" default="Rate" /></th>
					
						<g:sortableColumn property="startDate" title="${message(code: 'reservation.startDate.label', default: 'Start Date')}" />
					
						<g:sortableColumn property="endDate" title="${message(code: 'reservation.endDate.label', default: 'End Date')}" />
					
						<g:sortableColumn property="checkedIn" title="${message(code: 'reservation.checkedIn.label', default: 'Checked In')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${reservationInstanceList}" status="i" var="reservationInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${reservationInstance.id}">${fieldValue(bean: reservationInstance, field: "owner")}</g:link></td>
					
						<td>${fieldValue(bean: reservationInstance, field: "pet")}</td>
					
						<td>${fieldValue(bean: reservationInstance, field: "rate")}</td>
					
						<td><g:formatDate date="${reservationInstance.startDate}" /></td>
					
						<td><g:formatDate date="${reservationInstance.endDate}" /></td>
					
						<td><g:formatBoolean boolean="${reservationInstance.checkedIn}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${reservationInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
