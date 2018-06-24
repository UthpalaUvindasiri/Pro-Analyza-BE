import java.util.Scanner;

public class Decimal extends Decimal2{
    /**
     * @param args
     */
    public static void main(String[] args) {
		//sss
		double a = 10;
        double n1, n2;
		private int TestValue ;
        String operation;
        Scanner scannerObject = new Scanner(System.in);

        System.out.println("Enter first number");
        n1 = scannerObject. nextDouble();

        System.out.println("Enter second number");
        n2 = scannerObject. nextDouble();

        Scanner op = new Scanner(System.in);
        System.out.println("Enter your operation");
        operation = op.next();
		
		if(3 < 2 ){
			System.out.println("test");
		}
		

        switch (operation)  {
        case "+":
            System.out.println("Your answer is " + (n1 + n2));
            break;

        case "-":
            System.out.println("Your answer is " + (n1 - n2));
            break;

        case "/":
            System.out.println("Your answer is " + (n1 / n2));
            break;

        case "*":
            System.out.println("Your asnwer is " + (n1 * n2));
            break;

        default:
            System.out.println("Je ne sais pas");

        }
    }
	
	//Developer: DevA ; Start
	public int methodA() {
		int ttt;
		System.out.println("Enter first number");
        n1 = scannerObject. nextDouble();

        System.out.println("Enter second number");
        n2 = scannerObject. nextDouble();

        Scanner op = new Scanner(System.in);
        System.out.println("Enter your operation");
        operation = op.next();
		
		if(3 < 2 ){
			System.out.println("test");
		}
		else
		{
			System.out.println("else");
		}
		
		}
		
		

        
}