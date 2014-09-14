package educally

import educally.security.User

class TeacherService {

    def springSecurityService

    def Teacher getCurrentTeacher() {

        Teacher.findByAccount(Account.findByUser(springSecurityService.currentUser as User))

    }


}
