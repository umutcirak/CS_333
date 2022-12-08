package com.umutcirak.pda;

import java.util.Dictionary;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        Grid grid = new Grid(6,7);
        NodeManager nodeManager = new NodeManager(grid);
        Pathfinder pathfinder = new Pathfinder(nodeManager,grid);
        Game game = new Game(grid, nodeManager, pathfinder);

        game.RunGame();

    }





}
