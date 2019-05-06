package vending_machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {
	// メッセージ
	private static final String MESSAGE_OPERATION_EXPLANATION = "入金する金額または商品名を入力してください";
	private static final String MESSAGE_INPUT_ERROR = "入力が間違っています";
	private static final String MESSAGE_LACK_WALLET = "入金金額が不足しています";
	private static final String MESSAGE_SOLD_OUT = "%sは売り切れです";
	private static final String MESSAGE_CHANGE = "お釣りは%d円です";
	private static final String MESSAGE_WALLET_AMOUNT = "入金金額: %d円";

	// 自動販売機に入っている飲料
	private final List<Drink> drinkList = new ArrayList<Drink>();
	// 自動販売機の料金管理を行うクラス
	private final Wallet wallet = new Wallet();

	public VendingMachine() {
	}

	/**
	 * 飲み物をセットする
	 * @param drink 飲み物
	 */
	public void setDrink(Drink drink) {
		this.drinkList.add(drink);
	}

	/**
	 * 飲み物をセットする
	 * @param drinkList 飲み物のリスト
	 */
	public void setDrink(List<Drink> drinkList) {
		this.drinkList.addAll(drinkList);
	}

	/**
	 * 商品を表示
	 */
	public void displayDrink() {
		for (Drink drink: this.drinkList) {
			System.out.println(drink);
		}
	}

	/**
	 * 標準入力から文字列を入力させる
	 * @return
	 */
	public String inputStr() {
		Scanner scan = new Scanner(System.in);
		return scan.nextLine();
	}

	/**
	 * 引数に渡した文字列が数値かどうかを判定する
	 * @param str
	 * @return	ture: 数値のとき, false: 数値ではないとき
	 */
	public boolean isNumeric(String str) {
		return str.matches("[0-9]+");
	}

	/**
	 * 引数に渡した文字列を数値に変換する
	 * @param str
	 * @return	変換した数値(変換に失敗した場合は0を返す)
	 */
	public int parseInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			// 数値ではなかったとき
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 引数に指定した名前の飲み物を返す
	 * @param drinkName
	 * @return	一致した飲み物（一致する飲み物がなければnullを返す）
	 */
	public Drink getDrink(String drinkName) {
		for (Drink drink: drinkList) {
			if (drink.getName().equals(drinkName)) {
				return drink;
			}
		}

		return null;
	}


	/**
	 * 自動販売機を起動
	 */
	public void run() {
		// 自販機はずっと動いているのでwhile(true)にしておく
		while (true) {
			// 改行
			System.out.println();

			// ドリンクの情報を表示
			displayDrink();

			// 入金金額を表示
			System.out.printf(MESSAGE_WALLET_AMOUNT, this.wallet.getAmount());
			System.out.println();

			// 操作説明を表示
			System.out.println(MESSAGE_OPERATION_EXPLANATION);

			// キーボードから読み取る
			String str = inputStr();

			// 数値がどうか判定
			if (isNumeric(str)) {
				int money = parseInt(str);
				this.wallet.add(money);
				continue;
			}

			// 商品名と一致する飲み物を取得（一致する飲み物がなければnull）
			Drink selectedDrink = getDrink(str);

			// 商品が見つからなかったとき
			if (selectedDrink == null) {
				System.out.println(MESSAGE_INPUT_ERROR);
				continue;
			}

			// 商品が見つかった場合
			// 入金金額が不足していないか判定
			if (!this.wallet.canPay(selectedDrink.getPrice())) {
				System.out.println(MESSAGE_LACK_WALLET);
				continue;
			}
			// 商品の在庫があるか判定
			if (selectedDrink.isEmptyStock()) {
				System.out.printf(MESSAGE_SOLD_OUT, selectedDrink.getName());
				System.out.println();
				continue;
			}

			// 商品を渡し在庫を減らす
			selectedDrink.buy();

			// 入金金額を減らす（お釣りがあれば返す）
			int change = this.wallet.pay(selectedDrink.getPrice());
			if (change > 0) {
				System.out.printf(MESSAGE_CHANGE, change);
				System.out.println();
			}
		}
	}



	/**
	 * 手続き型で実行
	 */
	public void _run() {
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
