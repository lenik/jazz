import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import FormDefValidators from "./FormDefValidators";
import _FormDef_stuff_TypeInfo from "./_FormDef_stuff_TypeInfo";

// Type Info

export class FormDefTypeInfo extends _FormDef_stuff_TypeInfo {

    name = "net.bodz.lily.schema.meta.FormDef"
    icon = "fa-tag"

    static validators = new FormDefValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(FormDefTypeInfo.declaredProperty);
    }

}

export default FormDef;
