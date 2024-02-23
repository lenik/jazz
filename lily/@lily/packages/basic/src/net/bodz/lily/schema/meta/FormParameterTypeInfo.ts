import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import FormParameterValidators from "./FormParameterValidators";
import _FormParameter_stuff_TypeInfo from "./_FormParameter_stuff_TypeInfo";

// Type Info

export class FormParameterTypeInfo extends _FormParameter_stuff_TypeInfo {

    name = "net.bodz.lily.schema.meta.FormParameter"
    icon = "fa-tag"

    static validators = new FormParameterValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(FormParameterTypeInfo.declaredProperty);
    }

}

export default FormParameter;
