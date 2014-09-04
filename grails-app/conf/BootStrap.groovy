import educally.Account
import educally.EvaluationSkill
import educally.NotationSystem
import educally.NotationValue
import educally.EvaluatedSkill
import educally.Evaluation
import educally.Skill
import educally.Pupil
import educally.SkillGroup
import educally.Teacher
import educally.security.Role
import educally.security.User
import educally.security.UserRole
import org.joda.time.DateTime

class BootStrap {

    def init = { servletContext ->

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
        log.info('Initializing skill models...')
        //SkillLevel.findAllByRevision('2011-2012').each { it.delete() }

        def level = SkillGroup.findByTitleAndReference('LPC Palier 2 (CM2)', '2012')
        if (!level) {
            new SkillGroup(title: 'LPC Palier 2 (CM2)', reference: '2012')

            /* FRANCAIS */
                    .addToSkills(new Skill(title: 'La maîtrise de la langue française', path: ''))

                    .addToSkills(new Skill(title: 'Dire', path: ',La_maitrise_de_la_langue_francaise,'))
                    .addToSkills(new Skill(title: 'Communiquer, au besoin avec des pauses pour chercher ses mots', path: ',La_maitrise_de_la_langue_francaise,dire,'))
                    .addToSkills(new Skill(title: 'Se présenter ; présenter quelqu’un ; demander à quelqu’un de ses nouvelles en utilisant les formes de politesse les plus élémentaires ; accueil et prise de congé', path: ',La_maitrise_de_la_langue_francaise,dire,'))
                    .addToSkills(new Skill(title: 'Répondre à des questions et en poser (sujets familiers ou besoins immédiats)', path: ',La_maitrise_de_la_langue_francaise,dire,'))
                    .addToSkills(new Skill(title: 'Epeler des mots familiers', path: ',La_maitrise_de_la_langue_francaise,dire,'))

                    .addToSkills(new Skill(title: 'Lire', path: ',La_maitrise_de_la_langue_francaise,'))
                    .addToSkills(new Skill(title: 'Lire avec aisance (à haute voix, silencieusement) un texte', path: ',La_maitrise_de_la_langue_francaise,lire,'))
                    .addToSkills(new Skill(title: 'Lire seul des textes du patrimoine et des œuvres intégrales de la littérature de jeunesse, adaptés à son âge', path: ',La_maitrise_de_la_langue_francaise,lire,'))
                    .addToSkills(new Skill(title: 'Lire seul et comprendre un énoncé, une consigne', path: ',La_maitrise_de_la_langue_francaise,lire,'))
                    .addToSkills(new Skill(title: 'Dégager le thème d’un texte', path: ',La_maitrise_de_la_langue_francaise,lire,'))
                    .addToSkills(new Skill(title: 'Repérer dans un texte des informations explicites', path: ',La_maitrise_de_la_langue_francaise,lire,'))
                    .addToSkills(new Skill(title: 'Inférer des informations nouvelles (implicites)', path: ',La_maitrise_de_la_langue_francaise,lire,'))
                    .addToSkills(new Skill(title: 'Repérer les effets de choix formels (emploi de certains mots, utilisation d’un niveau de langue)', path: ',La_maitrise_de_la_langue_francaise,lire,'))
                    .addToSkills(new Skill(title: 'Utiliser ses connaissances pour réfléchir sur un texte, mieux le comprendre', path: ',La_maitrise_de_la_langue_francaise,lire,'))
                    .addToSkills(new Skill(title: 'Effectuer, seul, des recherches dans des ouvrages documentaires (livres, produits multimédia)', path: ',La_maitrise_de_la_langue_francaise,lire,'))
                    .addToSkills(new Skill(title: 'Se repérer dans une bibliothèque, une médiathèque', path: ',La_maitrise_de_la_langue_francaise,lire,'))

                    .addToSkills(new Skill(title: 'Ecrire', path: ',La_maitrise_de_la_langue_francaise,'))
                    .addToSkills(new Skill(title: 'Copier sans erreur un texte d’au moins quinze lignes en lui donnant une présentation adaptée', path: ',La_maitrise_de_la_langue_francaise,ecrire,'))
                    .addToSkills(new Skill(title: 'Utiliser ses connaissances pour réfléchir sur un texte, mieux l’écrire', path: ',La_maitrise_de_la_langue_francaise,ecrire,'))
                    .addToSkills(new Skill(title: 'Répondre à une question par une phrase complète à l’écrit', path: ',La_maitrise_de_la_langue_francaise,ecrire,'))
                    .addToSkills(new Skill(title: 'Rédiger un texte d’une quinzaine de lignes (récits, description, dialogue, texte poétique, compte rendu) en utilisant ses connaissances en vocabulaire et en grammaire', path: ',La_maitrise_de_la_langue_francaise,ecrire,'))

                    .addToSkills(new Skill(title: 'Etude de la langue : vocabulaire', path: ',La_maitrise_de_la_langue_francaise,'))
                    .addToSkills(new Skill(title: 'Comprendre des mots nouveaux et les utiliser à bon escient', path: ',La_maitrise_de_la_langue_francaise,etude_de_la_langue_:_vocabulaire,'))
                    .addToSkills(new Skill(title: 'Maîtriser quelques relations de sens entre les mots', path: ',La_maitrise_de_la_langue_francaise,etude_de_la_langue_:_vocabulaire,'))
                    .addToSkills(new Skill(title: 'Maîtriser quelques relations concernant la forme et le sens des mots', path: ',La_maitrise_de_la_langue_francaise,etude_de_la_langue_:_vocabulaire,'))
                    .addToSkills(new Skill(title: 'Savoir utiliser un dictionnaire papier ou numérique', path: ',La_maitrise_de_la_langue_francaise,etude_de_la_langue_:_vocabulaire,'))

                    .addToSkills(new Skill(title: 'Etude de la langue : grammaire', path: ',La_maitrise_de_la_langue_francaise,'))
                    .addToSkills(new Skill(title: 'Distinguer les mots selon leur nature', path: ',La_maitrise_de_la_langue_francaise,etude_de_la_langue_:_grammaire,'))
                    .addToSkills(new Skill(title: 'Identifier les fonctions des mots dans la phrase', path: ',La_maitrise_de_la_langue_francaise,etude_de_la_langue_:_grammaire,'))
                    .addToSkills(new Skill(title: 'Conjuguer les verbes, utiliser les temps à bon escient', path: ',La_maitrise_de_la_langue_francaise,etude_de_la_langue_:_grammaire,'))

                    .addToSkills(new Skill(title: 'Etude de la langue : orthographe', path: ',La_maitrise_de_la_langue_francaise,'))
                    .addToSkills(new Skill(title: 'Maîtriser l’orthographe grammaticale', path: ',La_maitrise_de_la_langue_francaise,etude_de_la_langue_:_orthographe,'))
                    .addToSkills(new Skill(title: 'Maîtriser l’orthographe lexicale', path: ',La_maitrise_de_la_langue_francaise,etude_de_la_langue_:_orthographe,'))
                    .addToSkills(new Skill(title: 'Orthographier correctement un texte simple de dix lignes – lors de sa rédaction ou de sa dictée – en se référant aux règles connues d’orthographe et de grammaire ainsi qu’à la connaissance du vocabulaire', path: ',La_maitrise_de_la_langue_francaise,etude_de_la_langue_:_orthographe,'))

            /* MATHEMATIQUES */
                    .addToSkills(new Skill(title: 'Les principaux éléments de mathématiques et la culture scientifique et technologique', path: ''))

                    .addToSkills(new Skill(title: 'Nombres et calcul', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,'))
                    .addToSkills(new Skill(title: 'Ecrire, nommer, comparer et utiliser les nombres entiers, les nombres décimaux (jusqu’au centième) et quelques fractions simples', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,nombres_et_calcul'))
                    .addToSkills(new Skill(title: 'Restituer les tables d’addition et de multiplication de 2 à 9', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,nombres_et_calcul'))
                    .addToSkills(new Skill(title: 'Utiliser les techniques opératoires des quatre opérations sur les nombres entiers et décimaux (pour la division, le diviseur est un nombre entier)', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,nombres_et_calcul'))
                    .addToSkills(new Skill(title: 'Ajouter deux fractions décimales ou deux fractions simples de même dénominateur', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,nombres_et_calcul'))
                    .addToSkills(new Skill(title: 'Calculer mentalement en utilisant les quatre opérations', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,nombres_et_calcul'))
                    .addToSkills(new Skill(title: 'Estimer l’ordre de grandeur d’un résultat', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,nombres_et_calcul'))
                    .addToSkills(new Skill(title: 'Résoudre des problèmes relevant des quatre opérations', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,nombres_et_calcul'))
                    .addToSkills(new Skill(title: 'Utiliser une calculatrice', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,nombres_et_calcul'))

                    .addToSkills(new Skill(title: 'Géométrie', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,'))
                    .addToSkills(new Skill(title: 'Reconnaître, décrire et nommer les figures et solides usuels', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,geometrie'))
                    .addToSkills(new Skill(title: 'Utiliser la règle, l’équerre et le compas pour vérifier la nature de figures planes usuelles et les construire avec soin et précision', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,geometrie'))
                    .addToSkills(new Skill(title: 'Percevoir et reconnaître parallèles et perpendiculaires', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,geometrie'))
                    .addToSkills(new Skill(title: 'Résoudre des problèmes de reproduction, de construction', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,geometrie'))

                    .addToSkills(new Skill(title: 'Grandeurs et mesures', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,'))
                    .addToSkills(new Skill(title: 'Utiliser des instruments de mesure', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,grandeurs_et_mesures'))
                    .addToSkills(new Skill(title: 'Connaître et utiliser les formules du périmètre et de l’aire d’un carré, d’un rectangle et d’un triangle', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,grandeurs_et_mesures'))
                    .addToSkills(new Skill(title: 'Utiliser les unités de mesures usuelles', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,grandeurs_et_mesures'))
                    .addToSkills(new Skill(title: 'Résoudre des problèmes dont la résolution implique des conversions', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,grandeurs_et_mesures'))

                    .addToSkills(new Skill(title: 'Organisation et gestion de données', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,'))
                    .addToSkills(new Skill(title: 'Lire, interpréter et construire quelques représentations simples : tableaux, graphiques', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,organisation_et_gestion_de_donnees'))
                    .addToSkills(new Skill(title: 'Savoir organiser des informations numériques ou géométriques, justifier et apprécier la vraisemblance d’un résultat', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,organisation_et_gestion_de_donnees'))
                    .addToSkills(new Skill(title: 'Résoudre un problème mettant en jeu une situation de proportionnalité', path: ',les_principaux_elements_de_mathematiques_et_la_culture_scientifique_et_technologique,organisation_et_gestion_de_donnees'))

                    .save()
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

            def removeDemoData = true

            if (!removeDemoData) {

                teacher = new Teacher(account: account)

                Skill.list().each {
                    teacher.addToEvaluationSkills(new EvaluationSkill(skill: it, title: it.title))
                }

                teacher.evaluationTags.add('exercice')
                teacher.evaluationTags.add('contrôle')
                teacher.pupilTags.add('cm2')

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

                def evaluationSkill1 = teacher.evaluationSkills.find {
                    it.title == 'Restituer les tables d’addition et de multiplication de 2 à 9'
                }

                def evaluationSkill2 = teacher.evaluationSkills.find {
                    it.title == 'Estimer l’ordre de grandeur d’un résultat'
                }

                def evaluationSkill3 = teacher.evaluationSkills.find { it.title == 'Utiliser une calculatrice' }

                def evaluation11 = new Evaluation(tags: ['exercice'], pupil: pupil1, title: 'Récitation tables de multiplication', preferredNotationSystem: NotationSystem.findByTitle('Note sur 20'))
                        .addToEvaluatedSkills(new EvaluatedSkill(skill: evaluationSkill1, value: 0.6))
                def evaluation12 = new Evaluation(tags: ['exercice'], pupil: pupil1, title: 'Récitation tables de multiplication', preferredNotationSystem: NotationSystem.findByTitle('Note sur 20'))
                        .addToEvaluatedSkills(new EvaluatedSkill(skill: evaluationSkill1, value: 0.4))
                def evaluation13 = new Evaluation(tags: ['contrôle'], pupil: pupil1, title: 'Contrôle grands nombres', preferredNotationSystem: NotationSystem.findByTitle('Note sur 20'))
                        .addToEvaluatedSkills(new EvaluatedSkill(skill: evaluationSkill2, value: 0.75))
                def evaluation14 = new Evaluation(tags: ['contrôle'], pupil: pupil1, title: 'Contrôle calcul', preferredNotationSystem: NotationSystem.findByTitle('Note sur 20'))
                        .addToEvaluatedSkills(new EvaluatedSkill(skill: evaluationSkill3, missed: true))

                def evaluation21 = new Evaluation(tags: ['exercice'], pupil: pupil1, title: 'Récitation tables de multiplication', preferredNotationSystem: NotationSystem.findByTitle('Note sur 20'))
                        .addToEvaluatedSkills(new EvaluatedSkill(skill: evaluationSkill1, value: 0.1))
                def evaluation22 = new Evaluation(tags: ['exercice'], pupil: pupil1, title: 'Récitation tables de multiplication', preferredNotationSystem: NotationSystem.findByTitle('Note sur 20'))
                        .addToEvaluatedSkills(new EvaluatedSkill(skill: evaluationSkill1, value: 0.25))
                def evaluation23 = new Evaluation(tags: ['contrôle'], pupil: pupil1, title: 'Contrôle grands nombres', preferredNotationSystem: NotationSystem.findByTitle('Note sur 20'))
                        .addToEvaluatedSkills(new EvaluatedSkill(skill: evaluationSkill2, value: 0.05))
                def evaluation24 = new Evaluation(tags: ['contrôle'], pupil: pupil1, title: 'Contrôle calcul', preferredNotationSystem: NotationSystem.findByTitle('Note sur 20'))
                        .addToEvaluatedSkills(new EvaluatedSkill(skill: evaluationSkill3, value: 0.45))

                def evaluation31 = new Evaluation(tags: ['exercice'], pupil: pupil1, title: 'Récitation tables de multiplication', preferredNotationSystem: NotationSystem.findByTitle('Note sur 20'))
                        .addToEvaluatedSkills(new EvaluatedSkill(skill: evaluationSkill1, missed: true))
                def evaluation32 = new Evaluation(tags: ['exercice'], pupil: pupil1, title: 'Récitation tables de multiplication', preferredNotationSystem: NotationSystem.findByTitle('Note sur 20'))
                        .addToEvaluatedSkills(new EvaluatedSkill(skill: evaluationSkill1, value: 0.1))
                def evaluation33 = new Evaluation(tags: ['contrôle'], pupil: pupil1, title: 'Contrôle grands nombres', preferredNotationSystem: NotationSystem.findByTitle('Note sur 20'))
                        .addToEvaluatedSkills(new EvaluatedSkill(skill: evaluationSkill2, missed: true))
                def evaluation34 = new Evaluation(tags: ['contrôle'], pupil: pupil1, title: 'Contrôle calcul', preferredNotationSystem: NotationSystem.findByTitle('Note sur 20'))
                        .addToEvaluatedSkills(new EvaluatedSkill(skill: evaluationSkill3, missed: true))

                def evaluation41 = new Evaluation(tags: ['exercice'], pupil: pupil1, title: 'Récitation tables de multiplication', preferredNotationSystem: NotationSystem.findByTitle('Note sur 20'))
                        .addToEvaluatedSkills(new EvaluatedSkill(skill: evaluationSkill1, value: 0.5))
                def evaluation42 = new Evaluation(tags: ['exercice'], pupil: pupil1, title: 'Récitation tables de multiplication', preferredNotationSystem: NotationSystem.findByTitle('Note sur 20'))
                        .addToEvaluatedSkills(new EvaluatedSkill(skill: evaluationSkill1, value: 0.55))
                def evaluation43 = new Evaluation(tags: ['contrôle'], pupil: pupil1, title: 'Contrôle grands nombres', preferredNotationSystem: NotationSystem.findByTitle('Note sur 20'))
                        .addToEvaluatedSkills(new EvaluatedSkill(skill: evaluationSkill2, value: 0.45))
                def evaluation44 = new Evaluation(tags: ['contrôle'], pupil: pupil1, title: 'Contrôle calcul', preferredNotationSystem: NotationSystem.findByTitle('Note sur 20'))
                        .addToEvaluatedSkills(new EvaluatedSkill(skill: evaluationSkill3, value: 0.575))

                def evaluation51 = new Evaluation(tags: ['exercice'], pupil: pupil1, title: 'Récitation tables de multiplication', preferredNotationSystem: NotationSystem.findByTitle('Note sur 20'))
                        .addToEvaluatedSkills(new EvaluatedSkill(skill: evaluationSkill1, value: 0.95))
                def evaluation52 = new Evaluation(tags: ['exercice'], pupil: pupil1, title: 'Récitation tables de multiplication', preferredNotationSystem: NotationSystem.findByTitle('Note sur 20'))
                        .addToEvaluatedSkills(new EvaluatedSkill(skill: evaluationSkill1, value: 1))
                def evaluation53 = new Evaluation(tags: ['contrôle'], pupil: pupil1, title: 'Contrôle grands nombres', preferredNotationSystem: NotationSystem.findByTitle('Note sur 20'))
                        .addToEvaluatedSkills(new EvaluatedSkill(skill: evaluationSkill2, value: 1))
                def evaluation54 = new Evaluation(tags: ['contrôle'], pupil: pupil1, title: 'Contrôle calcul', preferredNotationSystem: NotationSystem.findByTitle('Note sur 20'))
                        .addToEvaluatedSkills(new EvaluatedSkill(skill: evaluationSkill3, value: 0.9875))


                pupil1
                        .addToEvaluations(evaluation11)
                        .addToEvaluations(evaluation12)
                        .addToEvaluations(evaluation13)
                        .addToEvaluations(evaluation14)
                pupil2
                        .addToEvaluations(evaluation21)
                        .addToEvaluations(evaluation22)
                        .addToEvaluations(evaluation23)
                        .addToEvaluations(evaluation24)
                pupil3
                        .addToEvaluations(evaluation31)
                        .addToEvaluations(evaluation32)
                        .addToEvaluations(evaluation33)
                        .addToEvaluations(evaluation34)
                pupil4
                        .addToEvaluations(evaluation41)
                        .addToEvaluations(evaluation42)
                        .addToEvaluations(evaluation43)
                        .addToEvaluations(evaluation44)
                pupil5
                        .addToEvaluations(evaluation51)
                        .addToEvaluations(evaluation52)
                        .addToEvaluations(evaluation53)
                        .addToEvaluations(evaluation54)

                if (!teacher.save()) {
                    log.error('Error saving teacher.')
                }
            }
        } else {
            log.warn('Cannot insert demo data because no demo user found.')
        }

        log.info('Data initializing done.')
    }

    private static void createNotationSystem(String title, List values, int steps) {
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

    def destroy = {
    }
}
