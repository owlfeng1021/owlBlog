package com.owl.owlBlog.bo;

public class Images {
    private String url; // 主面板图片引用路径
    private String image;// 主面板图片路径

    public Images() {
    }

    public Images(String url, String image) {
        this.url = url;
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
