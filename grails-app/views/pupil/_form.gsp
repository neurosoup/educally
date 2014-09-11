<%@ page import="educally.Pupil" %>



<div class="fieldcontain ${hasErrors(bean: pupilInstance, field: 'birthDay', 'error')} ">
	<label for="birthDay">
		<g:message code="pupil.birthDay.label" default="Birth Day" />
		
	</label>
	

</div>

<div class="fieldcontain ${hasErrors(bean: pupilInstance, field: 'evaluations', 'error')} ">
	<label for="evaluations">
		<g:message code="pupil.evaluations.label" default="Evaluations" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${pupilInstance?.evaluations?}" var="e">
    <li><g:link controller="evaluation" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="evaluation" action="create" params="['pupil.id': pupilInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'evaluation.label', default: 'Evaluation')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: pupilInstance, field: 'firstName', 'error')} ">
	<label for="firstName">
		<g:message code="pupil.firstName.label" default="First Name" />
		
	</label>
	<g:textField name="firstName" value="${pupilInstance?.firstName}" />

</div>

<div class="fieldcontain ${hasErrors(bean: pupilInstance, field: 'lastName', 'error')} ">
	<label for="lastName">
		<g:message code="pupil.lastName.label" default="Last Name" />
		
	</label>
	<g:textField name="lastName" value="${pupilInstance?.lastName}" />

</div>

