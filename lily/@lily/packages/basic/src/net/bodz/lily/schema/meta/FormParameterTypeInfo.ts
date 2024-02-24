import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import FormParameterValidators from "./FormParameterValidators";
import _FormParameter_stuff_TypeInfo from "./_FormParameter_stuff_TypeInfo";

export class FormParameterTypeInfo extends _FormParameter_stuff_TypeInfo {

    name = "net.bodz.lily.schema.meta.FormParameter"
    icon = "fa-tag"

    validators = new FormParameterValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default FormParameterTypeInfo;
