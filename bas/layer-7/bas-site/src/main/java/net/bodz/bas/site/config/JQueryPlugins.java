package net.bodz.bas.site.config;

import net.bodz.bas.html.artifact.IArtifact;
import net.bodz.bas.html.artifact.MutableArtifactManager;
import net.bodz.bas.site.IBasicSiteAnchors;

public class JQueryPlugins
        extends MutableArtifactManager
        implements IBasicSiteAnchors {

    public static final String BIGVIDEO = "bigvideo";
    public static final String CHOSEN = "chosen";
    public static final String BOOTSTRAP3 = "bootstrap3";
    public static final String DATATABLES = "datatables";
    public static final String DATATABLES_BOOTSTRAP = "datatables.bootstrap";
    public static final String DATATABLES_COLVIS = "datatables.colVis";
    public static final String DATATABLES_RESPONSIVE = "datatables.responsive";
    public static final String DATATABLES_TABLETOOLS = "datatables.tableTools";
    public static final String FLOT = "flot";
    public static final String FONT_AWESOME = "font-awesome";
    public static final String HANDSONTABLE = "handsontable";
    public static final String ICHECK = "icheck";
    public static final String JQUERY_MIN = "jquery-min";
    public static final String JQUERY_UI_MIN = "jquery-ui-min";
    public static final String KNOB = "knob";
    public static final String MAGNIFIC_POPUP = "magnific-popup";
    public static final String PARSLEY = "parsley";
    public static final String QTIP = "qtip";
    public static final String SELECTIZE = "selectize";
    public static final String WALLPAPER = "wallpaper";
    public static final String WYSIWYG_EDITOR = "wysiwyg-editor";

    IArtifact bootstrap3_js = javascript(BOOTSTRAP3, "3.2.0", //
            _js_.join("twitter-bootstrap3/js/bootstrap.min.js"));
    {
        IArtifact css = css(BOOTSTRAP3 + ".css", "3.2.0", //
                _js_.join("twitter-bootstrap3/css/bootstrap.min.css"));
        bootstrap3_js.addDependency(css);
    }

    IArtifact jQueryMin = javascript(JQUERY_MIN, null, //
            _js_.join("jquery/jquery.min.js"));
    IArtifact jQueryUiMin = javascript(JQUERY_UI_MIN, null, //
            _js_.join("jquery-ui/jquery-ui.min.js"));
    {
        jQueryUiMin.addDependency(jQueryMin);
    }

    IArtifact bigvideo_js = javascript(BIGVIDEO, "1.1.3", //
            _js_.join("jquery-bigvideo/lib/bigvideo.js"));
    {
        IArtifact css = css(BIGVIDEO + ".css", "1.1.3", //
                _js_.join("jquery-bigvideo/css/bigvideo.css"));
        bigvideo_js.addDependency(jQueryMin);
        bigvideo_js.addDependency(css);
    }

    IArtifact chosen = javascript(CHOSEN, "1.3.0", //
            _js_.join("jquery-chosen/chosen.jquery.min.js"));
    {
        IArtifact css = css(CHOSEN + ".css", "1.3.0", //
                _js_.join("jquery-chosen/chosen.css"));
        chosen.addDependency(jQueryMin);
        chosen.addDependency(css);
    }

    IArtifact datatables = javascript(DATATABLES, "1.10.3", //
            _js_.join("datatables/media/js/jquery.dataTables.min.js"));
    {
        IArtifact css = css(DATATABLES + ".css", "1.10.3", //
                _js_.join("datatables/media/css/jquery.dataTables.min.css"));
        datatables.addDependency(jQueryMin);
        datatables.addDependency(css);
    }

    IArtifact datatablesBootstrap = javascript(DATATABLES_BOOTSTRAP, "1.10.3", //
            _js_.join("datatables/examples/resources/bootstrap/3/dataTables.bootstrap.js"));
    {
        IArtifact css = css(DATATABLES_BOOTSTRAP + ".css", "1.10.3", //
                _js_.join("datatables/examples/resources/bootstrap/3/dataTables.bootstrap.css"));
        datatablesBootstrap.addDependency(css);
        datatablesBootstrap.addDependency(datatables);
        datatablesBootstrap.addDependency(bootstrap3_js);
    }

    IArtifact datatablesColVis = javascript(DATATABLES_COLVIS, "1.10.3", //
            _js_.join("datatables/extensions/ColVis/js/dataTables.colVis.js"));
    {
        IArtifact css = css(DATATABLES_COLVIS + ".css", "1.10.3", //
                _js_.join("datatables/extensions/ColVis/css/dataTables.colVis.css"));
        datatablesColVis.addDependency(css);
        datatablesColVis.addDependency(datatablesBootstrap);
    }

    IArtifact datatablesResponsive = javascript(DATATABLES_RESPONSIVE, "1.10.3", //
            _js_.join("datatables/extensions/Responsive/js/dataTables.responsive.js"));
    {
        IArtifact css = css(DATATABLES_RESPONSIVE + ".css", "1.10.3", //
                _js_.join("datatables/extensions/Responsive/css/dataTables.responsive.css"));
        datatablesResponsive.addDependency(css);
        datatablesResponsive.addDependency(datatablesBootstrap);
    }

    IArtifact datatablesTableTools = javascript(DATATABLES_TABLETOOLS, "1.10.3", //
            _js_.join("datatables/extensions/TableTools/js/dataTables.tableTools.js"));
    {
        IArtifact css = css(DATATABLES_TABLETOOLS + ".css", "1.10.3", //
                _js_.join("datatables/extensions/TableTools/css/dataTables.tableTools.css"));
        datatablesTableTools.addDependency(css);
        datatablesTableTools.addDependency(datatablesBootstrap);
    }

    IArtifact flot = javascript(FLOT, "0.8.2", //
            _js_.join("jquery-flot/jquery.flot.min.js"));
    {
        flot.addDependency(jQueryMin);
    }

    IArtifact fontAwesome = css(FONT_AWESOME, "4.2.0", //
            _fonts_.join("font-awesome/css/font-awesome.min.css"));
    {
        fontAwesome.addDependency(jQueryMin);
    }

    IArtifact handsontable_js = javascript(HANDSONTABLE, "0.12.2", //
            _js_.join("jquery-handsontable/dist/handsontable.full.min.js"));
    {
        IArtifact css = css(HANDSONTABLE + ".css", "0.12.2", //
                _js_.join("jquery-handsontable/dist/handsontable.full.min.css"));
        handsontable_js.addDependency(jQueryMin);
        handsontable_js.addDependency(css);
    }

    IArtifact icheck = javascript(ICHECK, "1.0.2", //
            _js_.join("jquery-icheck/icheck.min.js"));
    {
        IArtifact css = css(ICHECK + ".css", "1.0.2", //
                _js_.join("jquery-icheck/skins/all.css"));
        icheck.addDependency(jQueryMin);
        icheck.addDependency(css);
    }

    IArtifact knob = javascript(KNOB, "1.2.11", //
            _js_.join("jquery-knob/dist/jquery.knob.min.js"));
    {
        knob.addDependency(jQueryMin);
    }

    IArtifact magnificPopup = javascript(MAGNIFIC_POPUP, "1.0.0", //
            _js_.join("jquery-magnific-popup/dist/jquery.magnific-popup.min.js"));
    {
        IArtifact css = css(MAGNIFIC_POPUP + ".css", "1.0.0", //
                _js_.join("jquery-magnific-popup/dist/magnific-popup.css"));
        magnificPopup.addDependency(jQueryMin);
        magnificPopup.addDependency(css);
    }

    IArtifact parsley = javascript(PARSLEY, "2.0.6", //
            _js_.join("jquery-parsley/dist/parsley.min.js"));
    {
        IArtifact css = css(PARSLEY + ".css", "2.0.6", //
                _js_.join("jquery-parsley/src/parsley.css"));
        parsley.addDependency(jQueryMin);
        parsley.addDependency(css);
    }

    IArtifact qtip = javascript(QTIP, "2.2.1", //
            _js_.join("jquery-qtip/jquery.qtip.min.js"));
    {
        IArtifact css = css(QTIP + ".css", "2.2.1", //
                _js_.join("jquery-qtip/jquery.qtip.min.css"));
        qtip.addDependency(jQueryMin);
        qtip.addDependency(css);
    }

    IArtifact selectize = javascript(SELECTIZE, "0.11.2", //
            _js_.join("jquery-selectize/dist/js/standalone/selectize.min.js"));
    {
        IArtifact css = css(SELECTIZE + ".css", "0.11.2", //
                _js_.join("jquery-selectize/dist/css/selectize.css"));
        selectize.addDependency(jQueryMin);
        selectize.addDependency(css);
    }

    IArtifact wallpaper = javascript(WALLPAPER, "3.1.18", //
            _js_.join("jquery-wallpaper/jquery.fs.wallpaper.min.js"));
    {
        IArtifact css = css(WALLPAPER + ".css", "3.1.18", //
                _js_.join("jquery-wallpaper/jquery.fs.wallpaper.css"));
        wallpaper.addDependency(jQueryMin);
        wallpaper.addDependency(css);
    }

    IArtifact wysiwygEditor = javascript(WYSIWYG_EDITOR, "1.2.4", //
            _js_.join("jquery-wysiwyg-editor/js/froala_editor.min.js"));
    {
        IArtifact css1 = css(WYSIWYG_EDITOR + "-css1", "1.2.4", //
                _js_.join("jquery-wysiwyg-editor/css/froala_editor.min.css"));
        IArtifact css2 = css(WYSIWYG_EDITOR + "-css2", "1.2.4", //
                _js_.join("jquery-wysiwyg-editor/css/froala_style.min.css"));
        wysiwygEditor.addDependency(jQueryMin);
        wysiwygEditor.addDependency(fontAwesome);
        wysiwygEditor.addDependency(css1);
        wysiwygEditor.addDependency(css2);
    }

    public static final String ALL_DATA = "all-data";
    public static final String ALL_INPUTS = "all-inputs";
    public static final String ALL_EFFECTS = "all-effects";
    IArtifact allData = pseudo(ALL_DATA);
    {
        allData.addDependency(datatables);
        // allData.addDependency(datatablesBootstrap);
        allData.addDependency(datatablesColVis);
        // allData.addDependency(datatablesResponsive);
        allData.addDependency(datatablesTableTools);
    }
    IArtifact allInputs = pseudo(ALL_INPUTS);
    {
        allInputs.addDependency(chosen);
        allInputs.addDependency(icheck);
        allInputs.addDependency(knob);
        allInputs.addDependency(parsley);
        allInputs.addDependency(selectize);
        allInputs.addDependency(wysiwygEditor);
    }
    IArtifact allEffects = pseudo(ALL_EFFECTS);
    {
        allEffects.addDependency(flot);
        allEffects.addDependency(magnificPopup);
        allEffects.addDependency(qtip);
        allEffects.addDependency(wallpaper);
    }

}
