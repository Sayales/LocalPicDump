package localpicdumb

import  org.apache.commons.codec.binary.Base64 as Base64


class PictureController {

    PictureService pictureService

   // def scaffold = Picture

    def downloadPicGet() {
        render (view: 'downloadPage')
    }

    def downloadFromUrl() {
        String urlName = params.picUrl
        pictureService.downloadPic(urlName)
        redirect (action: 'index') //temp
    }

    def showPic(String id) {
        def pic = Picture.findById(Integer.valueOf(id))
        String imgSrc = "data:image/" + pic.type + ";base64," + Base64.encodeBase64String(pic.image) // src for image
        render (view: 'showPic', model:  [imageSrc: imgSrc])
    }

    def index() {
        def pics = Picture.findAll()
        def imgSources = pictureService.getPictureSrcList(pics)
        render (view: 'showPic', model: [srcList: imgSources])
    }

    def addTag(String id) {
        pictureService.addTag(id,params.tag)
        redirect (action: 'index')
    }

    def showTagged(String id) {
        def pics = Tag.findByTag(id).pics
        List<String> imgSources = pictureService.getPictureSrcList(pics)
        render (view: 'showPic', model: [srcList: imgSources])
    }

    def showFolder(String id) {
        def pics = Picture.findAllByFolder(id)
        List<String> imgSources = pictureService.getPictureSrcList(pics)
        render (view: 'showPic', model: [srcList: imgSources])
    }
}
