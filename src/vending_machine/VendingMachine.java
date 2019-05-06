package vending_machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {

	public VendingMachine() {
	}

	public void run() {
		// 自販機の中身
		List<String> drinkList = new ArrayList<String>();
		List<Integer> drinkCountList = new ArrayList<Integer>();
		List<Integer> drinkPriceList = new ArrayList<Integer>();
		// 名前
		drinkList.add("Tea");
		drinkList.add("Cola");
		// 個数
		drinkCountList.add(5);
		drinkCountList.add(5);
		// 値段
		drinkPriceList.add(100);
		drinkPriceList.add(100);

		// 入金金額
		int amount = 0;

		// 自販機はずっと動いているのでwhile(true)にしておく
		while (true) {
			// 改行
			System.out.println();

			// ドリンクの情報を表示
			for (int i = 0, n = drinkList.size(); i < n; i++) {
				String name = drinkList.get(i);
				int count = drinkCountList.get(i);
				int price = drinkPriceList.get(i);
				System.out.println(name + "(" + price + "円): 残り" + count + "本");
			}

			// 入金金額を表示
			System.out.println("入金金額: " + amount + "円");

			// 操作説明
			System.out.println("入金する金額または商品名を入力してください");

			// キーボードから読み取る
			Scanner scan = new Scanner(System.in);
			String str = scan.nextLine();

			// 数値がどうか判定
			if (str.matches("[0-9]+")) {
				try {
					int price = Integer.parseInt(str);
					amount += price;
					continue;
				} catch (NumberFormatException e) {
					// 数値ではなかったとき
					e.printStackTrace();
					continue;
				}
			}

			// 商品名と一致するかどうか判定
			boolean isFound = false;
			for (String name: drinkList) {
				if (str.equals(name)) {
					isFound = true;
					break;
				}
			}

			// 商品が見つからなかったとき
			if (!isFound) {
				System.out.println("入力が間違っています");
				continue;
			}

			// 商品が見つかった場合
			int index = drinkList.indexOf(str);
			// 商品の在庫があるか判定
			if (drinkCountList.get(index) <= 0) {
				System.out.println("その商品は売り切れです");
				continue;
			}
			// 入金金額が不足していないか判定
			if (amount < drinkPriceList.get(index)) {
				System.out.println("入金金額が不足しています");
				continue;
			}

			// 商品を渡し在庫を減らす
			System.out.println("( ´ ▽ ` )つ " + str);
			drinkCountList.set(index, drinkCountList.get(index) - 1);

			// 入金金額を減らす（お釣りがあれば返す）
			amount -= drinkPriceList.get(index);
			if (amount > 0) {
				System.out.println("お釣りは" + amount + "円です");
				amount = 0;
			}
		}
	}

}
