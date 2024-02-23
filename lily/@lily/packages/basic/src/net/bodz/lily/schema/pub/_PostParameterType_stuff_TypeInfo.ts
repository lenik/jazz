import type { integer } from "@skeljs/core/src/lang/type";
import CoParameterTypeInfo from "@skeljs/dba/src/net/bodz/lily/concrete/CoParameterTypeInfo";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { TypeParamType } from "../../meta/TypeParamType";
import _PostParameterType_stuff_Validators from "./_PostParameterType_stuff_Validators";

export class _PostParameterType_stuff_TypeInfo extends CoParameterTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "postparm";

    name = "net.bodz.lily.schema.pub.PostParameterType"
    icon = "fa-tag"

    static FIELD_NAME = "name";
    static FIELD_DUMMY = "dummy";

    static N_NAME = 30;
    static N_DUMMY = 10;

    static validators = new _PostParameterType_stuff_Validators();

    static declaredProperty: EntityPropertyMap = {
        name: property({ type: "string", precision: 30, validator: this.validators.validateName }),
        dummy: property({ type: "integer", precision: 10, validator: this.validators.validateDummy }),
    }

    constructor() {
        super();
        this.declare(_PostParameterType_stuff_TypeInfo.declaredProperty);
    }

}

export default _PostParameterType_stuff_TypeInfo;
