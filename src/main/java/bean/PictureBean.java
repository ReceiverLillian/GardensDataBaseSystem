package bean;

import java.sql.Blob;

public class PictureBean {
    int picture_id;
    String phototedby;
    String picture_discri;

    Blob picture_content;
    String picture_place;

    public void setPicture_id(int picture_id) {
        this.picture_id = picture_id;
    }

    public int getPicture_id() {
        return picture_id;
    }

    public Blob getPicture_content() {
        return picture_content;
    }

    public String getPicture_discri() {
        return picture_discri;
    }

    public String getPicture_place() {
        return picture_place;
    }

    public String getPhototedby() {
        return phototedby;
    }

    public void setPicture_content(Blob picture_content) {
        this.picture_content = picture_content;
    }

    public void setPicture_discri(String picture_discri) {
        this.picture_discri = picture_discri;
    }

    public void setPicture_place(String picture_place) {
        this.picture_place = picture_place;
    }

    public void setPhototedby(String phototedby) {
        this.phototedby = phototedby;
    }

}
