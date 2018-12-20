
public abstract class BankAccount 
{
	private static int nextAccNum;
	private String name;
	private int accNum;
	private double balance;
	
	public BankAccount(String n)
	{
		name = n;
		accNum = nextAccNum;
		balance = 0;
	}
	public BankAccount(String n, double b)
	{
		name = n;
		accNum = nextAccNum;
		balance = b;
	}
	public void deposit(double amt)
	{
		balance += amt;
	}
	public void withdraw(double amt)
	{
		balance -= amt;
	}
	public String getName()
	{
		return name;
	}
	public double getBalance()
	{
		return balance;
	}
	public abstract void endOfMonthUpdate();
	public void transfer(BankAccount other, double amt)
	{
		this.withdraw(amt);
		other.deposit(amt);
	}
	public String toString()
	{
		return "" + accNum + "\t" + name + "\t$" + balance;
	}
	public int getAccountNum()
	{
		return nextAccNum;
	}
}
