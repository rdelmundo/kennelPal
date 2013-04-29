<%@ page import="kennelpal.Owner" %>



<div class="fieldcontain ${hasErrors(bean: ownerInstance, field: 'accountNumber', 'error')} required">
	<label for="accountNumber">
		<g:message code="owner.accountNumber.label" default="Account Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="accountNumber" maxlength="5" required="" value="${ownerInstance?.accountNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ownerInstance, field: 'accountBalance', 'error')} required">
	<label for="accountBalance">
		<g:message code="owner.accountBalance.label" default="Account Balance" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="accountBalance" value="${fieldValue(bean: ownerInstance, field: 'accountBalance')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: ownerInstance, field: 'firstName', 'error')} required">
	<label for="firstName">
		<g:message code="owner.firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" maxlength="25" required="" value="${ownerInstance?.firstName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ownerInstance, field: 'lastName', 'error')} required">
	<label for="lastName">
		<g:message code="owner.lastName.label" default="Last Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" maxlength="25" required="" value="${ownerInstance?.lastName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ownerInstance, field: 'address', 'error')} ">
	<label for="address">
		<g:message code="owner.address.label" default="Address" />
		
	</label>
	<g:textField name="address" value="${ownerInstance?.address}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ownerInstance, field: 'city', 'error')} ">
	<label for="city">
		<g:message code="owner.city.label" default="City" />
		
	</label>
	<g:textField name="city" value="${ownerInstance?.city}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ownerInstance, field: 'state', 'error')} ">
	<label for="state">
		<g:message code="owner.state.label" default="State" />
		
	</label>
	<g:textField name="state" value="${ownerInstance?.state}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ownerInstance, field: 'zipcode', 'error')} ">
	<label for="zipcode">
		<g:message code="owner.zipcode.label" default="Zipcode" />
		
	</label>
	<g:textField name="zipcode" value="${ownerInstance?.zipcode}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ownerInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="owner.email.label" default="Email" />
		
	</label>
	<g:field type="email" name="email" value="${ownerInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ownerInstance, field: 'primaryPhone', 'error')} required">
	<label for="primaryPhone">
		<g:message code="owner.primaryPhone.label" default="Primary Phone" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="primaryPhone" required="" value="${ownerInstance?.primaryPhone}"/>
	<label for="phoneExample">
		<g:message code="phoneExample" default="Example: 555-555-5555" />
	</label>
</div>

<div class="fieldcontain ${hasErrors(bean: ownerInstance, field: 'secondaryPhone', 'error')} ">
	<label for="secondaryPhone">
		<g:message code="owner.secondaryPhone.label" default="Secondary Phone" />
		
	</label>
	<g:textField name="secondaryPhone" value="${ownerInstance?.secondaryPhone}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ownerInstance, field: 'notes', 'error')} ">
	<label for="notes">
		<g:message code="owner.notes.label" default="Notes" />
		
	</label>
	<g:textArea name="notes" cols="40" rows="5" maxlength="500" value="${ownerInstance?.notes}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ownerInstance, field: 'contacts', 'error')} ">
	<label for="contacts">
		<g:message code="owner.contacts.label" default="Contacts" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${ownerInstance?.contacts?}" var="c">
    <li><g:link controller="contact" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="contact" action="create" params="['owner.id': ownerInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'contact.label', default: 'Contact')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: ownerInstance, field: 'payments', 'error')} ">
	<label for="payments">
		<g:message code="owner.payments.label" default="Payments" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${ownerInstance?.payments?}" var="p">
    <li><g:link controller="payment" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="payment" action="create" params="['owner.id': ownerInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'payment.label', default: 'Payment')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: ownerInstance, field: 'pets', 'error')} ">
	<label for="pets">
		<g:message code="owner.pets.label" default="Pets" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${ownerInstance?.pets?}" var="p">
    <li><g:link controller="pet" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="pet" action="create" params="['owner.id': ownerInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'pet.label', default: 'Pet')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: ownerInstance, field: 'reservations', 'error')} ">
	<label for="reservations">
		<g:message code="owner.reservations.label" default="Reservations" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${ownerInstance?.reservations?}" var="r">
    <li><g:link controller="reservation" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="reservation" action="create" params="['owner.id': ownerInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'reservation.label', default: 'Reservation')])}</g:link>
</li>
</ul>

</div>

