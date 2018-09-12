$(document).ready(function() {
    
    appModel = {
        version: '1.0',
        view: 'modules',
        stat: {
            views: 0,
            stars: 0,
            upvotes: 0,
            downvotes: 0
        },
        
        css: {
            dataTable: [ "table", "table-striped", "table-hover", "table-condensed", "dataTable", "table-responsive" ]
        },
        
        menu: [
            { href: "index.html", iconfa: "fa-home", label: "主页" },
            { href: "entity.html", iconfa: "fa-cog", label: "实体访问" }
        ],
        
        struct: {},
        
        modules: [],
        module: {},
        
        init: function(s) {
        }
    };
    $.extend(appModel, appModel.struct);

    app = new Vue({
        el: "#main",
        data: appModel,
        computed: {
            parentInfo: function(x) {
                var p = this.station.parent;
                var inst = this.station.instance;
                if (p == null)
                    return null;
                return p.id + "/" + inst.label + " [" + p.startFormat + " ~ " + p.endFormat + "]";
            }
        },
        
        watch: {
        },
        
        methods: {
        }
    });
    
    new Vue({ el: "#top", data: app });
    new Vue({ el: ".dialogs", data: app });
    
    $.ajax("/ws-doc/modules")
        .done(function(data) {
            app.modules = data.data.modules;
        });
});
