
//Main Class File:   AppStore.java
//File:                  AppRating.java
//Semester:          Fall 2015

//Author:         Ben Leninton
//Email:           lenington@wisc.edu
//CS Login:      lenington
//Lecturer's Name:  Jim Skrentny

public class AppRating {	
	private App app;
	private User user;
	private short rating;
	
	public AppRating(App app, User user, short rating) {
		this.app = app;
		this.user = user;
		this.rating = rating;
	}
	
	public App getApp() {
		return this.app;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public short getRating() {
		return this.rating;
	}
}

