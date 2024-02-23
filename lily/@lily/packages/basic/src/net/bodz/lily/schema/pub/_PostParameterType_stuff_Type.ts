import type { integer } from "@skeljs/core/src/lang/type";
import CoParameterType from "@skeljs/dba/src/net/bodz/lily/concrete/CoParameterType";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { TypeParamType } from "../../meta/TypeParamType";
import PostParameterTypeValidators from "./PostParameterTypeValidators";

export class _PostParameterType_stuff_Type extends CoParameterType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "postparm";

    name = "net.bodz.lily.schema.pub.PostParameterType"
    icon = "fa-tag"

    static FIELD_NAME = "name";
    static FIELD_DUMMY = "dummy";

    static N_NAME = 30;
    static N_DUMMY = 10;

    static validators = new PostParameterTypeValidators();

    static declaredProperty: EntityPropertyMap = {
        name: property({ type: "string", precision: 30, validator: this.validators.validateName }),
        dummy: property({ type: "integer", precision: 10, validator: this.validators.validateDummy }),
    }

    constructor() {
        super();
        this.declare(_PostParameterType_stuff_Type.declaredProperty);
    }

}

export default _PostParameterType_stuff_Type;
