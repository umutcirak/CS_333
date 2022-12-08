package com.umutcirak.pda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class NodeManager
{
    public HashMap<Integer, Node> nodeMatrix;

    public ArrayList<Vector2Int> directions;

    public Node bestPlayerNode;
    public Node bestAINode;

    public NodeManager(Grid grid)
    {
        nodeMatrix = new HashMap<Integer,Node>();
        PopulateNodeMatrix(grid);
        SetDirections();
    }

    void PopulateNodeMatrix(Grid grid)
    {
        for (int x = 0; x < grid.rowLength; x++)
        {
            for (int y= 0; y < grid.columnLength; y++)
            {
                Node node = new Node(x+1,y+1);
                nodeMatrix.put(node.coordinates,node);
            }
        }
    }

    // WORKS FINE: CHECKED
    public void ResetNodeMatrix()
    {
        for(Map.Entry<Integer, Node> entry : nodeMatrix.entrySet())
        {
            Integer key = entry.getKey();
            Node node = entry.getValue();

            node.ResetNode();
        }
    }

    void SetBestNodes(Grid grid)
    {
        int maxScorePlayer = -100;
        int maxScoreAI = -100;

        for(Map.Entry<Integer, Node> entry : nodeMatrix.entrySet()) {

            Integer key = entry.getKey();
            Node node = entry.getValue();

            node.CalculateBestMove(grid);

        }

        for(Map.Entry<Integer, Node> entry : nodeMatrix.entrySet())
        {
            Integer key = entry.getKey();
            Node node = entry.getValue();

            Grid.Piece currentPiece = grid.grid[node.x_pos_grid][node.y_pos_grid];

            if(currentPiece == Grid.Piece.E){ continue; }

            else if(currentPiece == Grid.Piece.X)
            {
                if(node.bestChoiceScore > maxScorePlayer){maxScorePlayer = node.bestChoiceScore; bestPlayerNode = node;}
            }
            else if(currentPiece == Grid.Piece.O)
            {
                if(node.bestChoiceScore > maxScoreAI){ maxScoreAI = node.bestChoiceScore; bestAINode = node;}
            }

        }

    }
    void RunAI(Grid grid)
    {
        Node.BestChoice theMove = bestAINode.bestChoice;
        int columnToPlace = 0;

        if(theMove == Node.BestChoice.HorizontalForward)
        {
            columnToPlace = bestAINode.y_pos_grid + bestAINode.horizontalMatches;
        }
        else if(theMove == Node.BestChoice.HorizontalBackward)
        {
            columnToPlace = bestAINode.y_pos_grid - 1;
        }
        else if(theMove == Node.BestChoice.Vertical)
        {
            columnToPlace = bestAINode.y_pos_grid;
        }
        else if(theMove == Node.BestChoice.DownRightForward)
        {
            columnToPlace = bestAINode.y_pos_grid + 1;
        }
        else if(theMove == Node.BestChoice.DownRightBackward)
        {
            columnToPlace = bestAINode.y_pos_grid - 1;
        }
        else if(theMove == Node.BestChoice.DownLeftForward)
        {
            columnToPlace = bestAINode.y_pos_grid + 1;
        }
        else if(theMove == Node.BestChoice.DownLeftBackward)
        {
            columnToPlace = bestAINode.y_pos_grid - 1;
        }

        columnToPlace +=1;

        grid.PlaceThePiece(Grid.Piece.O, columnToPlace);

    }

    void RunAIRandom(Grid grid)
    {
        int random = (int) (Math.random() * 5 ) + 1;
        grid.PlaceThePiece(Grid.Piece.O, random);
    }

    void SuggestMoveForPlayer()
    {
        Node.BestChoice theMove = bestPlayerNode.bestChoice;
        int columnToPlace = 0;

        if(theMove == Node.BestChoice.HorizontalForward)
        {
            columnToPlace = bestPlayerNode.y_pos_grid + bestPlayerNode.horizontalMatches;
        }
        else if(theMove == Node.BestChoice.HorizontalBackward)
        {
            columnToPlace = bestPlayerNode.y_pos_grid - 1;
        }
        else if(theMove == Node.BestChoice.Vertical)
        {
            columnToPlace = bestPlayerNode.y_pos_grid;
        }
        else if(theMove == Node.BestChoice.DownRightForward)
        {
            columnToPlace = bestPlayerNode.y_pos_grid + 1;
        }
        else if(theMove == Node.BestChoice.DownRightBackward)
        {
            columnToPlace = bestPlayerNode.y_pos_grid - 1;
        }
        else if(theMove == Node.BestChoice.DownLeftForward)
        {
            columnToPlace = bestPlayerNode.y_pos_grid + 1;
        }
        else if(theMove == Node.BestChoice.DownLeftBackward)
        {
            columnToPlace = bestPlayerNode.y_pos_grid - 1;
        }

        columnToPlace +=1;

        String suggestion = "Best Move for Player is " + columnToPlace +". column to complete " +
                theMove.toString();
        System.out.println(suggestion);
    }


    void SetDirections()
    {
        this.directions = new ArrayList<Vector2Int>();

        Vector2Int down = new Vector2Int().Down();
        Vector2Int up = new Vector2Int().Up();
        Vector2Int right = new Vector2Int().Right();
        Vector2Int left = new Vector2Int().Left();

        Vector2Int upRight = new Vector2Int().DiagonalUpRight();
        Vector2Int upLeft = new Vector2Int().DiagonalUpLeft();
        Vector2Int downRight = new Vector2Int().DiagonalDownRight();
        Vector2Int downLeft = new Vector2Int().DiagonalDownLeft();

        directions.add(down); directions.add(up); directions.add(right); directions.add(left);
        directions.add(upRight); directions.add(upLeft); directions.add(downRight); directions.add(downLeft);

    }



}
