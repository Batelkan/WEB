/*
 * Copyright 2014, Productos de Harina S.A. de C.V.
 */
package com.donde.adv.web.ui.base;

import com.donde.adv.am.api.AuthManagerService;
import com.donde.adv.tour.domain.tables.pojos.Tour;
import com.donde.adv.web.controller.Binder;
import com.donde.adv.web.ui.enums.LabelColor;
import com.vaadin.annotations.PreserveOnRefresh;
import javax.servlet.annotation.WebServlet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.LayoutEvents;
import com.vaadin.server.BootstrapFragmentResponse;
import com.vaadin.server.BootstrapListener;
import com.vaadin.server.BootstrapPageResponse;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.util.HashMap;
import javax.servlet.ServletException;
import org.jsoup.nodes.Element;
import org.vaadin.addon.leaflet.LLayerGroup;

/**
 * UI principal del portal.
 *
 * @author angel.sansores
 */
@Theme("adv")
@PreserveOnRefresh
public class MainUI extends UI {

    /**
     * Administrador de tabs.
     */
    private final TabSheet tabManager = new TabSheet();

    /**
     * Window de login.
     */
    private LoginForm windowLogin;

    /**
     * Objetos de autorizacion
     */
    public static final String AUTH_ACTION_VISUALIZE = "Visualizacion";
    public static final String AUTH_ACTION_CREATE = "Creacion";
    public static final String AUTH_ACTION_DELETE = "Eliminacion";
    public static final String AUTH_ACTION_UPDATE = "Modificacion";

    public static final String AUTH_CATEGORY = "servidor";
    public static final String AUTH_ATTR_CENTRAL = "central";
    public static final String AUTH_ATTR_SATELLITE = "satellite";

    /**
     * Autorizaciones a modulos
     */
    public static final String AUTH_MODULE_CONFIG_RESOURCE = "ADVWeb_Configuracion";
    public static final String AUTH_MODULE_AM_RESOURCE = "ADVWeb_Permisos";
    public static final String AUTH_MODULE_PROMOTIONS_RESOURCE = "ADVWeb_Promociones";
    public static final String AUTH_MODULE_COMBOS_RESOURCE = "ADVWeb_Combos";
    public static final String AUTH_MODULE_COUPONS_RESOURCE = "ADVWeb_Cupones";
    public static final String AUTH_MODULE_FREEDIS_RESOURCE = "ADVWeb_Freedis";

    /**
     * Nombre del grupo de modulo configuraciones.
     */
    private static final String GROUP_MODULE_CONFIG = "Configuración";

    /**
     * Nombre del grupo de modulo mensajes.
     */
    private static final String GROUP_MODULE_MSG = "Mensajes";

    /**
     * Nombre del grupo de modulo configuraciones.
     */
    private static final String GROUP_MODULE_AM = "Permisos";

    /**
     * Nombre del grupo de modulo encuestas.
     */
    private static final String GROUP_MODULE_SURVEY = "Encuestas";

    /**
     * Nombre del grupo de modulo seguimiento.
     */
    private static final String GROUP_MODULE_TRACKING = "Seguimiento";

    /**
     * Nombre del grupo de modulo Inventario.
     */
    private static final String GROUP_MODULE_INVENTORY = "Inventario";

    /**
     * Nombre del grupo de modulo Pedidos.
     */
    private static final String GROUP_MODULE_ORDER = "Pedidos";

    /**
     * Nombre del grupo de modulo Promociones.
     */
    private static final String GROUP_MODULE_PROMOTIONS = "Promociones";

    /**
     * Nombre de módulo de exhibidores.
     */
    private static final String GROUP_MODULE_EXHIBITORS = "Exhibidores";

    /**
     * Nombre del grupo de Combo de ventas.
     */
    private static final String GROUP_MODULE_COMBOS = "Combo Ventas";

    /**
     * Nombre del grupo de Cupones.
     */
    private static final String GROUP_MODULE_COUPONS = "Cupones";

    /**
     * Nombre del grupo de reportes del dia.
     */
    private static final String GROUP_MODULE_REPORTS = "Reportes";

     /**
     * Nombre del grupo de creditos
     */
    private static final String GROUP_MODULE_CREDITS = "Creditos";

    
    /**
     * Nombre del modulo de Inicio.
     */
    public static final String MODULE_SERVICES = "Servicios";

    /**
     * Nombre del modulo de Encuesta.
     */
    public static final String MODULE_SCHEDULER = "Temporizador";

    /**
     * Nombre del modulo de Inicio.
     */
    public static final String MODULE_START = "Mensajes";

    /**
     * Nombre del modulo de Encuesta.
     */
    public static final String MODULE_NEW = "Nuevo mensaje";

    /**
     * Nombre del modulo de Busqueda.
     */
    public static final String MODULE_SEARCH = "Buscar mensajes";

    /**
     * Nombre del modulo de Inicio.
     */
    public static final String MODULE_START_SURVEY = "Encuestas";
     /* Nombre del modulo de Encuesta.
     */
    public static final String MODULE_NEW_SURVEY = "Nueva encuesta";

    /**
     * Nombre del modulo de Busqueda.
     */
    public static final String MODULE_SEARCH_SURVEY = "Buscar encuestas";

    /**
     * Nombre del modulo de Seguimiento.
     */
    public static final String MODULE_TRACKING = "Tracking";

    /**
     * Nombre del modulo de inventario de salida.
     */
    public static final String MODULE_INVENTORY = "Inventario";

    /**
     * Nombre del modulo de Busqueda.
     */
    public static final String MODULE_INVENTORY_SEARCH = "Buscar inventarios";

    /**
     * Nombre del modulo de Pedidos.
     */
    public static final String MODULE_ORDER = "Pedido Agente";

    /**
     * Nombre del modulo de Pedidos Push.
     */
    public static final String MODULE_PUSH_ORDER = "Pedido Automático";

    /**
     * Nombre del modulo de Seguimiento de Pedidos Push.
     */
    public static final String MODULE_PUSH_ORDER_SEARCH = "Buscar Pedidos Automáticos";

    /**
     * Nombre del modulo de promociones.
     */
    public static final String MODULE_PROMOTIONS = "Promociones";

    /**
     * Nombre del modulo de Busqueda.
     */
    public static final String MODULE_PROMOTIONS_SEARCH = "Buscar Promociones";

    /**
     * Nombre de módulo de exhibidores.
     */
    public static final String MODULE_CUSTOMER_EXHIBITORS = "Consultar Exhibidores por cliente";

    /**
     * Nombre de modulos de mantenimiento fuera de linea
     */
    public static final String GROUP_MODULE_OFFLINE = "Fuera de línea";

    /**
     * Nombre de módulo de paquetes de descarga.
     */
    public static final String MODULE_PACKTOOL = "Descarga de paquete";

    /**
     * Nombre de modulo de combo de ventas
     */
    public static final String MODULE_SALE_COMBO = "Combo ventas";

    /**
     * Nombre de modulo de visualización de combos
     */
    public static final String MODULE_SALE_COMBO_VIEW = "Ver Combos";

    /**
     * Nombre de modulo de cupones
     */
    public static final String MODULE_COUPONS = "Alta cupones";

    /**
     * Nombre de modulo de visualización de cupones
     */
    public static final String MODULE_COUPONS_VIEW = "Ver cupones";

    /**
     * Nombre del modulo de reportes del dia
     */
    public static final String MODULE_END_OF_DAY_REPORT = "Generar reportes del dia";

    /**
     * Nombre de modulo de reportes del fin de mes
     */
    public static final String MODULE_END_OF_MONTH_REPORT = "Generar reporte de fin de mes";

    /**
     * Nombre de modulo de administración de freedis.
     */
    public static final String GROUP_FREEDIS = "Freedis";

    /**
     * Nombre de modulo de administración de freedis.
     */
    public static final String MODULE_CREATE_FREEDIS_CAMPAIGN = "Crear Freedis";

    /**
     * Nombre de modulo de administración de freedis.
     */
    public static final String MODULE_SEARCH_FREEDIS_CAMPAIGN = "Buscar Freedis";
    
    /**
     * Nombre de modulo de Solicitud de creditos.
     */
    public static final String MODULE_CREDIT_VIEW = "Solicitud Creditos";
    

    /**
     * Manejador de modulos.
     */
    private AccordionConfig accordion;

    /**
     * Mapa de traking.
     */
    private HashMap<String, Tour> mapTracking = new HashMap<>();

    /**
     * Mapa de capas.
     */
    private HashMap<String, LLayerGroup> mapLayers = new HashMap<>();

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = true,
            ui = MainUI.class,
            widgetset = "com.donde.adv.web.ui.base.AppWidgetSet")
    public static class Servlet
            extends VaadinServlet {

        @Override
        protected void servletInitialized()
                throws ServletException {
            super.servletInitialized();
            getService().addSessionInitListener((
                    SessionInitEvent event) -> {
                event.getSession().addBootstrapListener(
                        new BootstrapListener() {
                    @Override
                    public void modifyBootstrapFragment(
                            BootstrapFragmentResponse response) {
                    }

                    @Override
                    public void modifyBootstrapPage(
                            BootstrapPageResponse response) {
                        Element script
                                = response.getDocument().createElement(
                                        "script");
                        script.attr("src",
                                "http://maps.google."
                                + "com/maps/api/js?sensor=false");
                        response.getDocument().getElementsByTag(
                                "head").get(0).appendChild(
                                script);
                    }
                });
            });
        }
    }

    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("Portal Web de ADV");
        CallBack callBack
                = (String itemId,
                        Object value) -> {
                    switch (itemId) {
                        case LoginForm.SESSION_STARTED:
                            removeWindow(windowLogin);
                            buildMainContain();
                            break;
                        case LoginForm.SESSION_CLOSED:
                            closeModule(MODULE_TRACKING);
                            mapTracking
                            = new HashMap<>();
                            setContent(null);
                            addWindow(windowLogin);
                            break;
                    }
                };
        windowLogin
                = new LoginForm(this,
                        callBack);
        addWindow(windowLogin);
    }

    /**
     * Construye la estructura principal de modulos.
     */
    private void buildMainContain() {
        /**
         * Obtener token de sesion y uuid de sesion
         */
        String token = getToken();
        String sessionUuid = "";
        if (token.split(":").length == 3) {
            sessionUuid = token.split(":")[2];
        }

        VerticalLayout mainPanel = new VerticalLayout();
        mainPanel.setSizeFull();
        tabManager.setSizeFull();
        HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
        HorizontalLayout top = new HorizontalLayout();
        HorizontalLayout center = new HorizontalLayout();
        HorizontalLayout bottom = new HorizontalLayout();
        top.setWidth("100%");
        top.setHeight("70px");
        Label title = new Label("Portal Web ADV");
        title.addStyleName(ValoTheme.LABEL_H1);
        title.addStyleName("adv-app-title");
        top.addComponent(title);
        top.addStyleName("adv_top_layout");
        HorizontalLayout hUser = new HorizontalLayout();
        Label lblWelcome = new Label("Bienvenido");
        lblWelcome.addStyleName("adv-welcome-label");
        Label lblUser = new Label(windowLogin.getUsername());
        lblUser.addStyleName("adv-user-label");
        hUser.addComponent(lblWelcome);
        hUser.addComponent(lblUser);
        hUser.setComponentAlignment(lblWelcome,
                Alignment.MIDDLE_CENTER);
        hUser.setComponentAlignment(lblUser,
                Alignment.MIDDLE_CENTER);
        ActionLabel closeSession
                = new ActionLabel("Cerrar sesión",
                        LabelColor.RED);
        closeSession.addLayoutClickListener(
                (LayoutEvents.LayoutClickEvent event) -> {
                    windowLogin.closeSession();
                });
        HorizontalLayout layoutSession = new HorizontalLayout();
        layoutSession.addComponent(hUser);
        layoutSession.addComponent(closeSession);
        layoutSession.setComponentAlignment(hUser, Alignment.MIDDLE_CENTER);
        layoutSession.setComponentAlignment(closeSession, Alignment.MIDDLE_CENTER);
        top.addComponent(title);

        top.setComponentAlignment(title, Alignment.MIDDLE_CENTER);

        top.addComponent(layoutSession);

        top.setComponentAlignment(layoutSession, Alignment.MIDDLE_RIGHT);
        bottom.setWidth("100%");
        bottom.setHeight("40px");
        top.addStyleName("adv_top_layout");
        bottom.addStyleName("adv_bottom_layout");
        center.addStyleName("adv_center_layout");
        center.setSizeFull();
        splitPanel.setSplitPosition(7);
        accordion = new AccordionConfig(tabManager, this, sessionUuid);
        splitPanel.setFirstComponent(accordion);

        Boolean isCentral = Binder.isCentral();
        String satelliteCenter = Binder.getCenter();

        AuthManagerService ams
                = Binder.getAmService(AuthManagerService.class);

        if (isCentral) {

            /**
             * AUTH_CHECK: Verificar que se tengan permisos de configuracion
             */
            if (ams.checkAuthorization(sessionUuid, AUTH_MODULE_CONFIG_RESOURCE, AUTH_ACTION_VISUALIZE, AUTH_CATEGORY, AUTH_ATTR_CENTRAL)) {
                accordion.addTab(GROUP_MODULE_CONFIG);
                accordion.addModule(GROUP_MODULE_CONFIG, MODULE_SERVICES, "adv_services");
                accordion.addModule(GROUP_MODULE_CONFIG, MODULE_SCHEDULER, "adv_timer");
            }

            accordion.addTab(GROUP_MODULE_MSG);
            accordion.addModule(GROUP_MODULE_MSG, MODULE_START, "adv_home");
            accordion.addModule(GROUP_MODULE_MSG, MODULE_NEW, "adv_new");
            accordion.addModule(GROUP_MODULE_MSG, MODULE_SEARCH, "adv_search");

            accordion.addTab(GROUP_MODULE_SURVEY);
            accordion.addModule(GROUP_MODULE_SURVEY, MODULE_START_SURVEY, "adv_home");
            accordion.addModule(GROUP_MODULE_SURVEY, MODULE_NEW_SURVEY, "adv_new_survey");
            accordion.addModule(GROUP_MODULE_SURVEY, MODULE_SEARCH_SURVEY, "adv_search");

            /**
             * AUTH_CHECK: Verificar permisos para paneles de permisos
             */
            if (ams.checkAuthorization(sessionUuid, AUTH_MODULE_AM_RESOURCE, AUTH_ACTION_VISUALIZE, AUTH_CATEGORY, AUTH_ATTR_CENTRAL)) {
                accordion.addTab(GROUP_MODULE_AM);
                accordion.addModule(GROUP_MODULE_AM, Naming.MODULE_ROLES, "adv_roles");
                accordion.addModule(GROUP_MODULE_AM, Naming.MODULE_RESOURCES, "adv_resources");
                accordion.addModule(GROUP_MODULE_AM, Naming.MODULE_ASSIGN_ROLES_TO_USER, "adv_roles_to_user");
                accordion.addModule(GROUP_MODULE_AM, Naming.MODULE_CATEGORIES, "adv_categories");
                accordion.addModule(GROUP_MODULE_AM, Naming.MODULE_PERMISSIONS, "adv_permissions");
            }

            accordion.addTab(GROUP_MODULE_ORDER);
            accordion.addModule(GROUP_MODULE_ORDER, MODULE_PUSH_ORDER, "adv_home");
            accordion.addModule(GROUP_MODULE_ORDER, MODULE_PUSH_ORDER_SEARCH, "adv_search");

            /**
             * AUTH_CHECK: Verificar permisos para paneles de promociones
             */
            if (ams.checkAuthorization(sessionUuid, AUTH_MODULE_PROMOTIONS_RESOURCE, AUTH_ACTION_VISUALIZE, AUTH_CATEGORY, AUTH_ATTR_CENTRAL)) {
                accordion.addTab(GROUP_MODULE_PROMOTIONS);
                /**
                 * AUTH_CHECK: Verificar permisos de creacion
                 */
                if (ams.checkAuthorization(sessionUuid, AUTH_MODULE_PROMOTIONS_RESOURCE, AUTH_ACTION_CREATE, AUTH_CATEGORY, AUTH_ATTR_CENTRAL)) {
                    accordion.addModule(GROUP_MODULE_PROMOTIONS, MODULE_PROMOTIONS, "adv_home");
                }
                accordion.addModule(GROUP_MODULE_PROMOTIONS, MODULE_PROMOTIONS_SEARCH, "adv_search");
            }

            /**
             * AUTH_CHECK: Verificar permisos para paneles de combos
             */
            if (ams.checkAuthorization(sessionUuid, AUTH_MODULE_COMBOS_RESOURCE, AUTH_ACTION_VISUALIZE, AUTH_CATEGORY, AUTH_ATTR_CENTRAL)) {
                accordion.addTab(GROUP_MODULE_COMBOS);
                /**
                 * AUTH_CHECK: Verificar permisos de creacion de combos
                 */
                if (ams.checkAuthorization(sessionUuid, AUTH_MODULE_COMBOS_RESOURCE, AUTH_ACTION_CREATE, AUTH_CATEGORY, AUTH_ATTR_CENTRAL)) {
                    accordion.addModule(GROUP_MODULE_COMBOS, MODULE_SALE_COMBO, "adv_home");
                }
                accordion.addModule(GROUP_MODULE_COMBOS, MODULE_SALE_COMBO_VIEW, "adv_search");
            }

            /**
             * AUTH_CHECK: Verificar permisos para paneles de cupones
             */
            if (ams.checkAuthorization(sessionUuid, AUTH_MODULE_COUPONS_RESOURCE, AUTH_ACTION_VISUALIZE, AUTH_CATEGORY, AUTH_ATTR_CENTRAL)) {
                accordion.addTab(GROUP_MODULE_COUPONS);
                /**
                 * AUTH_CHECK: Verificar permisos de creacion
                 */
                if (ams.checkAuthorization(sessionUuid, AUTH_MODULE_COUPONS_RESOURCE, AUTH_ACTION_CREATE, AUTH_CATEGORY, AUTH_ATTR_CENTRAL)) {
                    accordion.addModule(GROUP_MODULE_COUPONS, MODULE_COUPONS, "adv_home");
                }
                accordion.addModule(GROUP_MODULE_COUPONS, MODULE_COUPONS_VIEW, "adv_search");
            }

            accordion.addTab(GROUP_MODULE_REPORTS);
            accordion.addModule(GROUP_MODULE_REPORTS, MODULE_END_OF_MONTH_REPORT, "adv_report");

            /**
             * AUTH_CHECK: Verificar permisos para paneles de freedis
             */
            if (ams.checkAuthorization(sessionUuid, AUTH_MODULE_FREEDIS_RESOURCE, AUTH_ACTION_VISUALIZE, AUTH_CATEGORY, AUTH_ATTR_CENTRAL)) {
                accordion.addTab(GROUP_FREEDIS);
                /**
                 * AUTH_CHECK: Verificar permisos de creacion
                 */
                if (ams.checkAuthorization(sessionUuid, AUTH_MODULE_FREEDIS_RESOURCE, AUTH_ACTION_CREATE, AUTH_CATEGORY, AUTH_ATTR_CENTRAL)) {
                    accordion.addModule(GROUP_FREEDIS, MODULE_CREATE_FREEDIS_CAMPAIGN, "adv_new");
                }
                accordion.addModule(GROUP_FREEDIS, MODULE_SEARCH_FREEDIS_CAMPAIGN, "adv_search");
            }
        } else {
            accordion.addTab(GROUP_MODULE_OFFLINE);
            accordion.addModule(GROUP_MODULE_OFFLINE, MODULE_PACKTOOL, "adv_packtool");

            if (satelliteCenter.trim().equals("BP01")) {
                accordion.addTab(GROUP_MODULE_REPORTS);
                accordion.addModule(GROUP_MODULE_REPORTS, MODULE_END_OF_DAY_REPORT, "adv_report");
            }

            accordion.addTab(GROUP_MODULE_ORDER);
            accordion.addModule(GROUP_MODULE_ORDER, MODULE_ORDER, "adv_home");
        }

        accordion.addTab(GROUP_MODULE_EXHIBITORS);
        accordion.addModule(GROUP_MODULE_EXHIBITORS, MODULE_CUSTOMER_EXHIBITORS, "adv_home");

        accordion.addTab(GROUP_MODULE_INVENTORY);
        accordion.addModule(GROUP_MODULE_INVENTORY, MODULE_INVENTORY, "adv_home");
        accordion.addModule(GROUP_MODULE_INVENTORY, MODULE_INVENTORY_SEARCH, "adv_search");

        accordion.addTab(GROUP_MODULE_TRACKING);
        accordion.addModule(GROUP_MODULE_TRACKING, MODULE_TRACKING, "adv_categories");
        
        accordion.addTab(GROUP_MODULE_TRACKING);
        accordion.addModule(GROUP_MODULE_TRACKING, MODULE_TRACKING, "adv_categories");
     
        accordion.addTab(GROUP_MODULE_CREDITS);
        accordion.addModule(GROUP_MODULE_CREDITS, MODULE_CREDIT_VIEW, "adv_credits");

      
        
        
        if (isCentral) {
            this.selectModule(MODULE_TRACKING, null);
        } else {
            this.selectModule(MODULE_INVENTORY, null);
        }
        splitPanel.setFirstComponent(accordion);
        mainPanel.addComponent(top);
        mainPanel.addComponent(center);
        mainPanel.setExpandRatio(center, 1);
        center.addComponent(tabManager);
        splitPanel.addComponent(mainPanel);
        setContent(splitPanel);
    }

    /**
     * Cierra un modulo.
     *
     * @param moduleName Nombre del modulo a cerrar.
     */
    public final void closeModule(final String moduleName) {
        accordion.closeModule(moduleName);
    }

    /**
     * Selecciona un modulo.
     *
     * @param moduleName Nombre del modulo
     * @param surveyUuid Identificador de encuesta.
     */
    public final void selectModule(final String moduleName,
            final String surveyUuid) {
        this.accordion.selectModule(moduleName,
                surveyUuid);
    }

    /**
     * Devuelve el nombre del usuario que ha iniciado sesion.
     *
     * @return Nombre de usuario.
     */
    public final String getUsername() {
        return windowLogin.getUsername();
    }

    public final String getToken() {
        return windowLogin.getToken();
    }

    /**
     * Recarga el modulo de mensajes.
     */
    public final void reloadMsgs() {
        this.accordion.reloadMsgs();
    }

    /**
     * Devuelve el mapa de tracking.
     *
     * @return Mapa de tracking.
     */
    public HashMap<String, Tour> getMapTracking() {
        return mapTracking;
    }

    /**
     * Devuelve el mapa de capas.
     *
     * @return Mapa de capas.
     */
    public HashMap<String, LLayerGroup> getMapLayers() {
        return mapLayers;
    }
}
