package ru.geekbrains.ms.views;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.geekbrains.ms.models.DocItem;
import ru.geekbrains.ms.models.DocPurchase;
import ru.geekbrains.ms.services.DocumentsFacade;
import ru.geekbrains.ms.views.common.DocumentViewController;
import ru.geekbrains.ms.views.common.MainViewController;
import ru.geekbrains.ms.views.common.ViewCommand;

import javax.annotation.PostConstruct;

@Controller
@Slf4j
public class DocPurchaseViewController extends DocumentViewController<DocPurchase, DocItem> {

    @Autowired
    MainViewController mainViewController;
    @Autowired
    DocumentsFacade documentsFacade;

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
    public void setModel(DocPurchase model) {
        super.setModel(model);
        if (model == null) {
            setModel(new DocPurchase());
        } else {
            lblId.setText("№ " + model.getId());
            lblDate.setText("от " + model.getDate());
            loadItems();
        }
    }

    private void loadItems() {
        itemBox.getItems().clear();
        itemBox.getItems().addAll(model.getItems());
    }

    @Override
    protected void onCreateHeader() {

    }

    @Override
    protected void onCreateFooter() {
        footerBox.getChildren().add(ViewCommand.create("+", this::addItem).asButton());
    }

    private void addItem() {
        DocItem item = new DocItem();
        model.addItem(item);
        loadItems();
    }

    private void save() {
        log.info("save");
        documentsFacade.savePurchase(model);
        journal();
    }

    private void journal() {
        mainViewController.setContent(JournalViewController.class);
    }
}
