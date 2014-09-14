package educally

class SkillService {

    def buildHierarchicalSkills(SkillBook skillBook) {

        def hierarchicalSkills = new Expando()

        hierarchicalSkills.rootSkills = skillBook.skills.findAll { it.path == null }

        hierarchicalSkills

    }
}
