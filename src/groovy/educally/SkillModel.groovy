package educally

import educally.Evaluation
import educally.Skill

/**
 * Created by lawrence on 05/10/14.
 */
class SkillModel {
    Skill skill
    Map<Evaluation, List<Skill>> evaluations
    def stats = [:]
}
