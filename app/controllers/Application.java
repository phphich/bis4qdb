package controllers;

import models.*;
import play.*;
import play.api.cache.Cache;
import play.api.templates.Html;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import scala.util.parsing.combinator.testing.Str;
import views.html.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Application extends Controller {
    public static List<Picture> pictureList = new ArrayList<Picture >();
    public static Form<Picture> pictureForm= Form.form(Picture.class);
    public static Picture picture;

    public static List<Department> departmentList= new ArrayList<Department>();
    public static Form<Department> departmentForm=Form.form(Department.class);
    public static Department department;

    public static List<Faculty> facultyList = new ArrayList<Faculty>();


    public static Result showMain(Html content) {

        return ok(main.render(content));
    }

    public static Result listPicture(){

        return showMain(showPicture.render(pictureList));
    }
    public static Result newPicture() {
        pictureForm=Form.form(Picture.class);
        return showMain(newPicture.render(pictureForm));
    }
    public static Result listAdd(){
        Form<Picture> newPic=pictureForm.bindFromRequest();

        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart myfile = body.getFile("myfile");

        if(newPic.hasErrors()){
            return showMain(newPicture.render(newPic));
        }else{
            picture=newPic.get();

            if (myfile != null) {
                String fileName = myfile.getFilename();
                String extension= fileName.substring(fileName.indexOf("."));
                String realName = picture.getId()+ extension;
                File file = myfile.getFile();
                file.renameTo(new File("public/images/"+realName));
                picture.setFilename(realName);
            }
            pictureList.add(picture);

            return listPicture();
        }
    }
    public static Result editPicture(String id) {
        picture=null;
        for(int i=0; i<pictureList.size();i++){
            if(pictureList.get(i).getId().equals(id)){
                picture=pictureList.get(i);
                break;
            }
        }
        if(picture!=null) {
            pictureForm = Form.form(Picture.class).fill(picture);
            return showMain(editPicture.render(pictureForm));
        }else{
            return listPicture();
        }
    }

    public static Result updatePicture(){
        Form<Picture> editPic=pictureForm.bindFromRequest();
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart myfile = body.getFile("myfile");

        if(editPic.hasErrors()){
            return showMain(newPicture.render(editPic));
        }else{
            picture=editPic.get();

            if (myfile != null) {
                String fileName = myfile.getFilename();
                String extension= fileName.substring(fileName.indexOf("."));
                String realName = picture.getId()+ extension;
                File file = myfile.getFile();

                File temp = new File("public/images/"+realName);
                if(temp.exists()) {
                    temp.delete();
                }

                file.renameTo(new File("public/images/"+realName));
                picture.setFilename(realName);
            }

            for (int i=0;i<pictureList.size();i++){
              if(picture.getId().equals(pictureList.get(i).getId())){
                  pictureList.set(i, picture);
                  break;
              }

            }
            //pictureList.add(picture);

            return listPicture();
        }
    }

    public static Result deletePicture(String id) {
        picture = null;
        for (int i = 0; i < pictureList.size(); i++) {
            if (pictureList.get(i).getId().equals(id)) {
                pictureList.remove(i);
                break;
            }
        }
        return listPicture();
    }


    public static Result index() {
        return showMain(home.render());
    }

    public static Result second(){
        return showMain(secondpage.render());
    }

    public  static Result products() {
        return showMain(products.render());
    }

    public static Result showFlower(){
        Flower rose=new Flower();
        rose.setId("f001");
        rose.setName("ดอกกุหลาบ");
        rose.setPrice(15000.00);
        rose.setAmount(150);

        Flower justmin = new Flower("f002", "ดอกมะลิ", 2500.00, 1000);
        return showMain(showFlower.render(rose,justmin));
   }

   public static Result listDepartment() {
        if(session().get("uid")==null){
            flash("permisError", "ท่านไม่มีสิทธิ์ใช้งานในส่วนที่เลือก");
            return showMain(home.render());
        }else {
            String ustatus = session().get("ustatus");
            if (!ustatus.equals("Admin")) {
                flash("permisError", "ท่านไม่มีสิทธิ์ใช้งานในส่วนที่เลือก");
                return showMain(home.render());
            }
        }

        departmentList = Department.list();
        return showMain(listDepartment.render(departmentList));
   }

   public static Result inputDepartment() {
        facultyList = Faculty.list();
        departmentForm=Form.form(Department.class);
        return showMain(inputDepartment.render(departmentForm, facultyList));
   }
   public static Result saveDepartment() {
        facultyList = Faculty.list();
        Form<Department> newDepartment = departmentForm.bindFromRequest();
        if (newDepartment.hasErrors()){
            flash("msgError","ท่านป้อนข้อมุลไม่ถูกต้อง/ไม่สมบุรณ์ กรุณาตรวจสอบและแก้ไขให้ถูกต้อง");
            return showMain(inputDepartment.render(newDepartment, facultyList));
        }else{
            department=newDepartment.get();
            Department chk;
            chk=Department.find.byId(department.getId());
            if(chk!=null){
                flash("msgError","รหัสสาขาใหม่ ซ่ำกับที่มีอยู่แล้วในระบบ กรุณาตรวจสอบและแก้ไขให้ถูกต้อง");
                return showMain(inputDepartment.render(newDepartment, facultyList));
            }else {
                Department.create(department);
                return listDepartment();
            }
        }
   }

   public static Result editDepartment(String id){
        department = Department.find.byId(id);
        if(department == null){
            return listDepartment();
        }else{
            facultyList = Faculty.list();
            departmentForm=Form.form(Department.class).fill(department);
            return showMain(editDepartment.render(departmentForm, facultyList));
        }
   }

   public static Result updateDepartment(){
       facultyList = Faculty.list();
       Form<Department> newDepartment = departmentForm.bindFromRequest();
       if (newDepartment.hasErrors()){
           flash("msgError","ท่านป้อนข้อมุลไม่ถูกต้อง/ไม่สมบุรณ์ กรุณาตรวจสอบและแก้ไขให้ถูกต้อง");
           return showMain(inputDepartment.render(newDepartment, facultyList));
       }else{
           department=newDepartment.get();
           Department.update(department);
           return listDepartment();
       }
   }

   public static Result deleteDepartment(String id){
       department = Department.find.byId(id);
       if(department != null){
           Department.delete(department);
       }
       return listDepartment();
   }

   public static Result authen() {
       DynamicForm myForm = Form.form().bindFromRequest();
       String uid = myForm.get("uid");
       String upass=myForm.get("upass");
       User user = User.authen(uid, upass);
       if(user == null){
           flash("loginError", "Invalid user or password");
           return showMain(home.render());
       }else{
           session("uid", user.getId());
           session("uname", user.getName());
           session("ustatus", user.getStatus());
           return showMain(home.render());
       }
   }

   public static Result logout() {
       session().clear();
       return showMain(home.render());
   }

  // public static List<Department> departmentList; //รายการสินค้า
   public static Result showProduct() {
       departmentList=Department.list();
       return showMain(showProduct.render(departmentList));
   }

   public static Result pushToBasket(String id) {
        return ok();
   }

}
