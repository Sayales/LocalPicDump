package localpicdumb

class PictureController {

    PictureService pictureService

   // def scaffold = Picture


    def downloadFromUrl() {
        String urlName = params.picUrl
        pictureService.downloadPic(urlName)
        redirect (action: 'index') //temp
    }



    def index() {
        def pics = Picture.findAll()
        def imgSources = pictureService.getPictureSrcList(pics)
        def folders = Folder.getAll()
        render (view: 'showAllPics', model: [srcList: imgSources, folders: folders])
    }

    /*def addTag(String id) {
        pictureService.addTag(id,params.tag)
        redirect (action: 'index')
    }*/

    def showTagged() {
        String tags = params.tagName
        def tagList = tags.split("#")
        Set<Picture> pics = new HashSet<>();
        for (int i in 1..tagList.length - 1) {
            String t = tagList[i]
            def taggedPics = Tag.findByTag(t.trim())?.pics
            if (taggedPics != null)
                pics.addAll(taggedPics)
        }
        List<ImageSrc> imgSources = pictureService.getPictureSrcList(pics)
        render (view: 'showAllPics', model: [srcList: imgSources])
    }

    def showFolder(String id) {
        def pics = Picture.findAllByFolder(id)
        List<ImageSrc> imgSources = pictureService.getPictureSrcList(pics)
        render (view: 'showAllPics', model: [srcList: imgSources])
    }

    def editPic(String id) {
        def pic = Picture.get(Integer.valueOf(id))
        def tags = pic.getTags()
        String tagStr = ""
        for (Tag t : tags) {
            def prepTag = t.tag + ";"
            tagStr += prepTag
        }
        render (view: 'showPic', model: [src: PictureService.getPictureSrc(pic), picId: id ,tagString: tagStr])
    }

    def picEdition() {
        String folder = params.folderName
        String tagString = params.tagString
        int id = Integer.valueOf((String)params.picId)
        def tagsSplitted = tagString.split(";");
        pictureService.editPicInfo(id, folder, tagsSplitted)
        redirect (action: 'index')
    }
}
