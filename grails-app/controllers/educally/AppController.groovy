package educally

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

class AppController {

    def logoutHandlers

    def index() {}

    def dashboard() {

    }

    def logout = {

        Authentication auth = SecurityContextHolder.context.authentication
        if (auth) {
            logoutHandlers.each { handler ->
                handler.logout(request, response, auth)
            }
        }
        redirect uri: '/'
    }

}
