# OkHttpTest

java web开发的服务器源码，有三个接口，给Android端用来测试OkHttp框架的。

http://139.196.35.30:8080/OkHttpTest/getUserInfo.do  获取用户信息  
返回json:{"errorReason":"","password":"123","username":"ansen"}

http://139.196.35.30:8080/OkHttpTest/login.do  这是post请求  请求参数有两个username跟password  这两个参数的值从getUserInfo接口的返回值中获取
返回json:{"errorReason":"登录成功","password":"123","username":"ansen"}

http://139.196.35.30:8080/OkHttpTest/uploadFile.do 这是post请求,可以上传文件到服务器  表单参数可以随便填写，上传文件参数用upload_file
返回json:{"errorReason":"文件上传成功 在服务器的路径是:/local/tomcat7/webapps/OkHttpTest/WEB-INF/upload/ansen.txt"}
