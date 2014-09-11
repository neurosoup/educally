
<%@ page import="educally.Pupil" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'pupil.label', default: 'Pupil')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-pupil" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-pupil" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list pupil">
			
				<g:if test="${pupilInstance?.birthDay}">
				<li class="fieldcontain">
					<span id="birthDay-label" class="property-label"><g:message code="pupil.birthDay.label" default="Birth Day" /></span>
					
						<span class="property-value" aria-labelledby="birthDay-label"><g:fieldValue bean="${pupilInstance}" field="birthDay"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pupilInstance?.evaluations}">
				<li class="fieldcontain">
					<span id="evaluations-label" class="property-label"><g:message code="pupil.evaluations.label" default="Evaluations" /></span>
					
						<g:each in="${pupilInstance.evaluations}" var="e">
						<span class="property-value" aria-labelledby="evaluations-label"><g:link controller="evaluation" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${pupilInstance?.firstName}">
				<li class="fieldcontain">
					<span id="firstName-label" class="property-label"><g:message code="pupil.firstName.label" default="First Name" /></span>
					
						<span class="property-value" aria-labelledby="firstName-label"><g:fieldValue bean="${pupilInstance}" field="firstName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pupilInstance?.lastName}">
				<li class="fieldcontain">
					<span id="lastName-label" class="property-label"><g:message code="pupil.lastName.label" default="Last Name" /></span>
					
						<span class="property-value" aria-labelledby="lastName-label"><g:fieldValue bean="${pupilInstance}" field="lastName"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:pupilInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${pupilInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
