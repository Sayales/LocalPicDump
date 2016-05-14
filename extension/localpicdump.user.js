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



function main() {
    //Below is the userscript code itself
    var domReady = function (callback) {
        document.readyState === "interactive" || document.readyState === "complete" ? callback() : document.addEventListener("DOMContentLoaded", callback);
    };
    domReady(function () {
        var arr = document.getElementsByClassName("file-attr");
        for (var i = 0; i < arr.length; i++) {
            var currElem = arr[i];
            var elem = document.createElement("a");
            var currElemType = (currElem.getElementsByClassName('desktop')[0].href.indexOf('webm') == -1)
            elem.href = "http://localhost:1337/LocalPicDumb/picture/downloadFromUrl?picUrl=" + currElem.getElementsByClassName('desktop')[0].href;
            elem.text = 'To localPicDump';
            elem.target = '_blank';
            elem.setAttribute("class", "local-pic-dump");
            if (currElemType && currElem.getElementsByClassName("local-pic-dump").length < 1) {
                currElem.appendChild(elem)
            }
        }
    });
    prevHeight = document.body.offsetHeight;
}
main();
var prevHeight;
window.onscroll = function (ev) { //лютый костыль для бесконечной прокрутки
    if (document.body.offsetHeight > prevHeight) {
        main();
    }
    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
        prevHeight = document.body.offsetHeight
    }
};
/* }*/


