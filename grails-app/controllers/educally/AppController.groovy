package educally

import educally.security.User
import org.joda.time.LocalDate
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

class AppController {

    def logoutHandlers
    def evaluationService
    def teacherService

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
                    .addToSkills(new Skill(name: 'la_maitrise_de_la_langue_francaise', title: 'La maîtrise de la langue française', path: ''))

                    .addToSkills(new Skill(name: 'dire', title: 'Dire', path: ',la_maitrise_de_la_langue_francaise,'))
                    .addToSkills(new Skill(name: null, title: 'Communiquer, au besoin avec des pauses pour chercher ses mots', path: ',la_maitrise_de_la_langue_francaise,dire,'))
                    .addToSkills(new Skill(name: null, title: 'Se présenter ; présenter quelqu’un ; demander à quelqu’un de ses nouvelles en utilisant les formes de politesse les plus élémentaires ; accueil et prise de congé', path: ',la_maitrise_de_la_langue_francaise,dire,'))
                    .addToSkills(new Skill(name: null, title: 'Répondre à des questions et en poser (sujets familiers ou besoins immédiats)', path: ',la_maitrise_de_la_langue_francaise,dire,'))
                    .addToSkills(new Skill(name: null, title: 'Epeler des mots familiers', path: ',la_maitrise_de_la_langue_francaise,dire,'))

                    .addToSkills(new Skill(name: 'lire', title: 'Lire', path: ',la_maitrise_de_la_langue_francaise,'))
                    .addToSkills(new Skill(name: null, title: 'Lire avec aisance (à haute voix, silencieusement) un texte', path: ',la_maitrise_de_la_langue_francaise,lire,'))
                    .addToSkills(new Skill(name: null, title: 'Lire seul des textes du patrimoine et des œuvres intégrales de la littérature de jeunesse, adaptés à son âge', path: ',la_maitrise_de_la_langue_francaise,lire,'))
                    .addToSkills(new Skill(name: null, title: 'Lire seul et comprendre un énoncé, une consigne', path: ',la_maitrise_de_la_langue_francaise,lire,'))
                    .addToSkills(new Skill(name: null, title: 'Dégager le thème d’un texte', path: ',la_maitrise_de_la_langue_francaise,lire,'))
                    .addToSkills(new Skill(name: null, title: 'Repérer dans un texte des informations explicites', path: ',la_maitrise_de_la_langue_francaise,lire,'))
                    .addToSkills(new Skill(name: null, title: 'Inférer des informations nouvelles (implicites)', path: ',la_maitrise_de_la_langue_francaise,lire,'))
                    .addToSkills(new Skill(name: null, title: 'Repérer les effets de choix formels (emploi de certains mots, utilisation d’un niveau de langue)', path: ',la_maitrise_de_la_langue_francaise,lire,'))
                    .addToSkills(new Skill(name: null, title: 'Utiliser ses connaissances pour réfléchir sur un texte, mieux le comprendre', path: ',la_maitrise_de_la_langue_francaise,lire,'))
                    .addToSkills(new Skill(name: null, title: 'Effectuer, seul, des recherches dans des ouvrages documentaires (livres, produits multimédia)', path: ',la_maitrise_de_la_langue_francaise,lire,'))
                    .addToSkills(new Skill(name: null, title: 'Se repérer dans une bibliothèque, une médiathèque', path: ',la_maitrise_de_la_langue_francaise,lire,'))

                    .addToSkills(new Skill(name: 'ecrire', title: 'Ecrire', path: ',la_maitrise_de_la_langue_francaise,'))
                    .addToSkills(new Skill(name: null, title: 'Copier sans erreur un texte d’au moins quinze lignes en lui donnant une présentation adaptée', path: ',la_maitrise_de_la_langue_francaise,ecrire,'))
                    .addToSkills(new Skill(name: null, title: 'Utiliser ses connaissances pour réfléchir sur un texte, mieux l’écrire', path: ',la_maitrise_de_la_langue_francaise,ecrire,'))
                    .addToSkills(new Skill(name: null, title: 'Répondre à une question par une phrase complète à l’écrit', path: ',la_maitrise_de_la_langue_francaise,ecrire,'))
                    .addToSkills(new Skill(name: null, title: 'Rédiger un texte d’une quinzaine de lignes (récits, description, dialogue, texte poétique, compte rendu) en utilisant ses connaissances en vocabulaire et en grammaire', path: ',la_maitrise_de_la_langue_francaise,ecrire,'))

                    .addToSkills(new Skill(name: 'etude_de_la_langue_:_vocabulaire', title: 'Etude de la langue : vocabulaire', path: ',la_maitrise_de_la_langue_francaise,'))
                    .addToSkills(new Skill(name: null, title: 'Comprendre des mots nouveaux et les utiliser à bon escient', path: ',la_maitrise_de_la_langue_francaise,etude_de_la_langue_:_vocabulaire,'))
                    .addToSkills(new Skill(name: null, title: 'Maîtriser quelques relations de sens entre les mots', path: ',la_maitrise_de_la_langue_francaise,etude_de_la_langue_:_vocabulaire,'))
                    .addToSkills(new Skill(name: null, title: 'Maîtriser quelques relations concernant la forme et le sens des mots', path: ',la_maitrise_de_la_langue_francaise,etude_de_la_langue_:_vocabulaire,'))
                    .addToSkills(new Skill(name: null, title: 'Savoir utiliser un dictionnaire papier ou numérique', path: ',la_maitrise_de_la_langue_francaise,etude_de_la_langue_:_vocabulaire,'))

                    .addToSkills(new Skill(name: 'etude_de_la_langue_:_grammaire', title: 'Etude de la langue : grammaire', path: ',la_maitrise_de_la_langue_francaise,'))
                    .addToSkills(new Skill(name: null, title: 'Distinguer les mots selon leur nature', path: ',la_maitrise_de_la_langue_francaise,etude_de_la_langue_:_grammaire,'))
                    .addToSkills(new Skill(name: null, title: 'Identifier les fonctions des mots dans la phrase', path: ',la_maitrise_de_la_langue_francaise,etude_de_la_langue_:_grammaire,'))
                    .addToSkills(new Skill(name: null, title: 'Conjuguer les verbes, utiliser les temps à bon escient', path: ',la_maitrise_de_la_langue_francaise,etude_de_la_langue_:_grammaire,'))

                    .addToSkills(new Skill(name: 'etude_de_la_langue_:_orthographe', title: 'Etude de la langue : orthographe', path: ',la_maitrise_de_la_langue_francaise,'))
                    .addToSkills(new Skill(name: null, title: 'Maîtriser l’orthographe grammaticale', path: ',la_maitrise_de_la_langue_francaise,etude_de_la_langue_:_orthographe,'))
                    .addToSkills(new Skill(name: null, title: 'Maîtriser l’orthographe lexicale', path: ',la_maitrise_de_la_langue_francaise,etude_de_la_langue_:_orthographe,'))
                    .addToSkills(new Skill(name: null, title: 'Orthographier correctement un texte simple de dix lignes – lors de sa rédaction ou de sa dictée – en se référant aux règles connues d’orthographe et de grammaire ainsi qu’à la connaissance du vocabulaire', path: ',la_maitrise_de_la_langue_francaise,etude_de_la_langue_:_orthographe,'))

            /* MATHEMATIQUES */
                    .addToSkills(new Skill(name: 'les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique', title: 'Les principaux éléments de mathématiques et la culture scientifique et technologique', path: ''))

                    .addToSkills(new Skill(name: 'nombres_et_calcul', title: 'Nombres et calcul', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,'))
                    .addToSkills(new Skill(name: null, title: 'Ecrire, nommer, comparer et utiliser les nombres entiers, les nombres décimaux (jusqu’au centième) et quelques fractions simples', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,nombres_et_calcul,'))
                    .addToSkills(new Skill(name: null, title: 'Restituer les tables d’addition et de multiplication de 2 à 9', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,nombres_et_calcul,'))
                    .addToSkills(new Skill(name: null, title: 'Utiliser les techniques opératoires des quatre opérations sur les nombres entiers et décimaux (pour la division, le diviseur est un nombre entier)', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,nombres_et_calcul,'))
                    .addToSkills(new Skill(name: null, title: 'Ajouter deux fractions décimales ou deux fractions simples de même dénominateur', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,nombres_et_calcul,'))
                    .addToSkills(new Skill(name: null, title: 'Calculer mentalement en utilisant les quatre opérations', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,nombres_et_calcul,'))
                    .addToSkills(new Skill(name: null, title: 'Estimer l’ordre de grandeur d’un résultat', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,nombres_et_calcul,'))
                    .addToSkills(new Skill(name: null, title: 'Résoudre des problèmes relevant des quatre opérations', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,nombres_et_calcul,'))
                    .addToSkills(new Skill(name: null, title: 'Utiliser une calculatrice', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,nombres_et_calcul,'))

                    .addToSkills(new Skill(name: 'geometrie', title: 'Géométrie', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,'))
                    .addToSkills(new Skill(name: null, title: 'Reconnaître, décrire et nommer les figures et solides usuels', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,geometrie,'))
                    .addToSkills(new Skill(name: null, title: 'Utiliser la règle, l’équerre et le compas pour vérifier la nature de figures planes usuelles et les construire avec soin et précision', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,geometrie,'))
                    .addToSkills(new Skill(name: null, title: 'Percevoir et reconnaître parallèles et perpendiculaires', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,geometrie,'))
                    .addToSkills(new Skill(name: null, title: 'Résoudre des problèmes de reproduction, de construction', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,geometrie,'))

                    .addToSkills(new Skill(name: 'grandeurs_et_mesures', title: 'Grandeurs et mesures', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,'))
                    .addToSkills(new Skill(name: null, title: 'Utiliser des instruments de mesure', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,grandeurs_et_mesures,'))
                    .addToSkills(new Skill(name: null, title: 'Connaître et utiliser les formules du périmètre et de l’aire d’un carré, d’un rectangle et d’un triangle', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,grandeurs_et_mesures,'))
                    .addToSkills(new Skill(name: null, title: 'Utiliser les unités de mesures usuelles', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,grandeurs_et_mesures,'))
                    .addToSkills(new Skill(name: null, title: 'Résoudre des problèmes dont la résolution implique des conversions', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,grandeurs_et_mesures,'))

                    .addToSkills(new Skill(name: 'organisation_et_gestion_de_donnees', title: 'Organisation et gestion de données', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,'))
                    .addToSkills(new Skill(name: null, title: 'Lire, interpréter et construire quelques représentations simples : tableaux, graphiques', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,organisation_et_gestion_de_donnees,'))
                    .addToSkills(new Skill(name: null, title: 'Savoir organiser des informations numériques ou géométriques, justifier et apprécier la vraisemblance d’un résultat', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,organisation_et_gestion_de_donnees,'))
                    .addToSkills(new Skill(name: null, title: 'Résoudre un problème mettant en jeu une situation de proportionnalité', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,organisation_et_gestion_de_donnees,'))

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

                def preferredNotationSystem = NotationSystem.findByTitle('Note sur 20')

                def evaluation1 = evaluationService.create(['exercice'] as String[], 'Récitation tables de multiplication', preferredNotationSystem)
                def evaluation2 = evaluationService.create(['contrôle'] as String[], 'Contrôle grands nombres', preferredNotationSystem)
                def evaluation3 = evaluationService.create(['contrôle'] as String[], 'Contrôle calcul', preferredNotationSystem)

                evaluationService.rate(teacher, pupil1, evaluation1, skill1, 0.6)
                evaluationService.rate(teacher, pupil1, evaluation1, skill4, 0.4)
                evaluationService.rate(teacher, pupil1, evaluation2, skill2, 0.75)
                evaluationService.rate(teacher, pupil1, evaluation3, skill3)

                evaluationService.rate(teacher, pupil2, evaluation1, skill1, 0.0)
                evaluationService.rate(teacher, pupil2, evaluation1, skill4, 0.25)
                evaluationService.rate(teacher, pupil2, evaluation2, skill2, 0.05)
                evaluationService.rate(teacher, pupil2, evaluation3, skill3, 0.45)


                evaluationService.rate(teacher, pupil3, evaluation1, skill1)
                evaluationService.rate(teacher, pupil3, evaluation1, skill4, 0.1)
                evaluationService.rate(teacher, pupil3, evaluation2, skill2)
                evaluationService.rate(teacher, pupil3, evaluation3, skill3)

                evaluationService.rate(teacher, pupil4, evaluation1, skill1, 0.5)
                evaluationService.rate(teacher, pupil4, evaluation1, skill4, 0.55)
                evaluationService.rate(teacher, pupil4, evaluation2, skill2, 0.45)
                evaluationService.rate(teacher, pupil4, evaluation3, skill3, 0.575)

                evaluationService.rate(teacher, pupil5, evaluation1, skill1, 0.95)
                evaluationService.rate(teacher, pupil5, evaluation1, skill4, 0.1)
                evaluationService.rate(teacher, pupil5, evaluation2, skill2, 1)
                evaluationService.rate(teacher, pupil5, evaluation3, skill3, 0.9875)

            }
        } else {
            log.warn('Cannot insert demo data because no demo user found.')
        }

        log.info('Data initialization done.')
    }


}
