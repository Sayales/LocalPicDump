import localpicdumb.Folder
class BootStrap {


    def init = { servletContext ->
       new Folder(name: 'random').save()
    }
    def destroy = {
    }
}
