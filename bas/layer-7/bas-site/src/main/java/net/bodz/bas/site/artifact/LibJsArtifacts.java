package net.bodz.bas.site.artifact;

import net.bodz.bas.html.artifact.IArtifact;
import net.bodz.bas.html.artifact.MutableArtifactManager;
import net.bodz.bas.site.IBasicSiteAnchors;

public class LibJsArtifacts
        extends MutableArtifactManager
        implements IBasicSiteAnchors {

    public static final String JQUERY_MIN = "jquery-min";
    public static final String JQUERY_UI_MIN = "jquery-ui-min";
    public static final String JQUERY_UI_WIDGET_MIN = "jquery-ui-widget-min";

    IArtifact bootstrap3_js = group("bootstrap3", _js_.join("twitter-bootstrap3/"), //
            "css/bootstrap.min.css", "js/bootstrap.min.js");

    IArtifact jQueryMin = javascript(JQUERY_MIN, null, //
            _js_.join("jquery/jquery.min.js"));
    IArtifact jQueryUiMin = javascript(JQUERY_UI_MIN, null, //
            _js_.join("jquery-ui/jquery-ui.min.js"));
    IArtifact jQueryUiWidgetMin = javascript(JQUERY_UI_WIDGET_MIN, null, //
            _js_.join("jquery-ui/ui/jquery.ui.widget.min.js"));
    {
        jQueryUiMin.addDependency(jQueryMin);
        jQueryUiWidgetMin.addDependency(jQueryUiMin);
    }

    IArtifact bigvideo_js = group("bigvideo", _js_.join("jquery-bigvideo/"), //
            "css/bigvideo.css", "lib/bigvideo.js")//
            .addDependency(jQueryMin);

    IArtifact chosen = group("chosen", _js_.join("jquery-chosen/"), //
            "chosen.css", "chosen.jquery.min.js")//
            .addDependency(jQueryMin);

    IArtifact cropper = group("cropper", _js_.join("jquery-cropper/dist/"),//
            "cropper.min.css", "cropper.min.js")//
            .addDependency(jQueryMin);

    IArtifact datatables = group("datatables", _js_.join("datatables/media/"),//
            "css/jquery.dataTables.min.css", "js/jquery.dataTables.min.js")//
            .addDependency(jQueryMin);

    IArtifact datatablesBootstrap = group("datatables.bootstrap",
            _js_.join("datatables/examples/resources/bootstrap/3/"), //
            "dataTables.bootstrap.css", "dataTables.bootstrap.js")//
            .addDependency(datatables)//
            .addDependency(bootstrap3_js);

    IArtifact datatablesColVis = group("datatables.colVis", _js_.join("datatables/extensions/ColVis/"),
            "css/dataTables.colVis.css", "js/dataTables.colVis.js")//
            .addDependency(datatablesBootstrap);

    IArtifact datatablesResponsive = group("datatables.responsive", _js_.join("datatables/extensions/Responsive/"),
            "css/dataTables.responsive.css", "js/dataTables.responsive.js")//
            .addDependency(datatablesBootstrap);

    IArtifact datatablesTableTools = group("datatables.tableTools", _js_.join("datatables/extensions/TableTools/"),
            "css/dataTables.tableTools.css", "js/dataTables.tableTools.js")//
            .addDependency(datatablesBootstrap);

    IArtifact fileUpload = group("file-upload", _js_.join("jquery-file-upload/"), //
            "css/jquery.fileupload.css", //
            "js/jquery.fileupload.js", //
            "js/jquery.iframe-transport.js", //
            // "js/jquery.fileupload-process.js", //
            "#")//
            .addDependency(jQueryMin)//
            .addDependency(jQueryUiWidgetMin);

    IArtifact flot = group("flot", _js_.join("jquery-flot/"), //
            "jquery.flot.min.js", //
            "jquery.flot.pie.min.js", //
            "jquery.flot.stack.min.js")//
            .addDependency(jQueryMin);

    IArtifact focuspoint = group("focuspoint", _js_.join("jquery-focuspoint/"), //
            "css/focuspoint.css", "js/jquery.focuspoint.min.js")//
            .addDependency(jQueryMin);

    IArtifact handsontable_js = group("handsontable", _js_.join("jquery-handsontable/dist/"), //
            "handsontable.full.min.css", "handsontable.full.min.js") //
            .addDependency(jQueryMin);

    IArtifact icheck = group("icheck", _js_.join("jquery-icheck/"), //
            "skins/all.css", "icheck.min.js")//
            .addDependency(jQueryMin);

    IArtifact knob = group("knob", _js_.join("jquery-knob/dist/"), //
            "jquery.knob.min.js")//
            .addDependency(jQueryMin);

    IArtifact magnificPopup = group("magnific-popup", _js_.join("jquery-magnific-popup/dist/"), //
            "magnific-popup.css", "jquery.magnific-popup.min.js")//
            .addDependency(jQueryMin);

    IArtifact parsley = group("parsley", _js_.join("jquery-parsley/"),//
            "src/parsley.css", "dist/parsley.min.js")//
            .addDependency(jQueryMin);

    IArtifact qrcode = group("qrcode", _js_.join("jquery-qrcode/dist/"), //
            "jquery.qrcode.min.js")//
            .addDependency(jQueryMin);

    IArtifact qtip = group("qtip", _js_.join("jquery-qtip/"), //
            "jquery.qtip.min.css", "jquery.qtip.min.js")//
            .addDependency(jQueryMin);

    IArtifact selectize = group("selectize", _js_.join("jquery-selectize/dist/"), //
            "css/selectize.css", "js/standalone/selectize.min.js")//
            .addDependency(jQueryMin);

    IArtifact tagsinput = group("tagsinput", _js_.join("bootstrap-tagsinput/dist/"), //
            "bootstrap-tagsinput.css", "bootstrap-tagsinput.min.js")//
            .addDependency(jQueryMin);

    IArtifact typeahead = group("typeahead", _js_.join("twitter-typeahead/dist/"), //
            "typeahead.bundle.min.js")//
            .addDependency(jQueryMin);

    IArtifact wallpaper = group("wallpaper", _js_.join("jquery-wallpaper/"), //
            "jquery.fs.wallpaper.min.js", "jquery.fs.wallpaper.css")//
            .addDependency(jQueryMin);

    IArtifact wysiwygEditor = group("wysiwyg-editor", _js_.join("jquery-wysiwyg-editor/"), //
            "css/froala_editor.min.css", //
            "css/froala_style.min.css", //
            "js/froala_editor.min.js", //
            "#")//
            .addDependency(jQueryMin)//
    // .addDependency(LibFontsArtifacts.fontAwesome)
    ;

    {
        tagsinput.addDependency(typeahead);
    }

    public static final String ALL_DATA = "all-data";
    public static final String ALL_INPUTS = "all-inputs";
    public static final String ALL_EFFECTS = "all-effects";
    IArtifact allData = group(ALL_DATA);
    {
        allData.addDependency(datatables);
        // allData.addDependency(datatablesBootstrap);
        allData.addDependency(datatablesColVis);
        // allData.addDependency(datatablesResponsive);
        allData.addDependency(datatablesTableTools);
    }
    IArtifact allInputs = group(ALL_INPUTS);
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
    IArtifact allEffects = group(ALL_EFFECTS);
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
