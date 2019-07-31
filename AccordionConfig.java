/*
 * Copyright 2014, Productos de Harina S.A. de C.V.
 */
package com.donde.adv.web.ui.base;

import com.donde.adv.web.ui.modules.*;
import com.donde.adv.web.ui.dialogs.MsgDialog;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.vaadin.peter.contextmenu.ContextMenu;
import org.vaadin.peter.contextmenu.ContextMenu.ContextMenuItem;
import org.vaadin.peter.contextmenu.ContextMenu.ContextMenuItemClickEvent;

/**
 * Clase administradora de los items-modulos.
 *
 * @author angel.sansores
 */
public class AccordionConfig
        extends AccordionClass {

    /**
     * 2
     * UI principal.
     */
    private final UI ui;
    
    /**
     * Instancia del modulo inicial de mensajes.
     */
    private StartView startView;
    
    /**
     * Instancia del modulo de seguimiento.
     */
    private MTrackingMap trackingMap;
    
    /**
     * Uuid de sesion
     */
    private String sessionUuid;

    /**
     * Inicializa una instancia del accordion de modulos para encuestas.
     *
     * @param tabManager Administrador de tabs
     * @param ui Interfaz de usuario de vaadin.
     */
    public AccordionConfig(final TabSheet tabManager,
            final UI ui, String sessionUuid) {
        super(tabManager,
                ui);
        this.ui
                = ui;
        this.sessionUuid = sessionUuid;
        ContextMenu contextMenu
                = new ContextMenu();
        ContextMenuItem itemCloseTabs
                = contextMenu.addItem(
                        "Cerrar demás");
        ContextMenuItem itemCloseAllTabs
                = contextMenu.addItem(
                        "Cerrar todo");
        contextMenu.setAsContextMenuOf(this.getTabManager());
        itemCloseTabs.setIcon(new ThemeResource("images/ico_close_16.png"));
        itemCloseAllTabs.setIcon(new ThemeResource("images/ico_close_16.png"));
        itemCloseAllTabs.addItemClickListener((
                ContextMenuItemClickEvent event) -> {
                    Iterator<Component> iterator
                    = getTabManager().iterator();
                    List<Tab> tabstoDelete
                    = new ArrayList<>();
                    while (iterator.hasNext()) {
                        Component c
                        = (Component) iterator.next();
                        Tab tab
                        = getTabManager().getTab(c);
                        tabstoDelete.add(tab);
                    }
                    tabstoDelete.stream().forEach(t -> {
                        closeModule(t.getCaption());
                    });
                });
        itemCloseTabs.addItemClickListener((
                ContextMenuItemClickEvent event) -> {
                    Component tabSelected
                    = getTabManager().getSelectedTab();
                    Iterator<Component> iterator
                    = getTabManager().iterator();
                    List<Tab> tabstoDelete
                    = new ArrayList<>();
                    while (iterator.hasNext()) {
                        Component c
                        = (Component) iterator.next();
                        Tab tab
                        = getTabManager().getTab(c);
                        if (!c.getId().equals(tabSelected.getId())) {
                            tabstoDelete.add(tab);
                        }
                    }
                    tabstoDelete.stream().forEach(t -> {
                        closeModule(t.getCaption());
                    });
                });
        tabManager.setCloseHandler((final TabSheet tabsheet,
                final Component tabContent) -> {
                    Tab tab
                    = tabManager.getTab(tabContent);
                    closeModule(tab.getCaption());
                });
    }

    @Override
    public final void closeModule(final String moduleName) {
        if (moduleName.equals(MainUI.MODULE_TRACKING)) {
            this.closeSidebar();
        }
        super.closeModule(moduleName);
    }

    @Override
    public final void selectTab(final String itemId,
            final String surveyUuid) {
        /**
         * Verifica si esta abierto, y selecciona.
         */
        if (getTabManager().getComponentCount() > 0) {
            for (int i
                    = 0;
                    i < getTabManager().getComponentCount();
                    i++) {
                if (((VerticalLayout) getTabManager().getTab(i).getComponent())
                        .getId().equals(itemId)) {
                    closeModule(getTabManager().getTab(i).getCaption());
                }
            }
        }
        //Si no esta agregado, se agrega.
        VerticalLayout module
                = null;
        switch (itemId) {
            case MainUI.MODULE_SERVICES:
                module = new ServicesConfigModule(ui);
                break;
            case MainUI.MODULE_SCHEDULER:
                module = new TempoConfigModule(ui);
                break;
            case MainUI.MODULE_START:
                startView = new StartView(ui);
                module = startView;
                break;
            case MainUI.MODULE_NEW:
                MsgDialog msgDialog = new MsgDialog((MainUI) ui);
                ui.addWindow(msgDialog);
                break;
            case MainUI.MODULE_SEARCH:
                Notification.show("Modo Busqueda, sin implementacion.");
                break;
            case MainUI.MODULE_START_SURVEY:
                module = new SurveyInit(ui);
                break;
            case MainUI.MODULE_NEW_SURVEY:
                module = new SurveyView(ui, surveyUuid);
                break;
            case MainUI.MODULE_SEARCH_SURVEY:
                Notification.show("Modo Busqueda, sin implementacion.");
                break;
            case Naming.MODULE_ROLES:
                module = new RolesModule(ui);
                break;
            case Naming.MODULE_RESOURCES:
                module = new ResourcesModule().getMainContainer(ui);
                break;
            case Naming.MODULE_ASSIGN_ROLES_TO_USER:
                module = new RoleToUserModule(ui);
                break;
            case Naming.MODULE_CATEGORIES:
                module = new CategoriesModule().getMainContainer(ui);
                break;
            case Naming.MODULE_PERMISSIONS:
                module = new PermissionsModule().getMainContainer(ui);
                break;
            case MainUI.MODULE_TRACKING:
                trackingMap = new MTrackingMap();
                module = trackingMap.getMainContainer(ui);
                break;
            case MainUI.MODULE_INVENTORY:
                module  = new MInventory(ui);
                break;
            case MainUI.MODULE_INVENTORY_SEARCH:
                module  = new MInventoryView(ui);
                break;
            case MainUI.MODULE_ORDER:
                module = new OrderModule(ui);
                break;
            case MainUI.MODULE_PUSH_ORDER:
                module = new PushOrderModule(ui);
                break;
            case MainUI.MODULE_PUSH_ORDER_SEARCH:
                module = new PushOrderViewModule(ui);
                break;
            case MainUI.MODULE_PROMOTIONS:
                module = new PromotionsModule(ui);
                break;
            case MainUI.MODULE_PROMOTIONS_SEARCH:
                module = new PromotionsViewModule(ui, sessionUuid);
                break;
            case MainUI.MODULE_CUSTOMER_EXHIBITORS:
                module = new ExhibitorsModule(ui);
                break;
            case MainUI.MODULE_SALE_COMBO:
                module = new SaleComboModule(ui);
                break;
            case MainUI.MODULE_SALE_COMBO_VIEW:
                module = new SaleComboViewModule(ui);
                break;
            case MainUI.MODULE_COUPONS:
                module = new CouponModule(ui);
                break;
            case MainUI.MODULE_COUPONS_VIEW:
                module = new CouponViewModule(ui);
                break;
            case MainUI.MODULE_END_OF_MONTH_REPORT:
                module = new MonthlyReportModule(ui);
                break;
            case MainUI.MODULE_PACKTOOL:
                module = new PackToolsModule(ui);
                break;
            case MainUI.MODULE_END_OF_DAY_REPORT:
                module = new EndOfDayReportModule(ui);
                break;
            case MainUI.MODULE_CREATE_FREEDIS_CAMPAIGN:
                module = new FreedisCampaignModule(ui);
                break;
            case MainUI.MODULE_SEARCH_FREEDIS_CAMPAIGN:
                module = new FreedisCampaignViewModule(ui, sessionUuid);
                break;
            case MainUI.MODULE_CREDIT_VIEW:
                module  = new CreditsView(ui);
                break;
            case MainUI.MODULE_SEARCH_FREEDIS_CAMPAIGN:
                module = new FreedisCampaignViewModule(ui, sessionUuid);
                break;
            case MainUI.MODULE_CREDIT_VIEW:
                module  = new CreditsView(ui);
                break;
            default:
                Notification.show("Not module to show!");
                break;
        }
        if (module != null) {
            module.setId(itemId);
            module.setSizeFull();
            Tab newTab = getTabManager().addTab(module, itemId);
            newTab.setClosable(true);
            newTab.setDescription(itemId);
            getTabManager().setSelectedTab(newTab);
        }
    }

    /**
     * Recarga los datos del modulo de mensajes.
     */
    public final void reloadMsgs() {
        if (startView != null) {
            startView.reloadData();
        }
    }

    private void closeSidebar() {
        if (trackingMap != null) {
            trackingMap.closeSidebar();
        }
    }
}
