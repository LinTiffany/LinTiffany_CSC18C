/*
 * Author: Tiffany Lin
 * Date: 04/09/2015
 * Description: Assignment 5 : Recursion 
 */
public class SumFunction {
	
	public int sum(int size, int[] list){
		if(size<=0){
			return 0;
		}
		return list[size-1]+sum(size-1, list);
	}
	
	public static void main(String[] args){
		SumFunction newSum=new SumFunction();
		int[] newList={3,5,7,9,2,4,6};//sum: 36
		System.out.println(newSum.sum(newList.length, newList));
	}
}
