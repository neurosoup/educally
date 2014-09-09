import educally.NotationSystem
import educally.NotationValue
import educally.security.Role
import educally.security.User
import educally.security.UserRole

class BootStrap {

    def init = { servletContext ->

        //Default admin user
        log.info('Initializing admin user...')
        def adminUser = User.findByUsername('admin')
        if (!adminUser) {
            def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
            //def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

            adminUser = new User(username: 'admin', password: 'admin')
            adminUser.save(flush: true)

            UserRole.create adminUser, adminRole, true
        }

        //Default Notation systems
        log.info('Initializing notation systems...')
        createNotationSystem('Note sur 40', (0..40), 4)
        createNotationSystem('Note sur 20', (0..20), 4)
        createNotationSystem('Note sur 10', (0..10), 4)
        createNotationSystem('Note sur 5', (0..5), 1)
        createNotationSystem('Lettres de E à A', ('E'..'A'), 1)
        createNotationSystem('Lettres de E à A avec signes + et -', ['E-', 'E', 'E+', 'D-', 'D', 'D+', 'C-', 'C', 'C+', 'B-', 'B', 'B+', 'A-', 'A', 'A+'], 1)
        createNotationSystem('Rouge, Orange, Jaune, Vert', ['Rouge', 'Orange', 'Jaune', 'Vert'], 1)
        createNotationSystem("Non acquis, En cours d'acquisition, Acquis", ["Non acquis", "En cours d'acquisition", "Acquis"], 1)

    }

    def destroy = {
    }

    private void createNotationSystem(String title, List values, int steps) {
        def notationSystem = NotationSystem.findByTitle(title)
        if (!notationSystem) {
            notationSystem = new NotationSystem(title: title)
            BigDecimal value = 0
            BigDecimal total = values.size() * steps - steps
            values.eachWithIndex { x, i ->
                for (int j = 0; j < steps; j++) {
                    if (value > total && j > 0) break
                    def dec = j / steps
                    def displayDec = "${dec}".substring(1).replace('.', '')
                    def displayValue = displayDec.length() > 0 ? "${x},${displayDec}" : "${x}"
                    BigDecimal decimalValue = value / total
                    def notationValue = new NotationValue(displayValue: displayValue, decimalValue: decimalValue)
                    notationSystem.values.add(notationValue)
                    value++
                }
            }
            notationSystem.save()
        }
    }
}
