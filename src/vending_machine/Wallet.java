package vending_machine;

public class Wallet {
	private int amount;

	public Wallet() {
		this.amount = 0;
	}

	public int add(int money) {
		this.amount += money;
		return this.amount;
	}

	public int getAmount() {
		return this.amount;
	}

	public boolean canPay(int price) {
		return this.amount >= price;
	}

	public int pay(int price) {
		int change = this.amount - price;
		this.amount = 0;
		return change;
	}
}
