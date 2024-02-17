
import { property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _TagGroupDef_stuff_Type } from "./_TagGroupDef_stuff_Type";

// Type Info

export class TagGroupDefType extends _TagGroupDef_stuff_Type {

    name = "net.bodz.lily.schema.meta.TagGroupDef"
    icon = "fa-tag"
    label = "Tag Group"

    static declaredProperty: EntityPropertyMap = {
        ortho: property({ type: "boolean", nullable: false, validator: validators.validate_ortho }),
        tags: property({ type: "java.util.List", validator: validators.validate_tags }),
    }

    constructor() {
        super();
        this.declare(TagGroupDefType.declaredProperty);
    }

}
