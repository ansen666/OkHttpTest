package com.ansen.action;

import com.ansen.entity.BaseResult;
import com.ansen.entity.User;
import com.ansen.util.Utils;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Created by  ansen
 * Create Time 2017-06-05
 */
public class ActionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String url = request.getRequestURI();
        String action = url.substring(url.lastIndexOf("/"), url.lastIndexOf("."));

        System.out.println("url:"+url+" action:"+action);

        User user=new User();
        PrintWriter out = response.getWriter();
        if (action.equals("/getUserInfo")) {
            user.setUsername("ansen");
            user.setPassword("123");
            out.append(JSONObject.fromObject(user).toString());
        }

        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String url = request.getRequestURI();
        String action = url.substring(url.lastIndexOf("/"), url.lastIndexOf("."));

        System.out.println("url:"+url+" action:"+action);
        PrintWriter out = response.getWriter();
        User user=new User();
        if (action.equals("/login")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            user.setUsername(username);
            user.setPassword(password);

            if(Utils.isEmpty(username)){
                user.setErrorReason("用户名不能为空");
                out.append(JSONObject.fromObject(user).toString());
                return;
            }

            if(!username.equals("ansen")){
                user.setErrorReason("用户名输入错误");
                out.append(JSONObject.fromObject(user).toString());
                return ;
            }

            if(Utils.isEmpty(password)){
                user.setErrorReason("密码不能为空");
                out.append(JSONObject.fromObject(user).toString());
                return ;
            }

            if(!password.equals("123")){
                user.setErrorReason("密码输入错误");
                out.append(JSONObject.fromObject(user).toString());
                return ;
            }

            user.setErrorReason("登录成功");
            out.append(JSONObject.fromObject(user).toString());
        }else if(action.equals("/uploadFile")){
            String savepath=this.getServletContext().getRealPath("/upload");
            File file=new File(savepath);
            System.out.println(savepath);
            if(!file.exists() && !file.isDirectory()){
                System.out.println(savepath+"目录不存在创建");
                file.mkdir();
            }else{//如果存在删除下面的所有文件
                String[] children = file.list();
                for (int i=0; i<children.length; i++) {
                    System.out.println("删除文件:"+children[i]);
                    File deleteFile=new File(children[i]);
                    deleteFile.delete();
                }
            }

            BaseResult baseResult=new BaseResult();

            try{
                System.out.println("1");
                DiskFileItemFactory fctory=new DiskFileItemFactory();
                ServletFileUpload fileuplaod=new ServletFileUpload(fctory);
                fileuplaod.setSizeMax(1024*1024*50);//将页面请求传递信息最大值设置为50M
                fileuplaod.setFileSizeMax(1024*1024*6);//将单个上传文件信息最大值设置为6M

                fileuplaod.setHeaderEncoding("UTF-8");
                if(!ServletFileUpload.isMultipartContent(request)){
                    System.out.println("5");
                    return;
                }
                System.out.println("6");
                List<FileItem> list=fileuplaod.parseRequest(request);
                for(FileItem item : list){
                    if(item.isFormField()){
                        System.out.println("2");
                        String name=item.getFieldName();
                        String value=item.getString("UTF-8");
                        System.out.println(name+"="+value);
                    }else{
                        System.out.println("3");
                        String name=item.getName();
                        System.out.println("文件名称:"+name);
                        if(name==null||name.trim().equals("")){
                            continue;
                        }
                        name=name.substring(name.lastIndexOf("\\")+1);
                        InputStream in=item.getInputStream();
                        FileOutputStream fileOutputStream =new FileOutputStream(savepath+"/"+name);
                        byte buffer[]=new byte[1024];
                        int len;
                        while((len=in.read(buffer))>0){
                            fileOutputStream.write(buffer, 0, len);;
                        }
                        System.out.println("4");
                        in.close();
                        fileOutputStream.close();
                        item.delete();
                        System.out.println("路径是:"+(savepath+"/"+name));
                        baseResult.setErrorReason("文件上传成功 在服务器的路径是:"+(savepath+"/"+name));
                    }
                }
            }catch(Exception e){
                baseResult.setErrorReason("文件上传失败！");
                e.printStackTrace();
            }
            out.append(JSONObject.fromObject(baseResult).toString());
        }
        out.close();
    }
}
