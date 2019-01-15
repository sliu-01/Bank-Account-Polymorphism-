
public class CheckingAccount extends BankAccount
{
	private final double OVER_DRAFT_FEE;
	private final double TRANSACTION_FEE;
	private final double FREE_TRANS;
	private int numTransactions;
	
	/**
	 * Constructs a checking account whose balance can be changed upon creation.
	 * @param n the name of the account holder
	 * @param b the balance of the account
	 * @param odf the fee applied when the account is withdrawn from when already negative
	 * @param tf the transaction fee
	 * @param freeTrans the number of transaction fees the account is authorized
	 */
	public CheckingAccount(String n, double b, double odf, double tf, double freeTrans)
	{
		super(n, b);
		this.OVER_DRAFT_FEE = odf;
		this.TRANSACTION_FEE = tf;
		this.FREE_TRANS = freeTrans;
		
	}
	/**
	 * 
	 * @param n the name of the account holder
	 * @param odf the fee applied when the account is withdrawn from when already negative
	 * @param tf the transaction fee
	 * @param freeTrans the number of transaction fees the account is authorized
	 */
	public CheckingAccount(String n, double odf, double tf, double freeTrans)
	{
		this(n, 0, odf, tf, freeTrans);
	}
	
	/**
	 * Deposits a certain positive amount into the account.
	 * @param amt the amount deposited into the account
	 */
	public void deposit(double amt)
	{
		if (numTransactions < FREE_TRANS)
		{
			if (amt < 0)
			{
				throw new IllegalArgumentException();
			}
			numTransactions ++;
			super.deposit(amt);
		}
		else
		{
			if (amt < 0)
			{
				throw new IllegalArgumentException();
			}
			numTransactions ++;
			super.deposit(amt - TRANSACTION_FEE);
		}
	}
	/**
	 * Withdraws a certain positive amount from the account, with each subsequent withdraw from a negative balance applying the overdraft fee.
	 * @param amt the amount deposited into the account
	 */
	public void withdraw(double amt)
	{
		if (numTransactions < FREE_TRANS)
		{
			if (amt <= 0 || this.getBalance() < 0 )
			{
				throw new IllegalArgumentException();
			}
			numTransactions ++;
			super.withdraw(amt);
			if (this.getBalance() < 0)
			{
				super.withdraw(OVER_DRAFT_FEE);
			}
		}
		else
		{
			if (amt < 0 || this.getBalance() < 0 )
			{
				throw new IllegalArgumentException();
			}
			numTransactions ++;
			super.withdraw(amt + TRANSACTION_FEE);
			if (this.getBalance() < 0)
			{
				super.withdraw(OVER_DRAFT_FEE);
			}
		}
	}
	/**
	 * Transfers a certain positive amount from the user's account to an alternate account under the same name, with the overdraft fee applying when the balance of the first account is below 0.
	 * @param other the other bank account with a certain amount transfered to
	 * @param amt the amount transfered between the accounts
	 */
	public void transfer(BankAccount other, double amt)
	{
		
		if (!this.getName().equals(other.getName()) || amt < 0 || (this.getBalance() - amt) < 0)
		{
			throw new IllegalArgumentException();
		}
		if (numTransactions < FREE_TRANS)
		{
			this.withdraw(amt);
			if (this.getBalance() < 0)
			{
				this.withdraw(OVER_DRAFT_FEE);
			}
			other.deposit(amt);
		}
		else
		{
			if (amt < 0 || (this.getBalance() - amt) < 0)
			{
				throw new IllegalArgumentException();
			}
			this.withdraw(amt + TRANSACTION_FEE);
			if (this.getBalance() < 0)
			{
				this.withdraw(OVER_DRAFT_FEE);
			}
			other.deposit(amt);
		}
	}
	/**
	 * At the end of each month, the number of free transactions left is reset.
	 */
	public void endOfMonthUpdate()
	{
		numTransactions = 0;
	}
	/**
	 * Gets the account's identification number.
	 */
	public int getAccountNum()
	{
		return super.getAccountNum();
	}
	/**
	 * Gets the name the account falls under.
	 */
	public String getName()
	{
		return super.getName();
	}
}
