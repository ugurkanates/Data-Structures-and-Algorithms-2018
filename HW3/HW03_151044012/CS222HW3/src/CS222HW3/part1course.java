/**
 * Created by paypaytr on 3/19/18.
 */
package CS222HW3;

public class part1course {
    private String courseCode;

    public void printer(){
        System.out.printf("Course code:%s\n",courseCode);
        System.out.printf("SEMESTER:%d\n",semester);
        System.out.printf("ECTS and GTU:%f%f\n",ectsCredits,gtuCredits);
        System.out.printf("Course name :%s\n",courseTitle);

    }
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    private int semester;

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    private double ectsCredits;

    public double getEctsCredits() {
        return ectsCredits;
    }

    private double gtuCredits;

    public void setEctsCredits(double ectsCredits) {
        this.ectsCredits = ectsCredits;
    }

    public double getGtuCredits() {
        return gtuCredits;
    }

    public void setGtuCredits(double gtuCredits) {
        this.gtuCredits = gtuCredits;
    }

    private String courseTitle;

    part1course(){
        semester = 0;
        courseCode ="XXX XXX";
        ectsCredits = 0;
        gtuCredits = 0;
        courseTitle = "dummy title";

    }
    part1course(int semesterer,String course,double ects,double gtu,String title){
        semester = semesterer;
        courseCode = course;
        ectsCredits= ects;
        gtuCredits = gtu;
        courseTitle =title;

    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    private String HTL;

    public String getHTL() {
        return HTL;
    }

    public void setHTL(String HTL) {
        this.HTL = HTL;
    }
}
