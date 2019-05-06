package vending_machine;

public class Main {

	public static void main(String[] args) {
		// 自動販売機を生成
		VendingMachine vm = new VendingMachine();

		// ドリンクをセット
		vm.setDrink(new Drink("Tea", 5, 100));
		vm.setDrink(new Drink("Cola", 5, 100));
		vm.setDrink(new Drink("Green Tea", 10, 120));

		// 自動販売機を起動
		vm.run();
	}

}
