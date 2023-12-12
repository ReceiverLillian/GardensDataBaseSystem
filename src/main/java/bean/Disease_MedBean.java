package bean;

import java.util.Date;

public class Disease_MedBean {
    private int med_id;
    private int dis_id;
    private float dis_mednum;
    private Date dis_ddl;
    private int dis_target;
    private Date dis_ctime;
    private Date dis_utime;

    private String dis_name;
    private String med_name;

    public int getMed_id() {
        return med_id;
    }

    public void setMed_id(int med_id) {
        this.med_id = med_id;
    }

    public int getDis_id() {
        return dis_id;
    }

    public void setDis_id(int dis_id) {
        this.dis_id = dis_id;
    }

    public Date getDis_ddl() {
        return dis_ddl;
    }

    public float getDis_mednum() {
        return dis_mednum;
    }

    public void setDis_mednum(float dis_mednum) {
        this.dis_mednum = dis_mednum;
    }

    public void setDis_ddl(Date dis_ddl) {
        this.dis_ddl = dis_ddl;
    }

    public int getDis_target() {
        return dis_target;
    }

    public void setDis_target(int dis_target) {
        this.dis_target = dis_target;
    }

    public Date getDis_ctime() {
        return dis_ctime;
    }

    public void setDis_ctime(Date dis_ctime) {
        this.dis_ctime = dis_ctime;
    }

    public Date getDis_utime() {
        return dis_utime;
    }

    public void setDis_utime(Date dis_utime) {
        this.dis_utime = dis_utime;
    }

    public String getDis_name() {
        return dis_name;
    }

    public void setDis_name(String dis_name) {
        this.dis_name = dis_name;
    }

    public String getMed_name() {
        return med_name;
    }

    public void setMed_name(String med_name) {
        this.med_name = med_name;
    }
}
