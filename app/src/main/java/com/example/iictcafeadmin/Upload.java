package com.example.iictcafeadmin;

public class Upload {
    String item_name , item_id;
    Integer price;
    String image_url;

    private boolean avaibality;

    public String getItem_id() {
        return item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public Upload (){

    }
    public Upload(String imageName , String imageUrl, Integer imagePrice){
            this.item_id=imageName;
            this.image_url=imageUrl;
            this.price=imagePrice;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isAvaibality() {
        return avaibality;
    }

    public void setAvaibality(boolean avaibality) {
        this.avaibality = avaibality;
    }
    //    public String getImageName() {
//        return item_id;
//    }
//
//    public String getPrice() {
//        return price;
//    }
//
//    public void setPrice(String price) {
//        this.price = price;
//    }
//
//    public void setImageName(String imageName) {
//        this.item_id = imageName;
//    }
//
//    public String getImageUrl() {
//        return imageUrl;
//    }
//
//    public void setImageUrl(String imageUrl) {
//        this.imageUrl = imageUrl;
//    }
}
