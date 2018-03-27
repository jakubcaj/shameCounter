package com.idc.sterba.demo.view.component;

import com.idc.sterba.demo.entity.Employee;
import com.idc.sterba.demo.entity.Match;
import com.vaadin.data.Binder;
import com.vaadin.data.ValueProvider;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.server.Setter;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.ui.NumberField;

import java.util.List;

public class OneSideTeamComponent extends HorizontalLayout {

    private List<Employee> employees;
    private Binder<Match> binder;

    public OneSideTeamComponent(Binder<Match> binder, List<Employee> employees) {
        this.employees = employees;
        this.binder = binder;
        buildLayout();
    }

    private void buildLayout() {
        ComboBox<Employee> player1 = buildPlayerCombo();
        ComboBox<Employee> player2 = buildPlayerCombo();
        VerticalLayout playersLayout = new VerticalLayout(player1, player2);

        NumberField goals = buildGoalsField();

        addComponents(playersLayout, goals);
        setComponentAlignment(goals, Alignment.MIDDLE_CENTER);
        addStyleName("outlined");
        setMargin(false);
        setSpacing(false);
    }

    private ComboBox<Employee> buildPlayerCombo() {
        ComboBox<Employee> player = new ComboBox<>(null, employees);
//        binder.forField(player)
//                .asRequired()
//                .bind((ValueProvider<Match, Employee>) match -> match.getTeam1().getP1(),
//                        (Setter<Match, Employee>) (match, employee) -> match.getTeam1().setP1(employee))
//        ;

        player.setItemCaptionGenerator((ItemCaptionGenerator<Employee>) item -> item.getFirstName() + " " + item.getLastName());

        return player;
    }

    private NumberField buildGoalsField() {
        NumberField goals = new NumberField();
//        binder.forField(goals)
//                //.asRequired()
//                .withConverter(new StringToIntegerConverter("Cannot parse"))
//                .withValidator(new IntegerRangeValidator("Does not make sence", 0, 5))
//                .bind(Match::getGoals1, Match::setGoals1);
        return goals;
    }
}
