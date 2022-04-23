package ru.geekbrains.ms.views.common;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import ru.geekbrains.ms.models.Document;

public abstract class DocumentViewController<D extends Document, I> implements ContentViewController {

    private VBox vBox;
    protected D model;
    protected HBox headerBox = new HBox();
    protected ListView<I> itemBox = new ListView<>();
    protected HBox footerBox = new HBox();
    protected Label lblId = new Label();
    protected Label lblDate = new Label();

    @Override
    public VBox getView() {
        if (vBox == null) {
            vBox = new VBox();
            headerBox.getChildren().addAll(lblId, lblDate);
            VBox.setVgrow(itemBox, Priority.ALWAYS);
            footerBox.setAlignment(Pos.CENTER_RIGHT);
            vBox.getChildren().addAll(headerBox, itemBox, footerBox);
            onCreateHeader();
            onCreateItemBox();
            onCreateFooter();
        }
        return vBox;
    }

    public void setModel(D model) {
        this.model = model;
    }

    protected abstract void onCreateHeader();

    protected abstract void onCreateItemBox();

    protected abstract void onCreateFooter();
}
