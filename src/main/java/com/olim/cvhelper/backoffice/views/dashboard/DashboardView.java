package com.olim.cvhelper.backoffice.views.dashboard;

import com.olim.cvhelper.backoffice.entity.CvApplication;
import com.olim.cvhelper.backoffice.entity.CvApplicationStatus;
import com.olim.cvhelper.backoffice.entity.User;
import com.olim.cvhelper.backoffice.service.CvApplicationService;
import com.olim.cvhelper.backoffice.service.UserService;
import com.olim.cvhelper.backoffice.views.MainLayout;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.gridpro.GridPro;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LocalDateTimeRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@UIScope
@PageTitle("Dashboard")
@Route(value = "dashboard", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@RolesAllowed("ADMIN")
@SpringComponent
public class DashboardView extends Div {

    private GridPro<CvApplication> grid;
    private GridListDataView<CvApplication> gridListDataView;

    private Grid.Column<CvApplication> clientColumn;
    private Grid.Column<CvApplication> statusColumn;
    private Grid.Column<CvApplication> cvLinkColumn;
    private Grid.Column<CvApplication> telegramUsernameColumn;
    private Grid.Column<CvApplication> linkedInLinkColumn;
    private Grid.Column<CvApplication> assigneeColumn;
    private Grid.Column<CvApplication> dateColumn;

    private final CvApplicationService cvApplicationService;
    private final UserService userService;

    public DashboardView(CvApplicationService cvApplicationService, UserService userService) {
        this.cvApplicationService = cvApplicationService;
        this.userService = userService;
        addClassName("dashboard-view");
        setSizeFull();
        createGrid();
        add(grid);
    }

    private void createGrid() {
        createGridComponent();
        addColumnsToGrid();
        addFiltersToGrid();
    }

    private void createGridComponent() {
        grid = new GridPro<>();
        grid.setSelectionMode(SelectionMode.MULTI);
        grid.addThemeVariants(GridVariant.MATERIAL_COLUMN_DIVIDERS, GridVariant.MATERIAL_COLUMN_DIVIDERS);
        grid.setHeight("100%");

        List<CvApplication> cvApplications = getCvApplications();
        gridListDataView = grid.setItems(cvApplications);
        grid.getListDataView().addItemCountChangeListener((c) -> {

        });
    }

    private void addColumnsToGrid() {
        createClientColumn();
        createStatusColumn();
        createCvLinkColumn();
        createLinkedInLinkColumn();
        createDateColumn();
        createAssigneeColumn();
        createTelegramUsernameColumn();
    }

    private void createCvLinkColumn() {
        cvLinkColumn = grid.addColumn(new ComponentRenderer<>(cvApplication -> {
                    HorizontalLayout hl = new HorizontalLayout();
                    hl.setAlignItems(Alignment.CENTER);
                    Anchor anchor = new Anchor();
                    anchor.setHref(cvApplication.getCvLink());
                    anchor.setText(cvApplication.getCvLink());
                    anchor.setClassName("Cv_link");
                    hl.add(anchor);
                    return hl;
                }))
                .setComparator(CvApplication::getFullName).setHeader("CV link")
                .setAutoWidth(true)
                .setFlexGrow(1);
        ;
    }

    private void createLinkedInLinkColumn() {
        linkedInLinkColumn = grid.addColumn(new ComponentRenderer<>(cvApplication -> {
                    HorizontalLayout hl = new HorizontalLayout();
                    hl.setAlignItems(Alignment.CENTER);
                    Anchor anchor = new Anchor();
                    anchor.setHref(cvApplication.getLinkedInLink());
                    anchor.setText(cvApplication.getLinkedInLink());
                    anchor.setClassName("LinkedIn_link");
                    hl.add(anchor);
                    return hl;
                }))
                .setComparator(CvApplication::getFullName).setHeader("LinkedIn profile")
                .setAutoWidth(true)
                .setFlexGrow(1);
    }

    private void createTelegramUsernameColumn() {
        telegramUsernameColumn = grid.addColumn(new ComponentRenderer<>(cvApplication -> {
                    HorizontalLayout hl = new HorizontalLayout();
                    hl.setAlignItems(Alignment.CENTER);
                    Anchor anchor = new Anchor();
                    anchor.setHref("https://t.me/" + cvApplication.getTelegramUsername());
                    anchor.setText("Click me");
                    anchor.setClassName("Telegram_username");
                    hl.add(anchor);
                    return hl;
                }))
                .setComparator(CvApplication::getFullName).setHeader("Telegram")
                .setAutoWidth(true)
                .setFlexGrow(1);
        ;
    }

    private void createStatusColumn() {
        statusColumn = grid.addEditColumn(CvApplication::getStatus, new ComponentRenderer<>(cvApplication -> {
                    Span span = new Span(cvApplication.getStatus().toString());
                    span.getElement().getThemeList().add("badge " + cvApplication.getStatus().getColor());
                    return span;
                })).select((item, newValue) -> {
                    item.setStatus(CvApplicationStatus.valueOf(newValue));
                    cvApplicationService.update(item);
                }, CvApplicationStatus.getValues())
                .setComparator(CvApplication::getStatus).setHeader("Status")
                .setAutoWidth(true)
                .setFlexGrow(1);
    }

    private void createAssigneeColumn() {
        List<User> admins = userService.loadAll();
        assigneeColumn = grid.addEditColumn((CvApplication::getAssignee),
                        new ComponentRenderer<>(cvApplication -> new Span(cvApplication.getAssignee().getName())))
                .select((item, newValue) -> {
                    User user = userService.get(newValue);
                    item.setAssignee(user);
                    cvApplicationService.update(item);
                }, admins.stream().map(User::getName).collect(Collectors.toList()))
                .setComparator(cvApplication -> cvApplication.getAssignee().getName()).setHeader("Assignee")
                .setAutoWidth(true)
                .setFlexGrow(1);
        ;
    }

    private void createClientColumn() {
        clientColumn = grid.addColumn(new ComponentRenderer<>(CvApplication -> {
                    HorizontalLayout hl = new HorizontalLayout();
                    hl.setAlignItems(Alignment.CENTER);
                    Span span = new Span();
                    span.setClassName("name");
                    span.setText(CvApplication.getFullName());
                    hl.add(span);
                    return hl;
                }))
                .setComparator(CvApplication::getFullName).setHeader("Client")
                .setAutoWidth(true)
                .setFlexGrow(1);
        ;
    }


    private void createDateColumn() {
        dateColumn = grid
                .addColumn(new LocalDateTimeRenderer<>(CvApplication::getUpdatedAt,
                        DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                .setComparator(CvApplication::getUpdatedAt).setHeader("Date")
                .setWidth("180px")
                .setAutoWidth(true)
                .setFlexGrow(1);
        ;
    }

    private void addFiltersToGrid() {
        HeaderRow filterRow = grid.appendHeaderRow();

        TextField clientFilter = new TextField();
        clientFilter.setPlaceholder("Filter");
        clientFilter.setClearButtonVisible(true);
        clientFilter.setWidth("100%");
        clientFilter.setValueChangeMode(ValueChangeMode.EAGER);
        clientFilter.addValueChangeListener(event -> gridListDataView
                .addFilter(cvApplication -> StringUtils.containsIgnoreCase(cvApplication.getFullName(), clientFilter.getValue())));
        filterRow.getCell(clientColumn).setComponent(clientFilter);

        TextField assigneeFilter = new TextField();
        assigneeFilter.setPlaceholder("Filter");
        assigneeFilter.setClearButtonVisible(true);
        assigneeFilter.setWidth("100%");
        assigneeFilter.setValueChangeMode(ValueChangeMode.EAGER);
        assigneeFilter.addValueChangeListener(event -> gridListDataView.addFilter(cvApplication -> StringUtils
                .containsIgnoreCase(cvApplication.getAssignee().getName(), assigneeFilter.getValue())));
        filterRow.getCell(assigneeColumn).setComponent(assigneeFilter);

        ComboBox<String> statusFilter = new ComboBox<>();
        statusFilter.setItems(CvApplicationStatus.getValues());
        statusFilter.setPlaceholder("Filter");
        statusFilter.setClearButtonVisible(true);
        statusFilter.setWidth("100%");
        statusFilter.addValueChangeListener(
                event -> gridListDataView.addFilter(CvApplication -> areStatusesEqual(CvApplication, statusFilter)));
        filterRow.getCell(statusColumn).setComponent(statusFilter);

        DatePicker dateFilter = new DatePicker();
        dateFilter.setPlaceholder("Filter");
        dateFilter.setClearButtonVisible(true);
        dateFilter.setWidth("100%");
        dateFilter.addValueChangeListener(
                event -> gridListDataView.addFilter(CvApplication -> areDatesEqual(CvApplication, dateFilter)));
        filterRow.getCell(dateColumn).setComponent(dateFilter);
    }

    private boolean areStatusesEqual(CvApplication cvApplication, ComboBox<String> statusFilter) {
        String statusFilterValue = statusFilter.getValue();
        if (statusFilterValue != null) {
            return StringUtils.equals(cvApplication.getStatus().toString(), statusFilterValue);
        }
        return true;
    }

    private boolean areDatesEqual(CvApplication cvApplication, DatePicker dateFilter) {
        LocalDate dateFilterValue = dateFilter.getValue();
        if (dateFilterValue != null) {
            return dateFilterValue.equals(cvApplication.getUpdatedAt().toLocalDate());
        }
        return true;
    }

    private List<CvApplication> getCvApplications() {
        return cvApplicationService.findAll();
    }
};
