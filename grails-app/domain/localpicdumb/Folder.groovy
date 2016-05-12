package localpicdumb

class Folder {

    String name
    long picCount

    static constraints = {
        name(unique: true)
    }
}
