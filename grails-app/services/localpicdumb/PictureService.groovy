package localpicdumb

import grails.transaction.Transactional
import org.apache.commons.codec.binary.Base64

@Transactional
class PictureService {

    static transactional = false

    def getTagged(String[] tagList, def offset) {
        Set<Tag> tagsSet = new HashSet<>()
        for (String t : tagList) {
            if (!t.equals(""))
                tagsSet.add(Tag.findByTag(t.trim()))
        }
        if (tagsSet.isEmpty()) {
            return Picture.list(max: 30, offset: offset)
        }
        def pics = Picture.executeQuery('''SELECT picture FROM Picture picture WHERE :numberOfTags =
        (select count(tag.id) from Picture picture2
        inner join picture2.tags tag
        where picture2.id = picture.id
        and tag in (:tags))''', [numberOfTags: Integer.toUnsignedLong(tagsSet.size()), tags: tagsSet], [max   : 30,
                                                                                                        offset: offset])
        pics
    }

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
        def folder = Folder.findByName('random')
        folder.picCount = folder.picCount + 1
        folder.save(flush: true)
        pic.image = outs.toByteArray()
        pic.save(flush: true)
    }
    /* @param id picture id
    *  @param folderStr picture new folderStr
    *  @param tags new picture tags
    * */

    def editPicInfo(int id, String folderStr, String[] tags) {
        def pic = Picture.get(id)
        pic.tags.clear()
        for (String t : tags) {
            if (!t.equals("")) {
                def tag = Tag.findByTag(t)
                if (tag == null) {
                    tag = new Tag(tag: t)
                    tag.addToPics(pic)
                    pic.addToTags(tag)
                } else {
                    tag.addToPics(pic)
                    pic.addToTags(tag)
                }
                tag.save()
            }
        }
        if (Folder.findByName(folderStr) == null) {
            new Folder(name: folderStr, picCount: 1).save(flush: true)
        } else {
            def newExistFolder = Folder.findByName(folderStr)
            newExistFolder.picCount = newExistFolder.picCount + 1
            newExistFolder.save()
        }
        def oldPicFolder = Folder.findByName(pic.folder)
        oldPicFolder.picCount = oldPicFolder.picCount - 1
        oldPicFolder.save()
        pic.folder = folderStr
        pic.save(flush: true)
    }


    static def getTaggedCount(String[] tagList) {
        Set<Tag> tagsSet = new HashSet<>()
        for (String t : tagList) {
            if (!t.equals(""))
                tagsSet.add(Tag.findByTag(t.trim()))
        }
        if (tagsSet.isEmpty()) {
            return Picture.count()
        }
        def count = Picture.executeQuery('''SELECT count(*) FROM Picture picture WHERE :numberOfTags =
        (select count(tag.id) from Picture picture2
        inner join picture2.tags tag
        where picture2.id = picture.id
        and tag in (:tags))''', [numberOfTags: Integer.toUnsignedLong(tagsSet.size()), tags: tagsSet])[0]
        count
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
