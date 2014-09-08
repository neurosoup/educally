package educally

import educally.security.Role
import educally.security.User
import educally.security.UserRole
import org.joda.time.DateTime
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

class AppController {

    def logoutHandlers
    def evaluationService

    def index() {
        initializeData(false)
    }

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

    def initializeData(Boolean removeDemoData) {
        log.info('Data initialization.')

        //Initial admin user
        log.info('Initializing admin user...')
        def adminUser = User.findByUsername('admin')
        if (!adminUser) {
            def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
            //def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

            adminUser = new User(username: 'admin', password: 'admin')
            adminUser.save(flush: true)

            UserRole.create adminUser, adminRole, true
        }

        //2012-2013 Official Skills
        log.info('Initializing skill books...')
        //SkillLevel.findAllByRevision('2011-2012').each { it.delete() }

        def schoolYear = SchoolYear.findByTitle('2012-2013')
        if (!schoolYear) {
            schoolYear = new SchoolYear(title: '2012-2013', start: new DateTime(2012, 9, 2, 0, 0), end: new DateTime(2013, 7, 2, 0, 0))
            schoolYear.save()
        }

        def skillBook = SkillBook.findByTitle('LPC TEST')
        if (!skillBook) {
            skillBook = new SkillBook(title: 'LPC TEST', schoolYear: SchoolYear.findByTitle('2012-2013'))

            /* FRANCAIS */
                    .addToSkills(new Skill(name: 'la_maitrise_de_la_langue_francaise', title: 'La maîtrise de la langue française', path: ''))

                    .addToSkills(new Skill(name: 'dire', title: 'Dire', path: ',La_maitrise_de_la_langue_francaise,'))
                    .addToSkills(new Skill(name: null, title: 'Communiquer, au besoin avec des pauses pour chercher ses mots', path: ',La_maitrise_de_la_langue_francaise,dire,'))
                    .addToSkills(new Skill(name: null, title: 'Se présenter ; présenter quelqu’un ; demander à quelqu’un de ses nouvelles en utilisant les formes de politesse les plus élémentaires ; accueil et prise de congé', path: ',La_maitrise_de_la_langue_francaise,dire,'))
                    .addToSkills(new Skill(name: null, title: 'Répondre à des questions et en poser (sujets familiers ou besoins immédiats)', path: ',La_maitrise_de_la_langue_francaise,dire,'))
                    .addToSkills(new Skill(name: null, title: 'Epeler des mots familiers', path: ',La_maitrise_de_la_langue_francaise,dire,'))

                    .addToSkills(new Skill(name: 'lire', title: 'Lire', path: ',La_maitrise_de_la_langue_francaise,'))
                    .addToSkills(new Skill(name: null, title: 'Lire avec aisance (à haute voix, silencieusement) un texte', path: ',La_maitrise_de_la_langue_francaise,lire,'))
                    .addToSkills(new Skill(name: null, title: 'Lire seul des textes du patrimoine et des œuvres intégrales de la littérature de jeunesse, adaptés à son âge', path: ',La_maitrise_de_la_langue_francaise,lire,'))
                    .addToSkills(new Skill(name: null, title: 'Lire seul et comprendre un énoncé, une consigne', path: ',La_maitrise_de_la_langue_francaise,lire,'))
                    .addToSkills(new Skill(name: null, title: 'Dégager le thème d’un texte', path: ',La_maitrise_de_la_langue_francaise,lire,'))
                    .addToSkills(new Skill(name: null, title: 'Repérer dans un texte des informations explicites', path: ',La_maitrise_de_la_langue_francaise,lire,'))
                    .addToSkills(new Skill(name: null, title: 'Inférer des informations nouvelles (implicites)', path: ',La_maitrise_de_la_langue_francaise,lire,'))
                    .addToSkills(new Skill(name: null, title: 'Repérer les effets de choix formels (emploi de certains mots, utilisation d’un niveau de langue)', path: ',La_maitrise_de_la_langue_francaise,lire,'))
                    .addToSkills(new Skill(name: null, title: 'Utiliser ses connaissances pour réfléchir sur un texte, mieux le comprendre', path: ',La_maitrise_de_la_langue_francaise,lire,'))
                    .addToSkills(new Skill(name: null, title: 'Effectuer, seul, des recherches dans des ouvrages documentaires (livres, produits multimédia)', path: ',La_maitrise_de_la_langue_francaise,lire,'))
                    .addToSkills(new Skill(name: null, title: 'Se repérer dans une bibliothèque, une médiathèque', path: ',La_maitrise_de_la_langue_francaise,lire,'))

                    .addToSkills(new Skill(name: 'ecrire', title: 'Ecrire', path: ',La_maitrise_de_la_langue_francaise,'))
                    .addToSkills(new Skill(name: null, title: 'Copier sans erreur un texte d’au moins quinze lignes en lui donnant une présentation adaptée', path: ',La_maitrise_de_la_langue_francaise,ecrire,'))
                    .addToSkills(new Skill(name: null, title: 'Utiliser ses connaissances pour réfléchir sur un texte, mieux l’écrire', path: ',La_maitrise_de_la_langue_francaise,ecrire,'))
                    .addToSkills(new Skill(name: null, title: 'Répondre à une question par une phrase complète à l’écrit', path: ',La_maitrise_de_la_langue_francaise,ecrire,'))
                    .addToSkills(new Skill(name: null, title: 'Rédiger un texte d’une quinzaine de lignes (récits, description, dialogue, texte poétique, compte rendu) en utilisant ses connaissances en vocabulaire et en grammaire', path: ',La_maitrise_de_la_langue_francaise,ecrire,'))

                    .addToSkills(new Skill(name: 'etude_de_la_langue_:_vocabulaire', title: 'Etude de la langue : vocabulaire', path: ',La_maitrise_de_la_langue_francaise,'))
                    .addToSkills(new Skill(name: null, title: 'Comprendre des mots nouveaux et les utiliser à bon escient', path: ',La_maitrise_de_la_langue_francaise,etude_de_la_langue_:_vocabulaire,'))
                    .addToSkills(new Skill(name: null, title: 'Maîtriser quelques relations de sens entre les mots', path: ',La_maitrise_de_la_langue_francaise,etude_de_la_langue_:_vocabulaire,'))
                    .addToSkills(new Skill(name: null, title: 'Maîtriser quelques relations concernant la forme et le sens des mots', path: ',La_maitrise_de_la_langue_francaise,etude_de_la_langue_:_vocabulaire,'))
                    .addToSkills(new Skill(name: null, title: 'Savoir utiliser un dictionnaire papier ou numérique', path: ',La_maitrise_de_la_langue_francaise,etude_de_la_langue_:_vocabulaire,'))

                    .addToSkills(new Skill(name: 'etude_de_la_langue_:_grammaire', title: 'Etude de la langue : grammaire', path: ',La_maitrise_de_la_langue_francaise,'))
                    .addToSkills(new Skill(name: null, title: 'Distinguer les mots selon leur nature', path: ',La_maitrise_de_la_langue_francaise,etude_de_la_langue_:_grammaire,'))
                    .addToSkills(new Skill(name: null, title: 'Identifier les fonctions des mots dans la phrase', path: ',La_maitrise_de_la_langue_francaise,etude_de_la_langue_:_grammaire,'))
                    .addToSkills(new Skill(name: null, title: 'Conjuguer les verbes, utiliser les temps à bon escient', path: ',La_maitrise_de_la_langue_francaise,etude_de_la_langue_:_grammaire,'))

                    .addToSkills(new Skill(name: 'etude_de_la_langue_:_orthographe', title: 'Etude de la langue : orthographe', path: ',La_maitrise_de_la_langue_francaise,'))
                    .addToSkills(new Skill(name: null, title: 'Maîtriser l’orthographe grammaticale', path: ',La_maitrise_de_la_langue_francaise,etude_de_la_langue_:_orthographe,'))
                    .addToSkills(new Skill(name: null, title: 'Maîtriser l’orthographe lexicale', path: ',La_maitrise_de_la_langue_francaise,etude_de_la_langue_:_orthographe,'))
                    .addToSkills(new Skill(name: null, title: 'Orthographier correctement un texte simple de dix lignes – lors de sa rédaction ou de sa dictée – en se référant aux règles connues d’orthographe et de grammaire ainsi qu’à la connaissance du vocabulaire', path: ',La_maitrise_de_la_langue_francaise,etude_de_la_langue_:_orthographe,'))

            /* MATHEMATIQUES */
                    .addToSkills(new Skill(name: 'les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique', title: 'Les principaux éléments de mathématiques et la culture scientifique et technologique', path: ''))

                    .addToSkills(new Skill(name: 'nombres_et_calcul', title: 'Nombres et calcul', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,'))
                    .addToSkills(new Skill(name: null, title: 'Ecrire, nommer, comparer et utiliser les nombres entiers, les nombres décimaux (jusqu’au centième) et quelques fractions simples', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,nombres_et_calcul'))
                    .addToSkills(new Skill(name: null, title: 'Restituer les tables d’addition et de multiplication de 2 à 9', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,nombres_et_calcul'))
                    .addToSkills(new Skill(name: null, title: 'Utiliser les techniques opératoires des quatre opérations sur les nombres entiers et décimaux (pour la division, le diviseur est un nombre entier)', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,nombres_et_calcul'))
                    .addToSkills(new Skill(name: null, title: 'Ajouter deux fractions décimales ou deux fractions simples de même dénominateur', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,nombres_et_calcul'))
                    .addToSkills(new Skill(name: null, title: 'Calculer mentalement en utilisant les quatre opérations', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,nombres_et_calcul'))
                    .addToSkills(new Skill(name: null, title: 'Estimer l’ordre de grandeur d’un résultat', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,nombres_et_calcul'))
                    .addToSkills(new Skill(name: null, title: 'Résoudre des problèmes relevant des quatre opérations', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,nombres_et_calcul'))
                    .addToSkills(new Skill(name: null, title: 'Utiliser une calculatrice', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,nombres_et_calcul'))

                    .addToSkills(new Skill(name: 'geometrie', title: 'Géométrie', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,'))
                    .addToSkills(new Skill(name: null, title: 'Reconnaître, décrire et nommer les figures et solides usuels', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,geometrie'))
                    .addToSkills(new Skill(name: null, title: 'Utiliser la règle, l’équerre et le compas pour vérifier la nature de figures planes usuelles et les construire avec soin et précision', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,geometrie'))
                    .addToSkills(new Skill(name: null, title: 'Percevoir et reconnaître parallèles et perpendiculaires', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,geometrie'))
                    .addToSkills(new Skill(name: null, title: 'Résoudre des problèmes de reproduction, de construction', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,geometrie'))

                    .addToSkills(new Skill(name: 'grandeurs_et_mesures', title: 'Grandeurs et mesures', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,'))
                    .addToSkills(new Skill(name: null, title: 'Utiliser des instruments de mesure', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,grandeurs_et_mesures'))
                    .addToSkills(new Skill(name: null, title: 'Connaître et utiliser les formules du périmètre et de l’aire d’un carré, d’un rectangle et d’un triangle', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,grandeurs_et_mesures'))
                    .addToSkills(new Skill(name: null, title: 'Utiliser les unités de mesures usuelles', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,grandeurs_et_mesures'))
                    .addToSkills(new Skill(name: null, title: 'Résoudre des problèmes dont la résolution implique des conversions', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,grandeurs_et_mesures'))

                    .addToSkills(new Skill(name: 'organisation_et_gestion_de_donnees', title: 'Organisation et gestion de données', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,'))
                    .addToSkills(new Skill(name: null, title: 'Lire, interpréter et construire quelques représentations simples : tableaux, graphiques', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,organisation_et_gestion_de_donnees'))
                    .addToSkills(new Skill(name: null, title: 'Savoir organiser des informations numériques ou géométriques, justifier et apprécier la vraisemblance d’un résultat', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,organisation_et_gestion_de_donnees'))
                    .addToSkills(new Skill(name: null, title: 'Résoudre un problème mettant en jeu une situation de proportionnalité', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,organisation_et_gestion_de_donnees'))

            skillBook.save()
        }

        //Notation systems
        log.info('Initializing notation systems...')
        createNotationSystem('Note sur 40', (0..40), 4)
        createNotationSystem('Note sur 20', (0..20), 4)
        createNotationSystem('Note sur 10', (0..10), 4)
        createNotationSystem('Note sur 5', (0..5), 1)
        createNotationSystem('Lettres de E à A', ('E'..'A'), 1)
        createNotationSystem('Lettres de E à A avec signes + et -', ['E-', 'E', 'E+', 'D-', 'D', 'D+', 'C-', 'C', 'C+', 'B-', 'B', 'B+', 'A-', 'A', 'A+'], 1)
        createNotationSystem('Rouge, Orange, Jaune, Vert', ['Rouge', 'Orange', 'Jaune', 'Vert'], 1)
        createNotationSystem("Non acquis, En cours d'acquisition, Acquis", ["Non acquis", "En cours d'acquisition", "Acquis"], 1)

        //Initial demo data
        log.info('Initializing demo data...')

        //Teacher pupils and evaluations
        def user = User.findByEmail('demo.educally@gmail.com')
        if (user) {
            def account = Account.findByUser(user)

            def teacher = Teacher.findByAccount(account)
            if (teacher) {
                teacher.pupils.each { it.delete() }
                teacher.delete()
            }

            if (!removeDemoData) {

                teacher = new Teacher(account: account)

                def teacherSkillBook = new SkillBook(title: 'Mon livret bleu', reference: '2012')
                skillBook.skills.each {
                    teacherSkillBook.addToSkills(new Skill(basedOn: it, title: it.title, name: it.name, path: it.path))
                }
                teacher.addToSkillBooks(teacherSkillBook)

                teacher.evaluationTags.add('exercice')
                teacher.evaluationTags.add('contrôle')
                teacher.pupilTags.add('cm2')

                if (!teacher.save()) {
                    log.error('Error saving teacher world.')
                }

                def pupil1 = new Pupil(firstName: 'Michel', lastName: 'Constantin', birthDay: new DateTime(1924, 7, 13, 0, 0), tags: ['cm2'])
                def pupil2 = new Pupil(firstName: 'Alain', lastName: 'Peters', birthDay: new DateTime(1962, 3, 10, 0, 0), tags: ['cm2'])
                def pupil3 = new Pupil(firstName: 'Audrey', lastName: 'Hepburn', birthDay: new DateTime(1929, 5, 4, 0, 0), tags: ['cm2'])
                def pupil4 = new Pupil(firstName: 'Garret', lastName: 'Fitzgerald', birthDay: new DateTime(1926, 5, 19, 0, 0), tags: ['cm2'])
                def pupil5 = new Pupil(firstName: 'Joelle', lastName: 'Mogensen', birthDay: new DateTime(1953, 2, 3, 0, 0), tags: ['cm2'])

                teacher
                        .addToPupils(pupil1)
                        .addToPupils(pupil2)
                        .addToPupils(pupil3)
                        .addToPupils(pupil4)
                        .addToPupils(pupil5)

                def skill1 = teacher.skillBooks.find { it.title = 'Mon livret bleu' }.skills.find {
                    it.title == 'Restituer les tables d’addition et de multiplication de 2 à 9'
                }

                def skill2 = teacher.skillBooks.find { it.title = 'Mon livret bleu' }.skills.find {
                    it.title == 'Estimer l’ordre de grandeur d’un résultat'
                }

                def skill3 = teacher.skillBooks.find { it.title = 'Mon livret bleu' }.skills.find {
                    it.title == 'Utiliser une calculatrice'
                }

                def preferredNotationSystem = NotationSystem.findByTitle('Note sur 20')

                def ev = evaluationService.createEvaluationForPupil(['exercice'], pupil1, 'Récitation tables de multiplication', preferredNotationSystem, skill1, 0.6)
                evaluationService.addEvaluationValue(ev, skill1, 0.4)
                evaluationService.createEvaluationForPupil(['contrôle'], pupil1, 'Contrôle grands nombres', preferredNotationSystem, skill2, 0.75)
                evaluationService.createEvaluationForPupil(['contrôle'], pupil1, 'Contrôle calcul', preferredNotationSystem, skill3)

                ev = evaluationService.createEvaluationForPupil(['exercice'], pupil2, 'Récitation tables de multiplication', preferredNotationSystem, skill1, 0.1)
                evaluationService.addEvaluationValue(ev, skill1, 0.25)
                evaluationService.createEvaluationForPupil(['contrôle'], pupil2, 'Contrôle grands nombres', preferredNotationSystem, skill2, 0.05)
                evaluationService.createEvaluationForPupil(['contrôle'], pupil2, 'Contrôle calcul', preferredNotationSystem, skill3, 0.45)


                ev = evaluationService.createEvaluationForPupil(['exercice'], pupil3, 'Récitation tables de multiplication', preferredNotationSystem, skill1)
                evaluationService.addEvaluationValue(ev, skill1, 0.1)
                evaluationService.createEvaluationForPupil(['contrôle'], pupil3, 'Contrôle grands nombres', preferredNotationSystem, skill2)
                evaluationService.createEvaluationForPupil(['contrôle'], pupil3, 'Contrôle calcul', preferredNotationSystem, skill3)

                ev = evaluationService.createEvaluationForPupil(['exercice'], pupil4, 'Récitation tables de multiplication', preferredNotationSystem, skill1, 0.5)
                evaluationService.addEvaluationValue(ev, skill1, 0.55)
                evaluationService.createEvaluationForPupil(['contrôle'], pupil4, 'Contrôle grands nombres', preferredNotationSystem, skill2, 0.45)
                evaluationService.createEvaluationForPupil(['contrôle'], pupil4, 'Contrôle calcul', preferredNotationSystem, skill3, 0.575)

                ev = evaluationService.createEvaluationForPupil(['exercice'], pupil5, 'Récitation tables de multiplication', preferredNotationSystem, skill1, 0.95)
                evaluationService.addEvaluationValue(ev, skill1, 0.1)
                evaluationService.createEvaluationForPupil(['contrôle'], pupil5, 'Contrôle grands nombres', preferredNotationSystem, skill2, 1)
                evaluationService.createEvaluationForPupil(['contrôle'], pupil5, 'Contrôle calcul', preferredNotationSystem, skill3, 0.9875)


                if (!teacher.save()) {
                    log.error('Error saving pupils world.')
                }
            }
        } else {
            log.warn('Cannot insert demo data because no demo user found.')
        }

        log.info('Data initializing done.')
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
