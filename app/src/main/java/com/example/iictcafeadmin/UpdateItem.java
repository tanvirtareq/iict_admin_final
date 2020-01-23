package com.example.iictcafeadmin;

public class UpdateItem {

    public String item_name, item_id;
    public boolean avaibality;

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public boolean isAvaibality() {
        return avaibality;
    }

    public void setAvaibality(boolean avaibality) {
        this.avaibality = avaibality;
    }
}
