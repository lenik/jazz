package net.bodz.bas.site.config;

import net.bodz.bas.html.artifact.IArtifact;
import net.bodz.bas.html.artifact.MutableArtifactManager;
import net.bodz.bas.site.IBasicSiteAnchors;

public class JQueryPlugins
        extends MutableArtifactManager
        implements IBasicSiteAnchors {

    public static final String BIGVIDEO = "bigvideo";
    public static final String BOOTSTRAP3 = "bootstrap3";
    public static final String CHOSEN = "chosen";
    public static final String CROPPER = "cropper";
    public static final String DATATABLES = "datatables";
    public static final String DATATABLES_BOOTSTRAP = "datatables.bootstrap";
    public static final String DATATABLES_COLVIS = "datatables.colVis";
    public static final String DATATABLES_RESPONSIVE = "datatables.responsive";
    public static final String DATATABLES_TABLETOOLS = "datatables.tableTools";
    public static final String FILE_UPLOAD = "file-upload";
    public static final String FLOT = "flot";
    public static final String FOCUSPOINT = "focuspoint";
    public static final String FONT_AWESOME = "font-awesome";
    public static final String HANDSONTABLE = "handsontable";
    public static final String ICHECK = "icheck";
    public static final String JQUERY_MIN = "jquery-min";
    public static final String JQUERY_UI_MIN = "jquery-ui-min";
    public static final String JQUERY_UI_WIDGET_MIN = "jquery-ui-widget-min";
    public static final String KNOB = "knob";
    public static final String MAGNIFIC_POPUP = "magnific-popup";
    public static final String PARSLEY = "parsley";
    public static final String QRCODE = "qrcode";
    public static final String QTIP = "qtip";
    public static final String SELECTIZE = "selectize";
    public static final String TAGSINPUT = "tagsinput";
    public static final String TYPEAHEAD = "typeahead";
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
    IArtifact jQueryUiWidgetMin = javascript(JQUERY_UI_WIDGET_MIN, null, //
            _js_.join("jquery-ui/ui/jquery.ui.widget.min.js"));
    {
        jQueryUiMin.addDependency(jQueryMin);
        jQueryUiWidgetMin.addDependency(jQueryMin);
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

    IArtifact cropper = javascript(CROPPER, "0.9.1", //
            _js_.join("jquery-cropper/dist/cropper.min.js"));
    {
        IArtifact css = css(CROPPER + ".css", "0.9.1", //
                _js_.join("jquery-cropper/dist/cropper.min.css"));
        cropper.addDependency(jQueryMin);
        cropper.addDependency(css);
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

    IArtifact fileUpload = javascript(FILE_UPLOAD, "9.8.1", //
            _js_.join("jquery-file-upload/js/jquery.fileupload.js"));
    {
        IArtifact iframeTransport = javascript(FILE_UPLOAD + "-iframe", "9.8.1", //
                _js_.join("jquery-file-upload/js/jquery.iframe-transport.js"));

        IArtifact css = css(FILE_UPLOAD + ".css", "9.8.1", //
                _js_.join("jquery-file-upload/css/jquery.fileupload.css"));

        fileUpload.addDependency(jQueryMin);
        fileUpload.addDependency(jQueryUiWidgetMin);
        fileUpload.addDependency(iframeTransport);

        // IArtifact process = javascript(FILE_UPLOAD + "-process", "9.8.1", //
        // _js_.join("jquery-file-upload/js/jquery.fileupload-process.js"));
        // fileUpload.addDependency(process);
        fileUpload.addDependency(css);
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

    IArtifact focuspoint = javascript(FOCUSPOINT, "1.1.1", //
            _js_.join("jquery-focuspoint/js/jquery.focuspoint.min.js"));
    {
        IArtifact css = css(FOCUSPOINT + ".css", "1.1.1", //
                _js_.join("jquery-focuspoint/css/focuspoint.css"));
        focuspoint.addDependency(jQueryMin);
        focuspoint.addDependency(css);
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

    IArtifact qrcode = javascript(QRCODE, "0.11.0", //
            _js_.join("jquery-qrcode/dist/jquery.qrcode.min.js"));
    {
        qrcode.addDependency(jQueryMin);
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

    IArtifact tagsinput = javascript(TAGSINPUT + "-base", "0.5.0", //
            _js_.join("bootstrap-tagsinput/dist/bootstrap-tagsinput.min.js"));
    {
        IArtifact css = css(SELECTIZE + ".css", "0.5.0", //
                _js_.join("bootstrap-tagsinput/dist/bootstrap-tagsinput.css"));
        tagsinput.addDependency(jQueryMin);
        tagsinput.addDependency(css);
    }

    IArtifact typeahead = javascript(TYPEAHEAD, "0.10.5", //
            _js_.join("twitter-typeahead/dist/typeahead.bundle.min.js"));
    {
        typeahead.addDependency(jQueryMin);
        tagsinput.addDependency(typeahead);
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
        allInputs.addDependency(fileUpload);
        allInputs.addDependency(icheck);
        allInputs.addDependency(knob);
        allInputs.addDependency(parsley);
        allInputs.addDependency(selectize);
        allInputs.addDependency(tagsinput);
        allInputs.addDependency(typeahead);
        allInputs.addDependency(wysiwygEditor);
    }
    IArtifact allEffects = pseudo(ALL_EFFECTS);
    {
        allEffects.addDependency(cropper);
        allEffects.addDependency(flot);
        allEffects.addDependency(focuspoint);
        allEffects.addDependency(magnificPopup);
        allEffects.addDependency(qrcode);
        allEffects.addDependency(qtip);
        allEffects.addDependency(wallpaper);
    }

}
