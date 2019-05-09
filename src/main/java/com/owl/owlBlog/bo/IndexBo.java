package com.owl.owlBlog.bo;

import java.util.List;

public class IndexBo {
    private List<Images> images;
    private List<Images> titleImages;

    public IndexBo() {
    }

    public IndexBo(List<Images> images, List<Images> titleImages) {
        this.images = images;
        this.titleImages = titleImages;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public List<Images> getTitleImages() {
        return titleImages;
    }

    public void setTitleImages(List<Images> titleImages) {
        this.titleImages = titleImages;
    }
}
