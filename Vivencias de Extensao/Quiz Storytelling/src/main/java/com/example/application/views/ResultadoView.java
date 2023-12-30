package com.example.application.views;

import com.example.application.data.Texto;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Resultado")
@Route(value = "resultado")
@Uses(Icon.class)
public class ResultadoView extends Composite<VerticalLayout> {

    Texto texto = new Texto();
    public ResultadoView() {
        Icon icon = new Icon();
        Paragraph textLarge = new Paragraph();
        Button voltar = new Button();

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.CENTER);
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, textLarge);
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, icon);
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, voltar);
        getContent().setAlignItems(Alignment.CENTER);

        if (Texto.isSucesso()){
            icon.getElement().setAttribute("icon", "vaadin:check");
            icon.setColor("green");
            textLarge.setText("Resposta enviada com sucesso!");
        } else {
            icon.getElement().setAttribute("icon", "vaadin:exclamation-circle");
            icon.setColor("red");
            textLarge.setText("Houve um erro ao tentar enviar sua resposta.");
        }

        icon.setSize("100px");
        textLarge.getStyle().set("font-size", "var(--lumo-font-size-xl)");

        voltar.setText("Voltar");
        voltar.setWidth("min-content");
        voltar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        voltar.addClickListener(e ->
                voltar.getUI().ifPresent(ui ->
                        ui.navigate("principal"))
        );

        getContent().add(icon);
        getContent().add(textLarge);
        getContent().add(voltar);
    }
}
