package educally

class SkillController {

    def skillService

    def index() {
        SkillBook skillBook = SkillBook.get(params.int('skillBookId'))
        skillService.getSkillsBySkillBook(skillBook)
    }
}
