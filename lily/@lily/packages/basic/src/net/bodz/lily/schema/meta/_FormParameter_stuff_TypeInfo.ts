import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import IdEntityTypeInfo from "../../concrete/IdEntityTypeInfo";
import FormDef from "./FormDef";
import _FormParameter_stuff_Validators from "./_FormParameter_stuff_Validators";

export class _FormParameter_stuff_TypeInfo extends IdEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "_formparm";

    static readonly FIELD_FORM_ID = "form";
    static readonly FIELD_NAME = "name";
    static readonly FIELD_VALUE = "value";

    static readonly N_FORM_ID = 10;
    static readonly N_NAME = 30;
    static readonly N_VALUE = 100;

    readonly validators = new _FormParameter_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.lily.schema.meta.FormParameter"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            name: property({ type: STRING, precision: 30, validator: this.validators.validateName }),
            value: property({ type: STRING, precision: 100, validator: this.validators.validateValue }),

            form: property({ type: FormDef.TYPE, nullable: false, validator: this.validators.validateForm }),
            formId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _FormParameter_stuff_TypeInfo();

}

export default _FormParameter_stuff_TypeInfo;
