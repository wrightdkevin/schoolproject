package foo;


// Student class which I have envisaged as an
// immutable.

public final class Student {
	private final int studentId;
	private final String name;
	
	public Student(int stId, String name){
		studentId = stId;
		this.name = name;
		
	}

	public int getStudentId() {
		return studentId;
	}

	public String getName() {
		return name;
	}
	
	
	

}
