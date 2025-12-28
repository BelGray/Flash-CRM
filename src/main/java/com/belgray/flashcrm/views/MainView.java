package com.belgray.flashcrm.views;

import com.belgray.flashcrm.entity.Contact;
import com.belgray.flashcrm.repository.ContactRepository;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    private final ContactRepository repo;

    Grid<Contact> grid = new Grid<>(Contact.class);
    TextField filterText = new TextField();

    public MainView(ContactRepository repo) {
        this.repo = repo;

        if (repo.count() == 0) {
            System.out.println(">>> ТАБЛИЦА ПУСТАЯ. СОЗДАЮ АВАРИЙНОГО ПОЛЬЗОВАТЕЛЯ...");

            Contact c = new Contact();
            c.setFirstName("Emergency");
            c.setLastName("User");
            c.setEmail("help@me.com");
            c.setStatus(Contact.Status.NEW);

            repo.save(c);
            System.out.println(">>> ПОЛЬЗОВАТЕЛЬ СОХРАНЕН! <<<");
        }

        addClassName("list-view");
        setSizeFull();

        add(filterText, grid);
    }

    private void configGrid() {
        grid.addClassName("contact-grid");
        grid.setSizeFull();

        grid.setColumns("firstName", "lastName", "email");
        grid.addColumn(contact -> contact.getStatus()).setHeader("Status");

        grid.getColumns().forEach(contactColumn -> contactColumn.setAutoWidth(true));

    }

    private void configFilter() {

        filterText.setPlaceholder("Поиск по фамилии...");
        filterText.setClearButtonVisible(true);

        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

    }

    private void updateList() {
        if (filterText.isEmpty()) {
            grid.setItems(repo.findAll());
        } else {
            grid.setItems(repo.findByLastNameStartsWithIgnoreCase(filterText.getValue()));
        }
    }

}
