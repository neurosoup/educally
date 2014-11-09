package educally

import grails.plugin.cache.Cacheable
import grails.transaction.Transactional

import java.text.Normalizer

@Transactional
class SkillService {

    def Skill create(SkillBook skillBook, Skill parent, Boolean isLeaf, String title) {

        def normalizedTitle = Normalizer.normalize(title, Normalizer.Form.NFKD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
        def name = isLeaf ? null : normalizedTitle.toLowerCase().replace(' ', '_').replace("'", '_').replace(',', '')

        def elements = null
        if (parent) {
            elements = parent.path ? parent.path.split(',').toList() - "" : new ArrayList<String>()
            elements << parent.name
        }

        def path = elements ? ",${elements.join(',')}," : ''
        def skill = new Skill(name: isLeaf ? null : name, title: title, path: path)

        skillBook.addToSkills(skill)
        skillBook.save()
        skill
    }

    @Cacheable(value = 'skillsBySkillBook', key = "#skillBook.id")
    def getSkillsBySkillBook(SkillBook skillBook) {
        Skill.findAllBySkillBook(skillBook).sort { it.path }
    }

}
