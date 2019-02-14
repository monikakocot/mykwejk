package pl.akademiakodu.kwejk.model;


import java.awt.*;

public class Gif {
    private String name;
    private boolean favorite;
    private String username;

//KOD 7
    private int categoryId;
//KOD 7

    public Gif(String name,boolean favorite) {
        this.name = name;
        this.favorite = favorite;
    }

    public Gif(String name, boolean favorite, int categoryId) {
        this.name = name;
        this.favorite = favorite;
        this.categoryId = categoryId;
    }

    public Gif(String name, boolean favorite, String username, int categoryId) {
        this.name = name;
        this.favorite = favorite;
        this.categoryId = categoryId;
        this.username = username;
    }

    //GETTERS,SETTERS
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//KOD 10

    public String getThymeleafFilePath(){
        return new StringBuilder("").append("/gifs/").append(getName()).append(".gif").toString();
    }

  /*  public String getThymeleafFilePathForNew(){
        return new StringBuilder("").append("kwejk/new/").append("pies").append(".gif").toString();
    }
  */

    /*** another option
     *    public String getThymeleafFilePath(){
     return "/gifs/" + "getName()" + ".gif";
     }
     */

}
