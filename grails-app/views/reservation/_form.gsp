<%@ page import="kennelpal.Reservation" %>



<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'owner', 'error')} required">
	<label for="owner">
		<g:message code="reservation.owner.label" default="Owner" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="owner" name="owner.id" from="${kennelpal.Owner.list()}" optionKey="id" required="" value="${reservationInstance?.owner?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'pet', 'error')} required">
	<label for="pet">
		<g:message code="reservation.pet.label" default="Pet" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="pet" name="pet.id" from="${kennelpal.Pet.list()}" optionKey="id" required="" value="${reservationInstance?.pet?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'rate', 'error')} required">
	<label for="rate">
		<g:message code="reservation.rate.label" default="Rate" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="rate" name="rate.id" from="${kennelpal.Rate.list()}" optionKey="id" required="" value="${reservationInstance?.rate?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'startDate', 'error')} required">
	<label for="startDate">
		<g:message code="reservation.startDate.label" default="Start Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="startDate" precision="day"  value="${reservationInstance?.startDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'endDate', 'error')} required">
	<label for="endDate">
		<g:message code="reservation.endDate.label" default="End Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="endDate" precision="day"  value="${reservationInstance?.endDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'checkedIn', 'error')} ">
	<label for="checkedIn">
		<g:message code="reservation.checkedIn.label" default="Checked In" />
		
	</label>
	<g:checkBox name="checkedIn" value="${reservationInstance?.checkedIn}" />
</div>

