package com.example.application.views;

import com.example.application.persistencia.Services;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.example.application.data.entity.Boletim;
import com.example.application.data.service.BoletimService;


import java.util.Comparator;
import java.util.List;

@PageTitle("Editar Notas")
@Route(value = "editar-notas", layout = MainLayout.class)
@Uses(Icon.class)
public class EditarNotasView extends Composite<VerticalLayout> {
    BoletimService service;

    //----------------------Componentes Grade-----------------------
    private Grid<Boletim> boletim = new Grid(Boletim.class, false);

    private void configureGrid() {
        boletim.addColumn(Boletim::getMatricula)
                .setFlexGrow(0)
                .setWidth("100px")
                .setHeader("Matricula");
        boletim.addColumn(Boletim::getNome).setHeader("Nome");
        boletim.addColumn(Boletim::getFaltas).setHeader("Faltas");
        boletim.addColumn(Boletim::getN1).setHeader("Nota1");
        boletim.addColumn(Boletim::getN2).setHeader("Nota2");
        boletim.addColumn(Boletim::getN3).setHeader("Nota3");
        boletim.addColumn(Boletim::getN4).setHeader("Nota4");
        boletim.getColumnByKey("aprovadoColumn");
    }

    public EditarNotasView(BoletimService service) {

        Services Services = new Services();
        this.service = service;
        configureGrid();
        updateGrid();

        //----------------------Componentes-----------------------

        H1 h1 = new H1("Notas");
        HorizontalLayout layoutRow = new HorizontalLayout();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        Button voltar = new Button(new Icon(VaadinIcon.ARROW_BACKWARD));

        IntegerField faltas = new IntegerField("Faltas");
        NumberField nota1 = new NumberField("Nota 1");
        NumberField nota2 = new NumberField("Nota 2");
        NumberField nota3 = new NumberField("Nota 3");
        NumberField nota4 = new NumberField("Nota 4");
        IntegerField matricula = new IntegerField("Matricula");
        Button editarNota = new Button("Confirmar");

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

        //----------------------Alinhamentos-----------------------

        getContent().setHeightFull();
        getContent().setWidthFull();

        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, h1);

        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setJustifyContentMode(JustifyContentMode.CENTER);

        layoutRow2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow2);
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setAlignItems(Alignment.START);
        layoutRow2.setJustifyContentMode(JustifyContentMode.CENTER);

        //----------------------Botoes-----------------------

        editarNota.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        nota1.setWidth("6vw");
        nota1.setMin(0);
        nota1.setMax(10);
        nota1.setValue(0.0);

        nota2.setWidth("6vw");
        nota2.setMin(0);
        nota2.setMax(10);
        nota2.setValue(0.0);

        nota3.setWidth("6vw");
        nota3.setMin(0);
        nota3.setMax(10);
        nota3.setValue(0.0);

        nota4.setWidth("6vw");
        nota4.setMin(0);
        nota4.setMax(10);
        nota4.setValue(0.0);

        matricula.setMin(1);

        faltas.setWidth("6vw");
        faltas.setMin(0);
        faltas.setValue(0);

        voltar.addClickListener(e ->
                voltar.getUI().ifPresent(ui ->
                        ui.navigate("tela-principal"))
        );

        editarNota.addClickListener(
                notaAd -> {
                    if(nota1.getValue().floatValue() <= 10 & nota1.getValue().floatValue() >= 0){
                        if(nota2.getValue().floatValue() <= 10 & nota2.getValue().floatValue() >= 0){
                            if(nota3.getValue().floatValue() <= 10 & nota3.getValue().floatValue() >= 0){
                                if(nota4.getValue().floatValue() <= 10 & nota4.getValue().floatValue() >= 0){
                                    if(faltas.getValue() >= 0){
                        Boletim boletim = new Boletim();
                        boletim.setN1(nota1.getValue().floatValue());
                        boletim.setN2(nota2.getValue().floatValue());
                        boletim.setN3(nota3.getValue().floatValue());
                        boletim.setN4(nota4.getValue().floatValue());
                        boletim.setMatricula(matricula.getValue());
                        boletim.setFaltas(faltas.getValue());
                        Services.alterBoletim(boletim);
                    }}}}}
                    updateGrid();
                });
        editarNota.addClickShortcut(Key.ENTER);

        //-----------------------Adds-----------------------

        getContent().add(voltar);
        getContent().add(h1);
        getContent().add(boletim);
        getContent().add(layoutRow);
        getContent().add(layoutRow2);

        layoutRow.add(matricula);
        layoutRow.add(faltas);
        layoutRow.add(nota1);
        layoutRow.add(nota2);
        layoutRow.add(nota3);
        layoutRow.add(nota4);


        layoutRow2.add(editarNota);
    }

    //-----------------------grade-----------------------

    private void updateGrid() {
        List<Boletim> listaBoletim = service.findAll();
        boletim.setItems(listaBoletim);

    }
}
