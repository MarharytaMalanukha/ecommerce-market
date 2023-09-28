package com.malanukha.market.view.admin;

import com.malanukha.market.domain.user.User;
import com.malanukha.market.dto.UserDto;
import com.malanukha.market.service.admin.BaseAdminService;
import com.malanukha.market.service.utils.UtilsService;
import com.malanukha.market.view.AdminLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.converter.Converter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.javatuples.Pair;

import java.util.List;
import java.util.Optional;

@PageTitle("Users")
@Route(value = "admin/users/:entityID?/:action?(edit)", layout = AdminLayout.class)
@RolesAllowed("SUPERADMIN")
@Uses(Icon.class)
public class AdminUsersView extends BaseAdminView<UserDto, User> {
    public AdminUsersView(BaseAdminService<UserDto, User> baseAdminService, UtilsService utilsService) {
        super(baseAdminService, utilsService);
    }

    @Override
    protected Class<UserDto> getEntityClass() {
        return UserDto.class;
    }

    @Override
    protected Class<? extends Div> getViewClass() {
        return AdminUsersView.class;
    }

    @Override
    protected List<String> getGridColumnNames() {
        return List.of("id", "username", "firstName", "");
    }

    @Override
    protected List<Pair<TextField, Optional<Converter>>> getGridColumnsWithConverters() {
        return List.of();
    }

    @Override
    protected List<Component> getComponentColumns() {
        return List.of();
    }

    @Override
    protected Long getIdFromEntity(UserDto sampleEntity) {
        return sampleEntity.getId();
    }

    @Override
    protected String getRoute() {
        return "users";
    }

    @Override
    protected UserDto getNewEntityObject() {
        return new UserDto();
    }
}
