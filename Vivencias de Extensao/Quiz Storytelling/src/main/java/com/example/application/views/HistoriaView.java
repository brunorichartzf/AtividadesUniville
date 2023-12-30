package com.example.application.views;

import com.example.application.data.Texto;
import com.example.application.services.EmailSender;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import javax.mail.MessagingException;

@PageTitle("Historia")
@Route(value = "historia")
@Uses(Icon.class)
public class HistoriaView extends Composite<VerticalLayout> {

    Texto texto = new Texto();
    EmailSender emailSender = new EmailSender("quizstorytelling@gmail.com", "zdalevhynyidjhid");

    public HistoriaView() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        HorizontalLayout layoutRow = new HorizontalLayout();
        H1 h1 = new H1();
        Button voltar = new Button(new Icon(VaadinIcon.ARROW_BACKWARD));
        Paragraph textMedium = new Paragraph();
        Paragraph textMedium2 = new Paragraph();
        Paragraph textMedium3 = new Paragraph();

        EmailField emailField = new EmailField();
        TextField nomeField = new TextField();
        TextArea textArea = new TextArea();
        Button buttonPrimary = new Button();

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setFlexGrow(1.0, layoutColumn2);

        layoutRow.setWidthFull();
        layoutRow.addClassName(LumoUtility.Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");

        layoutColumn2.setWidthFull();
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, h1);

        textMedium.setWidth("100%");
        textMedium.getStyle().set("font-size", "var(--lumo-font-size-m)");
        textMedium2.setWidth("100%");
        textMedium2.getStyle().set("font-size", "var(--lumo-font-size-m)");
        textMedium3.setWidth("100%");
        textMedium3.getStyle().set("font-size", "var(--lumo-font-size-m)");


        emailField.setLabel("Email");
        emailField.setWidth("min-content");

        nomeField.setLabel("Nome");
        nomeField.setWidth("min-content");

        textArea.setLabel("Resposta");
        textArea.setWidth("100%");
        textArea.setHeight("200px");

        buttonPrimary.setText("Enviar");
        buttonPrimary.setWidth("150px");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        getContent().add(layoutColumn2);
        layoutColumn2.add(voltar);

        voltar.addClickListener(e ->
                voltar.getUI().ifPresent(ui ->
                        ui.navigate("principal"))
        );

        buttonPrimary.addClickListener(
                sendMail -> {
                    try {
                        emailSender.sendEmail(
                                "gabriel.lopes26@univille.br",
                                "História " + texto.getPagina() + " - " + nomeField.getValue(),
                                textArea.getValue() + "\n\n" + "E-mail: " + emailField.getValue());
                        Texto.setSucesso(true);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                        Texto.setSucesso(false);
                    }

                    buttonPrimary.getUI().ifPresent(ui ->
                            ui.navigate("resultado"));
                });

        switch (texto.getPagina()) {
            case 1 ->
            {
                h1.setText(texto.getTitulos(0));
                textMedium.setText(texto.getParagrafos(0));
                textMedium2.setText(texto.getParagrafos(1));

                layoutColumn2.add(h1);
                layoutColumn2.add(textMedium);
                layoutColumn2.add(textMedium2);
            }
            case 2 ->
            {
                h1.setText(texto.getTitulos(1));
                textMedium.setText(texto.getParagrafos(1));
                textMedium2.setText(texto.getParagrafos(2));

                layoutColumn2.add(h1);
                layoutColumn2.add(textMedium);
                layoutColumn2.add(textMedium2);
            }
            case 3 ->
            {
                h1.setText(texto.getTitulos(2));
                textMedium.setText(texto.getParagrafos(2));
                textMedium2.setText(texto.getParagrafos(0));
                textMedium3.setText(texto.getParagrafos(1));

                layoutColumn2.add(h1);
                layoutColumn2.add(textMedium);
                layoutColumn2.add(textMedium2);
                layoutColumn2.add(textMedium3);
            }
        }

        layoutColumn2.add(layoutRow);
        layoutRow.add(emailField);
        layoutRow.add(nomeField);
        layoutColumn2.add(textArea);
        layoutColumn2.add(buttonPrimary);
    }
}
