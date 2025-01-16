import { INT } from "skel01-core/src/lang/baseinfo";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import AbstractDefinitionTypeInfo from "./AbstractDefinitionTypeInfo";
import { TagGroupDef_TYPE } from "./TagGroupDefTypeInfo";
import _TagDef_stuff_Validators from "./_TagDef_stuff_Validators";

export class _TagDef_stuff_TypeInfo extends AbstractDefinitionTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "_tag";

    static readonly FIELD_TAG_GROUP_ID = "tagv";

    static readonly N_TAG_GROUP_ID = 10;

    readonly validators = new _TagDef_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.meta.TagDef"; }
    get icon() { return "fa-tag"; }
    get description() { return ""; }

    override preamble() {
        super.preamble();
        this.declare({

            tagGroup: property({ type: TagGroupDef_TYPE, nullable: false, validator: this.validators.validateTagGroup }),
            tagGroupId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

}

export default _TagDef_stuff_TypeInfo;

export const _TagDef_stuff_TYPE = _TagDef_stuff_TypeInfo.INSTANCE;
