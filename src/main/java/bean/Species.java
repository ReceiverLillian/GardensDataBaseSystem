package bean;

import java.util.Date;

public class Species {
    private int speciesId;
    private String speciesName;
    private String speciesOtherName;
    private String familyName;
    private String genusName;
    private String speciesMorph;
    private String speciesTech;
    private String speciesAppl;
    private String pictureDescri;
    private String picturePlace;
    private String photoedBy;
    private String province;  //分布区域
    private String environment;  //生长环境
    private String createdby;  //创建人员
    private Date createTime;
    private Date updateTime;

    public Species(){}
    public Species(int speciesId, String speciesName, String speciesOtherName, String familyName, String genusName, String speciesMorph, String speciesTech, String speciesAppl, String pictureDescri, String picturedPlace, String photoedBy) {
        this.speciesId = speciesId;
        this.speciesName = speciesName;
        this.speciesOtherName = speciesOtherName;
        this.familyName = familyName;
        this.genusName = genusName;
        this.speciesMorph = speciesMorph;
        this.speciesTech = speciesTech;
        this.speciesAppl = speciesAppl;
        this.pictureDescri = pictureDescri;
        this.picturePlace = picturedPlace;
        this.photoedBy = photoedBy;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public void setSpeciesId(int speciesId) {
        this.speciesId = speciesId;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }


    public void setSpeciesOtherName(String speciesOtherName) {
        this.speciesOtherName = speciesOtherName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setGenusName(String genusName) {
        this.genusName = genusName;
    }

    public void setSpeciesMorph(String speciesMorph) {
        this.speciesMorph = speciesMorph;
    }

    public void setSpeciesTech(String speciesTech) {
        this.speciesTech = speciesTech;
    }

    public void setSpeciesAppl(String speciesAppl) {
        this.speciesAppl = speciesAppl;
    }

    public void setPictureDescri(String pictureDescri) {
        this.pictureDescri = pictureDescri;
    }

    public void setPicturePlace(String picturePlace) {
        this.picturePlace = picturePlace;
    }

    public void setPhotoedBy(String photoedBy) {
        this.photoedBy = photoedBy;
    }

    public int getSpeciesId() {
        return speciesId;
        //return 12;
    }

    public String getSpeciesName() {
        System.out.println("当前通过getSpeciesName()获得的植物名为"+speciesName);
        //return "植物名测试用";
        return speciesName;
    }

    public String getSpeciesOtherName() {
        return speciesOtherName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getGenusName() {
        return genusName;
    }

    public String getSpeciesMorph() {
        return speciesMorph;
    }

    public String getSpeciesTech() {
        return speciesTech;
    }

    public String getSpeciesAppl() {
        return speciesAppl;
    }

    public String getPictureDescri() {
        return pictureDescri;
    }

    public String getPicturePlace() {
        return picturePlace;
    }

    public String getPhotoedBy() {
        return photoedBy;
    }

    @Override
    public String toString() {
        //return "test用。";
        return "Species{" +
                "speciesId=" + speciesId +
                ", speciesName='" + speciesName + '\'' +
                ", speciesOtherName='" + speciesOtherName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", genusName='" + genusName + '\'' +
                ", speciesMorph='" + speciesMorph + '\'' +
                ", speciesTech='" + speciesTech + '\'' +
                ", speciesAppl='" + speciesAppl + '\'' +
                ", pictureDescri='" + pictureDescri + '\'' +
                ", picturePlace='" + picturePlace + '\'' +
                ", photoedBy='" + photoedBy + '\'' +
                '}';
    }
}
