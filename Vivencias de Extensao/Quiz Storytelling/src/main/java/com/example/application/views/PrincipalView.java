package com.example.application.views;

import com.example.application.data.Texto;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Principal")
@Route(value = "principal")
@RouteAlias(value = "")
@Uses(Icon.class)
public class PrincipalView extends Composite<VerticalLayout> {

    Texto texto = new Texto();

    public PrincipalView() {

        VerticalLayout layoutColumn2 = new VerticalLayout();

        H1 h1 = new H1();
        Button buttonPrimary = new Button();
        Button buttonPrimary2 = new Button();
        Button buttonPrimary3 = new Button();
        Paragraph textMedium = new Paragraph();

        buttonPrimary.addClickListener(
                e -> {
                    texto.setPagina(1);
                    buttonPrimary.getUI().ifPresent(ui ->
                            ui.navigate("historia"));
                });

        buttonPrimary2.addClickListener(
                e -> {
                    texto.setPagina(2);
                    buttonPrimary.getUI().ifPresent(ui ->
                            ui.navigate("historia"));
                });

        buttonPrimary3.addClickListener(
                e -> {
                    texto.setPagina(3);
                    buttonPrimary.getUI().ifPresent(ui ->
                            ui.navigate("historia"));
                });

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setFlexGrow(1.0, layoutColumn2);

        layoutColumn2.setWidthFull();
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");

        h1.setText("Quizzes");
        h1.setWidth("min-content");

        textMedium.setWidth("100%");
        textMedium.getStyle().set("font-size", "var(--lumo-font-size-m)");
        textMedium.setText(texto.getParagrafos(1));

        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, h1);
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, buttonPrimary);
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, buttonPrimary2);
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, buttonPrimary3);

        buttonPrimary.setText("Quiz 1");
        buttonPrimary.setWidth("200px");
        buttonPrimary.setHeight("40px");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        buttonPrimary2.setText("Quiz 2");
        buttonPrimary2.setWidth("200px");
        buttonPrimary2.setHeight("40px");
        buttonPrimary2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        buttonPrimary3.setText("Quiz 3");
        buttonPrimary3.setWidth("200px");
        buttonPrimary3.setHeight("40px");
        buttonPrimary3.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        getContent().add(layoutColumn2);
        layoutColumn2.add(h1);
        layoutColumn2.add(textMedium);
        layoutColumn2.add(buttonPrimary);
        layoutColumn2.add(buttonPrimary2);
        layoutColumn2.add(buttonPrimary3);
    }
}
