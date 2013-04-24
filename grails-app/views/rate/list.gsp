
<%@ page import="kennelpal.Rate" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="kpMain">
		<g:set var="entityName" value="${message(code: 'rate.label', default: 'Rate')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-rate" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/user/home')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-rate" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'rate.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="amount" title="${message(code: 'rate.amount.label', default: 'Amount')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${rateInstanceList}" status="i" var="rateInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${rateInstance.id}">${fieldValue(bean: rateInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: rateInstance, field: "amount")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${rateInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
