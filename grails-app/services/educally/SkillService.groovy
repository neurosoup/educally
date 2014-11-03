package educally

import grails.transaction.Transactional
import sun.invoke.empty.Empty

import java.text.Normalizer

@Transactional
class SkillService {

    def Skill create(SkillBook skillBook, Skill parent, Boolean isLeaf, String title) {

        def normalizedTitle = Normalizer.normalize(title, Normalizer.Form.NFKD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
        def name = isLeaf ? null : normalizedTitle.toLowerCase().replace(' ', '_').replace("'", '_')

        String[] elements = null
        if (parent) {
            elements = parent.path.split(',')
            elements << parent.name
        }

        def path = elements ? ",${elements.join(',')}," : ''
        //def path = parent ? parent.path.isEmpty() ? "$parent.name" : "$parent.path,$parent.name," : ''

        def skill = new Skill(name: isLeaf ? null : name, title: title, path: path)

        skillBook.addToSkills(skill)
        skillBook.save()
        skill
    }
}
