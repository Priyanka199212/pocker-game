package com.edu.au.card;

import java.util.Arrays;

import com.edu.au.enums.HandCategory;

/**
 * Priyanka Yadav 28/10/2020
 */

public class Hand {
	public CardInfo[] cards;

	public HandCategory category;

	public Integer handValue;

	public Hand(CardInfo[] cards) {
		this.cards = cards;
	}

	public Hand(String[] strArr) {
		if (strArr.length != 5) {
			System.out.println("Wrong hand format.");
		} else {
			CardInfo[] cards = new CardInfo[5];
			for (int i = 0; i < 5; i++) {
				cards[i] = new CardInfo(strArr[i]);
			}
			this.cards = cards;
		}
	}

	public void sortCards() {
		Arrays.sort(this.cards);
	}

	public CardInfo getCard(int index) {
		if (index >= 5) {
			return null;
		}
		return cards[index];
	}

	public String toString() {
		String str = "";
		for (CardInfo card : this.cards) {
			str += card.toString() + " ";
		}
		if (str.length() > 0)
			str += "(" + this.getHandCategory().getDesc() + ")";
		return str;
	}

	public HandCategory getHandCategory() {
		return this.category;
	}

	public Integer getHandValue() {
		return this.handValue;

	}

	/**
	 * Evaluate card pairs
	 */
	public void evaluateInfo() {

		if (this.allSameSuit() != -1 && this.straight() != -1) {
			if (this.getCard(0).getValue() == 10) {
				this.category = HandCategory.ROYAL_FLUSH;
				this.handValue = 9999;
				return;
			} else {
				this.category = HandCategory.STRAIGHT_FLUSH;
				return;
			}
		}

		if (this.four() != -1) {
			this.category = HandCategory.FOUR_OF_A_KIND;
			return;
		}

		if (this.fullHouse() != -1) {
			this.category = HandCategory.FULL_HOUSE;
			return;
		}

		if (this.allSameSuit() != -1) {
			this.category = HandCategory.FLUSH;
			return;
		}

		if (this.straight() != -1) {
			this.category = HandCategory.STRAIGHT;
			return;
		}

		if (this.three() != -1) {
			this.category = HandCategory.THREE_OF_A_KIND;
			return;
		}

		if (this.twoPairs() != -1) {
			this.category = HandCategory.TWO_PAIRS;
			return;
		}

		if (this.pair() != -1) {
			this.category = HandCategory.ONE_PAIR;
			return;
		}

		this.handValue = this.getCard(4).getValue();
		this.category = HandCategory.HIGH_CARD;
	}

	/**
	 * 
	 * @return 
	 * Get pair of card
	 */
	private int pair() {
		int prev = this.cards[4].getValue();
		int total = 0, nOfCards = 1;

		for (int i = 3; i >= 0; i--) {
			if (this.cards[i].getValue() == prev) {
				total += this.cards[i].getValue();
				nOfCards++;
			}

			if (nOfCards == 2) {
				break;
			}
			prev = this.cards[i].getValue();
		}

		if (nOfCards == 2) {
			this.handValue = total;
			return total;
		}
		return -1;
	}

	/**
	 * 
	 * @return 
	 * Get 2 card pairs
	 */
	private int twoPairs() {
		int prev = this.cards[4].getValue();
		int i = 3, total = 0, nOfCards = 1;

		for (; i >= 0; i--) {
			if (this.cards[i].getValue() == prev) {
				total += this.cards[i].getValue();
				nOfCards++;
			}

			if (nOfCards == 2) {

				break;
			} else {
				total = 0;
				nOfCards = 1;
			}
			prev = this.cards[i].getValue();
		}

		if (nOfCards == 2 && i > 0) {
			nOfCards = 1;
			prev = this.cards[i - 1].getValue();
			for (i = i - 2; i >= 0; i--) {
				if (this.cards[i].getValue() == prev) {
					total += this.cards[i].getValue();
					nOfCards++;
				}
				if (nOfCards == 2) {
					break;
				} else {
					total = 0;
					nOfCards = 1;
				}
				prev = this.cards[i].getValue();
			}
		} else {
			return -1;
		}

		if (nOfCards == 2) {
			this.handValue = total;
			return total;
		}
		return -1;
	}

	/**
	 * 
	 * @return 
	 * Get card pair
	 */
	private int three() {
		int prev = this.cards[4].getValue();
		int total = 0, nOfCards = 1;

		for (int i = 3; i >= 0; i--) {
			if (this.cards[i].getValue() == prev) {
				total += this.cards[i].getValue();
				nOfCards++;
			} else {
				total = 0;
				nOfCards = 1;
			}

			prev = this.cards[i].getValue();
		}

		if (nOfCards == 3) {
			this.handValue = total;
			return total;
		}
		return -1;
	}

	/**
	 * 
	 * @return 
	 * Full house
	 */
	private int fullHouse() {
		// System.out.println(this.toString());
		boolean changed = false;
		int prev = this.cards[4].getValue();
		int total = 0, nOfCards = 1;

		for (int i = 3; i >= 0; i--) {
			if (this.cards[i].getValue() == prev) {
				total += this.cards[i].getValue();
				nOfCards++;

			} else if (changed == false) {
				changed = true;
				if (nOfCards < 2) {
					this.handValue = -1;
					return -1;
				}

				if (nOfCards == 3)
					this.handValue = total;

			} else {
				this.handValue = -1;
				return -1;
			}
			prev = this.cards[i].getValue();
		}
		// System.out.println(total);
		this.handValue = total;
		return total;

	}

	/**
	 * 
	 * @return
	 * Get all 4 cards
	 */
	private int four() {
		int prev = this.cards[4].getValue();
		int total = 0, nOfCards = 1;

		for (int i = 3; i >= 0 && nOfCards < 4; i--) {
			if (this.cards[i].getValue() == prev) {
				total += this.cards[i].getValue();
				nOfCards++;
			} else {
				total = 0;
				nOfCards = 1;
			}

			prev = this.cards[i].getValue();
		}

		if (nOfCards == 4) {
			this.handValue = total;
			return total;
		}
		return -1;
	}

	/**
	 * 
	 * @return 
	 * Same suite
	 */
	private int allSameSuit() {

		char prev = this.cards[0].getSuit();
		int total = this.cards[0].getValue();

		for (int i = 1; i < 5; i++) {
			if (this.cards[i].getSuit() != prev) {
				return -1;
			}
			total += this.cards[i].getValue();
			prev = this.cards[i].getSuit();
		}
		this.handValue = total;
		return total;
	}

	/**
	 * @return 
	 * Straight information
	 */
	private int straight() {

		int prev = this.cards[0].getValue();
		int total = prev;
		for (int i = 1; i < 5; i++) {
			if (this.cards[i].getValue() != prev + 1) {
				return -1;
			}
			prev = this.cards[i].getValue();
			total += 1;
		}
		this.handValue = total;
		return total;
	}
}