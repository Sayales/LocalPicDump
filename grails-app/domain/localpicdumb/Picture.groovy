package localpicdumb

import pl.burningice.plugins.image.ast.DBImageContainer


class Picture {
    int id
    String folder
    byte[] image
    static constraints = {
        image(nullable: true)
    }
}
