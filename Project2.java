package project2;

import java.util.Scanner;

public class Project2 {
	public static void main(String [] args) throws Exception {	
		LinkedStudentList studentList = new LinkedStudentList();
		StudentList sorted = new StudentList(studentList);
		
		LinkedWaitList waitReader = new LinkedWaitList();
		StudentWaitList waitList = new StudentWaitList(waitReader);
		
		getUser(sorted,waitList);//Method that allows interacts with the user telling them what they can do	
	}

	private static void getUser(StudentList sortedList, LinkedWaitList waitList) throws Exception {
		int stop = -1;
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Scanner user = new Scanner(System.in);
		String first,last,id;
		
		while(stop != 0) {
			menu();
			int end = scanner.nextInt();
				//Exits the program
				if(end == 0) {
					System.out.println("Program Exied");
					user.close();
					break;
				}
				
				//Allows the user to add a student to the list if its full prevents it
				if(end == 1) {
					//if the list is not full add student to the roster
					if(sortedList.getLength() != 10) {	
						System.out.println("Enter First Name, then last name, then ID one at a time#\n");
						 first = user.nextLine();
						 last = user.nextLine();
						 id = user.nextLine();
						
						first = first.replaceAll("\\s", "");
						last = last.replaceAll("\\s", "");
						id = id.replaceAll("\\s", "");
				
						Student stu = new Student(first, last, id);
						sortedList.insertSorted(stu);
						System.out.println(sortedList+"\n");
					} 
					else {
						//if the roster is full add student to waitlist
						System.out.println("The list is full, this new student will be wait listed\n"
								+ "Enter First Name, Last Name and ID one at a time");
						 first = user.nextLine();
						 last = user.nextLine();
						 id = user.nextLine();
						 Student wait = new Student(first, last, id);
						waitList.Enqueue(wait);
						System.out.println("\nCurrent WaitList\n" + waitList.toString());
					}
				}
				
				//Allows the user to remove a student if there are none prevents user from using method
				if(end == 2) {
					if(sortedList.getLength() != 0){
						System.out.println("Enter Student ID#");
						id = user.nextLine();
						id = id.replaceAll("\\s", "");
						Student stu = new Student(null, null, id);
					
						sortedList.deleteStudent(stu);
						
						if(sortedList.getLength() != 10) {
						Student waitStu = waitList.Dequeue();
						sortedList.insertSorted(waitStu);
						sortedList.length++;
						System.out.println(sortedList);
						}						
					}
					else {
						System.out.println("\nThere are no students to delete, try adding some.");
					}
						
				}
				
				//Allows the user to search for students by ID numbers
				if(end == 3) {
					System.out.println("Enter Student ID#");
					 id = user.nextLine();
					id = id.replaceAll("\\s", "");
					
					Student stu = new Student(null, null, id);
					StudentNode temp = sortedList.listSearchID(stu);
					//if the returned null then the student being searched for is not in the roster
					if(temp != null) {
						
						System.out.println("Student Found"+"\n");
						System.out.println(temp.student.toString());
					}
					else
						System.out.println("Student Not Found" +"\n");
				}
				
				//use search method that make another method that calls it and can return a the student that it found
				if(end == 4) {
					System.out.println("Enter Student First Name and Last");
					first = user.nextLine();
					last  = user.nextLine();
					
					first = first.replaceAll("\\s", "");
					last = last.replaceAll("\\s", "");
					
					Student stu = sortedList.newStudent(first, last);
					
					if(sortedList.listSearchName(stu)) {
						System.out.println("Student Found");
						
					}
					else
						System.out.println("Student Not Found");				
				}
				
				//Saves the users students into an array
				if(end == 5 || end == 6) {
					System.out.println("Saved into an array");
					Student[] array = sortedList.saveArray();
					for(int i = 0; i < sortedList.getLength(); i++) {
						System.out.println(array[i].toString());
					}
					//allows the user to save the students into a text file and load it up for use again
					if(end == 6) {
						sortedList.saveFile(array);
						waitList.writeList();
						System.out.println("Successfully saved to file, GoodBye!");
						user.close();
						break;
					}
				}
		}
	}
	//a method that can be called ot print out a menu
	public static void menu() {
		System.out.println("(0) to exit"
				       + "\n(1) add student"
				       + "\n(2) remove student from list"
				       + "\n(3) search student by ID"
				       + "\n(4) search student by name"
				       + "\n(5) save to array/update array"
				       + "\n(6) save to file & exit program");
		
	}
}





