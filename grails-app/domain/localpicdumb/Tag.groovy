package localpicdumb

class Tag {

    int id
    String tag
    static hasMany = [pics: Picture]
    static constraints = {
    }
}
