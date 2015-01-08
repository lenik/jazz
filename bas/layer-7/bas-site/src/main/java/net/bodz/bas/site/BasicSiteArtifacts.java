package net.bodz.bas.site;

import net.bodz.bas.html.artifact.IArtifact;
import net.bodz.bas.html.artifact.MutableArtifactManager;

public class BasicSiteArtifacts
        extends MutableArtifactManager
        implements IBasicSiteAnchors {

    public static final String BIGVIDEO_CSS = "bigvideo.css";
    public static final String BIGVIDEO_JS = "bigvideo.js";
    public static final String CHOSEN_CSS = "chosen.css";
    public static final String CHOSEN_JS = "chosen.js";
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
    public static final String HANDSONTABLE_CSS = "handsontable.css";
    public static final String HANDSONTABLE_JS = "handsontable.js";
    public static final String ICHECK_CSS = "icheck.css";
    public static final String ICHECK_JS = "icheck.js";
    public static final String JQUERY_MIN = "jquery-min";
    public static final String JQUERY_UI_MIN = "jquery-ui-min";
    public static final String KNOB_JS = "knob.js";
    public static final String MAGNIFIC_POPUP_CSS = "magnific-popup.css";
    public static final String MAGNIFIC_POPUP_JS = "magnific-popup.js";
    public static final String PARSLEY_CSS = "parsley.css";
    public static final String PARSLEY_JS = "parsley.js";
    public static final String SELECTIZE_CSS = "selectize.css";
    public static final String SELECTIZE_JS = "selectize.js";
    public static final String WALLPAPER_CSS = "wallpaper.css";
    public static final String WALLPAPER_JS = "wallpaper.js";

    IArtifact bootstrap3_css = css(BOOTSTRAP3_CSS, "3.2.0", //
            _js_.join("twitter-bootstrap3/css/bootstrap.min.css"));
    IArtifact bootstrap3_js = javascript(BOOTSTRAP3_JS, "3.2.0", //
            _js_.join("twitter-bootstrap3/js/bootstrap.min.js"));

    {
        bootstrap3_js.addDependency(bootstrap3_css);
    }

    IArtifact jQueryMin = javascript(JQUERY_MIN, null, //
            _js_.join("jquery/jquery.min.js"));
    IArtifact jQueryUiMin = javascript(JQUERY_UI_MIN, null, //
            _js_.join("jquery-ui/jquery-ui.min.js"));
    {
        jQueryUiMin.addDependency(jQueryMin);
    }

    IArtifact bigvideo_css = css(BIGVIDEO_CSS, "1.1.3", //
            _js_.join("jquery-bigvideo/css/bigvideo.css"));
    IArtifact bigvideo_js = javascript(BIGVIDEO_JS, "1.1.3", //
            _js_.join("jquery-bigvideo/lib/bigvideo.js"));
    {
        bigvideo_js.addDependency(jQueryMin);
        bigvideo_js.addDependency(bigvideo_css);
    }

    IArtifact chosen_css = css(CHOSEN_CSS, "1.3.0", //
            _js_.join("jquery-chosen/chosen.css"));
    IArtifact chosen_js = javascript(CHOSEN_JS, "1.3.0", //
            _js_.join("jquery-chosen/chosen.jquery.min.js"));
    {
        chosen_js.addDependency(jQueryMin);
        chosen_js.addDependency(chosen_css);
    }

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

    {
        datatables_js.addDependency(jQueryMin);
        datatables_js.addDependency(datatables_css);

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
    }

    IArtifact fontAwesome = css(FONT_AWESOME, "4.2.0", //
            _fonts_.join("font-awesome/css/font-awesome.min.css"));
    {
        fontAwesome.addDependency(jQueryMin);
    }

    IArtifact handsontable_css = css(HANDSONTABLE_CSS, "0.12.2", //
            _js_.join("jquery-handsontable/dist/handsontable.full.min.css"));
    IArtifact handsontable_js = javascript(HANDSONTABLE_JS, "0.12.2", //
            _js_.join("jquery-handsontable/dist/handsontable.full.min.js"));
    {
        handsontable_js.addDependency(jQueryMin);
        handsontable_js.addDependency(handsontable_css);
    }

    IArtifact icheck_css = css(ICHECK_CSS, "1.0.2", //
            _js_.join("jquery-icheck/skins/all.css"));
    IArtifact icheck_js = javascript(ICHECK_JS, "1.0.2", //
            _js_.join("jquery-icheck/icheck.min.js"));
    {
        icheck_js.addDependency(jQueryMin);
        icheck_js.addDependency(icheck_css);
    }

    IArtifact knob_js = javascript(KNOB_JS, "1.2.11", //
            _js_.join("jquery-knob/dist/jquery.knob.min.js"));
    {
        knob_js.addDependency(jQueryMin);
    }

    IArtifact magnificPopup_css = css(MAGNIFIC_POPUP_CSS, "1.0.0", //
            _js_.join("jquery-magnific-popup/dist/magnific-popup.css"));
    IArtifact magnificPopup_js = javascript(MAGNIFIC_POPUP_JS, "1.0.0", //
            _js_.join("jquery-magnific-popup/dist/jquery.magnific-popup.min.js"));
    {
        magnificPopup_js.addDependency(jQueryMin);
        magnificPopup_js.addDependency(magnificPopup_css);
    }

    IArtifact parsley_css = css(PARSLEY_CSS, "2.0.6", //
            _js_.join("jquery-parsley/src/parsley.css"));
    IArtifact parsley_js = javascript(PARSLEY_JS, "2.0.6", //
            _js_.join("jquery-parsley/dist/parsley.min.js"));
    {
        parsley_js.addDependency(jQueryMin);
        parsley_js.addDependency(parsley_css);
    }

    IArtifact selectize_css = css(SELECTIZE_CSS, "0.11.2", //
            _js_.join("jquery-selectize/dist/css/selectize.css"));
    IArtifact selectize_js = javascript(SELECTIZE_JS, "0.11.2", //
            _js_.join("jquery-selectize/dist/js/standalone/selectize.min.js"));
    {
        selectize_js.addDependency(jQueryMin);
        selectize_js.addDependency(selectize_css);
    }

    IArtifact wallpaper_css = css(WALLPAPER_CSS, "3.1.18", //
            _js_.join("jquery-wallpaper/jquery.fs.wallpaper.css"));
    IArtifact wallpaper_js = javascript(WALLPAPER_JS, "3.1.18", //
            _js_.join("jquery-wallpaper/jquery.fs.wallpaper.min.js"));
    {
        wallpaper_js.addDependency(jQueryMin);
        wallpaper_js.addDependency(wallpaper_css);
    }

    public static final String ALL_INPUTS = "all-inputs";
    public static final String ALL_EFFECTS = "all-effects";
    IArtifact allInputs = pseudo(ALL_INPUTS);
    {
        allInputs.addDependency(chosen_js);
        allInputs.addDependency(icheck_js);
        allInputs.addDependency(knob_js);
        allInputs.addDependency(parsley_js);
        allInputs.addDependency(selectize_js);
    }
    IArtifact allEffects = pseudo(ALL_EFFECTS);
    {
        allEffects.addDependency(magnificPopup_js);
    }

}
