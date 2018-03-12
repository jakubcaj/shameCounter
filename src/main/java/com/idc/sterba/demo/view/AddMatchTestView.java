package com.idc.sterba.demo.view;

import com.idc.sterba.demo.NavigatorUI;
import com.idc.sterba.demo.entity.Employee;
import com.idc.sterba.demo.entity.Match;
import com.idc.sterba.demo.entity.Team;
import com.idc.sterba.demo.repository.EmployeeRepository;
import com.idc.sterba.demo.repository.MatchRepository;
import com.idc.sterba.demo.repository.TeamRepository;
import com.idc.sterba.demo.service.MatchService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = "addMatchTest", ui = NavigatorUI.class)
public class AddMatchTestView extends VerticalLayout implements View {

    private Button button;

    private MatchService matchService;
    //@Autowired
    private MatchRepository matchRepository;
    //@Autowired
    private EmployeeRepository employeeRepository;
    //@Autowired
    private TeamRepository teamRepository;

    public AddMatchTestView(MatchRepository matchRepository, EmployeeRepository employeeRepository, TeamRepository teamRepository,
                            MatchService matchService) {
        this.matchRepository = matchRepository;
        this.employeeRepository = employeeRepository;
        this.teamRepository = teamRepository;
        this.matchService = matchService;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        button = new Button("add", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Match match = new Match();
                //match.setId(1L);
                {
                    Team t1 = new Team();
                    //t1.setId(2L);
                    match.setTeam1(t1);

                    Employee e1 = new Employee();
                    //e1.setId(1L);
                    e1.setFirstName("emp 1 fn");

                    employeeRepository.save(e1);

                    Employee e2 = new Employee();
                    //e2.setId(2L);
                    employeeRepository.save(e2);

                    t1.setP1(e1);
                    t1.setP2(e2);
                    teamRepository.save(t1);
                }

                {
                    Team t2 = new Team();
                    //t2.setId(3L);
                    match.setTeam2(t2);

                    Employee e1 = new Employee();
                    //e1.setId(3L);
                    employeeRepository.save(e1);
                    Employee e2 = new Employee();
                    //e2.setId(4L);
                    employeeRepository.save(e2);

                    t2.setP1(e1);
                    t2.setP2(e2);
                    teamRepository.save(t2);
                }

                match.setGoals1(1);
                match.setGoals2(2);

                matchRepository.save(match);
            }
        });
        addComponent(button);

        Button b2 = new Button("load", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Match match = matchService.getMatch(7L);

                Label l = new Label(match.getTeam1().getP1().getFirstName());
                AddMatchTestView.this.addComponent(l);
            }
        });

        addComponent(b2);
    }
}
