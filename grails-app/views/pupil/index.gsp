
<%@ page import="educally.Pupil" %>
<!DOCTYPE html>
<html>
	<head>
	</head>
	<body>
		<a href="#list-pupil" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-pupil" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="birthDay" title="${message(code: 'pupil.birthDay.label', default: 'Birth Day')}" />
					
						<g:sortableColumn property="firstName" title="${message(code: 'pupil.firstName.label', default: 'First Name')}" />
					
						<g:sortableColumn property="lastName" title="${message(code: 'pupil.lastName.label', default: 'Last Name')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${pupilInstanceList}" status="i" var="pupilInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${pupilInstance.id}">${fieldValue(bean: pupilInstance, field: "birthDay")}</g:link></td>
					
						<td>${fieldValue(bean: pupilInstance, field: "firstName")}</td>
					
						<td>${fieldValue(bean: pupilInstance, field: "lastName")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${pupilInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
