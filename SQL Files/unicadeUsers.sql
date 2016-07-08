USE unicade;

DROP TABLE IF EXISTS users;
CREATE TABLE users
(
  id              int unsigned NOT NULL auto_increment, # Unique ID for the record
  username        varchar (255) NOT NULL,     # Username
  password        varchar (255) NOT NULL,     # Username
  email           varchar (255) NOT NULL,     # User email
  info            varchar (255) NOT NULL,     # User Info
  allowedEsrb     varchar (255) NOT NULL,     # Max Allowed Esrb Rating
  logincount      smallint(127) NOT NULL,     # Number of times the user has logged in
  launchcount     smallint(127) NOT NULL,     # Total number of games the user has launch
  profilepic      varchar (255) NOT NULL,     # Path to the user's profile picture
  

  PRIMARY KEY     (id)
);





