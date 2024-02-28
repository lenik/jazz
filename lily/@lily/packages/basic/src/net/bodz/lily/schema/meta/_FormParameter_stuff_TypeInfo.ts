import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import FormDef from "./FormDef";
import _FormParameter_stuff_Validators from "./_FormParameter_stuff_Validators";

export class _FormParameter_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "_formparm";

    get name() { return "net.bodz.lily.schema.meta.FormParameter"; }
    get icon() { return "fa-tag"; }

    static FIELD_ID = "id";
    static FIELD_FORM_ID = "form";
    static FIELD_NAME = "name";
    static FIELD_VALUE = "value";

    static N_ID = 10;
    static N_FORM_ID = 10;
    static N_NAME = 30;
    static N_VALUE = 100;

    validators = new _FormParameter_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),
        name: property({ type: STRING, precision: 30, validator: this.validators.validateName }),
        value: property({ type: STRING, precision: 100, validator: this.validators.validateValue }),

        form: property({ type: FormDef.TYPE, nullable: false, validator: this.validators.validateForm }),
        formId: property({ type: INT, nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _FormParameter_stuff_TypeInfo;
