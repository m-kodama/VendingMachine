package vending_machine;

public class Drink {
	private String name;
	private int count;
	private int price;

	public Drink(String name, int count, int price) {
		this.name = name;
		this.count = count;
		this.price = price;
	}

	public String getName() {
		return this.name;
	}

	public int getPrice() {
		return this.price;
	}

	public boolean isEmptyStock() {
		return this.count <= 0;
	}

	public void buy() {
		this.count--;
		System.out.println("( ´ ▽ ` )つ [" + this.name + "]");
	}

	@Override
	public String toString() {
		return this.name + "(" + this.price + "円): 残り" + this.count + "本";
	}
}
