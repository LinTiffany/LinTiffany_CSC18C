/*
 * Author: Tiffany Lin
 * Class: CSC18C
 * Date: 3/11/15
 * Description: Implement Stack and its Functions
 */

public class Stack {
	private static final int size =10;
	static int list[]=new int[size];
	
	public static int []push(int value)
	{
		int top=0;
		boolean notFilled=true;
		while(top<size&&notFilled)
		{
			if(list[top]==0){
				list[top]=value;
				notFilled=false;
			}
			else
				top++;
		}
		return list;
	}
	public static int []pop()
	{
		for(int i=0;i<list.length;i++)
		{
			if(list[i]==0)
				list[i-1]=0;
		}
		return list;
	}
	public static void print()
	{
		for(int i=0;i<list.length;i++){
			System.out.println(list[i]);
		}
		
	}
	public static void main(String[] args)
	{
		Stack array= new Stack();
		array.push(5);//5
		array.push(12);//5,12
		array.push(3);//5, 12, 3
		array.pop();//5, 12
		array.push(7);//5, 12, 7
		array.pop();//5,12
		array.print();
	}
}
