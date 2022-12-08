package com.umutcirak.pda;

import java.util.ArrayList;

public class Vector2Int
{
    public int x;
    public int y;

    public Vector2Int(int x, int y)
    {
        this.x = 0;
        this.y = 0;
    }
    public Vector2Int(){

    }

    public Vector2Int Down()
    {
        this.x = 0;
        this.y = -1;
        return this;
    }

    public Vector2Int Up()
    {
        this.x = 0;
        this.y = 1;
        return this;
    }

    public Vector2Int Right()
    {
        this.x = 1;
        this.y = 0;
        return this;
    }

    public Vector2Int Left()
    {
        this.x = -1;
        this.y = 0;
        return this;

    }

    public Vector2Int DiagonalUpRight()
    {
        this.x = 1;
        this.y = 1;
        return this;
    }

    public Vector2Int DiagonalUpLeft()
    {
        this.x = -1;
        this.y = 1;
        return this;
    }

    public Vector2Int DiagonalDownRight()
    {
        this.x = 1;
        this.y = -1;
        return this;
    }
    public Vector2Int DiagonalDownLeft()
    {
        this.x = -1;
        this.y = -1;
        return this;
    }







}
