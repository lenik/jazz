import type { integer } from "@skeljs/core/src/lang/type";
import CoEntityType from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { FormDef } from "./FormDef";
import FormParameterValidators from "./FormParameterValidators";

export class _FormParameter_stuff_Type extends CoEntityType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "_formparm";

    name = "net.bodz.lily.schema.meta.FormParameter"
    icon = "fa-tag"

    static FIELD_ID = "id";
    static FIELD_FORM_ID = "form";
    static FIELD_NAME = "name";
    static FIELD_VALUE = "value";

    static N_ID = 10;
    static N_FORM_ID = 10;
    static N_NAME = 30;
    static N_VALUE = 100;

    static validators = new FormParameterValidators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        name: property({ type: "string", precision: 30, validator: this.validators.validateName }),
        value: property({ type: "string", precision: 100, validator: this.validators.validateValue }),

        form: property({ type: FormDef.TYPE, nullable: false, validator: this.validators.validateForm }),
        formId: property({ type: "integer", nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(_FormParameter_stuff_Type.declaredProperty);
    }

}

export default _FormParameter_stuff_Type;
