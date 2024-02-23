import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import FormParameterValidators from "./FormParameterValidators";
import _FormParameter_stuff_Type from "./_FormParameter_stuff_Type";

// Type Info

export class FormParameterType extends _FormParameter_stuff_Type {

    name = "net.bodz.lily.schema.meta.FormParameter"
    icon = "fa-tag"

    static validators = new FormParameterValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(FormParameterType.declaredProperty);
    }

}

export default FormParameter;
