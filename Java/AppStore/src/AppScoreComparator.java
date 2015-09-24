import java.util.Comparator;

//Main Class File:   AppStore.java
//File:                   AppScoreComparator.java
//Semester:          Fall 2015

//Author:         Ben Leninton
//Email:           lenington@wisc.edu
//CS Login:      lenington
//Lecturer's Name:  Jim Skrentny

public class AppScoreComparator implements Comparator<App> {
	
	@Override
	public int compare(App app1, App app2) {
		if(app1.getAppScore()>app2.getAppScore()){
			return 1;
		}
		else{
			return -1;
		}
	}

}
