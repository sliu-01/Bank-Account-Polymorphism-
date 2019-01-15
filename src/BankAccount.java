
public abstract class BankAccount 
{
	private static int nextAccNum;
	private String name;
	private int accNum;
	private double balance;
	
	/**
	 * Constructs a bank account whose balance is 0.
	 * @param n the name the account falls under
	 */
	public BankAccount(String n)
	{
		name = n;
		accNum = nextAccNum;
		balance = 0;
		nextAccNum++;
	}
	/**
	 * Constructs a bank account whose balance is input by the user.
	 * @param n the name the account is under
	 * @param b the initial balance of the account
	 */
	public BankAccount(String n, double b)
	{
		name = n;
		accNum = nextAccNum;
		balance = b;
		nextAccNum++;
	}
	/**
	 * Deposits a certain amount into the account.
	 * @param amt the amount deposited
	 */
	public void deposit(double amt)
	{
		balance += amt;
	}
	/**
	 * Withdraws a certain amount from the account.
	 * @param amt the account withdrawn from the account
	 */
	public void withdraw(double amt)
	{
		balance -= amt;
	}
	/**
	 * Reveals the name the account falls under.
	 * @return the name the account is under
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * Gives the balance of the account
	 * @return the balance of the account
	 */
	public double getBalance()
	{
		return balance;
	}
	/**
	 * An abstract method to be filled for the classes which extend the Bank Account class that deals with what occurs at the end of each month.
	 */
	public abstract void endOfMonthUpdate();
	/**
	 * Transfers a certain amount from one account to another account.
	 * @param other the account to be transfered to 
	 * @param amt the amount transfered
	 */
	public void transfer(BankAccount other, double amt)
	{
		this.withdraw(amt);
		other.deposit(amt);
	}
	/**
	 * Returns the account number, name, and balance of the account.
	 */
	public String toString()
	{
		return "" + accNum + "\t" + name + "\t$" + balance;
	}
	/**
	 * Returns the account number
	 * @return the account number
	 */
	public int getAccountNum()
	{
		return accNum;
	}
}
