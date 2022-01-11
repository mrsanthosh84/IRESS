package com.toyrobo;


import com.toyrobo.enums.CommandEnum;
import com.toyrobo.enums.Direction;
import com.toyrobo.exception.ToyRobotException;
import com.toyrobo.interfaces.Board;
import com.toyrobo.pojo.Position;

public class Game {

    Board squareBoard;
    ToyRobot robot;

    public Game(Board squareBoard, ToyRobot robot) {
        this.squareBoard = squareBoard;
        this.robot = robot;
    }

    /**
     * Places the robot on the squareBoard  in position X,Y and facing NORTH, SOUTH, EAST or WEST
     *
     * @param position Robot position
     * @return valid string if placed successfully
     */
    public String placeToyRobot(Position position) throws ToyRobotException {

        if (squareBoard == null) {
            throw new ToyRobotException("Invalid squareBoard object");
        }

        if (position == null) {
            throw new ToyRobotException("Invalid position object");
        }

        if (position.getDirection() == null) {
            throw new ToyRobotException("Invalid direction value");
        }

        // validate the position
        if (!squareBoard.isValidPosition(position)) {
            return "Invalid position";
        }

        // if position is valid then assign values to fields
        robot.setPosition(position);
        return "Valid Position";
    }

    /**
     * Evals and executes a string command.
     *
     * @param inputString command string
     * @return string value of the executed command
     *
     */
    public String eval(String inputString) throws ToyRobotException {
        String[] args = inputString.split(" ");

        // validate command
        CommandEnum command;
        try {
            command = CommandEnum.valueOf(args[0]);
        } catch (IllegalArgumentException e) {
            throw new ToyRobotException("Invalid command");
        }
        if (command == CommandEnum.PLACE && args.length < 2) {
            throw new ToyRobotException("Invalid command");
        }

        // validate PLACE params
        String[] params;
        int x = 0;
        int y = 0;
        Direction commandDirection = null;
        if (command == CommandEnum.PLACE) {
            params = args[1].split(",");
            try {
                x = Integer.parseInt(params[0]);
                y = Integer.parseInt(params[1]);
                commandDirection = Direction.valueOf(params[2]);
            } catch (Exception e) {
                throw new ToyRobotException("Invalid command");
            }
        }

        String output;

        switch (command) {
            case PLACE:
                output = placeToyRobot(new Position(x, y, commandDirection));
                break;
            case MOVE:
                Position newPosition = robot.getPosition().getNextPosition();
                if (!squareBoard.isValidPosition(newPosition))
                    output = "Invalid Command";
                else
                    output = robot.move(newPosition);
                break;
            case LEFT:
                output = robot.rotateLeft();
                break;
            case RIGHT:
                output = robot.rotateRight();
                break;
            case REPORT:
                output = report();
                break;
            default:
                throw new ToyRobotException("Invalid command");
        }

        return output;
    }

    /**
     * Returns the X,Y and Direction of the robot
     */
    public String report() {
        if (robot.getPosition() == null) {
            return "Can't move  the robot to invalid directions";
        }

        return "Output: " + robot.getPosition().getX() + "," + robot.getPosition().getY() + "," + robot.getPosition().getDirection().toString();
    }
}

