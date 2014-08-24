package educally

import educally.security.User

class Account {

    static constraints = {
        user nullable: true
    }

    String firstName
    String lastName
    User user

}
