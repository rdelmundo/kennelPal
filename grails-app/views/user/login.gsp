<%@ page import="kennelpal.User" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="kpLogin">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<g:form name = "loginForm" url = "[controller:'user', action:'verify']">
		<div>Username:
			<g:textField name = "login" value = "${user?.login}"></g:textField>
		</div>
		<div>Password:
			<g:passwordField name = "password" value = "${user?.password}"></g:passwordField>
		</div>
		<br/>
		<g:submitButton class="formButton" name="register" value="Login"></g:submitButton>
		</g:form>
	</body>
</html>