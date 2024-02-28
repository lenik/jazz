import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoParameterTypeInfo from "../../concrete/CoParameterTypeInfo";
import _PostParameterType_stuff_Validators from "./_PostParameterType_stuff_Validators";

export class _PostParameterType_stuff_TypeInfo extends CoParameterTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "postparm";

    get name() { return "net.bodz.lily.schema.pub.PostParameterType"; }
    get icon() { return "fa-tag"; }

    static FIELD_NAME = "name";
    static FIELD_DUMMY = "dummy";

    static N_NAME = 30;
    static N_DUMMY = 10;

    validators = new _PostParameterType_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        name: property({ type: STRING, precision: 30, validator: this.validators.validateName }),
        dummy: property({ type: INT, precision: 10, validator: this.validators.validateDummy }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _PostParameterType_stuff_TypeInfo;
