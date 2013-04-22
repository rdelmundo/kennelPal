
<%@ page import="kennelpal.Owner" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'owner.label', default: 'Owner')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-owner" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-owner" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list owner">
			
				<g:if test="${ownerInstance?.accountNumber}">
				<li class="fieldcontain">
					<span id="accountNumber-label" class="property-label"><g:message code="owner.accountNumber.label" default="Account Number" /></span>
					
						<span class="property-value" aria-labelledby="accountNumber-label"><g:fieldValue bean="${ownerInstance}" field="accountNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ownerInstance?.accountBalance}">
				<li class="fieldcontain">
					<span id="accountBalance-label" class="property-label"><g:message code="owner.accountBalance.label" default="Account Balance" /></span>
					
						<span class="property-value" aria-labelledby="accountBalance-label"><g:fieldValue bean="${ownerInstance}" field="accountBalance"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ownerInstance?.firstName}">
				<li class="fieldcontain">
					<span id="firstName-label" class="property-label"><g:message code="owner.firstName.label" default="First Name" /></span>
					
						<span class="property-value" aria-labelledby="firstName-label"><g:fieldValue bean="${ownerInstance}" field="firstName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ownerInstance?.lastName}">
				<li class="fieldcontain">
					<span id="lastName-label" class="property-label"><g:message code="owner.lastName.label" default="Last Name" /></span>
					
						<span class="property-value" aria-labelledby="lastName-label"><g:fieldValue bean="${ownerInstance}" field="lastName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ownerInstance?.address}">
				<li class="fieldcontain">
					<span id="address-label" class="property-label"><g:message code="owner.address.label" default="Address" /></span>
					
						<span class="property-value" aria-labelledby="address-label"><g:fieldValue bean="${ownerInstance}" field="address"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ownerInstance?.city}">
				<li class="fieldcontain">
					<span id="city-label" class="property-label"><g:message code="owner.city.label" default="City" /></span>
					
						<span class="property-value" aria-labelledby="city-label"><g:fieldValue bean="${ownerInstance}" field="city"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ownerInstance?.state}">
				<li class="fieldcontain">
					<span id="state-label" class="property-label"><g:message code="owner.state.label" default="State" /></span>
					
						<span class="property-value" aria-labelledby="state-label"><g:fieldValue bean="${ownerInstance}" field="state"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ownerInstance?.zipcode}">
				<li class="fieldcontain">
					<span id="zipcode-label" class="property-label"><g:message code="owner.zipcode.label" default="Zipcode" /></span>
					
						<span class="property-value" aria-labelledby="zipcode-label"><g:fieldValue bean="${ownerInstance}" field="zipcode"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ownerInstance?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="owner.email.label" default="Email" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${ownerInstance}" field="email"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ownerInstance?.primaryPhone}">
				<li class="fieldcontain">
					<span id="primaryPhone-label" class="property-label"><g:message code="owner.primaryPhone.label" default="Primary Phone" /></span>
					
						<span class="property-value" aria-labelledby="primaryPhone-label"><g:fieldValue bean="${ownerInstance}" field="primaryPhone"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ownerInstance?.secondaryPhone}">
				<li class="fieldcontain">
					<span id="secondaryPhone-label" class="property-label"><g:message code="owner.secondaryPhone.label" default="Secondary Phone" /></span>
					
						<span class="property-value" aria-labelledby="secondaryPhone-label"><g:fieldValue bean="${ownerInstance}" field="secondaryPhone"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ownerInstance?.notes}">
				<li class="fieldcontain">
					<span id="notes-label" class="property-label"><g:message code="owner.notes.label" default="Notes" /></span>
					
						<span class="property-value" aria-labelledby="notes-label"><g:fieldValue bean="${ownerInstance}" field="notes"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ownerInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="owner.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${ownerInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${ownerInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="owner.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${ownerInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${ownerInstance?.contacts}">
				<li class="fieldcontain">
					<span id="contacts-label" class="property-label"><g:message code="owner.contacts.label" default="Contacts" /></span>
					
						<g:each in="${ownerInstance.contacts}" var="c">
						<span class="property-value" aria-labelledby="contacts-label"><g:link controller="contact" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${ownerInstance?.payments}">
				<li class="fieldcontain">
					<span id="payments-label" class="property-label"><g:message code="owner.payments.label" default="Payments" /></span>
					
						<g:each in="${ownerInstance.payments}" var="p">
						<span class="property-value" aria-labelledby="payments-label"><g:link controller="payment" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${ownerInstance?.pets}">
				<li class="fieldcontain">
					<span id="pets-label" class="property-label"><g:message code="owner.pets.label" default="Pets" /></span>
					
						<g:each in="${ownerInstance.pets}" var="p">
						<span class="property-value" aria-labelledby="pets-label"><g:link controller="pet" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${ownerInstance?.reservations}">
				<li class="fieldcontain">
					<span id="reservations-label" class="property-label"><g:message code="owner.reservations.label" default="Reservations" /></span>
					
						<g:each in="${ownerInstance.reservations}" var="r">
						<span class="property-value" aria-labelledby="reservations-label"><g:link controller="reservation" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${ownerInstance?.id}" />
					<g:link class="edit" action="edit" id="${ownerInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
