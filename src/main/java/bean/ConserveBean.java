package bean;

import java.sql.Date;

public class ConserveBean {
    int con_id;  //养护id
    String con_name;  //养护名称
    String con_desc; //养护描述
    String con_place; //养护地点
    String conby;//养护人
    String createdby;//创建人
    Date con_utime;//更新时间
    Date con_ctime; //创建时间

    private String species_name;


    public int getCon_id() {
        return con_id;
    }

    public void setCon_id(int con_id) {
        this.con_id = con_id;
    }

    public String getCon_name() {
        return con_name;
    }

    public void setCon_name(String con_name) {
        this.con_name = con_name;
    }

    public String getCon_desc() {
        return con_desc;
    }

    public void setCon_desc(String con_desc) {
        this.con_desc = con_desc;
    }

    public String getCon_place() {
        return con_place;
    }

    public void setCon_place(String con_place) {
        this.con_place = con_place;
    }

    public String getConby() {
        return conby;
    }

    public void setConby(String conby) {
        this.conby = conby;
    }

    public Date getCon_ctime() {
        return con_ctime;
    }

    public void setCon_ctime(Date con_ctime) {
        this.con_ctime = con_ctime;
    }

    public Date getCon_utime() {
        return con_utime;
    }
    public void setCon_utime(Date con_utime) {
        this.con_utime = con_utime;
    }
    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getSpecies_name() {
        return species_name;
    }

    public void setSpecies_name(String species_name) {
        this.species_name = species_name;
    }
}
