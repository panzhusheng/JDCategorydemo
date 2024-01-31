package com.example.jdcategorydemo;

import java.util.List;

public class GoodsTypeBN {
    private String type_id;
    private String type_name;
    private String parent_id;
    private String type_lv;
    private List<GoodsTypeBN> nextType;

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getType_lv() {
        return type_lv;
    }

    public void setType_lv(String type_lv) {
        this.type_lv = type_lv;
    }

    public List<GoodsTypeBN> getNextType() {
        return nextType;
    }

    public void setNextType(List<GoodsTypeBN> nextType) {
        this.nextType = nextType;
    }

    @Override
    public String toString() {
        return "GoodsTypeBN{" +
                "type_id='" + type_id + '\'' +
                ", type_name='" + type_name + '\'' +
                ", parent_id='" + parent_id + '\'' +
                ", type_lv='" + type_lv + '\'' +
                ", nextType=" + nextType +
                '}';
    }
}
