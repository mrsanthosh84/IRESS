package com.toyrobo;

import com.toyrobo.exception.ToyRobotException;

import java.io.Console;

public class Application {

    public static void main(String args[]) {
        Console console = System.console();

        if (console == null) {
            System.err.println("No console.");
            System.exit(1);
        }

        SquareBoard squareBoard = new SquareBoard(4, 4);
        ToyRobot robot = new ToyRobot();
        Game game = new Game(squareBoard, robot);

        System.out.println("Toy Robot Simulator");
        System.out.println("Enter a command, Valid commands are:");

        boolean keepRunning = true;
        while (keepRunning) {
            String inputString = console.readLine("Enter the Directions (\'PLACE X,Y,NORTH|SOUTH|EAST|WEST\') or Commands (MOVE, LEFT, RIGHT, REPORT or EXIT): ");
            if ("EXIT".equals(inputString)) {
                keepRunning = false;
            } else {
                try {
                    String outputVal = game.eval(inputString);
                    System.out.println(outputVal);
                } catch (ToyRobotException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}

