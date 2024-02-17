
import type { CoEntityType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import { * as validators } from "./PersonValidators";

// Type Info

export class _TagDef_stuff_Type extends CoEntityType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "_tag";

    name = "net.bodz.lily.schema.meta.TagDef"
    icon = "fa-tag"
    label = "Tag"

    static const FIELD_ID = "id";
    static const FIELD_TAG_GROUP_ID = "tagv";

    static const N_ID = 10;
    static const N_TAG_GROUP_ID = 10;

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: validators.validate_id }),

        tagGroup: property({ type: "net.bodz.lily.schema.meta.TagGroupDef", nullable: false, validator: validators.validate_tagGroup }),
        tagGroupId: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_tagGroupId }),
    }

    constructor() {
        super();
        this.declare(_TagDef_stuff_Type.declaredProperty);
    }

}
