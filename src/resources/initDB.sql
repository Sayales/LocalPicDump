DROP TABLE IF EXISTS folder_picture;
DROP TABLE IF EXISTS folder;
DROP TABLE IF EXISTS picture_tags;
DROP TABLE IF EXISTS tag;
DROP TABLE IF EXISTS picture;
/*CREATE TABLE IF NOT EXISTS picture (
  id      BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  version BIGINT(20)             NOT NULL,
  image   LONGBLOB,
  type    VARCHAR(255)           NOT NULL
);
CREATE TABLE IF NOT EXISTS tag (
  id      BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  version BIGINT(20)             NOT NULL,
  tag     VARCHAR(255)           NOT NULL
);
CREATE TABLE IF NOT EXISTS picture_tags (
  picture_id BIGINT(20) NOT NULL,
  tag_id     BIGINT(20) NOT NULL,
  CONSTRAINT `PRIMARY` PRIMARY KEY (picture_id, tag_id),
  CONSTRAINT FK_4gk2j0ncn8mniwix80d8ijcd5 FOREIGN KEY (picture_id) REFERENCES picture (id),
  CONSTRAINT FK_aqxnadnddfwe21ruait68881m FOREIGN KEY (tag_id) REFERENCES tag (id)
);
*/