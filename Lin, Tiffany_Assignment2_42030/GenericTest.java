/*
 * Author: Tiffany Lin
 * Class: CSC18C
 * Date: 3/11/15
 * Description: Implement Generic as a Class in GenericTest while Implementing
 * Stack and its Functions
 */
public class GenericTest<T> {
	private int top; private int size;
	private T[]array;
	public GenericTest(int num){
		array=(T[])new Object[num];
		top=-1;
		size=num;
	}
	public void push(T input){
		if(array.length>(++top)){
			array[top]=input;
		}else{
			int newSize=2*size;
			T[]newArray=(T[]) new Object[newSize];
			for(int i=0;i<size;i++){
				newArray[i]=array[i];
			}
			array=newArray;
			array[top]=input;
		}
		
	}
	public T pop(){
		if(top>=0){
			//top--;
			return array[top--];
		}
		else {
			return null;
		}
	}
	public T peek(){
		return array[top];
		
	}
	public void print(){
		for(int i=0;i<=top;i++){
		System.out.println(array[i]);
		}
	}
	public static void main(String[] args)
	{
		int n=100;
		GenericTest<Generic> myArray=new GenericTest<Generic>(n);
		Generic person1=new Generic("David",36);
		Generic person2=new Generic("Adam", 44);
		Generic person3= new Generic("Lucy", 15);
		Generic person4=new Generic("Harry",25);
		myArray.push(person1);//5
		myArray.push(person2);//5,12
		myArray.push(person3);//5, 12, 3
		myArray.pop();//5, 12
		myArray.push(person4);//5, 12, 7
		myArray.pop();//5,12
		myArray.print();
		
	}
	
}
