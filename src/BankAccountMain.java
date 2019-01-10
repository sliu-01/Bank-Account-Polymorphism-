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
			System.out.println("Would you like to: \nAdd Another Account\tMake a Transaction\tTerminate");
			String answer = in.nextLine().trim();

			while (!answer.toLowerCase().equals("terminate") && !answer.toLowerCase().equals("add another account") && !answer.toLowerCase().equals("make a transaction"))
			{
				System.out.println("Please enter a valid choice: \nAdd Another Account\tMake a Transaction\tTerminate");
				answer = in.nextLine().trim();
			}
			if (answer.toLowerCase().equals("terminate"))
			{
				ongoing = false;
			}
			else if (answer.toLowerCase().equals("add another account"))
			{
				System.out.println("What type of account would you like to create? \nChecking Account\tSavings Account");
				String type = in.nextLine().trim().toLowerCase();
				
				while (!type.equals("checking account") && !type.equals("savings account"))
				{
					System.out.println("Please enter a valid response: \nChecking Account\tSavings Account");
					type = in.nextLine().trim();
				}
				if (type.toLowerCase().equals("checking account"))
				{
					System.out.println("Please enter your name: ");
					String name = in.nextLine().trim();
					System.out.println("Would you like to enter a balance: (yes or no)");
					String choice = in.nextLine().trim();
					
					if (!choice.toLowerCase().equals("yes") && !choice.toLowerCase().equals("no"))
					{
						System.out.println("Please enter a valid response: (yes or no)");
						choice = in.nextLine().trim();
					}
					else if (choice.toLowerCase().equals("yes"))
					{
						System.out.println("Please enter your balance: ");
						String balance = in.nextLine().trim();
						
						while (!isNumeric(balance) || Double.parseDouble(balance) < 0)
						{
							System.out.println("The value you input is not valid. Please try again: ");
							balance = in.nextLine().trim();
						}
						CheckingAccount newEntry = new CheckingAccount(name, Double.parseDouble(balance), OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS);
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
					String name = in.nextLine().trim();
					System.out.println("Would you like to enter a balance: (yes or no)");
					String choice = in.nextLine().trim();
					
					if (!choice.toLowerCase().equals("yes") && !choice.toLowerCase().equals("no"))
					{
						System.out.println("Please enter a valid response: (yes or no)");
						choice = in.nextLine().trim();
					}
					else if (choice.toLowerCase().equals("yes"))
					{
						System.out.println("Please enter your balance: ");
						String balance = in.nextLine().trim();
						
						while (!isNumeric(balance) || Double.parseDouble(balance) < 0)
						{
							System.out.println("The value you input is not valid. Please try again: ");
							balance = in.nextLine().trim();
						}
						SavingsAccount newEntry = new SavingsAccount(name, Double.parseDouble(balance), RATE, MIN_BAL, MIN_BAL_FEE);
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
				String choice = in.nextLine().trim();
				
				while(!choice.toLowerCase().equals("withdraw") && !choice.toLowerCase().equals("deposit") && !choice.toLowerCase().equals("transfer") && !choice.toLowerCase().equals("get account number"))
				{
					System.out.println("Please enter a valid response: \nWithdraw\tDeposit\t\tTransfer\tGet Account Number\tTerminate");
					choice = in.nextLine().trim();
				}
				boolean exit = false;
				String accountNum;
				if (!choice.toLowerCase().equals("get account number"))
				{
					System.out.println("Please enter your account number: ");
					accountNum = in.nextLine().trim();
					
					while (!isNumeric(accountNum))
					{
						System.out.println("The value you input is not valid. Please try again: ");
						accountNum = in.nextLine().trim();
					}
					double accNum = Double.parseDouble(accountNum);
					boolean notFound = true;
					for (BankAccount account : accounts)
					{
						if (account.getAccountNum() == accNum)
						{
							notFound = false;
							accountInUse = account;
						}
					}
					
					while(notFound)
					{
						System.out.println("We apologise, but we cannot find the account linked with your input." + 
								"\nWould you like to enter a new account number or have us show all accounts under your name: " +
								"\nEnter Another Account Number\tShow My Accounts\tTerminate");
						String choice1 = in.nextLine().trim();
						
						while(!choice1.toLowerCase().equals("terminate") && !choice1.toLowerCase().equals("enter another account number") && !choice1.toLowerCase().equals("show my accounts"))
						{
							System.out.println("Please enter a valid response: \nEnter Another Acount Number\tShow My Accounts\tTerminate");
							choice1 = in.nextLine().trim();
						}
						if (choice1.toLowerCase().equals("terminate"))
						{
							exit = true;
							notFound = false;
						}
						else if (choice1.toLowerCase().equals("enter another account number"))
						{
							System.out.println("Please enter your account number: ");
							accountNum = in.nextLine().trim();
							
							while (!isNumeric(accountNum))
							{
								System.out.println("The value you input is not valid. Please try again: ");
								accountNum = in.nextLine().trim();
							}
							accNum = Double.parseDouble(accountNum);
							for (BankAccount account : accounts)
							{
								if (account.getAccountNum() == accNum)
								{
									notFound = false;
									accountInUse = account;
								}
							}
						}
						
						else if (choice1.toLowerCase().equals("show my accounts"))
						{
							System.out.println("Please enter the name your accounts are under");
							String nameInput = in.nextLine().trim();
							
							for (BankAccount account : accounts)
							{
								if (account.getName().equals(nameInput))
								{
									notFound = false;
									accountInUse = account;
								}
							}
							if (!notFound)
							{
								for (BankAccount account : accounts)
								{
									if (account.getName().equals(nameInput))
									{
										if (account instanceof CheckingAccount)
										{
											System.out.println("Checking Account " +  account.toString());
										}
										else
										{
											System.out.println("Savings Account " +  account.toString());
										}
									}
								}
							}
						}	
					}
				}
				if (exit)
				{
					exit = false;
					
				}
				else 
				{
					switch (choice.toLowerCase())
					{
						case "withdraw":
						{
							System.out.println("How much would you like to withdraw? ");
							String amount = in.nextLine().trim();
							
							while (!isNumeric(amount))
							{
								System.out.println("The value you input is not valid. Please try again: ");
								amount = in.nextLine().trim();
							}
							try
							{
								accountInUse.withdraw(Double.parseDouble(amount));
							}
							catch (IllegalArgumentException e)
							{
								System.out.println("You made an illegal action. It did not go through.");
							}
							break;
						}
						case "deposit":
						{
							System.out.println("How much would you like to deposit? ");
							String amount = in.nextLine().trim();
							while (!isNumeric(amount))
							{
								System.out.println("The value you input is not valid. Please try again: ");
								amount = in.nextLine().trim();
							}
							try
							{
								accountInUse.deposit(Double.parseDouble(amount));
							}
							catch (IllegalArgumentException e)
							{
								System.out.println("You made an illegal action. It did not go through.");
							}
							break;
						}
						case "transfer":
						{
							System.out.println("Please enter the account number you would like to transfer to: ");
							String otherAccNum = in.nextLine().trim();
							BankAccount otherAccount = null;
							while (!isNumeric(otherAccNum))
							{
								System.out.println("The value you input is not valid. Please try again: ");
								accountNum = in.nextLine().trim();
							}
							double otherAccountNum = Double.parseDouble(otherAccNum);
							boolean notValid = true;
							for (BankAccount account : accounts)
							{
								if (otherAccountNum == account.getAccountNum())
								{
									notValid = false;
									otherAccount = account;
								}
							}
							while(notValid)
							{
								System.out.println("We apologise, but we cannot find the account linked with your input." + 
										"\nWould you like to enter a new account number or have us show all accounts under the name: " +
										"\nEnter Another Account Number\tShow My Accounts\tTerminate");
								String choice1 = in.nextLine().trim();
								
								while(!choice1.toLowerCase().equals("terminate") && !choice1.toLowerCase().equals("enter another account number") && !choice1.toLowerCase().equals("show accounts"))
								{
									System.out.println("Please enter a valid response: \nEnter Another Acount Number\tShow Accounts\tTerminate");
									choice1 = in.nextLine().trim();
								}
								if (choice1.toLowerCase().equals("terminate"))
								{
									exit = true;
									notValid = false;
								}
								else if (choice1.toLowerCase().equals("enter another account number"))
								{
									System.out.println("Please enter your account number: ");
									otherAccNum = in.nextLine().trim();
									
									while (!isNumeric(otherAccNum))
									{
										System.out.println("The value you input is not valid. Exit, or try again: ");
										otherAccNum = in.nextLine().trim();
									}
									otherAccountNum = Double.parseDouble(otherAccNum);
									for (BankAccount account : accounts)
									{
										if (account.getAccountNum() == otherAccountNum)
										{
											notValid = false;
										}
									}
								}
								
								else if (choice1.toLowerCase().equals("show accounts"))
								{
									System.out.println("Please enter the name the accounts are under");
									String nameInput = in.nextLine().trim();
									
									for (BankAccount account : accounts)
									{
										if (account.getName().equals(nameInput))
										{
											notValid = false;
										}
									}
									if (!notValid)
									{
										for (BankAccount account : accounts)
										{
											if (account.getName().equals(nameInput))
											{
												if (account instanceof CheckingAccount)
												{
													System.out.println("Checking Account " +  account.toString());
												}
												else
												{
													System.out.println("Savings Account " +  account.toString());
												}
											}
										}
									}
								}
							}
							System.out.println("How much would you like to transfer? ");
							String amount = in.nextLine().trim();
							while (!isNumeric(amount))
							{
								System.out.println("The value you input is not valid. Please try again: ");
								amount = in.nextLine().trim();
							}
							double amt = Double.parseDouble(amount);
							try
							{
								accountInUse.transfer(otherAccount, amt);
							}
							catch (IllegalArgumentException e)
							{
								System.out.println("You made an illegal action. It did not do through.");
							}
							break;
						}
						case "get account number":
						{
							System.out.println("Please input the name you account falls under: ");
							String name = in.nextLine().trim();
							boolean existing = true;
							for (BankAccount account : accounts)
							{
								if (name.equals(account.getName()))
								{
									existing = false;
								}
							}
							while (existing)
							{
								System.out.println("The name you input does not match a name linked to an account.\nPlease Try Again, Capitalization Matters: ");
								name = in.nextLine().trim();
								for (BankAccount account : accounts)
								{
									if (name.equals(account.getName()))
									{
										existing = false;
									}
								}
							}
							for (BankAccount account : accounts)
							{
								if (account.getName().equals(name))
								{
									if (account instanceof CheckingAccount)
									{
										System.out.println("Checking Account: " +  account.toString());
									}
									else
									{
										System.out.println("Savings Account: " +  account.toString());
									}
								}
							}
							break;
						}
					}
				}
			}
		}
	}
	
	private static boolean isNumeric(String str)
	{
		try
		{
			Double.parseDouble(str);
			return true;
		}
		catch (IllegalArgumentException e)
		{
			return false;
		}
	}

}
