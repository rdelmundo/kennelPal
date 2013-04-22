
<%@ page import="kennelpal.Pet" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'pet.label', default: 'Pet')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-pet" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-pet" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list pet">
			
				<g:if test="${petInstance?.owner}">
				<li class="fieldcontain">
					<span id="owner-label" class="property-label"><g:message code="pet.owner.label" default="Owner" /></span>
					
						<span class="property-value" aria-labelledby="owner-label"><g:link controller="owner" action="show" id="${petInstance?.owner?.id}">${petInstance?.owner?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${petInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="pet.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${petInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${petInstance?.breed}">
				<li class="fieldcontain">
					<span id="breed-label" class="property-label"><g:message code="pet.breed.label" default="Breed" /></span>
					
						<span class="property-value" aria-labelledby="breed-label"><g:fieldValue bean="${petInstance}" field="breed"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${petInstance?.notes}">
				<li class="fieldcontain">
					<span id="notes-label" class="property-label"><g:message code="pet.notes.label" default="Notes" /></span>
					
						<span class="property-value" aria-labelledby="notes-label"><g:fieldValue bean="${petInstance}" field="notes"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${petInstance?.dateOfBirth}">
				<li class="fieldcontain">
					<span id="dateOfBirth-label" class="property-label"><g:message code="pet.dateOfBirth.label" default="Date Of Birth" /></span>
					
						<span class="property-value" aria-labelledby="dateOfBirth-label"><g:formatDate date="${petInstance?.dateOfBirth}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${petInstance?.reservations}">
				<li class="fieldcontain">
					<span id="reservations-label" class="property-label"><g:message code="pet.reservations.label" default="Reservations" /></span>
					
						<g:each in="${petInstance.reservations}" var="r">
						<span class="property-value" aria-labelledby="reservations-label"><g:link controller="reservation" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${petInstance?.id}" />
					<g:link class="edit" action="edit" id="${petInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
