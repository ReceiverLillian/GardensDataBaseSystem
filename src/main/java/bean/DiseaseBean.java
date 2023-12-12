package bean;

import java.sql.Date;

public class DiseaseBean {
    private String dis_name;
    private int dis_id;
    private String dis_tech;
    private String dis_medi;
    private String dis_mednum;
    private String dis_ddl;
    private String createdby;
    private Date createdtime;

    private Date updatetime;

    private String dis_target;

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getCreatedby() {
        return createdby;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public int getDis_id() {
        return dis_id;
    }

    public String getDis_ddl() {
        return dis_ddl;
    }

    public String getDis_medi() {
        return dis_medi;
    }

    public String getDis_mednum() {
        return dis_mednum;
    }

    public String getDis_name() {
        return dis_name;
    }

    public String getDis_tech() {
        return dis_tech;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public void setDis_ddl(String dis_ddl) {
        this.dis_ddl = dis_ddl;
    }

    public void setDis_id(int dis_id) {
        this.dis_id = dis_id;
    }

    public void setDis_medi(String dis_medi) {
        this.dis_medi = dis_medi;
    }

    public void setDis_mednum(String dis_mednum) {
        this.dis_mednum = dis_mednum;
    }

    public void setDis_name(String dis_name) {
        this.dis_name = dis_name;
    }

    public void setDis_tech(String dis_tech) {
        this.dis_tech = dis_tech;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getDis_target() {
        return dis_target;
    }

    public void setDis_target(String dis_target) {
        this.dis_target = dis_target;
    }
}
