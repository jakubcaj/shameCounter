package com.idc.sterba.demo.view;

import com.idc.sterba.demo.NavigatorUI;
import com.idc.sterba.demo.entity.Match;
import com.idc.sterba.demo.service.EmployeeService;
import com.idc.sterba.demo.view.component.OneSideTeamComponent;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = "addMatch", ui = NavigatorUI.class)
public class AddMatchView extends AbstractView {

    private EmployeeService employeeService;

    public AddMatchView(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        VerticalLayout mainVertical = new VerticalLayout();

        Binder<Match> binder = new BeanValidationBinder<>(Match.class);
        mainVertical.addComponent(new OneSideTeamComponent(binder, employeeService.getAllEmployees()));



//        ComboBox<Employee> player1 = new ComboBox("player1", employeeService.getAllEmployees());
//        binder.forField(player1)
//                .asRequired()
//                .bind((ValueProvider<Match, Employee>) match -> match.getTeam1().getP1(),
//                        (Setter<Match, Employee>) (match, employee) -> match.getTeam1().setP1(employee))
//                ;
//
//        player1.setItemCaptionGenerator((ItemCaptionGenerator<Employee>) item -> item.getFirstName() + " " + item.getLastName());
//        mainVertical.addComponentsAndExpand(player1);

//        TextField goals1 = new TextField();
//        mainVertical.addComponent(goals1);
//        binder.forField(goals1)
//                //.asRequired()
//                .withConverter(new StringToIntegerConverter("Cannot parse"))
//                .withValidator(new IntegerRangeValidator("Does not make sence", 0, 5))
//                .bind(Match::getGoals1, Match::setGoals1);

        Match match = new Match();
        binder.setBean(match);

        Button save = new Button("Save");
        mainVertical.addComponent(save);
        save.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    binder.writeBean(match);
                    Notification.show("Saved properly");
                } catch (ValidationException e) {
                    Notification.show("Not valid 2." + e.getMessage());
                }
            }
        });

        addComponent(mainVertical);
    }
}
