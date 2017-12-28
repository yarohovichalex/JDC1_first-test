import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;


public class StudentGroup implements GroupOperationService {

	private Student[] students;
	
	public StudentGroup(int length) {
		this.students = new Student[length];
	}

	@Override
	public Student[] getStudents() {
		
		return this.students;
	}

	@Override
	public void setStudents(Student[] students) {
		this.students = students;
	}

	@Override
	public Student getStudent(int index) {
		return this.students[index];
	}

	@Override
	public void setStudent(Student student, int index) {
		this.students[index] = student;
	}

	@Override
	public void addFirst(Student student) {
		Student [] oldStud = this.students;
		this.students = new Student[this.students.length + 1];		
		students[0] = student;
		for(int i=1; i<students.length; i++) {
			students[i] = oldStud[i-1];
		}
	}

	@Override
	public void addLast(Student student) {
		Student [] oldStud = this.students;
		this.students = new Student[this.students.length + 1];		
		students[students.length-1] = student;
		for (int i = 0; i < students.length-1; i++) {
			students[i] = oldStud[i];
		}
	}

	@Override
	public void add(Student student, int index) {
		Student [] oldStud = this.students;
		this.students = new Student[this.students.length + 1];		
		students[index] = student;
		for (int i = 0; i < index; i++) {
			students[i] = oldStud[i];
		}
		for (int i = index + 1; i < students.length; i++) {
			students[i] = oldStud[i-1];
		}
		//this.students[index] = student;
	}

	@Override
	public void remove(int index) {
		Student [] oldStud = this.students;
		this.students = new Student[this.students.length - 1];		
		for (int i = 0; i < index; i++) {
			students[i] = oldStud[i];
		}
		for (int i = index; i < students.length; i++) {
			students[i] = oldStud[i+1];
		}
	}

	@Override
	public void remove(Student student) {
		Student [] oldStud = this.students;
		for(int i = 0; i<students.length; i++) {
			if(students[i].equals(student)) {
				this.students = new Student[this.students.length - 1];
					for (int j = 0; j < i; j++) {
						students[j] = oldStud[j];					
					}
					for (int j = i; j < students.length; j++) {
						students[j] = oldStud[j+1];
					}			
			}
		}		
	}

	@Override
	public void removeFromIndex(int index) {
		Student [] oldStud = this.students;
		this.students = new Student[this.students.length - index]; //+1
		if(students.length != 0) {
			for (int i = 0; i < students.length; i++, index++) {
				students[i] = oldStud[index];					
			}
		}
	}

	@Override
	public void removeFromElement(Student student) {
		Student [] oldStud = this.students;
		for (int i = 0; i < students.length; i++) {
			if(students[i].equals(student)) {
				this.students = new Student[this.students.length - i];
				for (int j = 0; j < students.length; j++,i++) {
					students[j] = oldStud[i];
				}
				break;
			}
		}
	}

	@Override
	public void removeToIndex(int index) {
		Student [] oldStud = this.students;
		this.students = new Student[index]; //+1
		for (int i = 0; i < students.length; i++) {
			students[i] = oldStud[i];
		}

	}

	@Override
	public void removeToElement(Student student) {
		Student [] oldStud = this.students;
		for (int i = 0; i < students.length; i++) {
			if(students[i].equals(student)) {
				this.students = new Student[i];
				for (int j = 0; j < students.length; j++) {
					students[j] = oldStud[j];
				}
			}
		}
		
	}

	@Override
	public void bubbleSort() {
		for (int i = 0; i < students.length - 1; i++) {
            for (int j = i + 1; j < students.length; j++) {
                if(students[j].getFullName().compareToIgnoreCase(students[i].getFullName()) < 0){
                    Student tmp = students[j];
                    students[j] = students[i];
                    students[i] = tmp;
                }
            }
        }
	}

	@Override
	public Student[] getByBirthDate(Date date) {
		Student[] oldStud = this.students;
		int index = 0;
		for (int i = 0; i < students.length; i++) {
			if(students[i].getBirthDate().equals(date)) {
				index++;
			}
		}
		this.students = new Student[index];
		for (int i = 0; i < oldStud.length; i++) {
			if(oldStud[i].getBirthDate().equals(date)) {
				for (int j = 0; j < students.length; j++) {
					students[j] = oldStud[i];
				}
			}
		}
		return students;		
	}

	@Override
	public Student[] getBetweenBirthDates(Date firstDate, Date lastDate) {
		Student[] oldStud = this.students;
		int index = 0;
		for (int i = 0; i < students.length; i++) {
			if(students[i].getBirthDate().compareTo(firstDate)>=0 && students[i].getBirthDate().compareTo(lastDate)<=0) {
				index++;
			}
		}
		this.students = new Student[index];
		for (int i = 0; i < oldStud.length; i++) {
			if(oldStud[i].getBirthDate().compareTo(firstDate)>=0 && oldStud[i].getBirthDate().compareTo(lastDate)<=0) {
				for (int j = 0; j < students.length; j++) {
					students[j] = oldStud[i];
				}
			}
		}
		return students;
	}
//*
	@Override
	public Student[] getNearBirthDate(Date date, int days) {
		Calendar cal1 = Calendar.getInstance();
		Calendar nearCal1 = Calendar.getInstance();
		Calendar nearCal2 = Calendar.getInstance();
		nearCal1.setTime(date);
		nearCal1.add(Calendar.DATE, -days);
		nearCal2.setTime(date);
		nearCal2.add(Calendar.DATE, days);

		Student[] oldStud = this.students;
		int index = 0;
		for (int i = 0; i < students.length; i++) {
			cal1.setTime(students[i].getBirthDate());
			if(cal1.after(nearCal1) && cal1.before(nearCal2)) {
				index++;
			}
		}
		this.students = new Student[index];
		for (int i = 0,j=0; i < oldStud.length; i++) {
			cal1.setTime(oldStud[i].getBirthDate());
			if(cal1.after(nearCal1) && cal1.before(nearCal2)) {				 
				students[j] = oldStud[i];
				j++;				
			}
		}
		return null;
	}

	@Override
	public int getCurrentAgeByDate(int indexOfStudent) {
		
		Calendar birthdayDate = Calendar.getInstance();
	    Calendar today = Calendar.getInstance();
	 
	    birthdayDate.setTime(students[indexOfStudent].getBirthDate());
	    // include day of birth
	    birthdayDate.add(Calendar.DAY_OF_MONTH, -1);
	    
	    int age = today.get(Calendar.YEAR) - birthdayDate.get(Calendar.YEAR);
	    if (today.get(Calendar.DAY_OF_YEAR) <= birthdayDate.get(Calendar.DAY_OF_YEAR)) {
	        age--;
	    }
	    return age;		
	}

	@Override
	public Student[] getStudentsByAge(int age) {
		int index = 0;
		Calendar birthdayDate = Calendar.getInstance();
	    Calendar today = Calendar.getInstance();
		for (int i = 0; i < students.length; i++) {
		    birthdayDate.setTime(students[i].getBirthDate());
		    birthdayDate.add(Calendar.DAY_OF_MONTH, -1);
		    int ageV = today.get(Calendar.YEAR) - birthdayDate.get(Calendar.YEAR);
		    if(ageV == age) {
		    	index++;
		    }
		}
		Student[] oldStud = this.students;
		this.students = new Student[index];
		for (int i = 0, j = 0; i < oldStud.length; i++) {
		    birthdayDate.setTime(oldStud[i].getBirthDate());
		    birthdayDate.add(Calendar.DAY_OF_MONTH, -1);
		    int ageV = today.get(Calendar.YEAR) - birthdayDate.get(Calendar.YEAR);
		    if(ageV == age) {		    	 
		    	students[j] = oldStud[i];
		    	j++;
		    }
		}
		
		return students;
	}

	@Override
	public Student[] getStudentsWithMaxAvgMark() {
		Student[] oldStud = this.students;
		double maxMark = 10;
		int index = 0;
		for (int i = 0; i < students.length; i++) {
			if(students[i].getAvgMark() == maxMark) {
				index++;
			}
		}
		this.students = new Student[index];
		for (int i = 0,j = 0; i < oldStud.length; i++) {
			if(oldStud[i].getAvgMark() == maxMark) {
				students[j] = oldStud[i];
				j++;
			}
		}
		return null;
	}

	@Override
	public Student getNextStudent(Student student) {
		int i = 0;
		for (; i < students.length; i++) {
			if(students[i].equals(student)) {
				break;
			}
		}
		return students[i];				
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(students);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentGroup other = (StudentGroup) obj;
		if (!Arrays.equals(students, other.students))
			return false;
		return true;
	}
	
	
}
