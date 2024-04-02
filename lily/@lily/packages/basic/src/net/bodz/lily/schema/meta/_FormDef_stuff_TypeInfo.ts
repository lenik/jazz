import { STRING } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import AbstractDefinitionTypeInfo from "./AbstractDefinitionTypeInfo";
import _FormDef_stuff_Validators from "./_FormDef_stuff_Validators";

export class _FormDef_stuff_TypeInfo extends AbstractDefinitionTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "_form";

    static readonly FIELD_SUBJECT = "subject";
    static readonly FIELD_RAW_TEXT = "text";

    static readonly N_SUBJECT = 200;
    static readonly N_RAW_TEXT = 2147483647;

    readonly validators = new _FormDef_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.meta.FormDef"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            subject: property({ type: STRING, precision: 200, validator: this.validators.validateSubject }),
            rawText: property({ type: STRING, validator: this.validators.validateRawText }),
        });
    }

}

export default _FormDef_stuff_TypeInfo;

export const _FormDef_stuff_TYPE = _FormDef_stuff_TypeInfo.INSTANCE;
