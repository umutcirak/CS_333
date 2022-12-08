package com.umutcirak.pda;

import java.util.*;

public class Pathfinder
{
    int startPos = 11;

    Node startNode;

    Node currentNode;

    HashMap<Integer,Node> searchedNodes;    // Int (xy): x:row, y:column
    Queue <Node> frontier;

    ArrayList<Node> path = new ArrayList<Node>();

    Grid grid;
    NodeManager nodeManager;

    public Pathfinder(NodeManager nodeManager, Grid grid)
    {
        searchedNodes = new HashMap<Integer,Node>();
        frontier = new LinkedList<Node>();  // CHECK THIS ONE

        this.nodeManager = nodeManager;
        this.grid = grid;
        startNode = nodeManager.nodeMatrix.get(startPos);
    }

    public boolean IsPath()
    {
        nodeManager.ResetNodeMatrix();
        return BreadthFirstSearch();
    }


    void ExploreNeighbors()
    {
        ArrayList <Node> neighbors = new ArrayList<Node>();

        for (Vector2Int direction: nodeManager.directions)
        {
            int neighborCoordinate = currentNode.AddCoordinates(direction);

            if(nodeManager.nodeMatrix.containsKey(neighborCoordinate))
            {
                neighbors.add(nodeManager.nodeMatrix.get(neighborCoordinate));
            }
        }

        for (Node neighbor: neighbors)
        {
            if(!searchedNodes.containsKey(neighbor.coordinates))
            {
                searchedNodes.put(neighbor.coordinates,neighbor);
                frontier.add(neighbor);
            }
        }
    }


    boolean BreadthFirstSearch()
    {
        frontier.clear();
        searchedNodes.clear();

        var nodeMatrix = nodeManager.nodeMatrix;

        boolean isRunning = true;
        frontier.add(startNode);
        searchedNodes.put(startNode.coordinates,startNode);

        while (isRunning && frontier.size() > 0)
        {
            currentNode = frontier.poll();
            currentNode.isExplored = true;
            ExploreNeighbors();

            if(currentNode.CheckHorizontal(grid) || currentNode.CheckVertical(grid)
                    || currentNode.CheckDownLeftDiagonal(grid) || currentNode.CheckDownRightDiagonal(grid))
            {
                isRunning = false;
                return true;
            }



        }

        return false;

    }











}
