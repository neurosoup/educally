package educally

import educally.security.User
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.ui.RegistrationCode
import grails.transaction.Transactional
import org.codehaus.groovy.grails.compiler.web.async.TransactionalAsyncTransformUtils
import org.springframework.transaction.TransactionStatus
import org.springframework.transaction.interceptor.TransactionAspectSupport


class AccountService {

    def messageSource
    def springSecurityService

    void warnErrors(bean, messageSource, Locale locale = Locale.getDefault()) {
        if (!log.isWarnEnabled()) {
            return
        }

        def message = new StringBuilder(
                "problem ${bean.id ? 'updating' : 'creating'} ${bean.getClass().simpleName}: $bean")
        for (fieldErrors in bean.errors) {
            for (error in fieldErrors.allErrors) {
                message.append("\n\t").append(messageSource.getMessage(error, locale))
            }
        }
        log.warn message
    }

    String encodePassword(String password, salt) {
        def encode = SpringSecurityUtils.securityConfig.ui.encodePassword
        if (!(encode instanceof Boolean)) {
            encode = false
        }
        if (encode) {
            password = springSecurityService.encodePassword(password, salt)
        }
        password
    }

    @Transactional
    def RegistrationCode register(User user, Account account, String clearTextPassword, salt) {

        String usernameFieldName = SpringSecurityUtils.securityConfig.userLookup.usernamePropertyName
        String passwordFieldName = SpringSecurityUtils.securityConfig.userLookup.passwordPropertyName

        String password = encodePassword(clearTextPassword, salt)
        user."$passwordFieldName" = password
        if (!user.save()) {
            warnErrors user, messageSource
            throw new RuntimeException("error saving $user : $user.errors")
        }

        def registrationCode = new RegistrationCode(username: user."$usernameFieldName")
        if (!registrationCode.save()) {
            warnErrors registrationCode, messageSource
            throw new RuntimeException("error saving $registrationCode : $registrationCode.errors")
        }

        if (!account.save()) {
            warnErrors account, messageSource
            throw new RuntimeException("error saving $account : $account.errors")
        }

        registrationCode
    }
}
