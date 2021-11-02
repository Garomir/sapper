package com.ramich.models;

import java.util.List;
import java.util.Random;

public class LowerFields {
    
    private Field[][] lowerFields;
    private Coordinates coordinates;

    public LowerFields(Field[][] lowerFields, Coordinates coordinates) {
        this.lowerFields = lowerFields;
        this.coordinates = coordinates;
    }

    public Field[][] getLowerFields() {
        return lowerFields;
    }

    public void setLowerFields(Field[][] lowerFields) {
        this.lowerFields = lowerFields;
    }

    //ставим пустое поле на переданную ячейку в нижней матрице
    public void fillLowerFieldWithZero(){
        for (Coordinate c : coordinates.getCoordinateList()) {
            lowerFields[c.getX()][c.getY()] = Field.ZERO;
        }
    }

    //ставим бомбу на переданную ячейку в нижней матрице
    public void putBombToLowerFields(Coordinate c){
        lowerFields[c.getX()][c.getY()] = Field.BOMB;
    }

    //ставим бомбу на переданную ячейку в нижней матрице
    public void putBombedToLowerFields(Coordinate c){
        lowerFields[c.getX()][c.getY()] = Field.BOMBED;
    }

    public Field getFieldByCoordinate(Coordinate c){
        return lowerFields[c.getX()][c.getY()];
    }

    public void fillLowerFields(){
        setPlaceForBombsInLowerFields();
        fillNumbersToEmptyFields();
    }

    public void setPlaceForBombsInLowerFields(){
        for (int i = 1; i < coordinates.getBombsCount(); i++) {
            Coordinate c = getRandomCoordinate();
            putBombToLowerFields(c);
        }
    }

    public Coordinate getRandomCoordinate(){
        Random random = new Random();
        int rand = random.nextInt(coordinates.getCoordinateList().size());
        return coordinates.getCoordinateList().get(rand);
    }

    //заполняем пустые клетки числами
    public void fillNumbersToEmptyFields(){
        List<Coordinate> neighbors;
        for (Coordinate c : coordinates.getCoordinateList()) {
            //если данная ячейка не содержит бомбу, тогда получаем ее соседние ячейки
            if (!lowerFields[c.getX()][c.getY()].name().equals(Field.BOMB.name())){
                neighbors = coordinates.getNeighbors(c);
                //подсчитываем количество соседских бомб
                int neighborsBomb = 0;
                for (Coordinate neig : neighbors) {
                    if (lowerFields[neig.getX()][neig.getY()].name().equals(Field.BOMB.name())){
                        neighborsBomb += 1;
                    }
                }
                switch (neighborsBomb){
                    case 1:
                        lowerFields[c.getX()][c.getY()] = Field.NUM1;
                        break;
                    case 2:
                        lowerFields[c.getX()][c.getY()] = Field.NUM2;
                        break;
                    case 3:
                        lowerFields[c.getX()][c.getY()] = Field.NUM3;
                        break;
                    case 4:
                        lowerFields[c.getX()][c.getY()] = Field.NUM4;
                        break;
                    case 5:
                        lowerFields[c.getX()][c.getY()] = Field.NUM5;
                        break;
                    case 6:
                        lowerFields[c.getX()][c.getY()] = Field.NUM6;
                        break;
                    case 7:
                        lowerFields[c.getX()][c.getY()] = Field.NUM7;
                        break;
                    case 8:
                        lowerFields[c.getX()][c.getY()] = Field.NUM8;
                        break;
                }
            } else {
                lowerFields[c.getX()][c.getY()] = Field.BOMB;
            }
        }
    }
}