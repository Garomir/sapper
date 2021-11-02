package com.ramich.models;

public class UpperFields {
    private Field[][] upperFields;
    private Coordinates coordinates;

    public UpperFields(Field[][] upperFields, Coordinates coordinates) {
        this.upperFields = upperFields;
        this.coordinates = coordinates;
    }

    public Field[][] getUpperFields() {
        return upperFields;
    }

    public void setUpperFields(Field[][] upperFields) {
        this.upperFields = upperFields;
    }

    //пробегаюсь по списку координат и заполняю верхнюю матрицу картинкой closed.png и накладываю на сетку
    public void fillUpperFields(){
        for (Coordinate c : coordinates.getCoordinateList()) {
            upperFields[c.getX()][c.getY()] = Field.CLOSED;
            //gridMineField.add(new ImageView(getImage(Field.CLOSED.name())), c.getX(), c.getY());
        }
    }

    public Field getFieldByCoordinate(Coordinate c){
        return upperFields[c.getX()][c.getY()];
    }

    public void putFlagedToField(Coordinate c){
        upperFields[c.getX()][c.getY()] = Field.FLAGED;
    }

    public void putClosedToField(Coordinate c){
        upperFields[c.getX()][c.getY()] = Field.CLOSED;
    }
}
