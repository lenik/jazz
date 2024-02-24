import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ParameterValueValidators from "./ParameterValueValidators";
import _ParameterValue_stuff_TypeInfo from "./_ParameterValue_stuff_TypeInfo";

export class ParameterValueTypeInfo extends _ParameterValue_stuff_TypeInfo {

    name = "net.bodz.lily.schema.meta.ParameterValue"
    icon = "fa-tag"

    validators = new ParameterValueValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default ParameterValueTypeInfo;
