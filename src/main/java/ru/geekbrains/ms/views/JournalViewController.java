package ru.geekbrains.ms.views;

import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.geekbrains.ms.models.DocPurchase;
import ru.geekbrains.ms.models.Document;
import ru.geekbrains.ms.services.DocumentsFacade;
import ru.geekbrains.ms.views.common.ContentViewController;
import ru.geekbrains.ms.views.common.MainViewController;
import ru.geekbrains.ms.views.common.ViewCommand;

import javax.annotation.PostConstruct;

@Controller
@Slf4j
public class JournalViewController implements ContentViewController {

    @Autowired
    private MainViewController mainViewController;
    @Autowired
    private DocumentsFacade documentsFacade;
    private VBox vBox;
    private final ListView<Document> lvDocuments = new ListView<>();

    @PostConstruct
    private void init() {
        log.info("JournalViewController");
        mainViewController.registerView(this);
    }

    public JournalViewController() {
        lvDocuments.setOnMouseClicked(mouseEvent -> {
            Document item = lvDocuments.getSelectionModel().getSelectedItem();
            if (item != null && mouseEvent.getClickCount() == 2) {
                mainViewController.setContent(DocPurchaseViewController.class).setModel((DocPurchase) item);
            }
        });
    }
    @Override
    public VBox getView() {
        if (vBox == null) {
            vBox = new VBox();
            VBox.setVgrow(lvDocuments, Priority.ALWAYS);
            vBox.getChildren().add(lvDocuments);
        }
        return vBox;
    }

    @Override
    public void open() {
        log.info("JournalViewController open");
        lvDocuments.getItems().clear();
        lvDocuments.getItems().addAll(documentsFacade.getAll());
    }

    @Override
    public String getTitle() {
        return "Документы";
    }

    @Override
    public ViewCommand[] getCommands() {
        return new ViewCommand[] {
                ViewCommand.create("Поступление", this::newPurchase)
        };
    }

    private void newPurchase() {
        mainViewController.setContent(DocPurchaseViewController.class).setModel(null);
    }
}
