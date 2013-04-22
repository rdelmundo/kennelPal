
<%@ page import="kennelpal.Reservation" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'reservation.label', default: 'Reservation')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-reservation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-reservation" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list reservation">
			
				<g:if test="${reservationInstance?.owner}">
				<li class="fieldcontain">
					<span id="owner-label" class="property-label"><g:message code="reservation.owner.label" default="Owner" /></span>
					
						<span class="property-value" aria-labelledby="owner-label"><g:link controller="owner" action="show" id="${reservationInstance?.owner?.id}">${reservationInstance?.owner?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${reservationInstance?.pet}">
				<li class="fieldcontain">
					<span id="pet-label" class="property-label"><g:message code="reservation.pet.label" default="Pet" /></span>
					
						<span class="property-value" aria-labelledby="pet-label"><g:link controller="pet" action="show" id="${reservationInstance?.pet?.id}">${reservationInstance?.pet?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${reservationInstance?.rate}">
				<li class="fieldcontain">
					<span id="rate-label" class="property-label"><g:message code="reservation.rate.label" default="Rate" /></span>
					
						<span class="property-value" aria-labelledby="rate-label"><g:link controller="rate" action="show" id="${reservationInstance?.rate?.id}">${reservationInstance?.rate?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${reservationInstance?.startDate}">
				<li class="fieldcontain">
					<span id="startDate-label" class="property-label"><g:message code="reservation.startDate.label" default="Start Date" /></span>
					
						<span class="property-value" aria-labelledby="startDate-label"><g:formatDate date="${reservationInstance?.startDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${reservationInstance?.endDate}">
				<li class="fieldcontain">
					<span id="endDate-label" class="property-label"><g:message code="reservation.endDate.label" default="End Date" /></span>
					
						<span class="property-value" aria-labelledby="endDate-label"><g:formatDate date="${reservationInstance?.endDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${reservationInstance?.checkedIn}">
				<li class="fieldcontain">
					<span id="checkedIn-label" class="property-label"><g:message code="reservation.checkedIn.label" default="Checked In" /></span>
					
						<span class="property-value" aria-labelledby="checkedIn-label"><g:formatBoolean boolean="${reservationInstance?.checkedIn}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${reservationInstance?.id}" />
					<g:link class="edit" action="edit" id="${reservationInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
