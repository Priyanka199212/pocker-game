package com.edu.au.card;

/**
Priyanka Yadav
28/10/2020  
 */
public class CardInfo implements Comparable<CardInfo> {
	private int value;
	private char suit;

	public CardInfo(String str) {
		char v = str.charAt(0);
		switch (v) {
		case 'T':
			this.value = 10;
			break;
		case 'J':
			this.value = 11;
			break;
		case 'Q':
			this.value = 12;
			break;
		case 'K':
			this.value = 13;
			break;
		case 'A':
			this.value = 14;
			break;
		default:
			this.value = Integer.parseInt("" + v);
			break;
		}

		this.suit = str.charAt(1);

	}

	public int compareTo(CardInfo compareCard) {

		int compareValue = ((CardInfo) compareCard).getValue();

		// ascending order
		return this.value - compareValue;

	}

	public int getValue() {
		return this.value;
	}

	public char getSuit() {
		return this.suit;
	}

	public String toString() {
		String str = "";
		str = String.valueOf(this.value) + this.suit;
		return str;
	}
}
