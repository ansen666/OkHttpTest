# OkHttpTest

java web开发的服务器源码，开发工具是:IntelliJ Idea 服务器是:tomcat7.0

里面有三个接口，给Android端用来测试OkHttp框架的。

http://139.196.35.30:8080/OkHttpTest/getUserInfo.do  获取用户信息  
返回json:{"errorReason":"","password":"123","username":"ansen"}

http://139.196.35.30:8080/OkHttpTest/login.do  这是post请求  请求参数有两个username跟password  这两个参数的值从getUserInfo接口的返回值中获取
返回json:{"errorReason":"登录成功","password":"123","username":"ansen"}

http://139.196.35.30:8080/OkHttpTest/uploadFile.do 这
是post请求,可以上传文件到服务器  表单参数可以随便填写，上传文件参数用upload_file
返回json:{"errorReason":"文件上传成功 在服务器的路径是:/local/tomcat7/webapps/OkHttpTest/WEB-INF/upload/ansen.txt"}

大家如果做二次开发，或者代码部署到本地，测试的时候把ip地址替换成localhost就能在本地的浏览器中访问了，139.196.35.30这个是我一台服务器的外网ip.

# 不想编译源码,但是想在本地tomcat上部署
复制out/artifacts下的OkHttpTest文件夹到你的tomcat下的webapps文件夹里面，然后启动tomcat就行了。
