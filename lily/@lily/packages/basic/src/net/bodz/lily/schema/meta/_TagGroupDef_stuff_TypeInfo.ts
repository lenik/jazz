import { BOOLEAN } from "skel01-core/src/lang/baseinfo";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import AbstractDefinitionTypeInfo from "./AbstractDefinitionTypeInfo";
import _TagGroupDef_stuff_Validators from "./_TagGroupDef_stuff_Validators";

export class _TagGroupDef_stuff_TypeInfo extends AbstractDefinitionTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "_tagv";

    static readonly FIELD_FOR_TOPIC = "topic";
    static readonly FIELD_FOR_REPLY = "reply";

    static readonly N_FOR_TOPIC = 1;
    static readonly N_FOR_REPLY = 1;

    readonly validators = new _TagGroupDef_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.meta.TagGroupDef"; }
    get icon() { return "fa-tag"; }
    get description() { return ""; }

    override preamble() {
        super.preamble();
        this.declare({
            forTopic: property({ type: BOOLEAN, nullable: false, precision: 1, validator: this.validators.validateForTopic }),
            forReply: property({ type: BOOLEAN, nullable: false, precision: 1, validator: this.validators.validateForReply }),
        });
    }

}

export default _TagGroupDef_stuff_TypeInfo;

export const _TagGroupDef_stuff_TYPE = _TagGroupDef_stuff_TypeInfo.INSTANCE;
