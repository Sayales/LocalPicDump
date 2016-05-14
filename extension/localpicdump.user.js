/**
 * Created by Павел on 13.05.2016.
 */
// ==UserScript==
// @name local-pic-dump
// @description User script for local pic dumping on 2ch
// @author Opokin Pavel
// @license MIT
// @version 0.1
// @include https://2ch.hk/*


    // normalized window


    // additional url check.
    // Google Chrome do not treat @match as intended sometimes.
   /* if (/https:\/\/2ch.hk/.test(w.location.href)) {*/
        //Below is the userscript code itself

        var arr = document.getElementsByClassName("file-attr");
        for (var i = 0; i < arr.length; i++) {
            var currElem = arr[i];
            var elem = document.createElement("a");
            var currElemType = (currElem.getElementsByClassName('desktop')[0].href.indexOf('webm') == -1)
            elem.href = "http://localhost:1337/LocalPicDumb/picture/downloadFromUrl?picUrl=" + currElem.getElementsByClassName('desktop')[0].href;
            elem.text = 'To localPicDump';
            elem.target = '_blank';
            if (currElemType) {
                arr[i].appendChild(elem)
            }
        }

   /* }*/


