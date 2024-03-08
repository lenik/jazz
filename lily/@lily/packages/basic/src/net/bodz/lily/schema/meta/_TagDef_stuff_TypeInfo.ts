import { INT } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import TagGroupDef from "./TagGroupDef";
import _TagDef_stuff_Validators from "./_TagDef_stuff_Validators";

export class _TagDef_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "_tag";

    get name() { return "net.bodz.lily.schema.meta.TagDef"; }
    get icon() { return "fa-tag"; }
    get label() { return "Tag"; }

    static FIELD_ID = "id";
    static FIELD_TAG_GROUP_ID = "tagv";

    static N_ID = 10;
    static N_TAG_GROUP_ID = 10;

    validators = new _TagDef_stuff_Validators(this);

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),

            tagGroup: property({ type: TagGroupDef.TYPE, nullable: false, validator: this.validators.validateTagGroup }),
            tagGroupId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    constructor() {
        super();
    }

}

export default _TagDef_stuff_TypeInfo;
