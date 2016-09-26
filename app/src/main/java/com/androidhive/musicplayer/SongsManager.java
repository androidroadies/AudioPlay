package com.androidhive.musicplayer;

import android.os.Environment;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class SongsManager {
	final String MEDIA_PATH = Environment.getExternalStorageDirectory()
			.getPath() + "/";
	private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
	private String mp3Pattern = ".mp3";

	// Constructor
	public SongsManager() {

	}

	public ArrayList<HashMap<String, String>> getPlayList() {
		System.out.println(MEDIA_PATH);
		if (MEDIA_PATH != null) {
			File home = new File(MEDIA_PATH);
			File[] listFiles = home.listFiles();
			if (listFiles != null && listFiles.length > 0) {
				for (File file : listFiles) {
					System.out.println(file.getAbsolutePath());
					if (file.isDirectory()) {
						scanDirectory(file);
					} else {
						addSongToList(file);
					}
				}
			}
		}
		// return songs list array
		return songsList;
	}

	private void scanDirectory(File directory) {
		if (directory != null) {
			File[] listFiles = directory.listFiles();
			if (listFiles != null && listFiles.length > 0) {
				for (File file : listFiles) {
					if (file.isDirectory()) {
						scanDirectory(file);
					} else {
						addSongToList(file);
					}

				}
			}
		}
	}

	private void addSongToList(File song) {
		if (song.getName().endsWith(mp3Pattern)) {
			HashMap<String, String> songMap = new HashMap<String, String>();
			songMap.put("songTitle",
					song.getName().substring(0, (song.getName().length() - 4)));
			songMap.put("songPath", song.getPath());

			// Adding each song to SongList
			songsList.add(songMap);
		}
	}

//	======================================
	/**
	 * Function to read all mp3 files from sdcard
	 * and store the details in ArrayList
	 * */
//	public ArrayList<HashMap<String, String>> getPlayList(){
//		File home = new File(MEDIA_PATH);
//
//		if (home.listFiles(new FileExtensionFilter()).length > 0) {
//			for (File file : home.listFiles(new FileExtensionFilter())) {
//				HashMap<String, String> song = new HashMap<String, String>();
//				song.put("songTitle", file.getName().substring(0, (file.getName().length() - 4)));
//				song.put("songPath", file.getPath());
//
//				// Adding each song to SongList
//				songsList.add(song);
//			}
//		}
//		// return songs list array
//		return songsList;
//	}
	
	/**
	 * Class to filter files which are having .mp3 extension
	 * */
	class FileExtensionFilter implements FilenameFilter {
		public boolean accept(File dir, String name) {
			return (name.endsWith(".mp3") || name.endsWith(".MP3"));
		}
	}
}
