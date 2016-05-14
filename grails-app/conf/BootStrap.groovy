import groovy.sql.Sql
import localpicdumb.Folder
class BootStrap {

    def dataSource

    def init = { servletContext ->
       new Folder(name: 'random').save()
        def sql = new Sql(dataSource)
        sql.execute("SET GLOBAL max_allowed_packet=536870912;")
    }
    def destroy = {
    }
}
