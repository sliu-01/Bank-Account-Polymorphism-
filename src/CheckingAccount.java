
public class CheckingAccount extends BankAccount
{
	private final double OVER_DRAFT_FEE;
	private final double TRANSACTION_FEE;
	private final double FREE_TRANS;
	private int numTransactions;
	
	public CheckingAccount(String n, double b, double odf, double tf, double freeTrans)
	{
		super(n, b);
		this.OVER_DRAFT_FEE = odf;
		this.TRANSACTION_FEE = tf;
		this.FREE_TRANS = freeTrans;
	}
	public CheckingAccount(String n, double odf, double tf, double freeTrans)
	{
		this(n, 0, odf, tf, freeTrans);
	}
	
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
	public void endOfMonthUpdate()
	{
		numTransactions = 0;
	}
	public int getAccountNum()
	{
		return super.getAccountNum();
	}
	public String getName()
	{
		return super.getName();
	}
}
