$(document).ready(function() {
    
    appModel = {
        version: '1.0',
        view: 'list',
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
        
        className: '',
        mapperAlias: '',
        
        groups: [],
        flatten: [],
        maskGroups: [],
        maskFlatten: [],
        
        type: {},
        
        instance: {},
        
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
    
    var fqcn = location.params.name;
    var lastDot = fqcn.lastIndexOf('.');
    var simpleName = fqcn.substr(lastDot + 1);
    app.className = fqcn;
    app.mapperAlias = simpleName;
    
    function flattenGroups(list, groups) {
        for (var g in groups) {
            var group = groups[g];
            flattenGroup(list, group);
        }
    }
    
    function flattenGroup(list, group) {
        for (var p in group.properties) {
            var property = group.properties[p];
            flatten(list, property);
        }
    }
    
    function flatten(list, node) {
        list.push(node);
        if (node.propertyGroups != null)
            flattenGroups(list, node.propertyGroups);
    }
    
    $.ajax("/ws-doc/entity/" + app.className + "/properties")
        .done(function(data) {
            app.groups = data.data.propertyGroups;
            var list = [];
            flattenGroups(list, data.data.propertyGroups);
            app.flatten = list;
        });
        
    $.ajax("/ws-doc/entity/" + location.params.name + "/maskInfo/properties")
        .done(function(data) {
            app.maskGroups = data.data.propertyGroups;
            var list = [];
            flattenGroups(list, data.data.propertyGroups);
            app.maskFlatten = list;
        });
});
