import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ParameterDefValidators from "./ParameterDefValidators";
import _ParameterDef_stuff_TypeInfo from "./_ParameterDef_stuff_TypeInfo";

export class ParameterDefTypeInfo extends _ParameterDef_stuff_TypeInfo {

    name = "net.bodz.lily.schema.meta.ParameterDef"
    icon = "fa-tag"
    label = "Parameter"

    validators = new ParameterDefValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default ParameterDefTypeInfo;
