package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class SpotifyRepository {
    public HashMap<Artist, List<Album>> artistAlbumMap;
    public HashMap<Album, List<Song>> albumSongMap;
    public HashMap<Playlist, List<Song>> playlistSongMap;
    public HashMap<Playlist, List<User>> playlistListenerMap;
    public HashMap<User, Playlist> creatorPlaylistMap;
    public HashMap<User, List<Playlist>> userPlaylistMap;
    public HashMap<Song, List<User>> songLikeMap;

    public List<User> users;
    public List<Song> songs;
    public List<Playlist> playlists;
    public List<Album> albums;
    public List<Artist> artists;

    public SpotifyRepository(){
        //To avoid hitting apis multiple times, initialize all the hashmaps here with some dummy data
        artistAlbumMap = new HashMap<>();
        albumSongMap = new HashMap<>();
        playlistSongMap = new HashMap<>();
        playlistListenerMap = new HashMap<>();
        creatorPlaylistMap = new HashMap<>();
        userPlaylistMap = new HashMap<>();
        songLikeMap = new HashMap<>();

        users = new ArrayList<>();
        songs = new ArrayList<>();
        playlists = new ArrayList<>();
        albums = new ArrayList<>();
        artists = new ArrayList<>();
    }

    public User createUser(String name, String mobile) {
        User user = new User();
        user.setName(name);
        user.setMobile(mobile);
        users.add(user);
        return user;
    }

    public Artist createArtist(String name) {
        Artist artist = new Artist();
        artist.setName(name);
        artist.setLikes(0);
        artists.add(artist);
        return artist;

    }

    public Album createAlbum(String title, String artistName) { //album Must has [ title & artistName ]
        Artist artist_x = null;

        for(Artist artist:artists){ // check existing artist
            if(artist.getName()==artistName){
                artist_x=artist;
                break;
            }
        }
        if(artist_x==null){ //not found any existing artist creat new one
            artist_x = createArtist(artistName);

            Album album = new Album();

            album.setTitle(title);
            album.setReleaseDate(new Date());

            albums.add(album);

            List<Album> l = new ArrayList<>();
            l.add(album);
            artistAlbumMap.put(artist_x,l);

            return album;
        }else { // found----------------
            Album album = new Album();

            album.setTitle(title);
            album.setReleaseDate(new Date());

            albums.add(album);

            List<Album> l = artistAlbumMap.get(artist_x);
            if(l == null){
                l = new ArrayList<>();
            }
            l.add(album);
            artistAlbumMap.put(artist_x,l);

            return album;
        }
    }
//
//    public Song createSong(String title, String albumName, int length) throws Exception{
//    }
//
//    public Playlist createPlaylistOnLength(String mobile, String title, int length) throws Exception {
//
//    }
//
//    public Playlist createPlaylistOnName(String mobile, String title, List<String> songTitles) throws Exception {
//
//    }
//
//    public Playlist findPlaylist(String mobile, String playlistTitle) throws Exception {
//
//    }
//
//    public Song likeSong(String mobile, String songTitle) throws Exception {
//
//    }
//
//    public String mostPopularArtist() {
//    }
//
//    public String mostPopularSong() {
//    }
}
