package com.ramich;

import com.ramich.models.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Controller {
    private List<Coordinate> coordList = new ArrayList<>();
    private Coordinates coordinates;
    private LowerFields lowerFields;
    private UpperFields upperFields;
    private OpenedFields openedFields;

    @FXML
    private Button btnNewGame;

    @FXML
    private GridPane gridMineField;

    @FXML
    void initialize() {
        btnNewGame.setOnAction(arg0 -> {
            System.out.println("New game started!");
            coordList.clear();
            start();
        });

        start();
    }

    public void start(){
        for (int i = 0; i < gridMineField.getRowCount(); i++) {
            for (int j = 0; j < gridMineField.getColumnCount(); j++) {
                //составил список координат всех ячеек
                Coordinate coordinate = new Coordinate(i, j);
                coordList.add(coordinate);
            }
        }
        coordinates = new Coordinates(coordList);
        lowerFields = new LowerFields(new Field[10][10], coordinates);
        upperFields = new UpperFields(new Field[10][10], coordinates);
        openedFields = new OpenedFields(new Field[10][10]);

        //заполнил нижнюю матрицу пустыми ячейками
        lowerFields.fillLowerFieldWithZero();
        //Заполняем всю нижнюю матрицу бомбами и цифрами и накладываем на сетку
        lowerFields.fillLowerFields();
        upperFields.fillUpperFields();
        fillGridByClosedFields();
        setMouseEvents();

    }

    public Image getImage(String name){
        return new Image("File:res/img/" + name + ".png");
    }

    //слушатель мыши
    public void setMouseEvents(){
        gridMineField.setOnMouseClicked(mouseEvent -> {
            Node iv = mouseEvent.getPickResult().getIntersectedNode();
            Coordinate c = new Coordinate(GridPane.getColumnIndex(iv), GridPane.getRowIndex(iv));
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                if (lowerFields.getFieldByCoordinate(c).name().equals(Field.BOMB.name())){
                    lowerFields.putBombedToLowerFields(c);
                    openAllFields();
                } else {
                    if (lowerFields.getFieldByCoordinate(c).name().equals(Field.ZERO.name())){
                        lookField(c);
                    } else {
                        openedFields.putOpenedToOpenedField(c);
                        gridMineField.add(new ImageView(getImage(lowerFields.getFieldByCoordinate(c).name())), c.getX(), c.getY());
                    }
                }
                //слушатель правого нажатия мыши
            } else if (mouseEvent.getButton() == MouseButton.SECONDARY){
                if (upperFields.getFieldByCoordinate(c) == Field.FLAGED){
                    upperFields.putClosedToField(c);
                    gridMineField.add(new ImageView(getImage(Field.CLOSED.name())), c.getX(), c.getY());
                } else {
                    upperFields.putFlagedToField(c);
                    gridMineField.add(new ImageView(getImage(Field.FLAGED.name())), c.getX(), c.getY());
                }
            }
        });
    }

    //заполняем сетку закрытыми ячейками
    public void fillGridByClosedFields(){
        for (Coordinate c : coordinates.getCoordinateList()) {
            gridMineField.add(new ImageView(getImage(Field.CLOSED.name())), c.getX(), c.getY());
        }
    }

    //открываем все закрытые ячейки, конец игры
    public void openAllFields(){
        for (Coordinate c : coordinates.getCoordinateList()) {
            gridMineField.add(new ImageView(getImage(lowerFields.getFieldByCoordinate(c).name())), c.getX(), c.getY());
        }
    }

    //открываем текущую ячейку, перебираем соседей и открываем все соседские пустые ячейки
    public void lookField(Coordinate c){
        Stack<Coordinate> stack = new Stack<>();
        stack.push(c);

        while (!stack.empty()) {
            Coordinate cc = stack.pop();
            openedFields.putOpenedToOpenedField(cc);
            gridMineField.add(new ImageView(getImage(lowerFields.getFieldByCoordinate(cc).name())), cc.getX(), cc.getY());
            for (Coordinate coord : coordinates.getNeighbors(cc)) {
                if (!(openedFields.getFieldByCoordinate(coord) == (Field.OPENED))){
                    openedFields.putOpenedToOpenedField(coord);
                    gridMineField.add(new ImageView(getImage(lowerFields.getFieldByCoordinate(coord).name())), coord.getX(), coord.getY());
                    if (lowerFields.getFieldByCoordinate(coord).name().equals(Field.ZERO.name())) {
                        stack.push(coord);
                    }
                }
            }
        }
    }
}