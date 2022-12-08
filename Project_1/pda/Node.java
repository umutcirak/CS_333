package com.umutcirak.pda;

public class Node
{
    public int coordinates; // xy
    int x;
    int y;

    int x_pos_grid;
    int y_pos_grid;

    enum BestChoice {HorizontalForward, HorizontalBackward, Vertical, DownRightForward, DownRightBackward
        , DownLeftForward, DownLeftBackward, None}
    BestChoice bestChoice;

    int bestChoiceScore;

    public int horizontalMatches;
    public int verticalMatches;
    public int downRightMatches;
    public int downLeftMatches;

    public boolean isExplored;

    public Node(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.x_pos_grid = this.x - 1 ;
        this.y_pos_grid = this.y - 1;

        SetCoordinates();
        isExplored = false;
    }

    public void ResetNode(){
        this.isExplored = false;
        this.horizontalMatches = 0;
        this.verticalMatches = 0;
        this.downLeftMatches = 0;
        this.downRightMatches = 0;
        this.bestChoiceScore = 0;
        this.bestChoice = BestChoice.None;
    }

    public void CalculateBestMove(Grid grid)
    {
        this.bestChoiceScore = -1;
        this.bestChoice = BestChoice.None;

        // if(grid.grid[x_pos_grid][y_pos_grid] == Grid.Piece.E) { return; }

        int horizontalForwardTemp = -1;
        int horizontalBackwardTemp = -1;
        int verticalTemp = -1;
        int downRightForwardTemp = -1;
        int downRightBackwardTemp = -1;
        int downLeftForwardTemp = -1;
        int downLeftBackwardTemp = -1;

        Grid.Piece currentPiece = grid.grid[x_pos_grid][y_pos_grid];

        //HORIZONTAL FORWARD

        if(y_pos_grid + horizontalMatches < grid.columnLength){
            if(x_pos_grid  == grid.rowLength - 1 && grid.grid[x_pos_grid][y_pos_grid + horizontalMatches] == Grid.Piece.E)
            {
                horizontalForwardTemp = horizontalMatches;
            }
            else if(x_pos_grid + 1 < grid.rowLength
                    && grid.grid[x_pos_grid + 1][y_pos_grid + horizontalMatches] != Grid.Piece.E
                    && grid.grid[x_pos_grid][y_pos_grid + horizontalMatches] == Grid.Piece.E)
            {
                horizontalForwardTemp = horizontalMatches;
            }
        }
        //HORIZONTAL BACKWARD
        if(y_pos_grid - 1 >= 0)
        {
            if(x_pos_grid == grid.rowLength - 1 && grid.grid[x_pos_grid][y_pos_grid - 1] == Grid.Piece.E)
            {
                horizontalBackwardTemp = horizontalMatches;
            }
            else if(x_pos_grid + 1 < grid.rowLength && grid.grid[x_pos_grid + 1][y_pos_grid - 1] != Grid.Piece.E
                    && grid.grid[x_pos_grid][y_pos_grid - 1] == Grid.Piece.E)
            {
                horizontalBackwardTemp = horizontalMatches;
            }
        }

        //VERTICAL LINE

       if(x_pos_grid - 1 >= 0)
       {
           if(grid.grid[x_pos_grid -1][y_pos_grid] == Grid.Piece.E)
           {
               verticalTemp = verticalMatches;
           }
       }
       else { verticalTemp = -1;  }

        //DOWNRIGHT FORWARD
        if(x_pos_grid + downRightMatches < grid.rowLength && y_pos_grid + downRightMatches < grid.columnLength)
        {
            if(x_pos_grid + downRightMatches == grid.rowLength - 1 &&
                    grid.grid[x_pos_grid + downRightMatches][y_pos_grid + downRightMatches] == Grid.Piece.E)
            {
                downRightForwardTemp = downRightMatches;
            }
            else if(x_pos_grid + downRightMatches + 1 > grid.rowLength
                    && grid.grid[x_pos_grid + downRightMatches + 1][y_pos_grid + downRightMatches] != Grid.Piece.E
                    && grid.grid[x_pos_grid + downRightMatches][y_pos_grid + downRightMatches] == Grid.Piece.E)
            {
                downRightForwardTemp = downRightMatches;
            }
        }
        //DOWNRIGHT BACKWARD
        if(x_pos_grid - 1 >= 0 && y_pos_grid - 1 >= 0 )
        {
            if(grid.grid[x_pos_grid][y_pos_grid-1] != Grid.Piece.E
                    && grid.grid[x_pos_grid-1][y_pos_grid-1] != Grid.Piece.E)
            {
                downRightBackwardTemp = downRightMatches;
            }
        }
        //DOWNLEFT FORWARD
        if(x_pos_grid - 1 >= 0 && y_pos_grid + 1 < grid.columnLength)
        {
            if(grid.grid[x_pos_grid][y_pos_grid + 1] != Grid.Piece.E
                    && grid.grid[x_pos_grid - 1][y_pos_grid + 1] == Grid.Piece.E)
            {
                downLeftForwardTemp = downLeftMatches;
            }
        }
        // DOWNLEFT BACKWARD
        if(x_pos_grid + downLeftMatches < grid.rowLength && y_pos_grid - downLeftMatches >= 0)
        {
            if(x_pos_grid + downLeftMatches == grid.rowLength -1
                    && grid.grid[x_pos_grid + downLeftMatches][y_pos_grid - downLeftMatches] == Grid.Piece.E)
            {
                downLeftBackwardTemp = downLeftMatches;
            }
            else if(x_pos_grid + downLeftMatches + 1 < grid.rowLength && grid.grid[x_pos_grid + downLeftMatches + 1][y_pos_grid - downLeftMatches] != Grid.Piece.E
                    && grid.grid[x_pos_grid + downLeftMatches][y_pos_grid - downLeftMatches] == Grid.Piece.E)
            {
                downLeftBackwardTemp = downLeftMatches;
            }
        }

        // COMPARE Scores
        int max = -100;

        if(horizontalForwardTemp > max)
        { max = horizontalForwardTemp; this.bestChoiceScore = horizontalMatches; this.bestChoice = BestChoice.HorizontalForward;}

        if(horizontalBackwardTemp > max)
        { max = horizontalBackwardTemp; this.bestChoiceScore = horizontalMatches; this.bestChoice = BestChoice.HorizontalBackward;}

        if(verticalTemp > max)

        { max = verticalTemp; this.bestChoiceScore = verticalTemp; this.bestChoice = BestChoice.Vertical;}

        if(downRightForwardTemp > max)
        { max = downRightForwardTemp; this.bestChoiceScore = downRightMatches; this.bestChoice = BestChoice.DownRightForward;}

        if(downRightBackwardTemp > max)
        { max = downRightBackwardTemp; this.bestChoiceScore = downRightMatches; this.bestChoice = BestChoice.DownRightBackward;}

        if(downLeftForwardTemp > max)
        { max = downLeftForwardTemp; this.bestChoiceScore = downLeftMatches; this.bestChoice = BestChoice.DownLeftForward;}

        if(downLeftBackwardTemp > max)
        { max = downLeftBackwardTemp; this.bestChoiceScore = downLeftMatches; this.bestChoice = BestChoice.DownLeftBackward;}

        if(max == -100){
            this.bestChoiceScore = -1; this.bestChoice = BestChoice.None;
        }

    }



    void SetCoordinates()
    {
        String _x = Integer.toString(this.x);
        String _y = Integer.toString(this.y);
        String _coordinates = _x +_y;

        int pos = Integer.parseInt(_coordinates);
        this.coordinates = pos;

    }

    int AddCoordinates(Vector2Int pos_vec)
    {
        String posLine = Integer.toString(this.coordinates);
        int newPosX = Integer.parseInt(posLine.substring(0,1));
        int newPosY = Integer.parseInt(posLine.substring(1,2));

        newPosX += pos_vec.x;
        newPosY += pos_vec.y;

        String newPosLine = "" + newPosX + newPosY;
        int newPosSum = Integer.parseInt(newPosLine);

        return newPosSum;
    }


    public boolean CheckHorizontal(Grid grid)
    {
        Grid.Piece currentPiece = grid.grid[x_pos_grid][y_pos_grid];

        if(currentPiece == Grid.Piece.E){ return false; }

        int matchCounter = 0;

        for (int i = 1; i <= 3; i++)
        {
            if(y_pos_grid + i >= grid.columnLength)
            {
                this.horizontalMatches = matchCounter + 1;
                return false;
            }
            var nextPiece = grid.grid[x_pos_grid][y_pos_grid + i];
            if(nextPiece == currentPiece){ matchCounter++; }
        }
        this.horizontalMatches = matchCounter + 1;

        if(matchCounter == 3) { return true; }
        else                  { return false;}

    }
    public boolean CheckVertical(Grid grid)
    {
        Grid.Piece currentPiece = grid.grid[x_pos_grid][y_pos_grid];
        if(currentPiece == Grid.Piece.E){ return false; }
        //if(x_pos_grid + 3 >= grid.rowLength){ return false; }

        int matchCounter = 0;

        for (int i = 1; i <= 3; i++)
        {
            if(x_pos_grid + i >= grid.rowLength)
            {
                this.verticalMatches = matchCounter + 1;
                return false;
            }

            var nextPiece = grid.grid[x_pos_grid + i][y_pos_grid];
            if(nextPiece == currentPiece){ matchCounter++; }
        }

        this.verticalMatches = matchCounter + 1;

        if(matchCounter == 3) { return true; }
        else                  { return false;}

    }

    public boolean CheckDownRightDiagonal(Grid grid)
    {
        Grid.Piece currentPiece = grid.grid[x_pos_grid][y_pos_grid];
        if(currentPiece == Grid.Piece.E){ return false; }
        //if(x_pos_grid + 3 >= grid.rowLength || y_pos_grid + 3 >= grid.columnLength) { return false; }

        int matchCounter = 0;

        for (int i = 1; i <= 3; i++)
        {
            if(x_pos_grid + i >= grid.rowLength || y_pos_grid + i >= grid.columnLength)
            {
                this.downRightMatches = matchCounter + 1;
                return false;
            }

            var nextPiece = grid.grid[x_pos_grid + i][y_pos_grid + i];
            if(nextPiece == currentPiece){ matchCounter++; }
        }

        this.downRightMatches = matchCounter + 1;

        if(matchCounter == 3) { return true; }
        else                  { return false;}

    }

    public boolean CheckDownLeftDiagonal(Grid grid)
    {
        Grid.Piece currentPiece = grid.grid[x_pos_grid][y_pos_grid];
        if(currentPiece == Grid.Piece.E){ return false; }
        //if(x_pos_grid - 3 < 0 || y_pos_grid + 3 > grid.rowLength){ return false; }

        int matchCounter = 0;

        for (int i = 1; i <= 3; i++)
        {
            if(x_pos_grid - i < 0 || y_pos_grid + i > grid.rowLength)
            {
                this.downLeftMatches = matchCounter + 1;
                return false;
            }

            var nextPiece = grid.grid[x_pos_grid - i][y_pos_grid + i];
            if(nextPiece == currentPiece){ matchCounter++; }
        }

        this.downLeftMatches = matchCounter + 1;

        if(matchCounter == 3) { return true; }
        else                  { return false;}

    }



}
