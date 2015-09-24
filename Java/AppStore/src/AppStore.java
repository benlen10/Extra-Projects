import java.io.*;
import java.time.Instant;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

//Title:            App Store
//Files:            App.java, AppRating.java, AppScoreComparator.java
//Semester:     Fall 2015
//
//Author:         Ben Leninton
//Email:           lenington@wisc.edu
//CS Login:      lenington
//Lecturer's Name:  Jim Skrentny

public class AppStore {
	
	private static AppStoreDB appStoreDB = new AppStoreDB();
	private static User appUser = null;
	private static Scanner scanner = null;
	
	public static void main(String args[]) {
		if (args.length < 4) {			
			System.err.println("Bad invocation! Correct usage: "
					+ "java AppStore <UserDataFile> <CategoryListFile> "
					+ "<AppDataFile> <AppActivityFile>");
			System.exit(1);
		}
				
		boolean didInitialize = 
				initializeFromInputFiles(args[0], args[1], args[2], args[3]);
		
		if(!didInitialize) {
			System.err.println("Failed to initialize the application!");
			System.exit(1);
		}
		appStoreDB.initial = false;     
		System.out.println("Welcome to the App Store!\n"
				   + "Start by browsing the top free and the top paid apps "
				   + "today on the App Store.\n"
				   + "Login to download or upload your favorite apps.\n");

		processUserCommands();
	}
	
	private static boolean initializeFromInputFiles(String userDataFile, String 
			categoryListFile, String appDataFile, String appActivityFile)  {

		String email;
		String firstName;
		String lastName;
		String password;
		String country;
		String type;
		String category;
		
		try{
		BufferedReader userData = new BufferedReader(new FileReader(userDataFile));
		BufferedReader categoryList = new BufferedReader(new FileReader(categoryListFile));
		BufferedReader appData = new BufferedReader(new FileReader(appDataFile));
		BufferedReader appActivity = new BufferedReader(new FileReader(appActivityFile));

		
		//SCAN USERDATA
		StringBuilder sb = new StringBuilder();  
		char c;
		int tmp;

		while((tmp = userData.read())!=-1){
			c = (char) tmp;                             //Parse Email
		while(c!=','){
			sb.append(c);
			c = (char) userData.read();
		}
		email = sb.toString();
		
		sb = new StringBuilder();               //Parse password
		c = (char) userData.read();
		while(c!=','){
			sb.append(c);
			c = (char) userData.read();
		}
		password = sb.toString();
		
		sb = new StringBuilder();               //Parse first name
		c = (char) userData.read();
		while(c!=','){
			sb.append(c);
			c = (char) userData.read();
		}
		firstName = sb.toString();
		
		sb = new StringBuilder();               //Parse last name
		c = (char) userData.read();
		while(c!=','){
			sb.append(c);
			c = (char) userData.read();
		}
		lastName = sb.toString();
		
		
		
		sb = new StringBuilder();               //Parse country
		c = (char) userData.read();
		while(c!=','){
			sb.append(c);
			c = (char) userData.read();
		}
		country = sb.toString();
		
		sb = new StringBuilder();               //Parse type
		c = (char) userData.read();
		while((c!='\n')&&(c!=' ')){
			sb.append(c);
			c = (char) userData.read();
		}
		
		type = sb.toString();
		type= type.substring(0,(type.length()-1));
	
		appStoreDB.addUser(email, password, firstName,
			lastName, country, type);
		
		sb = new StringBuilder();
		
		}
		//SCAN CATEGORIES
		
		String cat;
		sb = new StringBuilder(); 
		while((tmp = categoryList.read())!=-1){
			c = (char) tmp;
			while((c!='\n')&&(tmp!=-1)){
				sb.append(c);
				tmp =  categoryList.read();
				c = (char) tmp;
			}
			cat = sb.toString();
			if(c=='\n'){
			cat= cat.substring(0,(cat.length()-1));
			}
			appStoreDB.addCategory(cat);
		
			sb = new StringBuilder();
		}

		sb = new StringBuilder();  
		
		//SCAN APPDATA
		String developeremail;
		String appId;
		String appName;
		String appcategory;
		String fName;
		String lName;
		String dPass;
		String dRegion;
		double price;
		long uploadTimestamp;
		

		
		 tmp = 0;
			while((tmp = appData.read())!=-1){           //Parse Email
				c = (char) tmp;
				sb = new StringBuilder();  
		while(c!=','){
			sb.append(c);
			c = (char) appData.read();
		}
		developeremail = sb.toString();

		
		sb = new StringBuilder();               //Parse app Id
		c = (char) appData.read();
		while(c!=','){
			sb.append(c);
			c = (char) appData.read();
		}
		appId = sb.toString();
		
		sb = new StringBuilder();               //Parse app name
		c = (char) appData.read();
		while(c!=','){
			sb.append(c);
			c = (char) appData.read();
		}
		appName = sb.toString();
		
		sb = new StringBuilder();               //Parse app category
		c = (char) appData.read();
		while(c!=','){
			sb.append(c);
			c = (char) appData.read();
		}
		appcategory = sb.toString();
	
		
		sb = new StringBuilder();               //Parse price
		c = (char) appData.read();
		while((c!=',')){
			sb.append(c);
			c = (char) appData.read();
		}
		
		price = Double.parseDouble(sb.toString());
		
		
		
		sb = new StringBuilder();               //Parse timestamp
		c = (char) appData.read();
		while((c!='\n')&&(tmp!= -1)){
			sb.append(c);
			tmp= appData.read();
			c = (char) tmp;
		}
		sb.append('\0');
		
		User u =  appStoreDB.findUserByEmail(developeremail );    //Parse developer name
		fName = u.getFirstName();
		lName = u.getLastName();
		dRegion = u.getCountry();
		//Parse pass??

		
		String t = sb.substring(0,13);
		uploadTimestamp = Long.parseLong(t);

		//Create developer user

	
		appStoreDB.uploadApp(u, appId, appName, appcategory, price, uploadTimestamp);
	
		}
		
		//APP ACTIVITY
		
		char appaction;
		String useremail;
		String appid;
		short rating;

			while((tmp = appActivity.read())!=-1){
				
				c = (char) tmp;
				appaction = c;
				if (appaction=='d'){
				
				
				
					appActivity.read();
				sb = new StringBuilder();             //Parse useremail  
				c= (char) appActivity.read(); //Skip comma

				while(c!=','){
					sb.append(c);
					c = (char) appActivity.read();
				}
				useremail = sb.toString();
		
	
		sb = new StringBuilder();             //Parse appid  
		c = (char) appActivity.read();
		while((c!='\n')&&(tmp!= -1)&&(c!=' ')){
			sb.append(c);
			tmp = appActivity.read();
			c = (char) tmp;
		}
		appid = sb.toString();
		appid = appid.substring(0,(appid.length()-1));
			App ap = appStoreDB.findAppByAppId(appid);
			User us = appStoreDB.findUserByEmail(useremail);

			
			appStoreDB.downloadApp(us, ap);
			
		
		}
		else{
			
			appActivity.read();
			sb = new StringBuilder();             //Parse useremail  
			c= (char) appActivity.read(); //Skip comma

			while(c!=','){
				sb.append(c);
				c = (char) appActivity.read();
			}
			useremail = sb.toString();
	

	sb = new StringBuilder();             //Parse appid  
	c = (char) appActivity.read();
	while(c!=','){
		sb.append(c);
		tmp = appActivity.read();
		c = (char) tmp;
	}
	appid = sb.toString();
	
	
	sb = new StringBuilder();              
	c = (char) appActivity.read();
	sb.append(c);
		rating = Short.parseShort(sb.toString());
		while((c!='\n')&&(tmp!= -1)){
			tmp = appActivity.read();
			c = (char) tmp;
		}
		App a = appStoreDB.findAppByAppId(appid);
		User u = appStoreDB.findUserByEmail(useremail);
		appStoreDB.rateApp(u,a,rating);
	}
			
			
		}
				
		
		
		
		
		
		
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		return true;

		
	}
	
	private static void processUserCommands() {
		scanner = new Scanner(System.in);
		String command = null;		
		do {
			if (appUser == null) {
				System.out.print("[anonymous@AppStore]$ ");
			} else {
				System.out.print("[" + appUser.getEmail().toLowerCase() 
						+ "@AppStore]$ ");
			}
			command = scanner.next();
			switch(command.toLowerCase()) {
				case "l":
					processLoginCommand();
					break;
					
				case "x": 
					processLogoutCommand();
					break;
					
				case "s":
					processSubscribeCommand();
					break;
				
				case "v":
					processViewCommand();
					break;
					
				case "d":
					processDownloadCommand();
					break;
					
				case "r":
					processRateCommand();
					break;
				
				case "u":
					processUploadCommand();
					break;
				
				case "p":
					processProfileViewCommand();
					break;								
					
				case "q":
					System.out.println("Quit");
					break;
				default:
					System.out.println("Unrecognized Command!");
					break;
			}
		} while (!command.equalsIgnoreCase("q"));
		scanner.close();
	}
	
	
	private static void processLoginCommand() {
		if (appUser != null) {
			System.out.println("You are already logged in!");
		} else {
			String email = scanner.next();
			String password = scanner.next();
			appUser = appStoreDB.loginUser(email, password);
			if (appUser == null) {
				System.out.println("Wrong username / password");
			}
		}
	}
	
	
	private static void processLogoutCommand() {
		if (appUser == null) {
			System.out.println("You are already logged out!");
		} else {
			appUser = null;
			System.out.println("You have been logged out.");
		}
	}
	
	private static void processSubscribeCommand() {
		if (appUser == null) {
			System.out.println("You need to log in "
					+ "to perform this action!");
		} else {
			if (appUser.isDeveloper()) {
				System.out.println("You are already a developer!");
			} else {
				appUser.subscribeAsDeveloper();
				System.out.println("You have been promoted as developer");
			}
		}
	}
	
	private static void processViewCommand() {
		String restOfLine = scanner.nextLine();
		Scanner in = new Scanner(restOfLine);
		String subCommand = in.next();
		int count;
		String category;
		switch(subCommand.toLowerCase()) {
			case "categories":
				System.out.println("Displaying list of categories...");
				List<String> categories = appStoreDB.getCategories();
				count = 1;
				for (String categoryName : categories) {
					System.out.println(count++ + ". " + categoryName);
				}
				break;
			case "recent":				
				category = null;
				if (in.hasNext()) {
					category = in.next();
				} 
				displayAppList(appStoreDB.getMostRecentApps(category));				
				break;
			case "free":
				category = null;
				if (in.hasNext()) {
					category = in.next();
				}
				displayAppList(appStoreDB.getTopFreeApps(category));
				break;
			case "paid":
				category = null;
				if (in.hasNext()) {
					category = in.next();
				}
				displayAppList(appStoreDB.getTopPaidApps(category));
				break;
			case "app":
				String appId = in.next();
				App app = appStoreDB.findAppByAppId(appId);
				if (app == null) {
					System.out.println("No such app found with the given app id!");
				} else {
					displayAppDetails(app);
				}
				break;
			default: 
				System.out.println("Unrecognized Command!");
		}
		in.close();
	}
					
	private static void processDownloadCommand() {
		if (appUser == null) {
			System.out.println("You need to log in "
					+ "to perform this action!");
		} else {
			String appId = scanner.next();
			App app = appStoreDB.findAppByAppId(appId);
			appStoreDB.downloadApp(appUser, app);
			//Moved println line to downloadApp() method in AppStoreDB.java
		}
		
	}
			
	private static void processRateCommand() {
		if (appUser == null) {
			System.out.println("You need to log in "
					+ "to perform this action!");
		} else {
			String appId = scanner.next();
			App app = appStoreDB.findAppByAppId(appId);
			short rating = scanner.nextShort();
			appStoreDB.rateApp(appUser, app, rating);
			//Moved println line to downloadApp() method in AppStoreDB.java
			
		}
		
	}
	
	private static void processUploadCommand() {
		if (appUser == null) {
			System.out.println("You need to log in "
					+ "to perform this action!");
		} else {
			String appName = scanner.next();
			String appId = scanner.next();
			String category = scanner.next();
			double price = scanner.nextDouble();
			long uploadTimestamp = Instant.now().toEpochMilli();
			appStoreDB.uploadApp(appUser, appId, appName, category, 
					price, uploadTimestamp);
		}
	}
	
	private static void processProfileViewCommand() {		
		String restOfLine = scanner.nextLine();
		Scanner in = new Scanner(restOfLine);
		String email = null;
		if (in.hasNext()) {
			email = in.next();
		}
		if (email != null) {
			displayUserDetails(appStoreDB.findUserByEmail(email));
		} else {
			displayUserDetails(appUser);
		}
		in.close();
		
	}
			

	private static void displayAppList(List<App> apps) {
		if (apps.size() == 0) {
			System.out.println("No apps to display!");
		} else {
			int count = 1;
			for(App app : apps) {
				System.out.println(count++ + ". " 
						+ "App: " + app.getAppName() + "\t" 
						+ "Id: " + app.getAppId() + "\t" 
						+ "Developer: " + app.getDeveloper().getEmail());
			}	
		}
	}
	
	private static void displayAppDetails(App app) {
		if (app == null) {
			System.out.println("App not found!");
		} else {
			System.out.println("App name: " + app.getAppName());
			System.out.println("App id: " + app.getAppId());
			System.out.println("Category: " + app.getCategory());
			System.out.println("Developer Name: " 
					+ app.getDeveloper().getFirstName() + " " 
					+ app.getDeveloper().getLastName());
			System.out.println("Developer Email: " 
					+ app.getDeveloper().getEmail());
			System.out.println("Total downloads: " + app.getTotalDownloads());
			System.out.println("Average Rating: " + app.getAverageRating());
			
			// show revenue from app if the logged-in user is the app developer
			if (appUser != null && 
					appUser.getEmail()
						.equalsIgnoreCase(app.getDeveloper().getEmail())) {
				System.out.println("Your Revenue from this app: $" 
						+ app.getRevenueForApp());
			}
				
		}		
	}
	
	private static void displayUserDetails(User user) {		
		if (user == null) {
			System.out.println("User not found!");
		} else {
			System.out.println("User name: " + user.getFirstName() + " "
					+ user.getLastName());
			System.out.println("User email: " + user.getEmail());
			System.out.println("User country: " + user.getCountry());
						
			// print the list of downloaded apps
			System.out.println("List of downloaded apps: ");			
			List<App> downloadedApps = user.getAllDownloadedApps();
			displayAppList(downloadedApps);
			
			// print the list of uploaded app
			System.out.println("List of uploaded apps: ");
			List<App> uploadedApps = user.getAllUploadedApps();
			displayAppList(uploadedApps);
			
			// show the revenue earned, if current user is developer
			if (appUser!= null 
					&& user.getEmail().equalsIgnoreCase(appUser.getEmail()) 
					&& appUser.isDeveloper()) {
				double totalRevenue = 0.0;
				for (App app : uploadedApps) {
					totalRevenue += app.getRevenueForApp();
				}
				System.out.println("Your total earnings: $" + totalRevenue);
			}
				
		}
	}
}

