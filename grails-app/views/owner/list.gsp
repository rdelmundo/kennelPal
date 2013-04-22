
<%@ page import="kennelpal.Owner" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'owner.label', default: 'Owner')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-owner" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-owner" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="accountNumber" title="${message(code: 'owner.accountNumber.label', default: 'Account Number')}" />
					
						<g:sortableColumn property="accountBalance" title="${message(code: 'owner.accountBalance.label', default: 'Account Balance')}" />
					
						<g:sortableColumn property="firstName" title="${message(code: 'owner.firstName.label', default: 'First Name')}" />
					
						<g:sortableColumn property="lastName" title="${message(code: 'owner.lastName.label', default: 'Last Name')}" />
					
						<g:sortableColumn property="address" title="${message(code: 'owner.address.label', default: 'Address')}" />
					
						<g:sortableColumn property="city" title="${message(code: 'owner.city.label', default: 'City')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${ownerInstanceList}" status="i" var="ownerInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${ownerInstance.id}">${fieldValue(bean: ownerInstance, field: "accountNumber")}</g:link></td>
					
						<td>${fieldValue(bean: ownerInstance, field: "accountBalance")}</td>
					
						<td>${fieldValue(bean: ownerInstance, field: "firstName")}</td>
					
						<td>${fieldValue(bean: ownerInstance, field: "lastName")}</td>
					
						<td>${fieldValue(bean: ownerInstance, field: "address")}</td>
					
						<td>${fieldValue(bean: ownerInstance, field: "city")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${ownerInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
