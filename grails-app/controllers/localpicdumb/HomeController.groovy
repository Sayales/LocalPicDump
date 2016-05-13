package localpicdumb

class HomeController {

    def index() {
        redirect (controller: 'picture', action: 'index')
    }
}
