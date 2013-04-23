
<%@ page import="kennelpal.Pet" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'pet.label', default: 'Pet')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-pet" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/user/home')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-pet" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="pet.owner.label" default="Owner" /></th>
					
						<g:sortableColumn property="name" title="${message(code: 'pet.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="breed" title="${message(code: 'pet.breed.label', default: 'Breed')}" />
					
						<g:sortableColumn property="notes" title="${message(code: 'pet.notes.label', default: 'Notes')}" />
					
						<g:sortableColumn property="dateOfBirth" title="${message(code: 'pet.dateOfBirth.label', default: 'Date Of Birth')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${petInstanceList}" status="i" var="petInstance">

					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" controller="owner" id="${petInstance.owner.id}">${fieldValue(bean: petInstance, field: "owner")}</g:link></td>
					
						<td><g:link action="show" id="${petInstance.id}">${fieldValue(bean: petInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: petInstance, field: "breed")}</td>
					
						<td>${fieldValue(bean: petInstance, field: "notes")}</td>
					
						<td><g:formatDate date="${petInstance.dateOfBirth}" /></td>
					</tr>
					
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${petInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
