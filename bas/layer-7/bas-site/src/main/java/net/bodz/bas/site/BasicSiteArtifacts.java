package net.bodz.bas.site;

import net.bodz.bas.html.artifact.IArtifact;
import net.bodz.bas.html.artifact.MutableArtifactManager;

public class BasicSiteArtifacts
        extends MutableArtifactManager
        implements IBasicSiteAnchors {

    public static final String BOOTSTRAP3_CSS = "bootstrap3.css";
    public static final String BOOTSTRAP3_JS = "bootstrap3.js";
    public static final String DATATABLES_CSS = "datatables.css";
    public static final String DATATABLES_JS = "datatables.js";
    public static final String DATATABLES_BOOTSTRAP_CSS = "datatables.bootstrap.css";
    public static final String DATATABLES_BOOTSTRAP_JS = "datatables.bootstrap.js";
    public static final String DATATABLES_COLVIS_CSS = "datatables.colVis.css";
    public static final String DATATABLES_COLVIS_JS = "datatables.colVis.js";
    public static final String DATATABLES_RESPONSIVE_CSS = "datatables.responsive.css";
    public static final String DATATABLES_RESPONSIVE_JS = "datatables.responsive.js";
    public static final String DATATABLES_TABLETOOLS_CSS = "datatables.tableTools.css";
    public static final String DATATABLES_TABLETOOLS_JS = "datatables.tableTools.js";
    public static final String FONT_AWESOME = "font-awesome";
    public static final String JQUERY_MIN = "jquery-min";

    IArtifact bootstrap3_css = css(BOOTSTRAP3_CSS, "3.2.0", //
            _js_.join("twitter-bootstrap3/css/bootstrap.min.css"));

    IArtifact bootstrap3_js = javascript(BOOTSTRAP3_JS, "3.2.0", //
            _js_.join("twitter-bootstrap3/js/bootstrap.min.js"));

    IArtifact datatables_css = css(DATATABLES_CSS, "1.10.3", //
            _js_.join("datatables/media/css/jquery.dataTables.min.css"));

    IArtifact datatables_js = javascript(DATATABLES_JS, "1.10.3", //
            _js_.join("datatables/media/js/jquery.dataTables.min.js"));

    IArtifact datatablesBootstrap_css = css(DATATABLES_BOOTSTRAP_CSS, "1.10.3", //
            _js_.join("datatables/examples/resources/bootstrap/3/dataTables.bootstrap.css"));

    IArtifact datatablesBootstrap_js = javascript(DATATABLES_BOOTSTRAP_JS, "1.10.3", //
            _js_.join("datatables/examples/resources/bootstrap/3/dataTables.bootstrap.js"));

    IArtifact datatablesColVis_css = css(DATATABLES_COLVIS_CSS, "1.10.3", //
            _js_.join("datatables/extensions/ColVis/css/dataTables.colVis.css"));

    IArtifact datatablesColVis_js = javascript(DATATABLES_COLVIS_JS, "1.10.3", //
            _js_.join("datatables/extensions/ColVis/js/dataTables.colVis.js"));

    IArtifact datatablesResponsive_css = css(DATATABLES_RESPONSIVE_CSS, "1.10.3", //
            _js_.join("datatables/extensions/Responsive/css/dataTables.responsive.css"));

    IArtifact datatablesResponsive_js = javascript(DATATABLES_RESPONSIVE_JS, "1.10.3", //
            _js_.join("datatables/extensions/Responsive/js/dataTables.responsive.js"));

    IArtifact datatablesTableTools_css = css(DATATABLES_TABLETOOLS_CSS, "1.10.3", //
            _js_.join("datatables/extensions/TableTools/css/dataTables.tableTools.css"));

    IArtifact datatablesTableTools_js = javascript(DATATABLES_TABLETOOLS_JS, "1.10.3", //
            _js_.join("datatables/extensions/TableTools/js/dataTables.tableTools.js"));

    IArtifact fontAwesome = css(FONT_AWESOME, "4.2.0", //
            _fonts_.join("font-awesome/css/font-awesome.min.css"));

    IArtifact jQueryMin = javascript(JQUERY_MIN, null, //
            _js_.join("jquery/jquery.min.js"));

    public BasicSiteArtifacts() {
        bootstrap3_js.addDependency(bootstrap3_css);

        datatables_js.addDependency(datatables_css);
        datatables_js.addDependency(jQueryMin);

        datatablesBootstrap_js.addDependency(datatablesBootstrap_css);
        datatablesBootstrap_js.addDependency(datatables_js);
        datatablesBootstrap_js.addDependency(bootstrap3_js);
        datatablesBootstrap_css.addDependency(datatables_css);
        datatablesBootstrap_css.addDependency(bootstrap3_css);

        datatablesColVis_js.addDependency(datatablesColVis_css);
        datatablesColVis_js.addDependency(datatablesBootstrap_js);
        datatablesColVis_css.addDependency(datatablesBootstrap_css);

        datatablesResponsive_js.addDependency(datatablesResponsive_css);
        datatablesResponsive_js.addDependency(datatablesBootstrap_js);
        datatablesResponsive_css.addDependency(datatablesBootstrap_css);

        datatablesTableTools_js.addDependency(datatablesTableTools_css);
        datatablesTableTools_js.addDependency(datatablesBootstrap_js);
        datatablesTableTools_css.addDependency(datatablesBootstrap_css);

        fontAwesome.addDependency(jQueryMin);
    }

}
