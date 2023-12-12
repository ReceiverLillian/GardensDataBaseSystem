package bean;

import java.util.Date;

public class MonitorBean {
    private int mon_id;
    private Date mon_time;
    private String mon_place;
    private String createdby;
    private String monby;
    private Date mon_utime;
    private Date mon_ctime;
    private int mon_target;
    private String species_name;
    private String species_othername;

    public int getMon_id() {
        return mon_id;
    }

    public void setMon_id(int mon_id) {
        this.mon_id = mon_id;
    }

    public Date getMon_time() {
        return mon_time;
    }

    public void setMon_time(Date mon_time) {
        this.mon_time = mon_time;
    }

    public String getMon_place() {
        return mon_place;
    }

    public void setMon_place(String mon_place) {
        this.mon_place = mon_place;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getMonby() {
        return monby;
    }

    public void setMonby(String monby) {
        this.monby = monby;
    }

    public Date getMon_utime() {
        return mon_utime;
    }

    public void setMon_utime(Date mon_utime) {
        this.mon_utime = mon_utime;
    }

    public Date getMon_ctime() {
        return mon_ctime;
    }

    public void setMon_ctime(Date mon_ctime) {
        this.mon_ctime = mon_ctime;
    }

    public int getMon_target() {
        return mon_target;
    }

    public void setMon_target(int mon_target) {
        this.mon_target = mon_target;
    }

    public String getSpecies_name() {
        return species_name;
    }

    public void setSpecies_name(String species_name) {
        this.species_name = species_name;
    }

    public String getSpecies_othername() {
        return species_othername;
    }

    public void setSpecies_othername(String species_othername) {
        this.species_othername = species_othername;
    }
}
