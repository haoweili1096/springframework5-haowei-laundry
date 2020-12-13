## Use to run mysql db docker image, optional if you're not using a local mysqldb
# docker run --name mysqldb -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql

# connect to mysql and run as root user
#Create Databases
CREATE DATABASE haowei_laundry_dev;
CREATE DATABASE haowei_laundry_prod;

#Create database service accounts
CREATE USER 'haowei_laundry_dev_user'@'localhost' IDENTIFIED BY 'haowei';
CREATE USER 'haowei_laundry_prod_user'@'localhost' IDENTIFIED BY 'haowei';
CREATE USER 'haowei_laundry_dev_user'@'%' IDENTIFIED BY 'haowei';
CREATE USER 'haowei_laundry_prod_user'@'%' IDENTIFIED BY 'haowei';

#Database grants
GRANT SELECT ON haowei_laundry_dev.* to 'haowei_laundry_dev_user'@'localhost';
GRANT INSERT ON haowei_laundry_dev.* to 'haowei_laundry_dev_user'@'localhost';
GRANT DELETE ON haowei_laundry_dev.* to 'haowei_laundry_dev_user'@'localhost';
GRANT UPDATE ON haowei_laundry_dev.* to 'haowei_laundry_dev_user'@'localhost';
GRANT SELECT ON haowei_laundry_prod.* to 'haowei_laundry_prod_user'@'localhost';
GRANT INSERT ON haowei_laundry_prod.* to 'haowei_laundry_prod_user'@'localhost';
GRANT DELETE ON haowei_laundry_prod.* to 'haowei_laundry_prod_user'@'localhost';
GRANT UPDATE ON haowei_laundry_prod.* to 'haowei_laundry_prod_user'@'localhost';
GRANT SELECT ON haowei_laundry_dev.* to 'haowei_laundry_dev_user'@'%';
GRANT INSERT ON haowei_laundry_dev.* to 'haowei_laundry_dev_user'@'%';
GRANT DELETE ON haowei_laundry_dev.* to 'haowei_laundry_dev_user'@'%';
GRANT UPDATE ON haowei_laundry_dev.* to 'haowei_laundry_dev_user'@'%';
GRANT SELECT ON haowei_laundry_prod.* to 'haowei_laundry_prod_user'@'%';
GRANT INSERT ON haowei_laundry_prod.* to 'haowei_laundry_prod_user'@'%';
GRANT DELETE ON haowei_laundry_prod.* to 'haowei_laundry_prod_user'@'%';
GRANT UPDATE ON haowei_laundry_prod.* to 'haowei_laundry_prod_user'@'%';