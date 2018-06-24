import java.util.Scanner;

public class Decimal2 extends Decimal3 {
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
		
		for ( int i = 0; i < 6; i++)
		{
			System.out.println("Test for loop");
		}
		
		 try{
			int data=50/0;  
		}
		catch(ArithmeticException e){
			System.out.println(e);	
		}  
		
		
}  
	//Developer: DevA End
	


	
		//Developer: DevB ; Start

	public int methodB(int i, int j){
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
		
		while( x < 20 ) {
         System.out.print("value of x : " + x );
         x++;
         System.out.print("\n");
      }
	  
		System.out.println("Je ne sais pas");
	}
		//Developer: DevB End

	
	public int methodC(int i, int j){
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
		
		while( x < 20 ) {
         System.out.print("value of x : " + x );
         x++;
         System.out.print("\n");
      }
	  
	  
	  
		System.out.println("Je ne sais pas");
	}
}