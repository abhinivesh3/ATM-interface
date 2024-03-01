import java.util.Scanner;

class BankAccount {
    String name;
    String userName;
    String password;
    String accountNo;
    Float balance =100000f;
    int transactions= 0;
    String transactionHistory="";


    public void register(){
        Scanner scanner =new Scanner(System.in);
        System.out.print("\n Enter Your Name - ");
        this.name = scanner.nextLine();
		System.out.print("\nEnter Your Username - ");
		this.userName = scanner.nextLine();
		System.out.print("\nEnter Your Password - ");
		this.password = scanner.nextLine();
		System.out.print("\nEnter Your Account Number - ");
		this.accountNo = scanner.nextLine();
		System.out.println("\nRegistration completed..kindly login");
    }

    public boolean login(){
        boolean isLogin =false;
        Scanner scanner = new Scanner(System.in);
        while(!isLogin){
            System.out.print("\n Enter Your Username - ");
            String Username = scanner.nextLine();
            if(Username.equals(userName)){
                while(!isLogin){
                    System.out.print("\n Enter Your Pasword - ");
                    String Password =scanner.nextLine();
                    if(Password.equals(password)){
                        System.out.print("\n Login Successfully !!!");
                        isLogin=true;
                    }
                    else{
                        System.out.println("\n Incorrect Password");
                    }
                }
            }
            else{
                System.out.println("\nUsername not found");
            }
        }
        return isLogin;
    }

    public void transactionHistory(){
        if(transactions==0){
            System.out.println("\nEmpty");
		}
		else {
			System.out.println("\n" + transactionHistory);
		}
    }

    public void withdraw(){
        System.out.print("\n Enter amount to withdraw - ");
        Scanner scanner =new Scanner(System.in);
        float amount =scanner.nextFloat();
        try{
            if(balance>=amount){
                transactions++;
                balance-=amount;
                System.out.print("\n Withdraw Successfully");
                String str =amount+" Rs Withdrawed\n";
                transactionHistory =transactionHistory.concat(str);
            }
            else{
                System.out.println("\n Insufficient Balance");
            }
        }
        catch(Exception e){
            System.err.println("Error occurs during withdraw "+e);
        }
    }

    public void deposit(){
        System.out.print("\nEnter amount to deposit - ");
        Scanner scanner =new Scanner(System.in);
        float amount =scanner.nextFloat();
        try{
            if(amount<=100000f){
                transactions++;
                balance+=amount;
                System.out.println("\nSuccessfully Deposited");
				String str = amount + " Rs deposited\n";
				transactionHistory = transactionHistory.concat(str);
			}
			else {
				System.out.println("\nSorry...Limit is 100000.00");
			}
			
        }
        catch(Exception e){
            System.err.println("Error occurs during deposite "+e);
        }
    }

    public void transfer() {
        Scanner scanner =new Scanner(System.in);
        System.out.print("\nEnter Receipent's Name - ");
        String receipent = scanner.nextLine();
        System.out.print("\n Enter amount to transfer - ");
        float amount =scanner.nextFloat();

        try{
            if(balance>=amount){
                if(amount<=50000f){
                    transactions++;
                    balance-=amount;
                    System.out.println("\nSuccessfully Transfered to " + receipent);
					String str = amount + " Rs transfered to " + receipent + "\n";
					transactionHistory = transactionHistory.concat(str);
				}
				else {
					System.out.println("\nSorry...Limit is 50000.00");
				}
			}
			else {
				System.out.println("\nInsufficient Balance");
			}
        }
        catch(Exception e){
            System.err.println("Error occurs during transfer "+e);
        }
    }

    public void checkBalance() {
		System.out.println("\n" + balance + " Rs");
	}

}
public class Atm{

    public static int takeIntegerInput(int limit){
        int input=0;
        boolean flag =false;
        while(!flag){
            try{
                Scanner scanner =new Scanner(System.in);
                input =scanner.nextInt();
                flag=true;
                if(flag && input>limit || input<1){
                    System.out.println("Choose the number between 1 to " + limit);
					flag = false;
				}
			}
			catch ( Exception e ) {
				System.out.println("Enter only integer value");
				flag = false;
			}
        };
        return input;
    }
    public static void main(String[] args) {
        
        System.out.println("\n**********WELCOME TO SBI ATM SYSTEM**********\n");
        System.err.println("1.Register \n2.Exit");
        System.out.print("Enter Your Choice - ");
        int choice =takeIntegerInput(2);

        if(choice ==1){
            BankAccount bankAccount =new BankAccount();
            bankAccount.register();
            while (true) {
                System.out.println("\n1.Login \n2.Exit");
                System.out.print("Enter Your Choice - ");
                int ch =takeIntegerInput(2);
                if(ch==1){
                    if(bankAccount.login()){
                        System.out.println("\n\n**********WELCOME BACK " + bankAccount.name + " **********\n");
						boolean isFinished = false;
						while (!isFinished) {
							System.out.println("\n1.Withdraw \n2.Deposit \n3.Transfer \n4.Check Balance \n5.Transaction History \n6.Exit");
							System.out.print("\nEnter Your Choice - ");
							int c = takeIntegerInput(6);
							switch(c) {
                                case 1:
                                bankAccount.withdraw();
                                break;
                                case 2:
								bankAccount.deposit();
								break;
								case 3:
								bankAccount.transfer();
								break;
								case 4:
								bankAccount.checkBalance();
								break;
								case 5:
								bankAccount.transactionHistory();
								break;
								case 6:
								isFinished = true;
								break;
                            }
                        }
                    }
                }
                else {
					System.exit(0);
				}
            }
        }
        else {
            System.exit(0);
        }
    }
}