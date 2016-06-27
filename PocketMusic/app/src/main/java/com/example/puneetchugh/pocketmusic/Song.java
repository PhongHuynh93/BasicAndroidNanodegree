package com.example.puneetchugh.pocketmusic;

/**
 * Created by puneetchugh on 6/26/16.
 */
public class Song {

    private String songId;
    private String songTitle;
    private String songArtist;

    public Song(String songId, String songTitle, String songArtist){
        this.songId = songId;
        this.songTitle = songTitle;
        this.songArtist = songArtist;
    }

    public String getSongId(){
        return songId;
    }

    public String getSongTitle(){
        return songTitle;
    }

    public String getSongArtist(){
        return songArtist;
    }

}
