<%@ page import="kennelpal.Rate" %>



<div class="fieldcontain ${hasErrors(bean: rateInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="rate.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="25" required="" value="${rateInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rateInstance, field: 'amount', 'error')} required">
	<label for="amount">
		<g:message code="rate.amount.label" default="Amount" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="amount" value="${fieldValue(bean: rateInstance, field: 'amount')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: rateInstance, field: 'reservations', 'error')} ">
	<label for="reservations">
		<g:message code="rate.reservations.label" default="Reservations" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${rateInstance?.reservations?}" var="r">
    <li><g:link controller="reservation" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="reservation" action="create" params="['rate.id': rateInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'reservation.label', default: 'Reservation')])}</g:link>
</li>
</ul>

</div>

