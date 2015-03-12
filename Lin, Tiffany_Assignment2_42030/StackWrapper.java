/*
 * Author: Tiffany Lin
 * Class: CSC18C
 * Date: 3/11/15
 * Description: Implement Wrapper Integer in Stack and its Functions
 */

public class StackWrapper {
	private int size; private int newSize;
	int top=0;	
	private Integer []list;
	private Integer []newList=new Integer[newSize];
	public StackWrapper(int n){
		list=new Integer[n];
		size=n;
	}
	
	public Integer []push(int value)
	{
		
		if(top>=size){//push goes past the boundary
			newSize=2*size;
			System.out.println("New Top: "+top);
			for(int i=0;i<size;i++)
			{
				newList[i]=list[i];
			}
			newList[top]=value;
			top++;
			return newList;
		}else	//if top is less than size
		{//
			System.out.println("Old Top: "+top);
			list[top]=value;
			//newSize=size;
			top++;
		}
		
		return list;
	}
	public Integer []pop()
	{
		if(top<size){
			top--;
			list[top]=null;
			//top--;
			size--;
			return list;
		}else{
			top--;
			newList[top]=null;
			newSize--;
		}
		return newList;
	}
	public void oldPrint(){
		for(int i=0;i<list.length;i++){
			System.out.println(list[i]);
		}
	}
	public void newPrint()
	{
		for(int i=0;i<newList.length;i++){
			System.out.println(newList[i]);
		}
		
	}
	public static void main(String[] args)
	{
		StackWrapper array= new StackWrapper(10);
		array.push(5);//5
		array.oldPrint();
		array.push(12);//5,12
		array.oldPrint();
		array.push(3);//5, 12, 3
		array.oldPrint();
		array.pop();//5, 12
		array.oldPrint();
		array.push(7);//5, 12, 7
		array.oldPrint();
		array.pop();//5,12
		array.oldPrint();
		if(array.size<10){
			array.oldPrint();
		}else{
			array.newPrint();
		}
	}
}
