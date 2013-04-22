<%@ page import="kennelpal.Pet" %>



<div class="fieldcontain ${hasErrors(bean: petInstance, field: 'owner', 'error')} required">
	<label for="owner">
		<g:message code="pet.owner.label" default="Owner" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="owner" name="owner.id" from="${kennelpal.Owner.list()}" optionKey="id" required="" value="${petInstance?.owner?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: petInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="pet.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="25" required="" value="${petInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: petInstance, field: 'breed', 'error')} ">
	<label for="breed">
		<g:message code="pet.breed.label" default="Breed" />
		
	</label>
	<g:textField name="breed" maxlength="50" value="${petInstance?.breed}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: petInstance, field: 'notes', 'error')} ">
	<label for="notes">
		<g:message code="pet.notes.label" default="Notes" />
		
	</label>
	<g:textArea name="notes" cols="40" rows="5" maxlength="500" value="${petInstance?.notes}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: petInstance, field: 'dateOfBirth', 'error')} required">
	<label for="dateOfBirth">
		<g:message code="pet.dateOfBirth.label" default="Date Of Birth" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateOfBirth" precision="day"  value="${petInstance?.dateOfBirth}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: petInstance, field: 'reservations', 'error')} ">
	<label for="reservations">
		<g:message code="pet.reservations.label" default="Reservations" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${petInstance?.reservations?}" var="r">
    <li><g:link controller="reservation" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="reservation" action="create" params="['pet.id': petInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'reservation.label', default: 'Reservation')])}</g:link>
</li>
</ul>

</div>

