import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import FormParameterValidators from "./FormParameterValidators";
import _FormParameter_stuff_TypeInfo from "./_FormParameter_stuff_TypeInfo";

export class FormParameterTypeInfo extends _FormParameter_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.meta.FormParameter"; }
    get icon() { return "fa-tag"; }

    validators = new FormParameterValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default FormParameterTypeInfo;
