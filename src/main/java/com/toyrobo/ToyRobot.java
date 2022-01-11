package com.toyrobo;

import com.toyrobo.exception.ToyRobotException;
import com.toyrobo.pojo.Position;

public class ToyRobot {
    private Position position;

    public ToyRobot() {
    }

    public ToyRobot(Position position) {
        this.position = position;
    }

    public boolean setPosition(Position position) {
        if (position == null)
            return false;

        this.position = position;
        return true;
    }

    public String move() throws ToyRobotException {
        return move(position.getNextPosition());
    }

    /**
     * Moves the robot one unit forward in the direction it is currently facing
     *
     * @return String if moved successfully
     */
    public String move(Position newPosition) {
        if (newPosition == null)
            return "Invalid Command";

        // change position
        this.position = newPosition;
        return "Valid Command";
    }

    public Position getPosition() {
        return this.position;
    }

    /**
     * Rotates the robot 90 degrees LEFT
     *
     * @return String if rotated successfully
     */
    public String rotateLeft() {
        if (this.position.direction == null) {
            return "Invalid Command";
        }

        this.position.direction = this.position.direction.leftDirection();
        return "Valid Command";
    }

    /**
     * Rotates the robot 90 degrees RIGHT
     *
     * @return String if rotated successfully
     */
    public String rotateRight() {
        if (this.position.direction == null) {
            return "Invalid Command";
        }

        this.position.direction = this.position.direction.rightDirection();
        return "Valid Command";
    }


}
