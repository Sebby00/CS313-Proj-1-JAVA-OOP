package project2;
import java.io.FileWriter;
import java.io.IOException;

public class LinkedStudentList {
	
	protected  StudentNode first = new StudentNode(null);
	protected  StudentNode last = first;
	protected  StudentNode head = last;
	protected  int length = 0;
	

	//returns the value of the length of the linked lit
	public int getLength() {
		return length;
	}
	//inserst the student nodes into correct order
	public void insertSorted(Student s) throws Exception {
		StudentNode tempNode = new StudentNode(s);
		
		
		if(head == last) {
			last.next = tempNode;
			last = last.next;
			length++;
		}
		else {
			last = head;
			while((last.next != null) && (last.next.student.compareTo(s) < 0)) {
				last = last.next;
			}
			tempNode.next = last.next;
			last.next = tempNode;
			while(last.next != null) {
				last = last.next;
			}
			length++;
		}
	}
		//Allows the user to delete a student by first checking to see 
	 	//if they exist then proceeding to deleting them
	public void deleteStudent(Student s) throws Exception {
		if(length == 0) {
			throw new Exception("Error, there are no students to delete");
		}
		if(listSearchID(s) == null) {
			System.out.println("The student you are trying to delete does not exist\n");
			return;
		}
		
		StudentNode temp = head.next, prev = head;
	
		if(temp != null && Integer.parseInt(temp.student.getID()) == Integer.parseInt(s.getID())) {
			head.next = temp.next;
			length--;
			return;
		}
		
		
		while(temp != null && Integer.parseInt(temp.student.getID()) != Integer.parseInt(s.getID())) {
			prev = temp;
			temp = temp.next;
		}
		prev.next = temp.next;
		length--;
	}
	//allows the user to search for a student by name if they are found returns
	//if they aren't returns false
	public boolean listSearchName(Student s) {
		StudentNode curr = head.next;
		while(curr != null) {
		int x =	curr.student.getfirstName().compareToIgnoreCase(s.getfirstName());
		int y = curr.student.getlastName().compareToIgnoreCase(s.getlastName());		
			if(x == 0 && y == 0) {
				return true;
			}
			curr = curr.next;
		}
		return false;
	}
	
	//a method that allows the user to search for a student by their id
	public StudentNode listSearchID(Student s) {
		StudentNode curr = head.next;
		while(curr != null) {
			if(curr.student.equals(s)) {
				return curr;
			}
			curr = curr.next;
		}
		StudentNode n = null;
		return n;
	}
	
	//saves the nodes of the linked list into a array and returns an array a filled array
	public Student[] saveArray() {
		Student []studentArray = new Student[length];
		int index = 0;
		StudentNode curr = head.next;
		
		while(curr != null) {
			if(curr.student != null) {
				studentArray[index++] = curr.student;
			}
			curr = curr.next;
		}
		return studentArray;
	} 

	//saves the elements of the array into a textfile
	public void saveFile(Student[] array) throws IOException {
		FileWriter writer = new FileWriter("C:/Users/parti/Desktop/eclipse-workspace/CS313 Project 2/students.txt");
		for(int i = 0; i < array.length; i++) {
			if(array[i] != null) {
			writer.write(array[i]+"\n");
			}
		}
		writer.close();
	}
	
	//creates a new student and returns it
	public Student newStudent(String fname, String lname) {
		Student stu = new Student(fname, lname, null);
		return stu;
	}
	
	//Converts the elements in the array list to string and returns it to string
	public String toString() {
		StudentNode p = first.next;
		String returnString = "";
		while(p != null) {
			returnString += p.student +"\n";
			p=p.next;
		}
		return returnString;
	}
	
}
