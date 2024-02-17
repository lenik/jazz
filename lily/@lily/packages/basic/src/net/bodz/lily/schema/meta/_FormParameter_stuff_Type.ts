
import type { CoEntityType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import { * as validators } from "./PersonValidators";

// Type Info

export class _FormParameter_stuff_Type extends CoEntityType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "_formparm";

    name = "net.bodz.lily.schema.meta.FormParameter"
    icon = "fa-tag"

    static const FIELD_ID = "id";
    static const FIELD_FORM_ID = "form";
    static const FIELD_NAME = "name";
    static const FIELD_VALUE = "value";

    static const N_ID = 10;
    static const N_FORM_ID = 10;
    static const N_NAME = 30;
    static const N_VALUE = 100;

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: validators.validate_id }),
        name: property({ type: "string", precision: 30, validator: validators.validate_name }),
        value: property({ type: "string", precision: 100, validator: validators.validate_value }),

        form: property({ type: "net.bodz.lily.schema.meta.FormDef", nullable: false, validator: validators.validate_form }),
        formId: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_formId }),
    }

    constructor() {
        super();
        this.declare(_FormParameter_stuff_Type.declaredProperty);
    }

}
