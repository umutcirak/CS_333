package com.umutcirak.pda;

public class Grid {

    int rowLength;
    int columnLength;

    enum Piece {X, O, E}   // X: Player Move, O: AI Move E: Empty
    public Piece piece;

    public Piece[][] grid;

    int[] boardEmptinessIndex;

    public Grid(int rowLength, int columnLength) {
        this.rowLength = rowLength;
        this.columnLength = columnLength;
        this.grid = new Piece[rowLength][columnLength];
        boardEmptinessIndex = new int[columnLength];
        PopulateGrid();
    }

    public void DisplayGrid() {

        System.out.println("GAME:");
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                String piece = GetPieceToDisplay(grid[x][y]);
                System.out.print(piece + "  ");
            }
            System.out.println();
        }
        System.out.println("--------------------");
    }

    void PopulateGrid() {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                grid[x][y] = Piece.E;
            }
        }
    }


    String GetPieceToDisplay(Piece piece) {
        if (piece == Piece.E) {
            return "-";
        } else if (piece == Piece.O) {
            return "O";
        } else {
            return "X";
        }

    }

    void PlaceThePiece(Piece piece, int column)
    {
        if(!CheckFreeArea(column)) {return;}

        int rowCoordinate = rowLength - boardEmptinessIndex[column - 1] - 1;

        boardEmptinessIndex[column - 1] = boardEmptinessIndex[column - 1] + 1;

        grid[rowCoordinate][column - 1] = piece;
    }

    boolean CheckFreeArea(int column)       //colunmnlar -1 ooldd
    {
        int rowCoordinate = rowLength - boardEmptinessIndex[column - 1] - 1;
        if (boardEmptinessIndex[column - 1] == rowLength)
        {
            return false; // FAILED
        }
        else if(grid[rowCoordinate][column - 1] == Piece.E)
        {
            return true; // Placed Successfully
        }
        else
        {
            return  false;
        }

    }






}
