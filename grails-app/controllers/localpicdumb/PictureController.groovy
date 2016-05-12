package localpicdumb

class PictureController {

    PictureService pictureService

    // def scaffold = Picture


    def downloadFromUrl() {
        String urlName = params.picUrl
        pictureService.downloadPic(urlName)
        redirect(action: 'index') //temp
    }


    def index() {

        def pics = Picture.findAll()
        def imgSources = pictureService.getPictureSrcList(pics)
        def folders = Folder.getAll()
        render(view: 'showAllPics', model: [srcList: imgSources, folders: folders, picCount: Picture.count()])
    }

    def list() {
        [pictures: Picture.list(params), picCount: Picture.count()]
    }

    def showTagged() {
        String tags = params.tagName
        def tagList = tags.split(";")
        Set<Tag> tagsSet = new HashSet<>()
        for (String t : tagList) {
            tagsSet.add(Tag.findByTag(t.trim()))
        }
        def pics = Picture.executeQuery('''SELECT picture FROM Picture picture WHERE :numberOfTags =
        (select count(tag.id) from Picture picture2
        inner join picture2.tags tag
        where picture2.id = picture.id
        and tag in (:tags))''', [numberOfTags: Integer.toUnsignedLong(tagsSet.size()), tags: tagsSet])
        List<ImageSrc> imgSources = pictureService.getPictureSrcList(pics)
        render(view: 'showAllPics', model: [srcList: imgSources])
    }

    def showFolder(String id) {
        def pics = Picture.findAllByFolder(id)
        List<ImageSrc> imgSources = pictureService.getPictureSrcList(pics)
        render(view: 'showAllPics', model: [srcList: imgSources])
    }

    def editPic(String id) {
        def pic = Picture.get(Integer.valueOf(id))
        def tags = pic.getTags()
        String tagStr = ""
        for (Tag t : tags) {
            def prepTag = t.tag + ";"
            tagStr += prepTag
        }
        render(view: 'showPic', model: [src: PictureService.getPictureSrc(pic), picId: id, folder: pic.folder, tagString: tagStr])
    }

    def picEdition() {
        String folder = params.folderName
        String tagString = params.tagString
        int id = Integer.valueOf((String) params.picId)
        def tagsSplitted = tagString.split(";");
        pictureService.editPicInfo(id, folder, tagsSplitted)
        redirect(action: 'index')
    }
}
