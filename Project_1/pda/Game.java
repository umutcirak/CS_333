package com.umutcirak.pda;

import java.util.Scanner;

public class Game {

    static final int MATCH_LENGTH_TO_WIN = 4;

    Scanner scanner;

    int stepCounter;
    int maxStep;
    boolean gameEnd;

    Grid grid;
    NodeManager nodeManager;
    Pathfinder pathfinder;

    enum Player{ PLAYER, AI}    // Player: X, AI: O
    Player currentPlayer;

    public Game(Grid grid, NodeManager nodeManager, Pathfinder pathfinder)
    {
        this.grid = grid;
        this.nodeManager = nodeManager;
        this.pathfinder = pathfinder;
        scanner = new Scanner(System.in);
        SetupGame();
    }

    void SetupGame()
    {
        int maxStep = grid.rowLength * grid.columnLength;
        currentPlayer = Player.PLAYER;
        this.stepCounter = 0;
        gameEnd = false;
    }

    public void RunGame()
    {

        while (this.stepCounter < 42 && !gameEnd)
        {
            this.ProcessInput();
            grid.DisplayGrid();
            this.gameEnd = pathfinder.IsPath();
            nodeManager.SetBestNodes(grid);

            if(this.gameEnd == true)
            {
                System.out.println("!!! " + currentPlayer.toString()  +" IS WON !!!");
            }
            ChangePlayer();
        }



    }

    public void ProcessInput()
    {
        this.stepCounter ++;

        if(currentPlayer == Player.PLAYER)
        {
            System.out.println("Player 1 is moving to place 'X'");
            System.out.println("pick a move (a column number between 1-7)");

            if(stepCounter > 4){  nodeManager.SuggestMoveForPlayer();  }


            String line = scanner.nextLine();
            int columnToPlace;

            if (CheckInput(line))
            {
                columnToPlace = Integer.parseInt(line);

                if(!grid.CheckFreeArea(columnToPlace))
                {
                    System.out.println("The Column " + columnToPlace + " is full !!!");
                    ProcessInput();
                }
                else
                {
                    grid.PlaceThePiece(Grid.Piece.X, columnToPlace);

                }

            }
            else
            {
                System.out.println("Enter a correct input!");
                ProcessInput();
            }

        }
        else
        {
            System.out.println("AI is moving to place 'O'");
            if(stepCounter < 4)
            {
                nodeManager.RunAIRandom(grid);
            }
            else
            {
                nodeManager.RunAI(grid);
            }
        }

    }

    boolean CheckInput(String input){

        return true;
    }


    private void ChangePlayer(){
        if(currentPlayer == Player.PLAYER) { currentPlayer = Player.AI;}
        else                                 { currentPlayer = Player.PLAYER;}
    }





}
