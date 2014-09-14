package educally

import grails.async.*

class AsyncEvaluationService {

    @DelegateAsync EvaluationService evaluationService

}
