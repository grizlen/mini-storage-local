package ru.geekbrains.ms.views;

import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.geekbrains.ms.models.Document;

import javax.annotation.PostConstruct;

@Controller
@Slf4j
public class JournalViewController implements ContentViewController {

    @Autowired
    private MainViewController mainViewController;
    private VBox vBox;
    private ListView<Document> lvDocuments = new ListView<>();

    @PostConstruct
    private void init() {
        log.info("JournalViewController");
        mainViewController.registerView(this);
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
    }

    @Override
    public String getTitle() {
        return "Документы";
    }

    @Override
    public ViewCommand[] getCommands() {
        return new ViewCommand[] {
                ViewCommand.create("Поступления", () -> mainViewController.setContent(DocPurchaseViewController.class))
        };
    }
}
