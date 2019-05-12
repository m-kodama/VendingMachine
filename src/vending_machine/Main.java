package vending_machine;

import common.WebAPI;

public class Main {

	public static void main(String[] args) {
		// 自動販売機を生成
		VendingMachine vm = new VendingMachine();

		// ドリンクをセット
		vm.setDrink(new Drink("Tea", 0, 100));
		vm.setDrink(new Drink("Cola", 5, 100));
		vm.setDrink(new Drink("Green Tea", 10, 120));
		vm.setDrink(new Drink("お茶", 100, 150));

		// 自動販売機を起動
//		vm.run();

		System.out.println("before api call");

		System.out.println(WebAPI.call());

		System.out.println("after api call");
	}

}
