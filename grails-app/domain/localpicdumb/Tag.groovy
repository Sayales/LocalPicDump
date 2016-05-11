package localpicdumb

class Tag {

    String tag
    Set<Picture> pics = []
    static hasMany = [pics: Picture]
    static belongsTo = Picture
    static constraints = {
    }
}
