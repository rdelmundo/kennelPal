
<%@ page import="kennelpal.Payment" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'payment.label', default: 'Payment')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-payment" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-payment" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list payment">
			
				<g:if test="${paymentInstance?.owner}">
				<li class="fieldcontain">
					<span id="owner-label" class="property-label"><g:message code="payment.owner.label" default="Owner" /></span>
					
						<span class="property-value" aria-labelledby="owner-label"><g:link controller="owner" action="show" id="${paymentInstance?.owner?.id}">${paymentInstance?.owner?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${paymentInstance?.amount}">
				<li class="fieldcontain">
					<span id="amount-label" class="property-label"><g:message code="payment.amount.label" default="Amount" /></span>
					
						<span class="property-value" aria-labelledby="amount-label"><g:fieldValue bean="${paymentInstance}" field="amount"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${paymentInstance?.dateReceived}">
				<li class="fieldcontain">
					<span id="dateReceived-label" class="property-label"><g:message code="payment.dateReceived.label" default="Date Received" /></span>
					
						<span class="property-value" aria-labelledby="dateReceived-label"><g:formatDate date="${paymentInstance?.dateReceived}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${paymentInstance?.id}" />
					<g:link class="edit" action="edit" id="${paymentInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
