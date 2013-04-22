<%@ page import="kennelpal.Payment" %>



<div class="fieldcontain ${hasErrors(bean: paymentInstance, field: 'owner', 'error')} required">
	<label for="owner">
		<g:message code="payment.owner.label" default="Owner" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="owner" name="owner.id" from="${kennelpal.Owner.list()}" optionKey="id" required="" value="${paymentInstance?.owner?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: paymentInstance, field: 'amount', 'error')} required">
	<label for="amount">
		<g:message code="payment.amount.label" default="Amount" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="amount" value="${fieldValue(bean: paymentInstance, field: 'amount')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: paymentInstance, field: 'dateReceived', 'error')} required">
	<label for="dateReceived">
		<g:message code="payment.dateReceived.label" default="Date Received" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateReceived" precision="day"  value="${paymentInstance?.dateReceived}"  />
</div>

