import CoTagTypeInfo from "@skeljs/dba/src/net/bodz/lily/concrete/CoTagTypeInfo";
import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { TypeParamType } from "../../meta/TypeParamType";
import _PersonTagType_stuff_Validators from "./_PersonTagType_stuff_Validators";

export class _PersonTagType_stuff_TypeInfo extends CoTagTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "persontag";

    name = "net.bodz.lily.schema.contact.PersonTagType"
    icon = "fa-tag"

    static validators = new _PersonTagType_stuff_Validators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(_PersonTagType_stuff_TypeInfo.declaredProperty);
    }

}

export default _PersonTagType_stuff_TypeInfo;
