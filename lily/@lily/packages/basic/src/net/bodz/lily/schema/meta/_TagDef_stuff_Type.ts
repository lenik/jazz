import type { integer } from "@skeljs/core/src/lang/type";
import CoEntityType from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import TagDefValidators from "./TagDefValidators";
import { TagGroupDef } from "./TagGroupDef";

export class _TagDef_stuff_Type extends CoEntityType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "_tag";

    name = "net.bodz.lily.schema.meta.TagDef"
    icon = "fa-tag"
    label = "Tag"

    static FIELD_ID = "id";
    static FIELD_TAG_GROUP_ID = "tagv";

    static N_ID = 10;
    static N_TAG_GROUP_ID = 10;

    static validators = new TagDefValidators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),

        tagGroup: property({ type: TagGroupDef.TYPE, nullable: false, validator: this.validators.validateTagGroup }),
        tagGroupId: property({ type: "integer", nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(_TagDef_stuff_Type.declaredProperty);
    }

}

export default _TagDef_stuff_Type;
