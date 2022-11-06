 CREATE TABLE USERS (
   id bigint(20) NOT NULL AUTO_INCREMENT,
   username varchar(100) NOT NULL,
   first_name varchar(50) NOT NULL,
   last_name varchar(50) NOT NULL,
   email varchar(50) NOT NULL,
   ssid varchar(4) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE KEY UK_username (username)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

  CREATE TABLE SOCIAL_SECURITY (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    ssid varchar(12) NOT NULL,
    mobile varchar(11) NOT NULL,
    PRIMARY KEY (id)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;


  INSERT INTO USERS(username, first_name, last_name, email, ssid) VALUE('john_doe', 'john', 'doe', 'john@gmail.com', '1234');
  INSERT INTO USERS(username, first_name, last_name, email, ssid) VALUE('sarah_parker', 'sarah', 'parker', 'sarah@gmail.com', '4321');

  INSERT INTO SOCIAL_SECURITY(ssid, mobile) VALUE ('1234', '9601077875');
  INSERT INTO SOCIAL_SECURITY(ssid, mobile) VALUE ('4321', '9458479875');