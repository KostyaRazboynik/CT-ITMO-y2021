package game;

import java.util.Scanner;

public class Main {
    public static Player WhatPlayer(int num) {

        System.out.println("Enter player: " + num);
        Scanner sc = new Scanner(System.in);
        String pl =  sc.nextLine();
        if (pl.equals("HP")) {
            return new HumanPlayer();
        } else if (pl.equals("SP")) {
            return new SequentialPlayer();
        } else if (pl.equals("RP")) {
            return new RandomPlayer();
        }
        return null;
    }

    public static void main(String[] args) {

        System.out.println("Welcome to the game MNK");

        final Game game = new Game(WhatPlayer(1), WhatPlayer(2), false);
        game.play();
    }
}