package localpicdumb

import grails.transaction.Transactional
import groovy.sql.Sql


@Transactional
class InitDatabaseService {

    def dataSource
    def grailsApplication

    def initDB() {
        String sqlString = grailsApplication.mainContext.getResource('classpath:/initDB.sql').inputStream.text
        def sql = new Sql(dataSource)
        sqlString.eachLine { line -> sql.execute(line) }
    }
}
