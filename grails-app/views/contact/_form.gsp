<%@ page import="kennelpal.Contact" %>



<div class="fieldcontain ${hasErrors(bean: contactInstance, field: 'owner', 'error')} required">
	<label for="owner">
		<g:message code="contact.owner.label" default="Owner" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="owner" name="owner.id" from="${kennelpal.Owner.list()}" optionKey="id" required="" value="${contactInstance?.owner?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contactInstance, field: 'firstName', 'error')} required">
	<label for="firstName">
		<g:message code="contact.firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" maxlength="25" required="" value="${contactInstance?.firstName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contactInstance, field: 'lastName', 'error')} required">
	<label for="lastName">
		<g:message code="contact.lastName.label" default="Last Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" maxlength="25" required="" value="${contactInstance?.lastName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contactInstance, field: 'phone', 'error')} required">
	<label for="phone">
		<g:message code="contact.phone.label" default="Phone" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="phone" required="" value="${contactInstance?.phone}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contactInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="contact.email.label" default="Email" />
		
	</label>
	<g:field type="email" name="email" value="${contactInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contactInstance, field: 'role', 'error')} ">
	<label for="role">
		<g:message code="contact.role.label" default="Role" />
		
	</label>
	<g:select name="role" from="${contactInstance.constraints.role.inList}" value="${contactInstance?.role}" valueMessagePrefix="contact.role" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contactInstance, field: 'notes', 'error')} ">
	<label for="notes">
		<g:message code="contact.notes.label" default="Notes" />
		
	</label>
	<g:textField name="notes" maxlength="100" value="${contactInstance?.notes}"/>
</div>

