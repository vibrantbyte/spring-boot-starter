# default 数据组
DROP DATABASE IF EXISTS `default`;
CREATE DATABASE `default`;
CREATE TABLE IF NOT EXISTS `default`.`t_order` (
  order_id INT NOT NULL,
  user_id  INT NOT NULL,
  PRIMARY KEY (order_id)
);
CREATE TABLE IF NOT EXISTS `default`.`t_order_item` (
  item_id  INT NOT NULL,
  order_id INT NOT NULL,
  user_id  INT NOT NULL,
  PRIMARY KEY (item_id)
);

INSERT INTO  `default`.`t_order`(order_id, user_id) VALUES(1000, 2000);
INSERT INTO  `default`.`t_order`(order_id, user_id) VALUES(1001, 2001);
INSERT INTO  `default`.`t_order`(order_id, user_id) VALUES(1002, 2002);
INSERT INTO  `default`.`t_order`(order_id, user_id) VALUES(1003, 2003);
INSERT INTO  `default`.`t_order`(order_id, user_id) VALUES(1004, 2004);
INSERT INTO  `default`.`t_order`(order_id, user_id) VALUES(1005, 2005);
INSERT INTO  `default`.`t_order`(order_id, user_id) VALUES(1006, 2006);
INSERT INTO  `default`.`t_order`(order_id, user_id) VALUES(1007, 2007);
INSERT INTO  `default`.`t_order`(order_id, user_id) VALUES(1008, 2008);
INSERT INTO  `default`.`t_order`(order_id, user_id) VALUES(1009, 2009);
INSERT INTO  `default`.`t_order`(order_id, user_id) VALUES(1010, 2010);
INSERT INTO  `default`.`t_order`(order_id, user_id) VALUES(1011, 2011);
INSERT INTO  `default`.`t_order`(order_id, user_id) VALUES(1012, 2012);
INSERT INTO  `default`.`t_order`(order_id, user_id) VALUES(1013, 2013);
INSERT INTO  `default`.`t_order`(order_id, user_id) VALUES(1014, 2014);
INSERT INTO  `default`.`t_order`(order_id, user_id) VALUES(1015, 2015);
INSERT INTO  `default`.`t_order`(order_id, user_id) VALUES(1016, 2016);
INSERT INTO  `default`.`t_order`(order_id, user_id) VALUES(1017, 2017);
INSERT INTO  `default`.`t_order`(order_id, user_id) VALUES(1018, 2018);
INSERT INTO  `default`.`t_order`(order_id, user_id) VALUES(1019, 2019);

INSERT INTO  `default`.`t_order_item`(item_id, order_id, user_id) VALUES(3000, 1000, 2000);
INSERT INTO  `default`.`t_order_item`(item_id, order_id, user_id) VALUES(3001, 1001, 2001);
INSERT INTO  `default`.`t_order_item`(item_id, order_id, user_id) VALUES(3002, 1002, 2002);
INSERT INTO  `default`.`t_order_item`(item_id, order_id, user_id) VALUES(3003, 1003, 2003);
INSERT INTO  `default`.`t_order_item`(item_id, order_id, user_id) VALUES(3004, 1004, 2004);
INSERT INTO  `default`.`t_order_item`(item_id, order_id, user_id) VALUES(3005, 1005, 2005);
INSERT INTO  `default`.`t_order_item`(item_id, order_id, user_id) VALUES(3006, 1006, 2006);
INSERT INTO  `default`.`t_order_item`(item_id, order_id, user_id) VALUES(3007, 1007, 2007);
INSERT INTO  `default`.`t_order_item`(item_id, order_id, user_id) VALUES(3008, 1008, 2008);
INSERT INTO  `default`.`t_order_item`(item_id, order_id, user_id) VALUES(3009, 1009, 2009);
INSERT INTO  `default`.`t_order_item`(item_id, order_id, user_id) VALUES(3010, 1010, 2010);
INSERT INTO  `default`.`t_order_item`(item_id, order_id, user_id) VALUES(3011, 1011, 2011);
INSERT INTO  `default`.`t_order_item`(item_id, order_id, user_id) VALUES(3012, 1012, 2012);
INSERT INTO  `default`.`t_order_item`(item_id, order_id, user_id) VALUES(3013, 1013, 2013);
INSERT INTO  `default`.`t_order_item`(item_id, order_id, user_id) VALUES(3014, 1014, 2014);
INSERT INTO  `default`.`t_order_item`(item_id, order_id, user_id) VALUES(3015, 1015, 2015);
INSERT INTO  `default`.`t_order_item`(item_id, order_id, user_id) VALUES(3016, 1016, 2016);
INSERT INTO  `default`.`t_order_item`(item_id, order_id, user_id) VALUES(3017, 1017, 2017);
INSERT INTO  `default`.`t_order_item`(item_id, order_id, user_id) VALUES(3018, 1018, 2018);
INSERT INTO  `default`.`t_order_item`(item_id, order_id, user_id) VALUES(3019, 1019, 2019);



#
DROP DATABASE IF EXISTS `one_master_master_01`;
CREATE DATABASE one_master_master_01;
CREATE TABLE IF NOT EXISTS `one_master_master_01`.`t_order` (
  order_id INT NOT NULL,
  user_id  INT NOT NULL,
  PRIMARY KEY (order_id)
);
CREATE TABLE IF NOT EXISTS `one_master_master_01`.`t_order_item` (
  item_id  INT NOT NULL,
  order_id INT NOT NULL,
  user_id  INT NOT NULL,
  PRIMARY KEY (item_id)
);

INSERT INTO  `one_master_master_01`.`t_order`(order_id, user_id) VALUES(1000, 2000);
INSERT INTO  `one_master_master_01`.`t_order`(order_id, user_id) VALUES(1001, 2001);
INSERT INTO  `one_master_master_01`.`t_order`(order_id, user_id) VALUES(1002, 2002);
INSERT INTO  `one_master_master_01`.`t_order`(order_id, user_id) VALUES(1003, 2003);
INSERT INTO  `one_master_master_01`.`t_order`(order_id, user_id) VALUES(1004, 2004);
INSERT INTO  `one_master_master_01`.`t_order`(order_id, user_id) VALUES(1005, 2005);
INSERT INTO  `one_master_master_01`.`t_order`(order_id, user_id) VALUES(1006, 2006);
INSERT INTO  `one_master_master_01`.`t_order`(order_id, user_id) VALUES(1007, 2007);
INSERT INTO  `one_master_master_01`.`t_order`(order_id, user_id) VALUES(1008, 2008);
INSERT INTO  `one_master_master_01`.`t_order`(order_id, user_id) VALUES(1009, 2009);
INSERT INTO  `one_master_master_01`.`t_order`(order_id, user_id) VALUES(1010, 2010);
INSERT INTO  `one_master_master_01`.`t_order`(order_id, user_id) VALUES(1011, 2011);
INSERT INTO  `one_master_master_01`.`t_order`(order_id, user_id) VALUES(1012, 2012);
INSERT INTO  `one_master_master_01`.`t_order`(order_id, user_id) VALUES(1013, 2013);
INSERT INTO  `one_master_master_01`.`t_order`(order_id, user_id) VALUES(1014, 2014);
INSERT INTO  `one_master_master_01`.`t_order`(order_id, user_id) VALUES(1015, 2015);
INSERT INTO  `one_master_master_01`.`t_order`(order_id, user_id) VALUES(1016, 2016);
INSERT INTO  `one_master_master_01`.`t_order`(order_id, user_id) VALUES(1017, 2017);
INSERT INTO  `one_master_master_01`.`t_order`(order_id, user_id) VALUES(1018, 2018);
INSERT INTO  `one_master_master_01`.`t_order`(order_id, user_id) VALUES(1019, 2019);

INSERT INTO  `one_master_master_01`.`t_order_item`(item_id, order_id, user_id) VALUES(3000, 1000, 2000);
INSERT INTO  `one_master_master_01`.`t_order_item`(item_id, order_id, user_id) VALUES(3001, 1001, 2001);
INSERT INTO  `one_master_master_01`.`t_order_item`(item_id, order_id, user_id) VALUES(3002, 1002, 2002);
INSERT INTO  `one_master_master_01`.`t_order_item`(item_id, order_id, user_id) VALUES(3003, 1003, 2003);
INSERT INTO  `one_master_master_01`.`t_order_item`(item_id, order_id, user_id) VALUES(3004, 1004, 2004);
INSERT INTO  `one_master_master_01`.`t_order_item`(item_id, order_id, user_id) VALUES(3005, 1005, 2005);
INSERT INTO  `one_master_master_01`.`t_order_item`(item_id, order_id, user_id) VALUES(3006, 1006, 2006);
INSERT INTO  `one_master_master_01`.`t_order_item`(item_id, order_id, user_id) VALUES(3007, 1007, 2007);
INSERT INTO  `one_master_master_01`.`t_order_item`(item_id, order_id, user_id) VALUES(3008, 1008, 2008);
INSERT INTO  `one_master_master_01`.`t_order_item`(item_id, order_id, user_id) VALUES(3009, 1009, 2009);
INSERT INTO  `one_master_master_01`.`t_order_item`(item_id, order_id, user_id) VALUES(3010, 1010, 2010);
INSERT INTO  `one_master_master_01`.`t_order_item`(item_id, order_id, user_id) VALUES(3011, 1011, 2011);
INSERT INTO  `one_master_master_01`.`t_order_item`(item_id, order_id, user_id) VALUES(3012, 1012, 2012);
INSERT INTO  `one_master_master_01`.`t_order_item`(item_id, order_id, user_id) VALUES(3013, 1013, 2013);
INSERT INTO  `one_master_master_01`.`t_order_item`(item_id, order_id, user_id) VALUES(3014, 1014, 2014);
INSERT INTO  `one_master_master_01`.`t_order_item`(item_id, order_id, user_id) VALUES(3015, 1015, 2015);
INSERT INTO  `one_master_master_01`.`t_order_item`(item_id, order_id, user_id) VALUES(3016, 1016, 2016);
INSERT INTO  `one_master_master_01`.`t_order_item`(item_id, order_id, user_id) VALUES(3017, 1017, 2017);
INSERT INTO  `one_master_master_01`.`t_order_item`(item_id, order_id, user_id) VALUES(3018, 1018, 2018);
INSERT INTO  `one_master_master_01`.`t_order_item`(item_id, order_id, user_id) VALUES(3019, 1019, 2019);

#
DROP DATABASE IF EXISTS `one_master_mutiple_slave_master_0`;
DROP DATABASE IF EXISTS `one_master_mutiple_slave_slave_0`;
DROP DATABASE IF EXISTS `one_master_mutiple_slave_slave_1`;
CREATE DATABASE one_master_mutiple_slave_master_0;
CREATE DATABASE one_master_mutiple_slave_slave_0;
CREATE DATABASE one_master_mutiple_slave_slave_1;
CREATE TABLE IF NOT EXISTS `one_master_mutiple_slave_master_0`.`t_order` (
  order_id INT NOT NULL,
  user_id  INT NOT NULL,
  PRIMARY KEY (order_id)
);
CREATE TABLE IF NOT EXISTS `one_master_mutiple_slave_master_0`.`t_order_item` (
  item_id  INT NOT NULL,
  order_id INT NOT NULL,
  user_id  INT NOT NULL,
  PRIMARY KEY (item_id)
);

CREATE TABLE IF NOT EXISTS `one_master_mutiple_slave_slave_0`.`t_order` (
  order_id INT NOT NULL,
  user_id  INT NOT NULL,
  PRIMARY KEY (order_id)
);
CREATE TABLE IF NOT EXISTS `one_master_mutiple_slave_slave_0`.`t_order_item` (
  item_id  INT NOT NULL,
  order_id INT NOT NULL,
  user_id  INT NOT NULL,
  PRIMARY KEY (item_id)
);

CREATE TABLE IF NOT EXISTS `one_master_mutiple_slave_slave_1`.`t_order` (
  order_id INT NOT NULL,
  user_id  INT NOT NULL,
  PRIMARY KEY (order_id)
);
CREATE TABLE IF NOT EXISTS `one_master_mutiple_slave_slave_1`.`t_order_item` (
  item_id  INT NOT NULL,
  order_id INT NOT NULL,
  user_id  INT NOT NULL,
  PRIMARY KEY (item_id)
);


INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order`(order_id, user_id) VALUES(1000, 2000);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order`(order_id, user_id) VALUES(1001, 2001);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order`(order_id, user_id) VALUES(1002, 2002);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order`(order_id, user_id) VALUES(1003, 2003);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order`(order_id, user_id) VALUES(1004, 2004);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order`(order_id, user_id) VALUES(1005, 2005);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order`(order_id, user_id) VALUES(1006, 2006);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order`(order_id, user_id) VALUES(1007, 2007);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order`(order_id, user_id) VALUES(1008, 2008);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order`(order_id, user_id) VALUES(1009, 2009);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order`(order_id, user_id) VALUES(1010, 2010);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order`(order_id, user_id) VALUES(1011, 2011);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order`(order_id, user_id) VALUES(1012, 2012);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order`(order_id, user_id) VALUES(1013, 2013);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order`(order_id, user_id) VALUES(1014, 2014);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order`(order_id, user_id) VALUES(1015, 2015);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order`(order_id, user_id) VALUES(1016, 2016);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order`(order_id, user_id) VALUES(1017, 2017);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order`(order_id, user_id) VALUES(1018, 2018);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order`(order_id, user_id) VALUES(1019, 2019);

INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3000, 1000, 2000);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3001, 1001, 2001);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3002, 1002, 2002);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3003, 1003, 2003);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3004, 1004, 2004);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3005, 1005, 2005);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3006, 1006, 2006);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3007, 1007, 2007);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3008, 1008, 2008);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3009, 1009, 2009);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3010, 1010, 2010);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3011, 1011, 2011);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3012, 1012, 2012);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3013, 1013, 2013);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3014, 1014, 2014);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3015, 1015, 2015);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3016, 1016, 2016);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3017, 1017, 2017);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3018, 1018, 2018);
INSERT INTO  `one_master_mutiple_slave_master_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3019, 1019, 2019);



INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order`(order_id, user_id) VALUES(1000, 2000);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order`(order_id, user_id) VALUES(1001, 2001);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order`(order_id, user_id) VALUES(1002, 2002);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order`(order_id, user_id) VALUES(1003, 2003);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order`(order_id, user_id) VALUES(1004, 2004);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order`(order_id, user_id) VALUES(1005, 2005);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order`(order_id, user_id) VALUES(1006, 2006);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order`(order_id, user_id) VALUES(1007, 2007);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order`(order_id, user_id) VALUES(1008, 2008);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order`(order_id, user_id) VALUES(1009, 2009);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order`(order_id, user_id) VALUES(1010, 2010);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order`(order_id, user_id) VALUES(1011, 2011);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order`(order_id, user_id) VALUES(1012, 2012);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order`(order_id, user_id) VALUES(1013, 2013);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order`(order_id, user_id) VALUES(1014, 2014);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order`(order_id, user_id) VALUES(1015, 2015);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order`(order_id, user_id) VALUES(1016, 2016);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order`(order_id, user_id) VALUES(1017, 2017);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order`(order_id, user_id) VALUES(1018, 2018);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order`(order_id, user_id) VALUES(1019, 2019);

INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3000, 1000, 2000);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3001, 1001, 2001);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3002, 1002, 2002);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3003, 1003, 2003);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3004, 1004, 2004);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3005, 1005, 2005);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3006, 1006, 2006);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3007, 1007, 2007);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3008, 1008, 2008);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3009, 1009, 2009);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3010, 1010, 2010);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3011, 1011, 2011);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3012, 1012, 2012);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3013, 1013, 2013);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3014, 1014, 2014);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3015, 1015, 2015);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3016, 1016, 2016);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3017, 1017, 2017);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3018, 1018, 2018);
INSERT INTO  `one_master_mutiple_slave_slave_0`.`t_order_item`(item_id, order_id, user_id) VALUES(3019, 1019, 2019);




INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order`(order_id, user_id) VALUES(1000, 2000);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order`(order_id, user_id) VALUES(1001, 2001);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order`(order_id, user_id) VALUES(1002, 2002);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order`(order_id, user_id) VALUES(1003, 2003);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order`(order_id, user_id) VALUES(1004, 2004);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order`(order_id, user_id) VALUES(1005, 2005);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order`(order_id, user_id) VALUES(1006, 2006);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order`(order_id, user_id) VALUES(1007, 2007);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order`(order_id, user_id) VALUES(1008, 2008);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order`(order_id, user_id) VALUES(1009, 2009);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order`(order_id, user_id) VALUES(1010, 2010);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order`(order_id, user_id) VALUES(1011, 2011);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order`(order_id, user_id) VALUES(1012, 2012);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order`(order_id, user_id) VALUES(1013, 2013);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order`(order_id, user_id) VALUES(1014, 2014);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order`(order_id, user_id) VALUES(1015, 2015);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order`(order_id, user_id) VALUES(1016, 2016);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order`(order_id, user_id) VALUES(1017, 2017);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order`(order_id, user_id) VALUES(1018, 2018);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order`(order_id, user_id) VALUES(1019, 2019);

INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order_item`(item_id, order_id, user_id) VALUES(3000, 1000, 2000);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order_item`(item_id, order_id, user_id) VALUES(3001, 1001, 2001);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order_item`(item_id, order_id, user_id) VALUES(3002, 1002, 2002);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order_item`(item_id, order_id, user_id) VALUES(3003, 1003, 2003);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order_item`(item_id, order_id, user_id) VALUES(3004, 1004, 2004);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order_item`(item_id, order_id, user_id) VALUES(3005, 1005, 2005);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order_item`(item_id, order_id, user_id) VALUES(3006, 1006, 2006);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order_item`(item_id, order_id, user_id) VALUES(3007, 1007, 2007);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order_item`(item_id, order_id, user_id) VALUES(3008, 1008, 2008);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order_item`(item_id, order_id, user_id) VALUES(3009, 1009, 2009);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order_item`(item_id, order_id, user_id) VALUES(3010, 1010, 2010);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order_item`(item_id, order_id, user_id) VALUES(3011, 1011, 2011);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order_item`(item_id, order_id, user_id) VALUES(3012, 1012, 2012);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order_item`(item_id, order_id, user_id) VALUES(3013, 1013, 2013);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order_item`(item_id, order_id, user_id) VALUES(3014, 1014, 2014);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order_item`(item_id, order_id, user_id) VALUES(3015, 1015, 2015);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order_item`(item_id, order_id, user_id) VALUES(3016, 1016, 2016);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order_item`(item_id, order_id, user_id) VALUES(3017, 1017, 2017);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order_item`(item_id, order_id, user_id) VALUES(3018, 1018, 2018);
INSERT INTO  `one_master_mutiple_slave_slave_1`.`t_order_item`(item_id, order_id, user_id) VALUES(3019, 1019, 2019);




#
DROP DATABASE IF EXISTS `mutiple_master_slaves_group_0_master_0`;
DROP DATABASE IF EXISTS `mutiple_master_slaves_group_0_slave_0`;
DROP DATABASE IF EXISTS `mutiple_master_slaves_group_1_master_1`;
DROP DATABASE IF EXISTS `mutiple_master_slaves_group_1_slave_1`;
CREATE DATABASE mutiple_master_slaves_group_0_master_0;
CREATE DATABASE mutiple_master_slaves_group_0_slave_0;
CREATE DATABASE mutiple_master_slaves_group_1_master_1;
CREATE DATABASE mutiple_master_slaves_group_1_slave_1;

CREATE TABLE IF NOT EXISTS `mutiple_master_slaves_group_0_master_0`.`t_order_0` (
  order_id INT NOT NULL,
  user_id  INT NOT NULL,
  PRIMARY KEY (order_id)
);
CREATE TABLE IF NOT EXISTS `mutiple_master_slaves_group_0_master_0`.`t_order_1` (
  order_id INT NOT NULL,
  user_id  INT NOT NULL,
  PRIMARY KEY (order_id)
);
CREATE TABLE IF NOT EXISTS `mutiple_master_slaves_group_0_master_0`.`t_order_item_0` (
  item_id  INT NOT NULL,
  order_id INT NOT NULL,
  user_id  INT NOT NULL,
  PRIMARY KEY (item_id)
);
CREATE TABLE IF NOT EXISTS `mutiple_master_slaves_group_0_master_0`.`t_order_item_1` (
  item_id  INT NOT NULL,
  order_id INT NOT NULL,
  user_id  INT NOT NULL,
  PRIMARY KEY (item_id)
);

CREATE TABLE IF NOT EXISTS `mutiple_master_slaves_group_0_slave_0`.`t_order_0` (
  order_id INT NOT NULL,
  user_id  INT NOT NULL,
  PRIMARY KEY (order_id)
);
CREATE TABLE IF NOT EXISTS `mutiple_master_slaves_group_0_slave_0`.`t_order_1` (
  order_id INT NOT NULL,
  user_id  INT NOT NULL,
  PRIMARY KEY (order_id)
);
CREATE TABLE IF NOT EXISTS `mutiple_master_slaves_group_0_slave_0`.`t_order_item_0` (
  item_id  INT NOT NULL,
  order_id INT NOT NULL,
  user_id  INT NOT NULL,
  PRIMARY KEY (item_id)
);
CREATE TABLE IF NOT EXISTS `mutiple_master_slaves_group_0_slave_0`.`t_order_item_1` (
  item_id  INT NOT NULL,
  order_id INT NOT NULL,
  user_id  INT NOT NULL,
  PRIMARY KEY (item_id)
);

CREATE TABLE IF NOT EXISTS `mutiple_master_slaves_group_1_master_1`.`t_order_0` (
  order_id INT NOT NULL,
  user_id  INT NOT NULL,
  PRIMARY KEY (order_id)
);
CREATE TABLE IF NOT EXISTS `mutiple_master_slaves_group_1_master_1`.`t_order_1` (
  order_id INT NOT NULL,
  user_id  INT NOT NULL,
  PRIMARY KEY (order_id)
);
CREATE TABLE IF NOT EXISTS `mutiple_master_slaves_group_1_master_1`.`t_order_item_0` (
  item_id  INT NOT NULL,
  order_id INT NOT NULL,
  user_id  INT NOT NULL,
  PRIMARY KEY (item_id)
);
CREATE TABLE IF NOT EXISTS `mutiple_master_slaves_group_1_master_1`.`t_order_item_1` (
  item_id  INT NOT NULL,
  order_id INT NOT NULL,
  user_id  INT NOT NULL,
  PRIMARY KEY (item_id)
);

CREATE TABLE IF NOT EXISTS `mutiple_master_slaves_group_1_slave_1`.`t_order_0` (
  order_id INT NOT NULL,
  user_id  INT NOT NULL,
  PRIMARY KEY (order_id)
);
CREATE TABLE IF NOT EXISTS `mutiple_master_slaves_group_1_slave_1`.`t_order_1` (
  order_id INT NOT NULL,
  user_id  INT NOT NULL,
  PRIMARY KEY (order_id)
);
CREATE TABLE IF NOT EXISTS `mutiple_master_slaves_group_1_slave_1`.`t_order_item_0` (
  item_id  INT NOT NULL,
  order_id INT NOT NULL,
  user_id  INT NOT NULL,
  PRIMARY KEY (item_id)
);
CREATE TABLE IF NOT EXISTS `mutiple_master_slaves_group_1_slave_1`.`t_order_item_1` (
  item_id  INT NOT NULL,
  order_id INT NOT NULL,
  user_id  INT NOT NULL,
  PRIMARY KEY (item_id)
);


# ds_0 t_0 master
INSERT INTO  `mutiple_master_slaves_group_0_master_0`.`t_order_0`(order_id, user_id) VALUES(1000, 2000);
INSERT INTO  `mutiple_master_slaves_group_0_master_0`.`t_order_0`(order_id, user_id) VALUES(1002, 2002);
INSERT INTO  `mutiple_master_slaves_group_0_master_0`.`t_order_0`(order_id, user_id) VALUES(1004, 2004);
INSERT INTO  `mutiple_master_slaves_group_0_master_0`.`t_order_0`(order_id, user_id) VALUES(1006, 2006);
INSERT INTO  `mutiple_master_slaves_group_0_master_0`.`t_order_0`(order_id, user_id) VALUES(1008, 2008);
INSERT INTO  `mutiple_master_slaves_group_0_master_0`.`t_order_0`(order_id, user_id) VALUES(1010, 2010);
INSERT INTO  `mutiple_master_slaves_group_0_master_0`.`t_order_0`(order_id, user_id) VALUES(1012, 2012);
INSERT INTO  `mutiple_master_slaves_group_0_master_0`.`t_order_0`(order_id, user_id) VALUES(1014, 2014);
INSERT INTO  `mutiple_master_slaves_group_0_master_0`.`t_order_0`(order_id, user_id) VALUES(1016, 2016);
INSERT INTO  `mutiple_master_slaves_group_0_master_0`.`t_order_0`(order_id, user_id) VALUES(1018, 2018);

INSERT INTO  `mutiple_master_slaves_group_0_master_0`.`t_order_item_0`(item_id, order_id, user_id) VALUES(3000, 1000, 2000);
INSERT INTO  `mutiple_master_slaves_group_0_master_0`.`t_order_item_0`(item_id, order_id, user_id) VALUES(3002, 1002, 2002);
INSERT INTO  `mutiple_master_slaves_group_0_master_0`.`t_order_item_0`(item_id, order_id, user_id) VALUES(3004, 1004, 2004);
INSERT INTO  `mutiple_master_slaves_group_0_master_0`.`t_order_item_0`(item_id, order_id, user_id) VALUES(3006, 1006, 2006);
INSERT INTO  `mutiple_master_slaves_group_0_master_0`.`t_order_item_0`(item_id, order_id, user_id) VALUES(3008, 1008, 2008);
INSERT INTO  `mutiple_master_slaves_group_0_master_0`.`t_order_item_0`(item_id, order_id, user_id) VALUES(3010, 1010, 2010);
INSERT INTO  `mutiple_master_slaves_group_0_master_0`.`t_order_item_0`(item_id, order_id, user_id) VALUES(3012, 1012, 2012);
INSERT INTO  `mutiple_master_slaves_group_0_master_0`.`t_order_item_0`(item_id, order_id, user_id) VALUES(3014, 1014, 2014);
INSERT INTO  `mutiple_master_slaves_group_0_master_0`.`t_order_item_0`(item_id, order_id, user_id) VALUES(3016, 1016, 2016);
INSERT INTO  `mutiple_master_slaves_group_0_master_0`.`t_order_item_0`(item_id, order_id, user_id) VALUES(3018, 1018, 2018);

# ds_0 t_0 slave
INSERT INTO  `mutiple_master_slaves_group_0_slave_0`.`t_order_0`(order_id, user_id) VALUES(1000, 2000);
INSERT INTO  `mutiple_master_slaves_group_0_slave_0`.`t_order_0`(order_id, user_id) VALUES(1002, 2002);
INSERT INTO  `mutiple_master_slaves_group_0_slave_0`.`t_order_0`(order_id, user_id) VALUES(1004, 2004);
INSERT INTO  `mutiple_master_slaves_group_0_slave_0`.`t_order_0`(order_id, user_id) VALUES(1006, 2006);
INSERT INTO  `mutiple_master_slaves_group_0_slave_0`.`t_order_0`(order_id, user_id) VALUES(1008, 2008);
INSERT INTO  `mutiple_master_slaves_group_0_slave_0`.`t_order_0`(order_id, user_id) VALUES(1010, 2010);
INSERT INTO  `mutiple_master_slaves_group_0_slave_0`.`t_order_0`(order_id, user_id) VALUES(1012, 2012);
INSERT INTO  `mutiple_master_slaves_group_0_slave_0`.`t_order_0`(order_id, user_id) VALUES(1014, 2014);
INSERT INTO  `mutiple_master_slaves_group_0_slave_0`.`t_order_0`(order_id, user_id) VALUES(1016, 2016);
INSERT INTO  `mutiple_master_slaves_group_0_slave_0`.`t_order_0`(order_id, user_id) VALUES(1018, 2018);

INSERT INTO  `mutiple_master_slaves_group_0_slave_0`.`t_order_item_0`(item_id, order_id, user_id) VALUES(3000, 1000, 2000);
INSERT INTO  `mutiple_master_slaves_group_0_slave_0`.`t_order_item_0`(item_id, order_id, user_id) VALUES(3002, 1002, 2002);
INSERT INTO  `mutiple_master_slaves_group_0_slave_0`.`t_order_item_0`(item_id, order_id, user_id) VALUES(3004, 1004, 2004);
INSERT INTO  `mutiple_master_slaves_group_0_slave_0`.`t_order_item_0`(item_id, order_id, user_id) VALUES(3006, 1006, 2006);
INSERT INTO  `mutiple_master_slaves_group_0_slave_0`.`t_order_item_0`(item_id, order_id, user_id) VALUES(3008, 1008, 2008);
INSERT INTO  `mutiple_master_slaves_group_0_slave_0`.`t_order_item_0`(item_id, order_id, user_id) VALUES(3010, 1010, 2010);
INSERT INTO  `mutiple_master_slaves_group_0_slave_0`.`t_order_item_0`(item_id, order_id, user_id) VALUES(3012, 1012, 2012);
INSERT INTO  `mutiple_master_slaves_group_0_slave_0`.`t_order_item_0`(item_id, order_id, user_id) VALUES(3014, 1014, 2014);
INSERT INTO  `mutiple_master_slaves_group_0_slave_0`.`t_order_item_0`(item_id, order_id, user_id) VALUES(3016, 1016, 2016);
INSERT INTO  `mutiple_master_slaves_group_0_slave_0`.`t_order_item_0`(item_id, order_id, user_id) VALUES(3018, 1018, 2018);

# ds_1 t_1 master
INSERT INTO  `mutiple_master_slaves_group_1_master_1`.`t_order_1`(order_id, user_id) VALUES(1001, 2001);
INSERT INTO  `mutiple_master_slaves_group_1_master_1`.`t_order_1`(order_id, user_id) VALUES(1003, 2003);
INSERT INTO  `mutiple_master_slaves_group_1_master_1`.`t_order_1`(order_id, user_id) VALUES(1005, 2005);
INSERT INTO  `mutiple_master_slaves_group_1_master_1`.`t_order_1`(order_id, user_id) VALUES(1007, 2007);
INSERT INTO  `mutiple_master_slaves_group_1_master_1`.`t_order_1`(order_id, user_id) VALUES(1009, 2009);
INSERT INTO  `mutiple_master_slaves_group_1_master_1`.`t_order_1`(order_id, user_id) VALUES(1011, 2011);
INSERT INTO  `mutiple_master_slaves_group_1_master_1`.`t_order_1`(order_id, user_id) VALUES(1013, 2013);
INSERT INTO  `mutiple_master_slaves_group_1_master_1`.`t_order_1`(order_id, user_id) VALUES(1015, 2015);
INSERT INTO  `mutiple_master_slaves_group_1_master_1`.`t_order_1`(order_id, user_id) VALUES(1017, 2017);
INSERT INTO  `mutiple_master_slaves_group_1_master_1`.`t_order_1`(order_id, user_id) VALUES(1019, 2019);

INSERT INTO  `mutiple_master_slaves_group_1_master_1`.`t_order_item_1`(item_id, order_id, user_id) VALUES(3001, 1001, 2001);
INSERT INTO  `mutiple_master_slaves_group_1_master_1`.`t_order_item_1`(item_id, order_id, user_id) VALUES(3003, 1003, 2003);
INSERT INTO  `mutiple_master_slaves_group_1_master_1`.`t_order_item_1`(item_id, order_id, user_id) VALUES(3005, 1005, 2005);
INSERT INTO  `mutiple_master_slaves_group_1_master_1`.`t_order_item_1`(item_id, order_id, user_id) VALUES(3007, 1007, 2007);
INSERT INTO  `mutiple_master_slaves_group_1_master_1`.`t_order_item_1`(item_id, order_id, user_id) VALUES(3009, 1009, 2009);
INSERT INTO  `mutiple_master_slaves_group_1_master_1`.`t_order_item_1`(item_id, order_id, user_id) VALUES(3011, 1011, 2011);
INSERT INTO  `mutiple_master_slaves_group_1_master_1`.`t_order_item_1`(item_id, order_id, user_id) VALUES(3013, 1013, 2013);
INSERT INTO  `mutiple_master_slaves_group_1_master_1`.`t_order_item_1`(item_id, order_id, user_id) VALUES(3015, 1015, 2015);
INSERT INTO  `mutiple_master_slaves_group_1_master_1`.`t_order_item_1`(item_id, order_id, user_id) VALUES(3017, 1017, 2017);
INSERT INTO  `mutiple_master_slaves_group_1_master_1`.`t_order_item_1`(item_id, order_id, user_id) VALUES(3019, 1019, 2019);


# ds_1 t_1 slave
INSERT INTO  `mutiple_master_slaves_group_1_slave_1`.`t_order_1`(order_id, user_id) VALUES(1001, 2001);
INSERT INTO  `mutiple_master_slaves_group_1_slave_1`.`t_order_1`(order_id, user_id) VALUES(1003, 2003);
INSERT INTO  `mutiple_master_slaves_group_1_slave_1`.`t_order_1`(order_id, user_id) VALUES(1005, 2005);
INSERT INTO  `mutiple_master_slaves_group_1_slave_1`.`t_order_1`(order_id, user_id) VALUES(1007, 2007);
INSERT INTO  `mutiple_master_slaves_group_1_slave_1`.`t_order_1`(order_id, user_id) VALUES(1009, 2009);
INSERT INTO  `mutiple_master_slaves_group_1_slave_1`.`t_order_1`(order_id, user_id) VALUES(1011, 2011);
INSERT INTO  `mutiple_master_slaves_group_1_slave_1`.`t_order_1`(order_id, user_id) VALUES(1013, 2013);
INSERT INTO  `mutiple_master_slaves_group_1_slave_1`.`t_order_1`(order_id, user_id) VALUES(1015, 2015);
INSERT INTO  `mutiple_master_slaves_group_1_slave_1`.`t_order_1`(order_id, user_id) VALUES(1017, 2017);
INSERT INTO  `mutiple_master_slaves_group_1_slave_1`.`t_order_1`(order_id, user_id) VALUES(1019, 2019);

INSERT INTO  `mutiple_master_slaves_group_1_slave_1`.`t_order_item_1`(item_id, order_id, user_id) VALUES(3001, 1001, 2001);
INSERT INTO  `mutiple_master_slaves_group_1_slave_1`.`t_order_item_1`(item_id, order_id, user_id) VALUES(3003, 1003, 2003);
INSERT INTO  `mutiple_master_slaves_group_1_slave_1`.`t_order_item_1`(item_id, order_id, user_id) VALUES(3005, 1005, 2005);
INSERT INTO  `mutiple_master_slaves_group_1_slave_1`.`t_order_item_1`(item_id, order_id, user_id) VALUES(3007, 1007, 2007);
INSERT INTO  `mutiple_master_slaves_group_1_slave_1`.`t_order_item_1`(item_id, order_id, user_id) VALUES(3009, 1009, 2009);
INSERT INTO  `mutiple_master_slaves_group_1_slave_1`.`t_order_item_1`(item_id, order_id, user_id) VALUES(3011, 1011, 2011);
INSERT INTO  `mutiple_master_slaves_group_1_slave_1`.`t_order_item_1`(item_id, order_id, user_id) VALUES(3013, 1013, 2013);
INSERT INTO  `mutiple_master_slaves_group_1_slave_1`.`t_order_item_1`(item_id, order_id, user_id) VALUES(3015, 1015, 2015);
INSERT INTO  `mutiple_master_slaves_group_1_slave_1`.`t_order_item_1`(item_id, order_id, user_id) VALUES(3017, 1017, 2017);
INSERT INTO  `mutiple_master_slaves_group_1_slave_1`.`t_order_item_1`(item_id, order_id, user_id) VALUES(3019, 1019, 2019);