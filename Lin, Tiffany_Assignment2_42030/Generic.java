/*
 * Author: Tiffany Lin
 * Class: CSC18C
 * Date: 3/11/15
 * Description: Create a Generic class
 */
public class Generic {
	String name;
	int age;
	public Generic(){
		name="";
		age=0;
	}
	public Generic(String n, int a){
		name=n;
		age=a;
	}
	public void setName(String n){
		name=n;
	}
	public void setAge(int a){
		age=a;
	}
	public String getName(){
		return name;
	}
	public int getAge(){
		return age;
	}
	public void print(){
		System.out.println(name+","+age);
	}
	
}