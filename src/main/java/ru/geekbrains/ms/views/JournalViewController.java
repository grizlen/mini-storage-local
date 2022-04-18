package ru.geekbrains.ms.views;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

@Controller
@Slf4j
public class JournalViewController implements ContentViewController {

    @Autowired
    private MainViewController mainViewController;
    private VBox vBox;

    @PostConstruct
    private void init() {
        log.info("JournalViewController");
        mainViewController.registerView(this);
    }

    @Override
    public VBox getView() {
        if (vBox == null) {
            vBox = new VBox();
            vBox.getChildren().add(new Label("JournalViewController"));
        }
        return vBox;
    }
}
