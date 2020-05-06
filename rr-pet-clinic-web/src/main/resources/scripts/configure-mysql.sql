## Use to run mysql db docker image, optional if you're not using a local mysqldb
# docker run -p 3307:3306 --name localMySql -v mysqldata:/var/lib/mysql –e MYSQL_ROOT_PASSWORD=dockermysqlpassword -d mysql

# connect to mysql and run as root user
#Create Databases
CREATE DATABASE petclinic_dev;
CREATE DATABASE petclinic_prod;

#Create database service accounts
CREATE USER 'petclinic_dev_user'@'localhost' IDENTIFIED BY 'dev';
CREATE USER 'petclinic_prod_user'@'localhost' IDENTIFIED BY 'prod';

#Database grants
GRANT SELECT ON petclinic_dev.* to 'petclinic_dev_user'@'localhost';
GRANT INSERT ON petclinic_dev.* to 'petclinic_dev_user'@'localhost';
GRANT DELETE ON petclinic_dev.* to 'petclinic_dev_user'@'localhost';
GRANT UPDATE ON petclinic_dev.* to 'petclinic_dev_user'@'localhost';
GRANT SELECT ON petclinic_prod.* to 'petclinic_prod_user'@'localhost';
GRANT INSERT ON petclinic_prod.* to 'petclinic_prod_user'@'localhost';
GRANT DELETE ON petclinic_prod.* to 'petclinic_prod_user'@'localhost';
GRANT UPDATE ON petclinic_prod.* to 'petclinic_prod_user'@'localhost';


#Docker -  use % wildcrad to specify as any host instead of local host
CREATE USER 'petclinic_dev_user'@'%' IDENTIFIED BY 'dev';
CREATE USER 'petclinic_prod_user'@'%' IDENTIFIED BY 'prod';

GRANT SELECT ON petclinic_dev.* to 'petclinic_dev_user'@'%';
GRANT INSERT ON petclinic_dev.* to 'petclinic_dev_user'@'%';
GRANT DELETE ON petclinic_dev.* to 'petclinic_dev_user'@'%';
GRANT UPDATE ON petclinic_dev.* to 'petclinic_dev_user'@'%';
GRANT SELECT ON petclinic_prod.* to 'petclinic_prod_user'@'%';
GRANT INSERT ON petclinic_prod.* to 'petclinic_prod_user'@'%';
GRANT DELETE ON petclinic_prod.* to 'petclinic_prod_user'@'%';
GRANT UPDATE ON petclinic_prod.* to 'petclinic_prod_user'@'%';

