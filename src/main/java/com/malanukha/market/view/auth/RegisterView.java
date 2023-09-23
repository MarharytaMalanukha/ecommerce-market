package com.malanukha.market.view.auth;

import com.malanukha.market.service.user.UserService;
import com.malanukha.market.view.auth.LoginView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;

@PageTitle("Register")
@Route(value = "register")
@Uses(Icon.class)
@AnonymousAllowed
public class RegisterView extends Composite<VerticalLayout> {

    private HorizontalLayout upperLayoutRow = new HorizontalLayout();
    private VerticalLayout upperLayoutLeftSideBar = new VerticalLayout();
    private VerticalLayout upperLayoutForm = new VerticalLayout();
    private VerticalLayout upperLayoutRightSideBar = new VerticalLayout();
    private TextField username = new TextField("Username");
    private PasswordField password = new PasswordField("Password");
    private PasswordField repeatPassword = new PasswordField("Repeat password");
    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private EmailField email = new EmailField("Email");
    private TextField mobile = new TextField("Phone number");
    private TextField profilePicture = new TextField("Profile picture url (optional)");
    private Button buttonPrimary = new Button("Register");
    private Button buttonSecondary = new Button("Go To Login");
    private UserService userService;

    public RegisterView(UserService userService) {
        this.userService = userService;

        getContent().setWidthFull();
        getContent().addClassName(Padding.LARGE);
        getContent().setHeightFull();

        H3 h3 = new H3("Register");
        HorizontalLayout layoutOfFields = new HorizontalLayout();
        VerticalLayout fieldsLeftLayout = new VerticalLayout();
        VerticalLayout fieldsRightLayout = new VerticalLayout();

        HorizontalLayout layoutRow3 = new HorizontalLayout();

        HorizontalLayout buttonsLayout = new HorizontalLayout();

        upperLayoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, upperLayoutRow);
        upperLayoutRow.setFlexGrow(1.0, upperLayoutLeftSideBar);

        upperLayoutLeftSideBar.setWidth(null);
        upperLayoutLeftSideBar.setHeightFull();
        upperLayoutRow.setFlexGrow(1.0, upperLayoutForm);
        upperLayoutForm.setWidth(null);

        layoutOfFields.setWidthFull();
        layoutOfFields.addClassName(Gap.LARGE);
        layoutOfFields.setFlexGrow(1.0, fieldsLeftLayout);
        fieldsLeftLayout.setWidth(null);

        username.setWidthFull();
        password.setWidthFull();
        repeatPassword.setWidthFull();
        firstName.setWidthFull();
        lastName.setWidthFull();
        email.setWidthFull();
        mobile.setWidthFull();
        profilePicture.setWidthFull();

        layoutOfFields.setFlexGrow(1.0, fieldsRightLayout);
        fieldsRightLayout.setWidth(null);
        layoutRow3.addClassName(Gap.MEDIUM);
        layoutRow3.setWidthFull();
        buttonsLayout.addClassName(Gap.MEDIUM);
        buttonsLayout.setWidthFull();

        upperLayoutForm.setFlexGrow(1.0, buttonsLayout);
        buttonsLayout.setFlexGrow(1.0, buttonPrimary);
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonsLayout.setFlexGrow(1.0, buttonSecondary);
        buttonsLayout.setAlignSelf(FlexComponent.Alignment.START, buttonSecondary);

        upperLayoutRow.setFlexGrow(1.0, upperLayoutRightSideBar);
        upperLayoutRightSideBar.setWidth(null);

        getContent().add(upperLayoutRow);
        upperLayoutRow.add(upperLayoutLeftSideBar);
        upperLayoutRow.add(upperLayoutForm);
        upperLayoutForm.add(h3);
        upperLayoutForm.add(layoutOfFields);

        layoutOfFields.add(fieldsLeftLayout);
        fieldsLeftLayout.add(username);
        fieldsLeftLayout.add(password);
        fieldsLeftLayout.add(firstName);
        fieldsLeftLayout.add(mobile);
        layoutOfFields.add(fieldsRightLayout);
        fieldsRightLayout.add(layoutRow3);
        fieldsRightLayout.add(email);
        fieldsRightLayout.add(repeatPassword);
        fieldsRightLayout.add(lastName);
        fieldsRightLayout.add(profilePicture);

        buttonPrimary.addClickListener(this::validateFieldsAndSaveUser);
        upperLayoutForm.add(buttonsLayout);
        buttonsLayout.add(buttonPrimary);
        buttonsLayout.add(buttonSecondary);
        upperLayoutRow.add(upperLayoutRightSideBar);
    }

    private void validateFieldsAndSaveUser(ClickEvent<Button> buttonClickEvent) {
        //todo add password and other fields validation
        boolean userSaved = userService.saveUser(username.getValue(), password.getValue(), firstName.getValue(),
                lastName.getValue(), email.getValue(), mobile.getValue(), profilePicture.getValue());
        if (!userSaved) {
            Notification.show("Some data is entered incorrectly. Please try again");
        } else {
            UI.getCurrent().navigate(LoginView.class);
        }
    }
}
