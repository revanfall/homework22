import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DatabaseConnector dc=new DatabaseConnector();

        for(Students st: dc.getAllStudents()){
            System.out.println(st.toString());
        }
        System.out.println("//////////////////////////");

    for(Students st:dc.getAllStududentsByGroup("171")){
        System.out.println(st.toString());
    }
        System.out.println("//////////////////////////");
    for(Students st:dc.getAllStududentsByYear(2017)){
        System.out.println(st.toString());
    }
        System.out.println("//////////////////////////");
    for(Students markList:dc.getStudentsMark()){
        System.out.println(markList.toString());
    }
        System.out.println("//////////////////////////");
        System.out.println(dc.getAverageMarkForStudent("Illia Stoliarchuk").toString());
        System.out.println("//////////////////////////");
        for(Students markList:dc.getAverageMarkForAllStudents()){
            System.out.println(markList.toString());
        }
    }
}
