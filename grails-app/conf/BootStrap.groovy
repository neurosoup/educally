import educally.Account
import educally.NotationSystem
import educally.TeacherSkill
import educally.Evaluation
import educally.Skill
import educally.Pupil
import educally.SkillLevel
import educally.Teacher
import educally.security.Role
import educally.security.User
import educally.security.UserRole
import org.joda.time.DateTime

class BootStrap {

    def init = { servletContext ->

        log.info('Inserting initial data.')

        //Initial admin user
        log.info('Inserting admin user...')
        def adminUser = User.findByUsername('admin')
        if (!adminUser) {
            def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
            //def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

            adminUser = new User(username: 'admin', password: 'admin')
            adminUser.save(flush: true)

            UserRole.create adminUser, adminRole, true
        }

        //2012-2013 Official Skills
        log.info('Inserting last official skills...')
        //SkillLevel.findAllByRevision('2011-2012').each { it.delete() }

        def level = SkillLevel.findByTitleAndRevision('Palier 2 CM2', '2012-2013')
        if (!level) {
            level = new SkillLevel(title: 'Palier 2 CM2', revision: '2012-2013')

            /* FRANCAIS */
            level.addToSkills(new Skill(title: 'La maîtrise de la langue française', path: ''))

            level.addToSkills(new Skill(title: 'Dire', path: ',La_maitrise_de_la_langue_francaise,'))
            level.addToSkills(new Skill(title: 'Communiquer, au besoin avec des pauses pour chercher ses mots', path: ',La_maitrise_de_la_langue_francaise,dire,'))
            level.addToSkills(new Skill(title: 'Se présenter ; présenter quelqu’un ; demander à quelqu’un de ses nouvelles en utilisant les formes de politesse les plus élémentaires ; accueil et prise de congé', path: ',La_maitrise_de_la_langue_francaise,dire,'))
            level.addToSkills(new Skill(title: 'Répondre à des questions et en poser (sujets familiers ou besoins immédiats)', path: ',La_maitrise_de_la_langue_francaise,dire,'))
            level.addToSkills(new Skill(title: 'Epeler des mots familiers', path: ',La_maitrise_de_la_langue_francaise,dire,'))

            level.addToSkills(new Skill(title: 'Lire', path: ',La_maitrise_de_la_langue_francaise,'))
            level.addToSkills(new Skill(title: 'Lire avec aisance (à haute voix, silencieusement) un texte', path: ',La_maitrise_de_la_langue_francaise,lire,'))
            level.addToSkills(new Skill(title: 'Lire seul des textes du patrimoine et des œuvres intégrales de la littérature de jeunesse, adaptés à son âge', path: ',La_maitrise_de_la_langue_francaise,lire,'))
            level.addToSkills(new Skill(title: 'Lire seul et comprendre un énoncé, une consigne', path: ',La_maitrise_de_la_langue_francaise,lire,'))
            level.addToSkills(new Skill(title: 'Dégager le thème d’un texte', path: ',La_maitrise_de_la_langue_francaise,lire,'))
            level.addToSkills(new Skill(title: 'Repérer dans un texte des informations explicites', path: ',La_maitrise_de_la_langue_francaise,lire,'))
            level.addToSkills(new Skill(title: 'Inférer des informations nouvelles (implicites)', path: ',La_maitrise_de_la_langue_francaise,lire,'))
            level.addToSkills(new Skill(title: 'Repérer les effets de choix formels (emploi de certains mots, utilisation d’un niveau de langue)', path: ',La_maitrise_de_la_langue_francaise,lire,'))
            level.addToSkills(new Skill(title: 'Utiliser ses connaissances pour réfléchir sur un texte, mieux le comprendre', path: ',La_maitrise_de_la_langue_francaise,lire,'))
            level.addToSkills(new Skill(title: 'Effectuer, seul, des recherches dans des ouvrages documentaires (livres, produits multimédia)', path: ',La_maitrise_de_la_langue_francaise,lire,'))
            level.addToSkills(new Skill(title: 'Se repérer dans une bibliothèque, une médiathèque', path: ',La_maitrise_de_la_langue_francaise,lire,'))

            level.addToSkills(new Skill(title: 'Ecrire', path: ',La_maitrise_de_la_langue_francaise,'))
            level.addToSkills(new Skill(title: 'Copier sans erreur un texte d’au moins quinze lignes en lui donnant une présentation adaptée', path: ',La_maitrise_de_la_langue_francaise,ecrire,'))
            level.addToSkills(new Skill(title: 'Utiliser ses connaissances pour réfléchir sur un texte, mieux l’écrire', path: ',La_maitrise_de_la_langue_francaise,ecrire,'))
            level.addToSkills(new Skill(title: 'Répondre à une question par une phrase complète à l’écrit', path: ',La_maitrise_de_la_langue_francaise,ecrire,'))
            level.addToSkills(new Skill(title: 'Rédiger un texte d’une quinzaine de lignes (récits, description, dialogue, texte poétique, compte rendu) en utilisant ses connaissances en vocabulaire et en grammaire', path: ',La_maitrise_de_la_langue_francaise,ecrire,'))

            level.addToSkills(new Skill(title: 'Etude de la langue : vocabulaire', path: ',La_maitrise_de_la_langue_francaise,'))
            level.addToSkills(new Skill(title: 'Comprendre des mots nouveaux et les utiliser à bon escient', path: ',La_maitrise_de_la_langue_francaise,etude_de_la_langue_:_vocabulaire,'))
            level.addToSkills(new Skill(title: 'Maîtriser quelques relations de sens entre les mots', path: ',La_maitrise_de_la_langue_francaise,etude_de_la_langue_:_vocabulaire,'))
            level.addToSkills(new Skill(title: 'Maîtriser quelques relations concernant la forme et le sens des mots', path: ',La_maitrise_de_la_langue_francaise,etude_de_la_langue_:_vocabulaire,'))
            level.addToSkills(new Skill(title: 'Savoir utiliser un dictionnaire papier ou numérique', path: ',La_maitrise_de_la_langue_francaise,etude_de_la_langue_:_vocabulaire,'))

            level.addToSkills(new Skill(title: 'Etude de la langue : grammaire', path: ',La_maitrise_de_la_langue_francaise,'))
            level.addToSkills(new Skill(title: 'Distinguer les mots selon leur nature', path: ',La_maitrise_de_la_langue_francaise,etude_de_la_langue_:_grammaire,'))
            level.addToSkills(new Skill(title: 'Identifier les fonctions des mots dans la phrase', path: ',La_maitrise_de_la_langue_francaise,etude_de_la_langue_:_grammaire,'))
            level.addToSkills(new Skill(title: 'Conjuguer les verbes, utiliser les temps à bon escient', path: ',La_maitrise_de_la_langue_francaise,etude_de_la_langue_:_grammaire,'))

            level.addToSkills(new Skill(title: 'Etude de la langue : orthographe', path: ',La_maitrise_de_la_langue_francaise,'))
            level.addToSkills(new Skill(title: 'Maîtriser l’orthographe grammaticale', path: ',La_maitrise_de_la_langue_francaise,etude_de_la_langue_:_orthographe,'))
            level.addToSkills(new Skill(title: 'Maîtriser l’orthographe lexicale', path: ',La_maitrise_de_la_langue_francaise,etude_de_la_langue_:_orthographe,'))
            level.addToSkills(new Skill(title: 'Orthographier correctement un texte simple de dix lignes – lors de sa rédaction ou de sa dictée – en se référant aux règles connues d’orthographe et de grammaire ainsi qu’à la connaissance du vocabulaire', path: ',La_maitrise_de_la_langue_francaise,etude_de_la_langue_:_orthographe,'))

            /* MATHEMATIQUES */
            level.addToSkills(new Skill(title: 'Les principaux éléments de mathématiques et la culture scientifique et technologique', path: ''))

            level.addToSkills(new Skill(title: 'Nombres et calcul', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,'))
            level.addToSkills(new Skill(title: 'Ecrire, nommer, comparer et utiliser les nombres entiers, les nombres décimaux (jusqu’au centième) et quelques fractions simples', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,nombres_et_calcul'))
            level.addToSkills(new Skill(title: 'Restituer les tables d’addition et de multiplication de 2 à 9', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,nombres_et_calcul'))
            level.addToSkills(new Skill(title: 'Utiliser les techniques opératoires des quatre opérations sur les nombres entiers et décimaux (pour la division, le diviseur est un nombre entier)', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,nombres_et_calcul'))
            level.addToSkills(new Skill(title: 'Ajouter deux fractions décimales ou deux fractions simples de même dénominateur', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,nombres_et_calcul'))
            level.addToSkills(new Skill(title: 'Calculer mentalement en utilisant les quatre opérations', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,nombres_et_calcul'))
            level.addToSkills(new Skill(title: 'Estimer l’ordre de grandeur d’un résultat', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,nombres_et_calcul'))
            level.addToSkills(new Skill(title: 'Résoudre des problèmes relevant des quatre opérations', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,nombres_et_calcul'))
            level.addToSkills(new Skill(title: 'Utiliser une calculatrice', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,nombres_et_calcul'))

            level.addToSkills(new Skill(title: 'Géométrie', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,'))
            level.addToSkills(new Skill(title: 'Reconnaître, décrire et nommer les figures et solides usuels', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,geometrie'))
            level.addToSkills(new Skill(title: 'Utiliser la règle, l’équerre et le compas pour vérifier la nature de figures planes usuelles et les construire avec soin et précision', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,geometrie'))
            level.addToSkills(new Skill(title: 'Percevoir et reconnaître parallèles et perpendiculaires', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,geometrie'))
            level.addToSkills(new Skill(title: 'Résoudre des problèmes de reproduction, de construction', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,geometrie'))

            level.addToSkills(new Skill(title: 'Grandeurs et mesures', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,'))
            level.addToSkills(new Skill(title: 'Utiliser des instruments de mesure', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,grandeurs_et_mesures'))
            level.addToSkills(new Skill(title: 'Connaître et utiliser les formules du périmètre et de l’aire d’un carré, d’un rectangle et d’un triangle', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,grandeurs_et_mesures'))
            level.addToSkills(new Skill(title: 'Utiliser les unités de mesures usuelles', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,grandeurs_et_mesures'))
            level.addToSkills(new Skill(title: 'Résoudre des problèmes dont la résolution implique des conversions', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,grandeurs_et_mesures'))

            level.addToSkills(new Skill(title: 'Organisation et gestion de données', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,'))
            level.addToSkills(new Skill(title: 'Lire, interpréter et construire quelques représentations simples : tableaux, graphiques', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,organisation_et_gestion_de_donnees'))
            level.addToSkills(new Skill(title: 'Savoir organiser des informations numériques ou géométriques, justifier et apprécier la vraisemblance d’un résultat', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,organisation_et_gestion_de_donnees'))
            level.addToSkills(new Skill(title: 'Résoudre un problème mettant en jeu une situation de proportionnalité', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,organisation_et_gestion_de_donnees'))

            level.save()
        }

        //Notation systems
        def notationSystem = NotationSystem.findByTitle('/20')
        if (!notationSystem) {
            notationSystem = new NotationSystem('sur 20')
            1..20.each {
                notationSystem.addToNotationValues(displayValue: "$it", decimalValue: it / 20)
            }
            notationSystem.save()
        }
        notationSystem = NotationSystem.findByTitle('/5')
        if (!notationSystem) {
            notationSystem = new NotationSystem('sur 5')
            1..20.each {
                notationSystem.addToNotationValues(displayValue: "$it", decimalValue: it / 5)
            }
            notationSystem.save()
        }
        notationSystem = NotationSystem.findByTitle('Lettres')
        if (!notationSystem) {
            notationSystem = new NotationSystem('Lettres')
            def index = 0
            'A'..'E'.each {
                index++
                notationSystem.addToNotationValues(displayValue: it, decimalValue: index / it.length())
            }
            notationSystem.save()
        }

        //Initial demo data
        log.info('Inserting demo data...')

        //Teacher pupils and evaluations
        def user = User.findByEmail('demo.educally@gmail.com')
        if (user) {
            def account = Account.findByUser(user)

            def teacher = Teacher.findByAccount(account)
            if (teacher) {
                teacher.delete()
            }

            teacher = new Teacher(account: account)

            def skill1 =

            def pupil1 = new Pupil(teacher: teacher, firstName: 'Michel', lastName: 'Constantin', birthDay: new DateTime(1924, 7, 13, 0, 0), tags: ['cm2'])
            def pupil2 = new Pupil(teacher: teacher, firstName: 'Alain', lastName: 'Peters', birthDay: new DateTime(1962, 3, 10, 0, 0), tags: ['cm2'])
            def pupil3 = new Pupil(teacher: teacher, firstName: 'Audrey', lastName: 'Hepburn', birthDay: new DateTime(1929, 5, 4, 0, 0), tags: ['cm2'])
            def pupil4 = new Pupil(teacher: teacher, firstName: 'Garret', lastName: 'Fitzgerald', birthDay: new DateTime(1926, 5, 19, 0, 0), tags: ['cm2'])
            def pupil5 = new Pupil(teacher: teacher, firstName: 'Joelle', lastName: 'Mogensen', birthDay: new DateTime(1953, 2, 3, 0, 0), tags: ['cm2'])

            def eval1 = new Evaluation(pupil: pupil1, title: 'Récitation tables de multiplication')
            eval1.addToSkills(new TeacherSkill(skill: Skill.findByTitle('Restituer les tables d’addition et de multiplication de 2 à 9')))

            teacher.addToPupils(pupil1)
            teacher.addToPupils(pupil2)
            teacher.addToPupils(pupil3)
            teacher.addToPupils(pupil4)
            teacher.addToPupils(pupil5)

            teacher.addToEvaluations(eval1)

            teacher.save()
        } else {
            log.info('Cannot insert demo data because no demo user found.')
        }

        log.info('Inserting initial data done.')
    }

    def destroy = {
    }
}
