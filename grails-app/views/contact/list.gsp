
<%@ page import="kennelpal.Contact" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="kpMain">
		<g:set var="entityName" value="${message(code: 'contact.label', default: 'Contact')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-contact" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/user/home')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-contact" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="contact.owner.label" default="Owner" /></th>
					
						<g:sortableColumn property="firstName" title="${message(code: 'contact.firstName.label', default: 'First Name')}" />
					
						<g:sortableColumn property="lastName" title="${message(code: 'contact.lastName.label', default: 'Last Name')}" />
					
						<g:sortableColumn property="phone" title="${message(code: 'contact.phone.label', default: 'Phone')}" />
					
						<g:sortableColumn property="email" title="${message(code: 'contact.email.label', default: 'Email')}" />
					
						<g:sortableColumn property="role" title="${message(code: 'contact.role.label', default: 'Role')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${contactInstanceList}" status="i" var="contactInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${contactInstance.id}">${fieldValue(bean: contactInstance, field: "owner")}</g:link></td>
					
						<td>${fieldValue(bean: contactInstance, field: "firstName")}</td>
					
						<td>${fieldValue(bean: contactInstance, field: "lastName")}</td>
					
						<td>${fieldValue(bean: contactInstance, field: "phone")}</td>
					
						<td>${fieldValue(bean: contactInstance, field: "email")}</td>
					
						<td>${fieldValue(bean: contactInstance, field: "role")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${contactInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
