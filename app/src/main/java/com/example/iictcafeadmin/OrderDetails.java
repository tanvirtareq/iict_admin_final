package com.example.iictcafeadmin;

public class OrderDetails {

    public String oid;
    public String uid;
    public String display_name;
    public String table_no;

    public String getTable_no() {
        return table_no;
    }

    public String getTable_or_room() {
        return table_or_room;
    }

    public void setTable_or_room(String table_or_room) {
        this.table_or_room = table_or_room;
    }

    public String table_or_room;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String details;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getTableno() {
        return table_no;
    }

    public void setTable_no(String table_no) {
        this.table_no = table_no;
    }
}


//public class OrderDetails {
//
//    public String display_name;
//    public String order;
//    public String tableno;
//    public String oid;
//
//    public String getTableno() {
//        return tableno;
//    }
//
//    public void setTableno(String tableno) {
//        this.tableno = tableno;
//    }
//
//    public OrderDetails(){
//    }
//
//    public OrderDetails(String display_name, String order) {
//        this.display_name = display_name;
//        this.order = order;
//    }
//
//    public void setDisplay_name(String display_name) {
//        this.display_name = display_name;
//    }
//
//    public void setOrder(String order) {
//        this.order = order;
//    }
//
//    public String getDisplay_name() {
//        return display_name;
//    }
//
//    public String getOrder() {
//        return order;
//    }
//
//    public String getOid() {
//        return oid;
//    }
//
//    public void setOid(String oid) {
//        this.oid = oid;
//    }
//}