import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector {
    Statement st;
    Connection connection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://"
                + "localhost" + ":" + "3306" + "/students?" + "allowMultiQueries=true&useUnicode=true" +
                "&useJDBCCompliantTimezoneShift=true" +
                "&useLegacyDatetimeCode=false" +
                "&serverTimezone=UTC";
        ;
        try {
            connection = DriverManager.getConnection(url, "root", "kolbasa260102");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        st=connection.createStatement();
        return connection;
    }

    public List<Students> getAllStudents() throws SQLException, ClassNotFoundException {
        getDbConnection();
        List<Students> students= new ArrayList<Students>();
        ResultSet resultSet=null;
            resultSet=st.executeQuery("select studname, studgroupname, yearOfEnroll from student" +
                    " join studgroups s on student.studgroup = s.idgroup;");
        while (resultSet.next()) {
            students.add(new Students(resultSet.getString("studname"),
                    resultSet.getString("studgroupname"),
                    resultSet.getInt("yearOfEnroll")));
        }
        return students;
    }
    public List<Students> getAllStududentsByGroup(String group) throws SQLException, ClassNotFoundException {
        getDbConnection();
    List<Students> students=new ArrayList<Students>();
    ResultSet resultSet=null;

    resultSet=st.executeQuery("select studname, studgroupname, yearOfEnroll from student " +
            "join studgroups s on student.studgroup = s.idgroup " +
            "where studgroupname="+group+";");
    while (resultSet.next()){
        students.add(new Students(resultSet.getString("studname"),
                resultSet.getString("studgroupname"),
                resultSet.getInt("yearOfEnroll")));
    }
        return students;
    }
    public List<Students> getAllStududentsByYear(int year) throws SQLException, ClassNotFoundException {
        getDbConnection();
        List<Students> students=new ArrayList<Students>();
        ResultSet resultSet=null;

        resultSet=st.executeQuery("select studname, studgroupname, yearOfEnroll from student " +
                "join studgroups s on student.studgroup = s.idgroup " +
                "where yearOfEnroll="+year+";");
        while (resultSet.next()){
            students.add(new Students(resultSet.getString("studname"),
                    resultSet.getString("studgroupname"),
                    resultSet.getInt("yearOfEnroll")));
        }
        return students;
    }
    public List<Students> getStudentsMark() throws SQLException, ClassNotFoundException {
        getDbConnection();
        List<Students> markLists=new ArrayList<Students>();
        ResultSet resultSet=null;

        resultSet=st.executeQuery("select studname, studgroupname, lessonName, name,score from student " +
                "left join marks on student=idstud left join lesson on idlesson=lesson " +
                "join tutors on tutor=idtutor left join studgroups on idgroup=studgroup;");
        while (resultSet.next()){
            markLists.add(new Students(resultSet.getString("studname"),
                    resultSet.getString("studgroupname"),
                    resultSet.getString("lessonName"),
                    resultSet.getString("name"),
                    resultSet.getInt("score")));
        }
        return markLists;
    }
    public Students getAverageMarkForStudent(String name) throws SQLException, ClassNotFoundException {
        getDbConnection();
        Students student=null;
        ResultSet resultSet=null;
        resultSet=st.executeQuery("select studname, studgroupname, avg(score) from student " +
                "left join marks on student=idstud left join studgroups on idgroup=studgroup where studname="+"'"+name+"';");
        while (resultSet.next()){
         student=new Students(resultSet.getString("studname"),resultSet.getString("studgroupname"),
                resultSet.getDouble("avg(score)"));}
        return student;
    }
    public List<Students> getAverageMarkForAllStudents() throws SQLException, ClassNotFoundException {
        getDbConnection();
        List<Students> markLists=new ArrayList<Students>();
        ResultSet resultSet=null;

        resultSet=st.executeQuery("select studname, studgroupname, avg(score) from student " +
                "left join marks on student=idstud left join studgroups on idgroup=studgroup group by studname;");
        while (resultSet.next()){
            markLists.add(new Students(resultSet.getString("studname"),resultSet.getString("studgroupname"),
                    resultSet.getDouble("avg(score)")));
        }
        return markLists;
    }
}
