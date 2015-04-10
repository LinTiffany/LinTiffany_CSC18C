/*
 * Author: Tiffany Lin
 * Date: 04/09/2015
 * Description: Assignment 5 : Recursion 
 */
import java.text.DecimalFormat;

public class CompoundInterest {
	public double savings(double PV, double IR, double years){
		if(years<0)
			return 1;
		return PV*savings(1+IR,IR, years-1);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DecimalFormat numFormat=new DecimalFormat("#.00");
		CompoundInterest newAccount=new CompoundInterest();
		System.out.println("$"+numFormat.format(newAccount.savings(1000, .1, 5)));

	}

}
