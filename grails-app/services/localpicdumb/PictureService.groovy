package localpicdumb

import grails.transaction.Transactional
import org.apache.commons.codec.binary.Base64

@Transactional
class PictureService {

    static transactional = false

    def downloadPic(String urlName) {
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
        String revUrl = urlName.reverse()
        def extension = revUrl.split("\\.")[0].reverse() // Picture extension
        def pic = new Picture(folder: 'random', type: extension)
        pic.image = outs.toByteArray()
        pic.save(flush: true)
    }

    def addTag(String id, def param) {
        def pic = Picture.get(id)
        def tag = Tag.findByTag(param)
        if (tag == null) {
            tag = new Tag(tag: param)
            tag.addToPics(pic)
            pic.addToTags(tag)
        }
        else {
            tag.addToPics(pic)
            pic.addToTags(tag)
        }
        tag.save(flush: true)
        pic.save(flush: true)
    }

    static def getPictureSrcList(def pics) {
        List<String> imgSources = new ArrayList<>();
        for (Picture pic : pics) {
            imgSources.add("data:image/" + pic.type + ";base64," + Base64.encodeBase64String(pic.image));
        }
        imgSources
    }
}
