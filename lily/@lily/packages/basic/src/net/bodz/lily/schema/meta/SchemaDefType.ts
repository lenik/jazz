import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { List } from "../../../../../java/util/List";
import SchemaDefValidators from "./SchemaDefValidators";
import _SchemaDef_stuff_Type from "./_SchemaDef_stuff_Type";

// Type Info

export class SchemaDefType extends _SchemaDef_stuff_Type {

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
        this.declare(SchemaDefType.declaredProperty);
    }

}

export default SchemaDef;
