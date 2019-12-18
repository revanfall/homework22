public class Students {
    String name;
    String group;
    int year;
    String lessonName;
    String tutorName;
    int score;
    double averageMark;

    public Students(String name, String group, int year) {
        this.name = name;
        this.group = group;
        this.year = year;
    }

    public Students(String name, String group,double averageMark) {
        this.name = name;
        this.group = group;
        this.averageMark = averageMark;
    }



    public Students(String name, String group, String lessonName, String tutorName, int score) {
        this.name = name;
        this.group = group;
        this.lessonName = lessonName;
        this.tutorName = tutorName;
        this.score = score;

    }



    @Override
    public String toString() {
        return "Students{" +
                "name='" + name + '\'' +
                ", group='" + group + '\'' +
                ", year=" + year +
                ", lessonName='" + lessonName + '\'' +
                ", tutorName='" + tutorName + '\'' +
                ", score=" + score +
                ", averageMark=" + averageMark +
                '}';
    }
}
