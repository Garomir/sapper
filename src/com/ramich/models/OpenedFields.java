package com.ramich.models;

public class OpenedFields {

    private Field[][] openedFields;

    public OpenedFields(Field[][] openedFields) {
        this.openedFields = openedFields;
    }

    public Field[][] getOpenedFields() {
        return openedFields;
    }

    public void setOpenedFields(Field[][] openedFields) {
        this.openedFields = openedFields;
    }

    public Field getFieldByCoordinate(Coordinate c){
        return openedFields[c.getX()][c.getY()];
    }

    public void putOpenedToOpenedField(Coordinate c){
        openedFields[c.getX()][c.getY()] = Field.OPENED;
    }
}