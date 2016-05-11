package localpicdumb

import grails.transaction.Transactional
import org.apache.commons.codec.binary.Base64
import localpicdumb.Folder

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
    /* @param id picture id
    *  @param folder picture new folder
    *  @param tags new picture tags
    * */
    def editPicInfo(int id, String folder, String[] tags) {
        def pic = Picture.get(id)
        pic.tags.clear()
        for (String t : tags) {
            def tag = Tag.findByTag(t)
            if (tag == null) {
                tag = new Tag(tag: t)
                tag.addToPics(pic)
                pic.addToTags(tag)
            } else {
                tag.addToPics(pic)
                pic.addToTags(tag)
            }
            tag.save(flush: true)
        }
        if (Folder.findByName(folder) == null) {
            new Folder(name: folder).save(flush: true)
        }
        pic.folder = folder
        pic.save(flush: true)
    }



    static def getPictureSrcList(def pics) {
        List<ImageSrc> imgSources = new ArrayList<>();
        for (Picture pic : pics) {
            String src = getPictureSrc(pic)
            imgSources.add(new ImageSrc(id: pic.id, src: src));
        }
        imgSources
    }

    static def getPictureSrc(Picture pic) {
        String src = "data:image/" + pic.type + ";base64," + Base64.encodeBase64String(pic.image)
        src
    }
}
