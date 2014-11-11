package educally

import educally.security.User
import org.joda.time.LocalDate
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

class AppController {

    def logoutHandlers
    def evaluationService
    def teacherService
    def skillService

    def index() {

        if (!teacherService.currentTeacher) {
            initializeData(false)
        }

        respond teacherService.currentTeacher.skillBooks
    }

    def undefined() {

    }

    def administration() {

    }

    def resetdata() {
        initializeData(true)
        initializeData(false)
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
        log.info('Demo data initialization.')

        log.info('Initializing school year...')
        def schoolYear = SchoolYear.findByTitle('2012-2013')
        if (removeDemoData) {
            if (schoolYear) schoolYear.delete()
        } else if (!schoolYear) {
            schoolYear = new SchoolYear(title: '2012-2013', start: new LocalDate(2012, 9, 2), end: new LocalDate(2013, 7, 2))
            schoolYear.save()
        }

        //Skill books
        log.info('Initializing skill books...')
        def skillBook = SkillBook.findByTitle('LPC TEST')
        if (removeDemoData) {
            if (skillBook) skillBook.delete()
        } else if (!skillBook) {
            skillBook = new SkillBook(title: 'LPC TEST', schoolYear: SchoolYear.findByTitle('2012-2013'))

            /* FRANCAIS */
            def S11 = skillService.create(skillBook, null, false, 'La maîtrise de la langue française')

            def S111 = skillService.create(skillBook, S11, false, 'Dire')
            skillService.create(skillBook, S111, true, 'Communiquer, au besoin avec des pauses pour chercher ses mots')
            skillService.create(skillBook, S111, true, 'Se présenter ; présenter quelqu’un ; demander à quelqu’un de ses nouvelles en utilisant les formes de politesse les plus élémentaires ; accueil et prise de congé')
            skillService.create(skillBook, S111, true, 'Répondre à des questions et en poser (sujets familiers ou besoins immédiats)')
            skillService.create(skillBook, S111, true, 'Epeler des mots familiers')

            def S112 = skillService.create(skillBook, S11, false, 'Lire')
            skillService.create(skillBook, S112, true, 'Lire avec aisance (à haute voix, silencieusement) un texte')
            skillService.create(skillBook, S112, true, 'Lire seul des textes du patrimoine et des œuvres intégrales de la littérature de jeunesse, adaptés à son âge')
            skillService.create(skillBook, S112, true, 'Lire seul et comprendre un énoncé, une consigne')
            skillService.create(skillBook, S112, true, 'Dégager le thème d’un texte')
            skillService.create(skillBook, S112, true, 'Repérer dans un texte des informations explicites')
            skillService.create(skillBook, S112, true, 'Inférer des informations nouvelles (implicites)')
            skillService.create(skillBook, S112, true, 'Repérer les effets de choix formels (emploi de certains mots, utilisation d’un niveau de langue)')
            skillService.create(skillBook, S112, true, 'Utiliser ses connaissances pour réfléchir sur un texte, mieux le comprendre')
            skillService.create(skillBook, S112, true, 'Effectuer, seul, des recherches dans des ouvrages documentaires (livres, produits multimédia)')
            skillService.create(skillBook, S112, true, 'Se repérer dans une bibliothèque, une médiathèque')

            def S113 = skillService.create(skillBook, S11, false, 'Ecrire')
            skillService.create(skillBook, S113, true, 'Copier sans erreur un texte d’au moins quinze lignes en lui donnant une présentation adaptée')
            skillService.create(skillBook, S113, true, 'Utiliser ses connaissances pour réfléchir sur un texte, mieux l’écrire')
            skillService.create(skillBook, S113, true, 'Répondre à une question par une phrase complète à l’écrit')
            skillService.create(skillBook, S113, true, 'Rédiger un texte d’une quinzaine de lignes (récits, description, dialogue, texte poétique, compte rendu) en utilisant ses connaissances en vocabulaire et en grammaire')

            def S114 = skillService.create(skillBook, S11, false, 'Etude de la langue : vocabulaire')
            skillService.create(skillBook, S114, true, 'Comprendre des mots nouveaux et les utiliser à bon escient')
            skillService.create(skillBook, S114, true, 'Maîtriser quelques relations de sens entre les mots')
            skillService.create(skillBook, S114, true, 'Maîtriser quelques relations concernant la forme et le sens des mots')
            skillService.create(skillBook, S114, true, 'Savoir utiliser un dictionnaire papier ou numérique')

            def S115 = skillService.create(skillBook, S11, false, 'Etude de la langue : grammaire')
            skillService.create(skillBook, S115, true, 'Distinguer les mots selon leur nature')
            skillService.create(skillBook, S115, true, 'Identifier les fonctions des mots dans la phrase')
            skillService.create(skillBook, S115, true, 'Conjuguer les verbes, utiliser les temps à bon escient')

            def S116 = skillService.create(skillBook, S11, false, 'Etude de la langue : orthographe')
            skillService.create(skillBook, S116, true, 'Maîtriser l’orthographe grammaticale')
            skillService.create(skillBook, S116, true, 'Maîtriser l’orthographe lexicale')
            skillService.create(skillBook, S116, true, 'Orthographier correctement un texte simple de dix lignes – lors de sa rédaction ou de sa dictée – en se référant aux règles connues d’orthographe et de grammaire ainsi qu’à la connaissance du vocabulaire')

            /* MATHEMATIQUES */
            def S12 = skillService.create(skillBook, null, false, 'Les principaux éléments de mathématiques et la culture scientifique et technologique')

            def S121 = skillService.create(skillBook, S12, false, 'Nombres et calcul')
            skillService.create(skillBook, S121, true, 'Ecrire, nommer, comparer et utiliser les nombres entiers, les nombres décimaux (jusqu’au centième) et quelques fractions simples')
            skillService.create(skillBook, S121, true, 'Restituer les tables d’addition et de multiplication de 2 à 9')
            skillService.create(skillBook, S121, true, 'Utiliser les techniques opératoires des quatre opérations sur les nombres entiers et décimaux (pour la division, le diviseur est un nombre entier)')
            skillService.create(skillBook, S121, true, 'Ajouter deux fractions décimales ou deux fractions simples de même dénominateur')
            skillService.create(skillBook, S121, true, 'Calculer mentalement en utilisant les quatre opérations')
            skillService.create(skillBook, S121, true, 'Estimer l’ordre de grandeur d’un résultat')
            skillService.create(skillBook, S121, true, 'Résoudre des problèmes relevant des quatre opérations')
            skillService.create(skillBook, S121, true, 'Utiliser une calculatrice')

            def S122 = skillService.create(skillBook, S12, false, 'Géométrie')
            skillService.create(skillBook, S122, true, 'Reconnaître, décrire et nommer les figures et solides usuels')
            skillService.create(skillBook, S122, true, 'Utiliser la règle, l’équerre et le compas pour vérifier la nature de figures planes usuelles et les construire avec soin et précision')
            skillService.create(skillBook, S122, true, 'Percevoir et reconnaître parallèles et perpendiculaires')
            skillService.create(skillBook, S122, true, 'Résoudre des problèmes de reproduction, de construction')

            def S123 = skillService.create(skillBook, S12, false, 'Grandeurs et mesures')
            skillService.create(skillBook, S123, true, 'Utiliser des instruments de mesure')
            skillService.create(skillBook, S123, true, 'Connaître et utiliser les formules du périmètre et de l’aire d’un carré, d’un rectangle et d’un triangle')
            skillService.create(skillBook, S123, true, 'Utiliser les unités de mesures usuelles')
            skillService.create(skillBook, S123, true, 'Résoudre des problèmes dont la résolution implique des conversions')

            def S124 = skillService.create(skillBook, S12, false, 'Organisation et gestion de données')
            skillService.create(skillBook, S124, true, 'Lire, interpréter et construire quelques représentations simples : tableaux, graphiques')
            skillService.create(skillBook, S124, true, 'Savoir organiser des informations numériques ou géométriques, justifier et apprécier la vraisemblance d’un résultat')
            skillService.create(skillBook, S124, true, 'Résoudre un problème mettant en jeu une situation de proportionnalité')

            skillBook.save()
        }

        //Teacher pupils and evaluations
        def user = User.findByEmail('demo.educally@gmail.com')
        if (user) {
            def account = Account.findByUser(user)

            log.info('Initializing classroom...')
            def teacher = Teacher.findByAccount(account)

            if (removeDemoData) {
                if (teacher) {
                    //TODO: remove classroom data
                }
            } else if (!teacher) {

                def classroom = new Classroom(name: 'CM1')

                teacher = new Teacher(account: account)

                def teacherSkillBook = new SkillBook(title: 'Livret bleu', schoolYear: SchoolYear.findByTitle('2012-2013'))
                skillBook.skills.each {
                    teacherSkillBook.addToSkills(new Skill(basedOn: it, title: it.title, name: it.name, path: it.path))
                }
                teacher.addToSkillBooks(teacherSkillBook)

                teacher.evaluationTags.add('exercice')
                teacher.evaluationTags.add('contrôle')
                teacher.pupilTags.add('cm2')

                classroom.addToTeachers(teacher)

                log.info('Initializing pupils...')
                def pupil1 = new Pupil(firstName: 'Michel', lastName: 'Constantin', birthDay: new LocalDate(1924, 7, 13), tags: ['cm2'])
                def pupil2 = new Pupil(firstName: 'Alain', lastName: 'Peters', birthDay: new LocalDate(1962, 3, 10), tags: ['cm2'])
                def pupil3 = new Pupil(firstName: 'Audrey', lastName: 'Hepburn', birthDay: new LocalDate(1929, 5, 4), tags: ['cm2'])
                def pupil4 = new Pupil(firstName: 'Garret', lastName: 'Fitzgerald', birthDay: new LocalDate(1926, 5, 19), tags: ['cm2'])
                def pupil5 = new Pupil(firstName: 'Joelle', lastName: 'Mogensen', birthDay: new LocalDate(1953, 2, 3), tags: ['cm2'])

                classroom
                        .addToPupils(pupil1)
                        .addToPupils(pupil2)
                        .addToPupils(pupil3)
                        .addToPupils(pupil4)
                        .addToPupils(pupil5)

                if (!classroom.save()) {
                    log.error('Error saving classRoom.')
                }

                log.info('Initializing evaluations...')
                def skill1 = Skill.findBySkillBookAndTitle(teacherSkillBook, 'Restituer les tables d’addition et de multiplication de 2 à 9')
                def skill2 = Skill.findBySkillBookAndTitle(teacherSkillBook, 'Estimer l’ordre de grandeur d’un résultat')
                def skill3 = Skill.findBySkillBookAndTitle(teacherSkillBook, 'Utiliser une calculatrice')
                def skill4 = Skill.findBySkillBookAndTitle(teacherSkillBook, 'Lire, interpréter et construire quelques représentations simples : tableaux, graphiques')
                def skill5 = Skill.findBySkillBookAndTitle(teacherSkillBook, 'Epeler des mots familiers')

                def preferredNotationSystem = NotationSystem.findByTitle('Note sur 20')

                def evaluation1 = evaluationService.create(['exercice'] as String[], 'Récitation tables de multiplication', preferredNotationSystem)
                def evaluation2 = evaluationService.create(['contrôle'] as String[], 'Contrôle grands nombres', preferredNotationSystem)
                def evaluation3 = evaluationService.create(['contrôle'] as String[], 'Contrôle calcul', preferredNotationSystem)
                def evaluation4 = evaluationService.create(['exercice'] as String[], "Evaluation français début d'année", preferredNotationSystem)

                evaluationService.rate(teacher, pupil1, evaluation1, skill1, 0.6)
                evaluationService.rate(teacher, pupil1, evaluation1, skill4, 0.4)
                evaluationService.rate(teacher, pupil1, evaluation2, skill2, 0.75)
                evaluationService.rate(teacher, pupil1, evaluation3, skill3)
                evaluationService.rate(teacher, pupil1, evaluation4, skill5, 1)

                evaluationService.rate(teacher, pupil2, evaluation1, skill1, 0.0)
                evaluationService.rate(teacher, pupil2, evaluation1, skill4, 0.25)
                evaluationService.rate(teacher, pupil2, evaluation2, skill2, 0.05)
                evaluationService.rate(teacher, pupil2, evaluation3, skill3, 0.55)
                evaluationService.rate(teacher, pupil2, evaluation4, skill5, 0.9875)

                evaluationService.rate(teacher, pupil3, evaluation1, skill1)
                evaluationService.rate(teacher, pupil3, evaluation1, skill4, 0.1)
                evaluationService.rate(teacher, pupil3, evaluation2, skill2)
                evaluationService.rate(teacher, pupil3, evaluation3, skill3)
                evaluationService.rate(teacher, pupil3, evaluation4, skill5, 0.9625)

                evaluationService.rate(teacher, pupil4, evaluation1, skill1, 0.5)
                evaluationService.rate(teacher, pupil4, evaluation1, skill4, 0.55)
                evaluationService.rate(teacher, pupil4, evaluation2, skill2, 0.45)
                evaluationService.rate(teacher, pupil4, evaluation3, skill3, 0.575)
                evaluationService.rate(teacher, pupil4, evaluation4, skill5, 1)

                evaluationService.rate(teacher, pupil5, evaluation1, skill1, 0.95)
                evaluationService.rate(teacher, pupil5, evaluation1, skill4, 0.1)
                evaluationService.rate(teacher, pupil5, evaluation2, skill2, 1)
                evaluationService.rate(teacher, pupil5, evaluation3, skill3, 0.9875)
                evaluationService.rate(teacher, pupil5, evaluation4, skill5, 0.9375)

            }
        } else {
            log.warn('Cannot insert demo data because no demo user found.')
        }

        log.info('Data initialization done.')
    }


}
