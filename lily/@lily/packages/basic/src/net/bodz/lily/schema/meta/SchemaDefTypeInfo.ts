import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { List } from "../../../../../java/util/List";
import SchemaDefValidators from "./SchemaDefValidators";
import _SchemaDef_stuff_TypeInfo from "./_SchemaDef_stuff_TypeInfo";

// Type Info

export class SchemaDefTypeInfo extends _SchemaDef_stuff_TypeInfo {

    name = "net.bodz.lily.schema.meta.SchemaDef"
    icon = "fa-tag"
    label = "Schema"

    static validators = new SchemaDefValidators();

    static declaredProperty: EntityPropertyMap = {
        categories: property({ type: "List", validator: this.validators.validateCategories }),
        parameters: property({ type: "List", validator: this.validators.validateParameters }),
        phases: property({ type: "List", validator: this.validators.validatePhases }),
        priorities: property({ type: "List", validator: this.validators.validatePriorities }),
        tagGroups: property({ type: "List", validator: this.validators.validateTagGroups }),
    }

    constructor() {
        super();
        this.declare(SchemaDefTypeInfo.declaredProperty);
    }

}

export default SchemaDef;
