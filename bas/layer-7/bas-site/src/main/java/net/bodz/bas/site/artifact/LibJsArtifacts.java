package net.bodz.bas.site.artifact;

import net.bodz.bas.html.artifact.ArtifactBuilder;
import net.bodz.bas.html.artifact.Group;
import net.bodz.bas.html.artifact.StaticArtifactProvider;
import net.bodz.bas.site.IBasicSiteAnchors;

public class LibJsArtifacts
        extends StaticArtifactProvider
        implements IBasicSiteAnchors {

    static final ArtifactBuilder fn = new ArtifactBuilder();

    public LibJsArtifacts() {
        super(fn.getContainer());
    }

    public static Group bootstrap3_js = fn.group("bootstrap3", _js_.join("twitter-bootstrap3/"), //
            "css/bootstrap.min.css", "js/bootstrap.min.js");

    public static Group jQuery = fn.group("jquery", _js_.join("jquery/"), //
            "jquery.min.js");

    public static Group jQueryUi = fn.group("jquery-ui", _js_.join("jquery-ui/"), //
            "jquery-ui.min.js", //
            "css/smoothness/jquery-ui.min.css")//
            .dependsOn(jQuery);

    public static Group jQueryUiWidget = fn.group("jquery-ui.widget", _js_.join("jquery-ui/"), //
            "ui/jquery.ui.widget.min.js")//
            .dependsOn(jQueryUi);

    public static Group bigvideo_js = fn.group("jquery-bigvideo", _js_.join("jquery-bigvideo/"), //
            "css/bigvideo.css", "lib/bigvideo.js")//
            .dependsOn(jQuery);

    public static Group chosen = fn.group("jquery-chosen", _js_.join("jquery-chosen/"), //
            "chosen.css", "chosen.jquery.min.js")//
            .dependsOn(jQuery);

    public static Group cropper = fn.group("jquery-cropper", _js_.join("jquery-cropper/dist/"),//
            "cropper.min.css", "cropper.min.js")//
            .dependsOn(jQuery);

    public static Group datatables = fn.group("datatables", _js_.join("datatables/media/"),//
            "css/jquery.dataTables.min.css", "js/jquery.dataTables.min.js")//
            .dependsOn(jQuery);

    public static Group datatablesBootstrap = fn
            .group("datatables.bootstrap", _js_.join("datatables/examples/resources/bootstrap/3/"), //
                    "dataTables.bootstrap.css", "dataTables.bootstrap.js")//
            .dependsOn(datatables)//
            .dependsOn(bootstrap3_js);

    public static Group datatablesColVis = fn.group("datatables.colVis", _js_.join("datatables/extensions/ColVis/"),
            "css/dataTables.colVis.css", "js/dataTables.colVis.js")//
            .dependsOn(datatablesBootstrap);

    public static Group datatablesResponsive = fn.group("datatables.responsive",
            _js_.join("datatables/extensions/Responsive/"), "css/dataTables.responsive.css",
            "js/dataTables.responsive.js")//
            .dependsOn(datatablesBootstrap);

    public static Group datatablesTableTools = fn.group("datatables.tableTools",
            _js_.join("datatables/extensions/TableTools/"), "css/dataTables.tableTools.css",
            "js/dataTables.tableTools.js")//
            .dependsOn(datatablesBootstrap);

    public static Group fileUpload = fn.group("jquery-file-upload", _js_.join("jquery-file-upload/"), //
            "css/jquery.fileupload.css", //
            "js/jquery.fileupload.js", //
            "js/jquery.iframe-transport.js", //
            // "js/jquery.fileupload-process.js", //
            "#")//
            .dependsOn(jQuery)//
            .dependsOn(jQueryUiWidget);

    public static Group flot = fn.group("jquery-flot", _js_.join("jquery-flot/"), //
            "jquery.flot.min.js", //
            "jquery.flot.pie.min.js", //
            "jquery.flot.resize.min.js", //
            "jquery.flot.stack.min.js")//
            .dependsOn(jQuery);

    public static Group focuspoint = fn.group("jquery-focuspoint", _js_.join("jquery-focuspoint/"), //
            "css/focuspoint.css", "js/jquery.focuspoint.min.js")//
            .dependsOn(jQuery);

    public static Group handsontable_js = fn.group("jquery-handsontable", _js_.join("jquery-handsontable/dist/"), //
            "handsontable.full.min.css", "handsontable.full.min.js") //
            .dependsOn(jQuery);

    public static Group icheck = fn.group("jquery-icheck", _js_.join("jquery-icheck/"), //
            "skins/all.css", "icheck.min.js")//
            .dependsOn(jQuery);

    public static Group knob = fn.group("jquery-knob", _js_.join("jquery-knob/dist/"), //
            "jquery.knob.min.js")//
            .dependsOn(jQuery);

    public static Group magnificPopup = fn.group("jquery-magnific-popup", _js_.join("jquery-magnific-popup/dist/"), //
            "magnific-popup.css", "jquery.magnific-popup.min.js")//
            .dependsOn(jQuery);

    public static Group parsley = fn.group("jquery-parsley", _js_.join("jquery-parsley/"),//
            "src/parsley.css", "dist/parsley.min.js")//
            .dependsOn(jQuery);

    public static Group qrcode = fn.group("jquery-qrcode", _js_.join("jquery-qrcode/dist/"), //
            "jquery.qrcode.min.js")//
            .dependsOn(jQuery);

    public static Group qtip = fn.group("jquery-qtip", _js_.join("jquery-qtip/"), //
            "jquery.qtip.min.css", "jquery.qtip.min.js")//
            .dependsOn(jQuery);

    public static Group selectize = fn.group("jquery-selectize", _js_.join("jquery-selectize/dist/"), //
            "css/selectize.css", "js/standalone/selectize.min.js")//
            .dependsOn(jQuery);

    public static Group tagsinput = fn.group("jquery-tagsinput", _js_.join("bootstrap-tagsinput/dist/"), //
            "bootstrap-tagsinput.css", "bootstrap-tagsinput.min.js")//
            .dependsOn(jQuery);

    public static Group typeahead = fn.group("jquery-typeahead", _js_.join("twitter-typeahead/dist/"), //
            "typeahead.bundle.min.js")//
            .dependsOn(jQuery);

    public static Group wallpaper = fn.group("jquery-wallpaper", _js_.join("jquery-wallpaper/"), //
            "jquery.fs.wallpaper.min.js", "jquery.fs.wallpaper.css")//
            .dependsOn(jQuery);

    public static Group wysiwygEditor = fn.group("jquery-wysiwyg-editor", _js_.join("jquery-wysiwyg-editor/"), //
            "css/froala_editor.min.css", //
            "css/froala_style.min.css", //
            "js/froala_editor.min.js", //
            "#")//
            .dependsOn(jQuery)//
    // .dependsOn(LibFontsArtifacts.fontAwesome)
    ;

    {
        tagsinput.dependsOn(typeahead);
    }

    public static Group allData = fn.group("jquery-all-data");
    {
        allData.dependsOn(datatables);
        // allData.dependsOn(datatablesBootstrap);
        allData.dependsOn(datatablesColVis);
        // allData.dependsOn(datatablesResponsive);
        allData.dependsOn(datatablesTableTools);
    }
    public static Group allInputs = fn.group("jquery-all-inputs");
    {
        allInputs.dependsOn(chosen);
        allInputs.dependsOn(fileUpload);
        allInputs.dependsOn(icheck);
        allInputs.dependsOn(knob);
        allInputs.dependsOn(parsley);
        allInputs.dependsOn(selectize);
        allInputs.dependsOn(tagsinput);
        allInputs.dependsOn(typeahead);
        allInputs.dependsOn(wysiwygEditor);
    }
    public static Group allEffects = fn.group("jquery-all-effects");
    {
        allEffects.dependsOn(cropper);
        allEffects.dependsOn(flot);
        allEffects.dependsOn(focuspoint);
        allEffects.dependsOn(magnificPopup);
        allEffects.dependsOn(qrcode);
        allEffects.dependsOn(qtip);
        allEffects.dependsOn(wallpaper);
    }

}
