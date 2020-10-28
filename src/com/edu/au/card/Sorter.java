package com.edu.au.card;

/**
Priyanka Yadav
28/10/2020  
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Sorter {
	public static final String TIE = "Unfortunately, There is a tie.";

	public static void main(String[] args) {
		int firstPlayer = 0;
		int secondPlayer = 0;

		BufferedReader br = null;

		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			// main loop for piping through stdin
			while (true) {
				String input = br.readLine();
				if (input == null) {
					break;
				}
				// regex match
				if (!input.matches("(?:[2-9TJQKA][SCHD] ){9}[2-9TJQKA][SCHD]")) {
					System.out.println("Wrong input");
					break;
				}

				String[] cards = input.split(" ");
				String[] firstPart = Arrays.copyOfRange(cards, 0, 5);
				String[] secondPart = Arrays.copyOfRange(cards, 5, 10);

				Hand firstHand = new Hand(firstPart);
				Hand secondHand = new Hand(secondPart);

				firstHand.sortCards();
				secondHand.sortCards();

				firstHand.evaluateInfo();
				secondHand.evaluateInfo();
				int res = winner(firstHand, secondHand);
				if (res == 1) {
					firstPlayer++;
				} else if (res == 2) {
					secondPlayer++;
				} else {
					System.out.println(TIE);
				}
			}

			System.out.println("Player 1: " + firstPlayer);
			System.out.println("Player 2: " + secondPlayer);

			System.exit(0);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int winner(Hand hand1, Hand hand2) {

		if (hand1.getHandCategory().getValue() > hand2.getHandCategory().getValue()) {
			return 1;
		} else if (hand1.getHandCategory().getValue() < hand2.getHandCategory().getValue()) {
			return 2;
		} else if (hand1.getHandValue() > hand2.getHandValue()) {
			return 1;
		} else if (hand1.getHandValue() < hand2.getHandValue()) {
			return 2;
		} else {
			// final tie break!
			for (int i = 4; i >= 0; i--) {
				if (hand1.getCard(i).getValue() > hand2.getCard(i).getValue()) {
					return 1;
				} else if (hand1.getCard(i).getValue() < hand2.getCard(i).getValue()) {
					return 2;
				}
			}
			// theres a tie here...
			return -1;
		}

	}
}
