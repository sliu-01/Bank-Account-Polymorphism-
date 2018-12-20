import java.util.ArrayList;
import java.util.Scanner;

public class BankAccountMain 
{

	public static void main(String[] args) 
	{
		ArrayList<BankAccount> accounts = new ArrayList<>();

		Scanner in = new Scanner(System.in);
		
		final double OVER_DRAFT_FEE = 15;
		final double RATE = .0025;
		final double TRANSACTION_FEE = 1.5;
		final double MIN_BAL = 300;
		final double MIN_BAL_FEE = 10;
		final int FREE_TRANSACTIONS = 10;
		
		boolean ongoing = true;
		
		while (ongoing)
		{
			System.out.println("Would you like to: \nadd another account\tmake a transaction\tterminate");
			String answer = in.nextLine();
			in.nextLine();
			while (!answer.toLowerCase().equals("add another account") && !answer.toLowerCase().equals("make a transaction") && !answer.toLowerCase().equals("terminate"))
			{
				System.out.println("Please enter a valid choice: \nadd another account\tmake a transaction\tterminate");
				answer = in.nextLine();
				in.nextLine();
			}
			if (answer.equals("terminate"))
			{
				ongoing = false;
			}
			else if (answer.equals("add another account"))
			{
				System.out.println("What type of account would you like to create? \nChecking Account\tSavings Account");
				String type = in.nextLine().toLowerCase();
				in.nextLine();
				
				while (!type.equals("checking account") && !type.equals("savings account"))
				{
					System.out.println("Please enter a valid response: \nChecking Account\tSavings Account");
					type = in.nextLine();
					in.nextLine();
				}
				if (type.toLowerCase().equals("checking account"))
				{
					System.out.println("Please enter your name: ");
					String name = in.next();
					in.nextLine();
					System.out.println("Would you like to enter a balance: (yes or no)");
					String choice = in.next();
					in.nextLine();
					
					if (!choice.toLowerCase().equals("yes") && !choice.toLowerCase().equals("no"))
					{
						System.out.println("Please enter a valid response: (yes or no)");
						choice = in.next();
						in.nextLine();
					}
					else if (choice.toLowerCase().equals("yes"))
					{
						System.out.println("Please enter your balance: ");
						int balance = in.nextInt();
						in.nextLine();
						CheckingAccount newEntry = new CheckingAccount(name, balance, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS);
						accounts.add(newEntry);
					}
					else
					{
						CheckingAccount newEntry = new CheckingAccount(name, 0, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS);
						accounts.add(newEntry);
					}

				}
				else
				{
					System.out.println("Please enter your name: ");
					String name = in.next();
					in.nextLine();
					System.out.println("Would you like to enter a balance: (yes or no)");
					String choice = in.next();
					in.nextLine();
					
					if (!choice.toLowerCase().equals("yes") && !choice.toLowerCase().equals("no"))
					{
						System.out.println("Please enter a valid response: (yes or no)");
						choice = in.next();
						in.nextLine();
					}
					else if (choice.toLowerCase().equals("yes"))
					{
						System.out.println("Please enter your balance: ");
						int balance = in.nextInt();
						in.nextLine();
						SavingsAccount newEntry = new SavingsAccount(name, balance, RATE, MIN_BAL, MIN_BAL_FEE);
						accounts.add(newEntry);
					}
					else
					{
						SavingsAccount newEntry = new SavingsAccount(name, 0, RATE, MIN_BAL, MIN_BAL_FEE);
						accounts.add(newEntry);
					}
				}
				System.out.println("Your account is created.");
			}
			else
			{
				BankAccount accountInUse = null;
				System.out.println("Which transaction would you like to select: \nWithdraw\tDeposit\tTransfer\tGet Account Number");
				String choice = in.nextLine();
				in.nextLine();
				
				while(!choice.toLowerCase().equals("withdraw") && !choice.toLowerCase().equals("desposit") && !choice.toLowerCase().equals("transfer") && !choice.toLowerCase().equals("get account number"))
				{
					System.out.println("Please enter a valid response: \nWithdraw\tDeposit\tTransfer\tGet Account Number");
					choice = in.nextLine();
					in.nextLine();
				}
				
				System.out.println("Please enter your account number: ");
				int accountNum = in.nextInt();
				in.nextLine();
				
				boolean notFound = true;
				for (BankAccount account : accounts)
				{
					if (account.getAccountNum() == accountNum)
					{
						notFound = false;
						accountInUse = account;
					}
				}
				while(notFound)
				{
					System.out.println("We apologise, but we cannot find the account linked with the number you entered." + 
							"\nWould you like to enter a new account number or have us show all accounts under your name: " +
							"\nEnter Another Account Number\tShow My Accounts");
					String choice1 = in.next();
					in.nextLine();
					
					while(!choice1.toLowerCase().equals("enter another account number") && !choice1.toLowerCase().equals("show my accounts"))
					{
						System.out.println("Please enter a valid response: \nEnter Another Acount Number\tShow My Accounts");
						choice1 = in.next();
						in.nextLine();
					}
					
					if (choice1.toLowerCase().equals("enter another account number"))
					{
						System.out.println("Please enter your account number: ");
						accountNum = in.nextInt();
						in.nextLine();
						
						for (BankAccount account : accounts)
						{
							if (account.getAccountNum() == accountNum)
							{
								notFound = false;
							}
						}
					}
					
					else if (choice1.toLowerCase().equals("show my accounts"))
					{
						System.out.println("Please enter the name your accounts are under");
						String nameInput = in.next();
						in.nextLine();
						
						for (BankAccount account : accounts)
						{
							if (account.getName().equals(nameInput))
							{
								notFound = false;
							}
						}
						
						if (notFound)
						{
							System.out.println("We are sorry, but there are no accounts under the name you entered.\nYou will be returned to the home screen.");
						}
						else
						{
							for (BankAccount account : accounts)
							{
								if (account.getName().equals(nameInput))
								{
									if (account instanceof CheckingAccount)
									{
										System.out.println("Checking Account " +  account.getAccountNum());
									}
									else
									{
										System.out.println("Savings Account " +  account.getAccountNum());
									}
								}
							}
						}
					}
					
				}
				if (!notFound)
				{
					if (choice.toLowerCase().equals("withdraw"))
					{
						System.out.println("How much would you like to withdraw? ");
						int amount = in.nextInt();
						in.nextLine();
						
						accountInUse.withdraw(amount);
					}
					
					else if (choice.toLowerCase().equals("deposit"))
					{
						System.out.println("How much would you like to deposit? ");
						int amount = in.nextInt();
						in.nextLine();
						
						accountInUse.deposit(amount);
					}
					
					else if (choice.toLowerCase().equals("transfer"))
					{
						
						System.out.println("How much would you like to transfer? ");
						int amount = in.nextInt();
						in.nextLine();
						

					}
				}
			}
		}

	}
	
	public static String reprompt()
	{
		Scanner in = new Scanner(System.in);
		System.out.println("The account number you entered is not a valid response.\nWhat would you like to do next?\nEnter Another Account Number\tFind My Accounts");
		String choice1 = in.next();
		in.nextLine();
		
		while (!choice1.toLowerCase().equals("enter another account number") && !choice1.toLowerCase().equals("find my account"))
		{
			
		}
	}

}
