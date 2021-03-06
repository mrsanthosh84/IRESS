package com.toyrobo;

import com.toyrobo.interfaces.Board;
import com.toyrobo.pojo.Position;

public class SquareBoard implements Board {

    int rows;
    int columns;

    public SquareBoard(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }


    @Override
    public boolean isValidPosition(Position position) {
        return !(
                position.getX() > this.columns || position.getX() < 0 ||
                        position.getY() > this.rows || position.getY() < 0
        );
    }
}
