package ec.edu.epn.mypolidomus.AppDomus.DesktopApp.CustomControl;


import ec.edu.epn.mypolidomus.Infrastructure.AppStyle;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class MyComboBox<T> extends ComboBox<T>{

    public MyComboBox(ObservableList<T> items) {
        super(items);
        customizeComponent();
    }

    public MyComboBox() {
        customizeComponent();
    }

    private void customizeComponent(){
        setPrefHeight(28);
        setPrefWidth(180);
        setCursor(AppStyle.CURSOR_HAND);

        setBorder(AppStyle.createBorderRect());
        setPadding(AppStyle.createPadding());

        setButtonCell(createCell());
        setCellFactory(createCellFactory());

        setEditable(false);
        setFocusTraversable(false);
    }

    private ListCell<T> createCell(){
        return new ListCell<>(){
            @Override
            protected void updateItem(T item, boolean empty){
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.toString());
                        setFont(AppStyle.FONT);
                        setTextFill(AppStyle.COLOR_FONT);
                        setAlignment(AppStyle.ALIGNMENT_LEFT);
                    }
                }
        };
    }
    private Callback<ListView<T>, ListCell<T>> createCellFactory(){
        return listView -> new ListCell<>(){
            @Override
            protected void updateItem(T item, boolean empty){
                super.updateItem(item,empty);

                if (empty|| item ==null) {
                    setText(null);
                } else {
                    setText(item.toString());
                    setFont(AppStyle.FONT);
                    setTextFill(AppStyle.COLOR_FONT_LIGHT);
                    setAlignment(Pos.CENTER_LEFT);
                }
            }
        };
    }

}
