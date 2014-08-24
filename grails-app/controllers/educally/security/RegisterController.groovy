package educally.security

import educally.Account

class RegisterController extends grails.plugin.springsecurity.ui.RegisterController {

    def register(RegisterCommand command) {
        super.register(command)

        if (command.hasErrors()) {
            return
        }

        def user = User.findByUsername(command.username)
        def account = new Account(firstname: command.firstName, lastName: command.lastName, user: user)

        account.save()

    }

}

class RegisterCommand extends grails.plugin.springsecurity.ui.RegisterCommand {

    String firstName
    String lastName

    def grailsApplication

    static constraints = {
        firstName blank: true
        lastName blank: true
    }
}
