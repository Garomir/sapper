package com.ramich.models;

import java.util.ArrayList;
import java.util.List;

public class Coordinates {

    private List<Coordinate> coordinateList;

    public Coordinates(List<Coordinate> coordinateList) {
        this.coordinateList = coordinateList;
    }

    public List<Coordinate> getCoordinateList() {
        return coordinateList;
    }

    public void setCoordinateList(List<Coordinate> coordinateList) {
        this.coordinateList = coordinateList;
    }

    //по одной ячейке получаем ее соседей
    public List<Coordinate> getNeighbors(Coordinate c) {
        List<Coordinate> neighbors = new ArrayList<>();
        Coordinate top = new Coordinate(c.getX()-1, c.getY());
        Coordinate bottom = new Coordinate(c.getX()+1, c.getY());
        Coordinate left = new Coordinate(c.getX(), c.getY()-1);
        Coordinate right = new Coordinate(c.getX(), c.getY()+1);
        Coordinate topLeft = new Coordinate(c.getX()-1, c.getY()-1);
        Coordinate topRight = new Coordinate(c.getX()-1, c.getY()+1);
        Coordinate bottomLeft = new Coordinate(c.getX()+1, c.getY()-1);
        Coordinate bottomRight = new Coordinate(c.getX()+1, c.getY()+1);
        if (isRelevant(top)){
            neighbors.add(top);
        }
        if (isRelevant(bottom)){
            neighbors.add(bottom);
        }
        if (isRelevant(left)){
            neighbors.add(left);
        }
        if (isRelevant(right)){
            neighbors.add(right);
        }
        if (isRelevant(topLeft)){
            neighbors.add(topLeft);
        }
        if (isRelevant(topRight)){
            neighbors.add(topRight);
        }
        if (isRelevant(bottomLeft)){
            neighbors.add(bottomLeft);
        }
        if (isRelevant(bottomRight)){
            neighbors.add(bottomRight);
        }
        return neighbors;
    }

    //проверяем переданные координаты находятся в рамках нашей сетки или нет
    public boolean isRelevant(Coordinate coordinate){
        return coordinate.getX() >= 0 && coordinate.getX() < 10 &&
                coordinate.getY() >= 0 && coordinate.getY() < 10;
    }

    public int getBombsCount(){
        return coordinateList.size() / 6;
    }
}