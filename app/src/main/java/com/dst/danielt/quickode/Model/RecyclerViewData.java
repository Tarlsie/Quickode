package com.dst.danielt.quickode.Model;

/**
 * Created by danielT on 09/08/2017.
 */

// Class for the data that will be parsed from the api network call

public class RecyclerViewData {

    private String image;
    private int imageID;
    private int albumId;
    private String imageTitle;
    private String getThunmbnai;

    public String getThunmbnail() {
        return thunmbnail;
    }

    public void setThunmbnail(String thunmbnail) {
        this.thunmbnail = thunmbnail;
    }

    private String thunmbnail;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }







}
