package educally.security

import educally.Account
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.authentication.dao.NullSaltSource
import grails.plugin.springsecurity.ui.RegistrationCode

class RegisterController extends grails.plugin.springsecurity.ui.RegisterController {

    def accountService

    def index() {
        def copy = [:] + (flash.chainedParams ?: [:])
        copy.remove 'controller'
        copy.remove 'action'
        [command: new RegisterCommand(copy)]
    }

    def register(RegisterCommand command) {

        if (command.hasErrors()) {
            render view: 'index', model: [command: command]
            return
        }

        String salt = saltSource instanceof NullSaltSource ? null : command.username
        def user = lookupUserClass().newInstance(email: command.email, username: command.username, accountLocked: true, enabled: true)
        def account = new Account(firstName: command.firstname, lastName: command.lastname, user: user)

        RegistrationCode registrationCode = accountService.register(user, account, command.password, salt)
        if (registrationCode == null || registrationCode.hasErrors()) {
            // null means problem creating the user or the account
            flash.error = message(code: 'spring.security.ui.register.miscError')
            flash.chainedParams = params
            redirect action: 'index'
            return
        }

        String url = generateLink('verifyRegistration', [t: registrationCode.token])

        def conf = SpringSecurityUtils.securityConfig
        def body = conf.ui.register.emailBody
        if (body.contains('$')) {
            body = evaluate(body, [user: user, url: url])
        }
        mailService.sendMail {
            to command.email
            from conf.ui.register.emailFrom
            subject conf.ui.register.emailSubject
            html body.toString()
        }

        render view: 'index', model: [emailSent: true]
    }


}

class RegisterCommand extends grails.plugin.springsecurity.ui.RegisterCommand {

    String firstname
    String lastname

    static constraints = {
        firstname nullable: true
        lastname nullable: true
    }
}
