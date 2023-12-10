package bean;

import java.util.Date;

public class QuotaBean {
    int quo_id;
    String quo_name;
    float quo_value;
    Date quo_time;

    public int getQuo_id() {
        return quo_id;
    }

    public void setQuo_id(int quo_id) {
        this.quo_id = quo_id;
    }

    public String getQuo_name() {
        return quo_name;
    }

    public void setQuo_name(String quo_name) {
        this.quo_name = quo_name;
    }

    public float getQuo_value() {
        return quo_value;
    }

    public void setQuo_value(float quo_value) {
        this.quo_value = quo_value;
    }

    public Date getQuo_time() {
        return quo_time;
    }

    public void setQuo_time(Date quo_time) {
        this.quo_time = quo_time;
    }
}
