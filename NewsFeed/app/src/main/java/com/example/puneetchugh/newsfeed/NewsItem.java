package com.example.puneetchugh.newsfeed;

/**
 * Created by puneetchugh on 6/29/16.
 */
public class NewsItem {
    private String webTitle;
    private String webUrl;
    private String sectionName;

    public NewsItem(String webTitle, String webUrl, String sectionName){
        this.webTitle = webTitle;
        this.webUrl = webUrl;
        this.sectionName = sectionName;
    }

    public String getWebTitle(){

        return webTitle;
    }

    public String getWebUrl(){
        return webUrl;
    }

    public String getSectionName(){
        return sectionName;
    }

}
