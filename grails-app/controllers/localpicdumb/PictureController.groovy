package localpicdumb

class PictureController {

    PictureService pictureService

    def downloadFromUrl() {
        String urlName = params.picUrl
        pictureService.downloadPic(urlName)
        redirect(action: 'index')
    }


    def index() {
        def max = params.max  ?: 30
        def offset = params.offset ?: 0
        def pics = Picture.list([max: max, offset: offset])
        def imgSources = pictureService.getPictureSrcList(pics)
        def folders = Folder.getAll()
        render(view: 'showAllPics', model: [srcList: imgSources, folders: folders,
                                            picCount: Picture.count(),
                                            paginateAction: 'index'])
    }


    def showTagged() {
        String tags = params.tagName
        def offset = params.offset  ?: 0
        def tagList = tags.split(";")
        def pics = pictureService.getTagged(tagList, offset)
        def picCount = PictureService.getTaggedCount(tagList)
        List<ImageSrc> imgSources = pictureService.getPictureSrcList(pics)
        render(view: 'showAllPics', model: [srcList: imgSources, picCount: picCount,
                                            paginateAction: "showTagged", params:
                                                    [tagName: tags], folders: Folder.list()
        ])
    }


    def showFolder(String id) {
        def pics = Picture.findAllByFolder(id,[max: 30, offset: params.offset])
        List<ImageSrc> imgSources = pictureService.getPictureSrcList(pics)
        render(view: 'showAllPics', model: [srcList: imgSources, picCount: Folder.findByName(id).picCount, paginateAction: "showFolder",
                                            additionalInfo: id, folders: Folder.list()])
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
        def tagsSplitted = tagString?.split(";");
        pictureService.editPicInfo(id, folder, tagsSplitted)
        redirect(action: 'index')
    }

    def remove(String id) {
        def pic = Picture.get(Integer.valueOf(id))
        pic.delete(flush: true)
        redirect(action: 'index')
    }
}
