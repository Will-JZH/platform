# 云制造服务平台原型系统
云制造服务云平台原型系统，提供服务发布、需求发布与匹配、服务组合、服务仿真评价及服务指标展示等功能

## MySQL数据库
目前项目文件所需要的mysql作为数据源，主要有以下几个表：    
(1)user      
'userID','int(11)','not null','primary key','auto_increment'   
'userName','varchar(50)','not null',  
'password','varchar(50)','not null',    
'email','varchar(50)','not null',   
'phone','varchar(50)','not null',    
'address','varchar(255)','not null',  
'authority','int(11)','not null',   
     
(2)productResourceInfo       
'productID','int(11)','not null','primary key','auto_increment'    
'productName','varchar(50)','not null',   
'userID','int(11)','not null',    
'productDescript','varchar(255)','default null',    
'inventory','int(11)','not null',   
'price','double','not null',    
'productContent','varchar(255)','not null',

(3)serviceResourceCal       
'calendarID','int(11)','not null','primary key','auto_increment'    
'resourceID','int(11)','not null',      
'startTime','date','not null',    
'endTime','date','not null',      

(4)serviceResourceInfo        
'resourceID','int(11)','not null','primary key','auto_increment'   
'resourceName','varchar(50)','not null',   
'userID','int(11)','not null',   
'category','int(11)','not null',   
'description','varchar(255)','default null',   
'procTime','double','not null',   
'fee','double','not null',    
'resourceContent','varchar(255)','default null',   

## RDF4J数据库搭建
在tomcat上搭建RDF4J（原名sesame）的方法请参考：https://blog.csdn.net/angle7777/article/details/20928293    


## 项目启动
将项目部署在tomcat上，若使用IDEA加载项目，则tomcat启动路径设置为http://localhost:8080/AngularSpringmvcMybatis ，deployment设置为/AngularSpringmvcMybatis   
