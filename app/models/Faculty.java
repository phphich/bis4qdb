package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by COM-1 on 27/9/2561.
 */
@Entity
@Table(name = "tbFaculty")
public class Faculty extends Model{
    @Id
    private String id;
    private String name, shotName;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
    private List<Department> departmentList=new ArrayList<Department>();

    public Faculty() {
    }

    public Faculty(String id, String name, String shotName) {
        this.id = id;
        this.name = name;
        this.shotName = shotName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShotName() {
        return shotName;
    }

    public void setShotName(String shotName) {
        this.shotName = shotName;
    }


    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public static Finder<String, Faculty> find =
            new Finder<String, Faculty>(String.class, Faculty.class);

    public static List<Faculty> list(){
        return find.all();
    }

    public static void create(Faculty faculty){
        faculty.save();
    }
    public static void update(Faculty faculty){
        faculty.update();
    }
    public static void delete(Faculty faculty){
        faculty.delete();
    }
}
