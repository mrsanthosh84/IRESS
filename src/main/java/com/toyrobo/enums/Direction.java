package com.toyrobo.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Direction {

    NORTH(0), EAST(1), SOUTH(2), WEST(3);
    private static Map<Integer, Direction> map = new HashMap<>();

    static {
        map = Stream.of(Direction.values()).collect(Collectors.toMap(key -> key.directionIndex, value -> value));
    }

    private int directionIndex;

    private Direction(int direction) {
        this.directionIndex = direction;
    }

    public static Direction valueOf(int directionNum) {
        return map.get(directionNum);
    }

    /**
     * Returns the direction on the left of the current one
     */
    public Direction leftDirection() {
        return rotate(-1);
    }

    /**
     * Returns the direction on the right of the current one
     */
    public Direction rightDirection() {
        return rotate(1);
    }

    private Direction rotate(int step) {

        int newIndex = (this.directionIndex + step) < 0 ?
                map.size() - 1 :
                (this.directionIndex + step) % map.size();

        return Direction.valueOf(newIndex);
    }

}
