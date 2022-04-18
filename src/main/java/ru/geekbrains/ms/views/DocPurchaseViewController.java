package ru.geekbrains.ms.views;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.geekbrains.ms.models.DocItem;
import ru.geekbrains.ms.models.Document;

import javax.annotation.PostConstruct;

@Controller
@Slf4j
public class DocPurchaseViewController extends DocumentViewController<Document, DocItem> {

    @Autowired
    MainViewController mainViewController;

    @PostConstruct
    private void init() {
        mainViewController.registerView(this);
    }

    @Override
    public void open() {
        log.info("DocPurchaseViewController open");
    }

    @Override
    public String getTitle() {
        return "Поступление";
    }

    @Override
    public ViewCommand[] getCommands() {
        return new ViewCommand[] {
                ViewCommand.create("Save", this::save),
                ViewCommand.create("Documents", this::journal)
        };
    }

    @Override
    public void setModel(Document model) {
        super.setModel(model);
        if (model == null) {
            setModel(new Document());
        } else {
            lblId.setText("№ " + model.getId());
            lblDate.setText("от " + model.getDate());
        }
    }

    private void save() {
        log.info("save");
        journal();
    }

    private void journal() {
        mainViewController.setContent(JournalViewController.class);
    }
}
