environments {
    development {
        grails {
            mongo {
                host = "ks313064.kimsufi.com"
                port = 27017
                username = "fullUser"
                password = "Fr@gile+123"
                databaseName = "educallydev"
            }
        }
    }
    test {
        grails {
            mongo {
                host = "ks313064.kimsufi.com"
                port = 27017
                username = "fullUser"
                password = "Fr@gile+123"
                databaseName = "educallytest"
            }
        }
    }
    production {
        grails {
            mongo {
                host = "ks313064.kimsufi.com"
                port = 27017
                username = "fullUser"
                password = "Fr@gile+123"
                databaseName = "educally"
            }
        }
    }
}