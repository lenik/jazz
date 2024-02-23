import type { integer } from "@skeljs/core/src/lang/type";
import CoEntityTypeInfo from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityTypeInfo";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import _TagDef_stuff_Validators from "./_TagDef_stuff_Validators";

export class _TagDef_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "_tag";

    name = "net.bodz.lily.schema.meta.TagDef"
    icon = "fa-tag"
    label = "Tag"

    static FIELD_ID = "id";
    static FIELD_TAG_GROUP_ID = "tagv";

    static N_ID = 10;
    static N_TAG_GROUP_ID = 10;

    static validators = new _TagDef_stuff_Validators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),

        tagGroup: property({ type: net.bodz.lily.schema.meta.TagGroupDefTypeInfo, nullable: false, validator: this.validators.validateTagGroup }),
        tagGroupId: property({ type: "integer", nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(_TagDef_stuff_TypeInfo.declaredProperty);
    }

}

export default _TagDef_stuff_TypeInfo;
