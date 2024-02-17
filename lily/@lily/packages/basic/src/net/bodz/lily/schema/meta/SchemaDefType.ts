
import { property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _SchemaDef_stuff_Type } from "./_SchemaDef_stuff_Type";

// Type Info

export class SchemaDefType extends _SchemaDef_stuff_Type {

    name = "net.bodz.lily.schema.meta.SchemaDef"
    icon = "fa-tag"
    label = "Schema"

    static declaredProperty: EntityPropertyMap = {
        categories: property({ type: "java.util.List", validator: validators.validate_categories }),
        parameters: property({ type: "java.util.List", validator: validators.validate_parameters }),
        phases: property({ type: "java.util.List", validator: validators.validate_phases }),
        priorities: property({ type: "java.util.List", validator: validators.validate_priorities }),
        tagGroups: property({ type: "java.util.List", validator: validators.validate_tagGroups }),
    }

    constructor() {
        super();
        this.declare(SchemaDefType.declaredProperty);
    }

}
