package localpicdumb

import  org.apache.commons.codec.binary.Base64 as Base64

class PictureController {

    def scaffold = Picture

    def downloadPicGet() {
        render (view: 'downloadPage')
    }

    def downloadFromUrl() {
        String urlName = params.picUrl
        URL url = new URL(urlName)
        InputStream puts = new BufferedInputStream(url.openStream())
        ByteArrayOutputStream outs = new ByteArrayOutputStream()
        byte[] buf = new byte[1024]
        int n = 0
        while (-1 != (n = puts.read(buf))) {
            outs.write(buf, 0, n)
        }
        outs.close()
        puts.close()
        byte[] response = outs.toByteArray()
        def pic = Picture.findById(1)
        pic.image = response
        pic.save(flush: true)
        render 'pic added  from ${urlName}'
    }

    def showPic() {
        def pic = Picture.findById(1)
        String imgSrc = "data:image/png;base64," + Base64.encodeBase64String(pic.image)
        render (view: 'showPic', model:  [imageSrc: imgSrc])
    }
}
