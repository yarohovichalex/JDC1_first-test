import java.util.Date;

public class MainApp {

	public static void main(String[] args) {
		StudentGroup sg = new StudentGroup(4);
		Date dt = new Date(1993, 1, 16);
		Date dt1 = new Date(1995, 8, 24);
		

		Student st4 = new Student(1,"Test test", new Date(1993, 1, 24), 7);

		Student st = new Student(1,"Aima Diminow", new Date((1993-1900), 1, 15), 8);
		Student st1 = new Student(1,"Aima Aiminow", new Date((1993-1900), 1, 17), 10);
		Student st2 = new Student(1,"Koly Kruglow", new Date((1991-1900), 1, 22), 9);
		Student st3 = new Student(1,"Grish Grishin", new Date((1995-1900), 1, 24), 10);
		
		sg.setStudent(st,0);
		sg.setStudent(st1,1);
		sg.setStudent(st2,2);
		sg.setStudent(st3,3);
		sg.getStudentsWithMaxAvgMark();
		


		
		System.out.println(sg.getStudent(2).getFullName());

	}

}
