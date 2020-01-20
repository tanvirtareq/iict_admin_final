package com.example.iictcafeadmin;

public class Upload {
    private  String imageName;
    private  String price;
    private  String imageUrl;
    public Upload (){

    }
    public Upload(String imageName , String imageUrl,String imagePrice){
            this.imageName=imageName;
            this.imageUrl=imageUrl;
            this.price=imagePrice;
    }

    public String getImageName() {
        return imageName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
