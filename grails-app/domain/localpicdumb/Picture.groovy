package localpicdumb




class Picture {
    String folder
    String type
    byte[] image
    static hasMany = [tags: Tag]
    static constraints = {
        image(nullable: true)
    }
}
