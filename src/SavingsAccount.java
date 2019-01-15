
public class SavingsAccount extends BankAccount
{
	private double intRate;
	private final double MIN_BAL;
	private final double MIN_BAL_FEE;
	
	/**
	 * Constructs a savings account with a balance that the user inputs.
	 * @param n the name the account falls under
	 * @param b the balance of the account
	 * @param r the interest rate
	 * @param mb the minimum balance for the account
	 * @param mbf the fee taken from the account when the balance drops under the minimum balance
	 */
	public SavingsAccount(String n, double b, double r, double mb, double mbf)
	{
		super(n, b);
		this.intRate = r;
		this.MIN_BAL = mb;
		this.MIN_BAL_FEE = mbf;
	}
	/**
	 * Constructs a savings account with a balance of 0.
	 * @param n the name of the account
	 * @param r the interest rate
	 * @param mb the minimum balance for the account
	 * @param mbf the fee taken from the account when the balance drops under the minimum balance
	 */
	public SavingsAccount(String n, double r, double mb, double mbf)
	{
		this(n, 0, r, mb, mbf);
	}
	
	/**
	 * Deposits a certain amount into the user's account.
	 * @param amt the amount deposited
	 */
	public void deposit(double amt)
	{
		if (amt <= 0)
		{
			throw new IllegalArgumentException();
		}
		super.deposit(amt);
	}
	
	/**
	 * Withdraws a certain positive amount from the account, as long as the balance does not fall below 0.
	 * @param amt the amount withdrawn from the user's account
	 */
	public void withdraw(double amt)
	{
		if (amt <= 0 || (this.getBalance() - amt) < 0)
		{
			throw new IllegalArgumentException();
		}
		super.withdraw(amt);
		if (this.getBalance() < MIN_BAL)
		{
			super.withdraw(MIN_BAL_FEE);
		}
	}
	/**
	 * Transfer a certain amount of money from the user account to a separate account under the same name.
	 * @param other the bank account to transfer to
	 * @param amt the amount to transfer to the other account
	 */
	public void transfer(BankAccount other, double amt)
	{
		if (!this.getName().equals(other.getName()) || (this.getBalance() - amt) < 0)
		{
			throw new IllegalArgumentException();
		}
		super.withdraw(amt);
		if (this.getBalance() < MIN_BAL)
		{
			super.withdraw(MIN_BAL_FEE);
		}
		other.deposit(amt);
	}
	
	/**
	 * A certain amount is added to the balance based on the interest rate.
	 */
	public void addInterest()
	{
		this.deposit(this.getBalance()*(this.intRate));
	}
	/**
	 * At the end of a month, interest is added to the balance.
	 */
	public void endOfMonthUpdate()
	{
		this.addInterest();
	}
	/**
	 * Gets the account's identification number.
	 */
	public int getAccountNum()
	{
		return super.getAccountNum();
	}
	/**
	 * The name is returned.
	 */
	public String getName()
	{
		return super.getName();
	}
}
