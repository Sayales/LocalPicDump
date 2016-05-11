package localpicdumb

class Folder {

    String name

    static constraints = {
        name(unique: true)
    }
}
