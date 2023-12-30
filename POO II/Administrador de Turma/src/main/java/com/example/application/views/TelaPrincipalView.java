package com.example.application.views;

import com.example.application.data.entity.Boletim;
import com.example.application.data.service.BoletimService;
import com.example.application.persistencia.Services;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.Comparator;
import java.util.List;

@PageTitle("Tela Principal")
@Route(value = "tela-principal", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@Uses(Icon.class)
public class TelaPrincipalView extends Composite<VerticalLayout> {

    BoletimService service;
    private Grid<Boletim> boletim = new Grid(Boletim.class, false);

    //----------------------Componentes Grade-----------------------
    private void configureGrid() {
        boletim.addColumn(Boletim::getMatricula).setHeader("Matricula");
        boletim.addColumn(Boletim::getNome).setHeader("Nome");
        boletim.addColumn(Boletim::getFaltas).setHeader("Faltas");
        boletim.addColumn(Boletim::getN1).setHeader("Nota1");
        boletim.addColumn(Boletim::getN2).setHeader("Nota2");
        boletim.addColumn(Boletim::getN3).setHeader("Nota3");
        boletim.addColumn(Boletim::getN4).setHeader("Nota4");
        boletim.getColumnByKey("aprovadoColumn");
    }
    public TelaPrincipalView(BoletimService service) {

        Services Services = new Services();
        this.service = service;
        configureGrid();
        updateGrid();

        //----------------------Componentes-----------------------

        HorizontalLayout layoutRow = new HorizontalLayout();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        HorizontalLayout layoutRow3 = new HorizontalLayout();

        H1 h1 = new H1("Turma");
        Button buttonPrimary = new Button();
        Button buttonPrimary2 = new Button();

        boletim.addComponentColumn(item -> {
                    Icon icon;
                    if(item.getAprovacao()){
                        icon = VaadinIcon.CHECK_CIRCLE.create();
                        icon.setColor("green");
                    } else {
                        icon = VaadinIcon.CLOSE_CIRCLE.create();
                        icon.setColor("red");
                    }
                    return icon;
                })
                .setKey("aprovadoColumn")
                .setHeader("Aprovado")
                .setComparator(Comparator.comparing(Boletim::getAprovacao));

        //----------------------Botoes-----------------------

        buttonPrimary.setText("Editar alunos");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        buttonPrimary.addClickListener(e ->
                buttonPrimary.getUI().ifPresent(ui ->
                        ui.navigate("editar-alunos"))
        );

        buttonPrimary2.setText("Editar notas");
        buttonPrimary2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        buttonPrimary2.addClickListener(e ->
                buttonPrimary2.getUI().ifPresent(ui ->
                        ui.navigate("editar-notas"))
        );

        getContent().setHeightFull();
        getContent().setWidthFull();

        //----------------------Alinhamentos-----------------------

        layoutRow.setWidthFull();
        layoutRow.setAlignItems(Alignment.START);
        layoutRow.setJustifyContentMode(JustifyContentMode.CENTER);

        layoutRow2.setWidthFull();
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setAlignItems(Alignment.START);
        layoutRow2.setJustifyContentMode(JustifyContentMode.CENTER);

        layoutRow3.setWidthFull();
        layoutRow3.addClassName(Gap.MEDIUM);
        layoutRow3.setAlignItems(Alignment.START);
        layoutRow3.setJustifyContentMode(JustifyContentMode.CENTER);

        //-----------------------Adds-----------------------

        getContent().add(layoutRow);
        getContent().add(layoutRow2);
        getContent().add(boletim);

        layoutRow.add(h1);

        layoutRow2.add(buttonPrimary);
        layoutRow2.add(buttonPrimary2);



    }

        //-----------------------grade-----------------------
        private void setGridSampleData(Grid grid) {
            grid.setItems(query -> samplePersonService.list(
                            PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                    .stream());
        }

        @Autowired()
        private BoletimService samplePersonService;

        record SampleItem(String value, String label, Boolean disabled) {
        }


        private void updateGrid() {
            List<Boletim> listaBoletim = service.findAll();
            boletim.setItems(listaBoletim);

        }


}
