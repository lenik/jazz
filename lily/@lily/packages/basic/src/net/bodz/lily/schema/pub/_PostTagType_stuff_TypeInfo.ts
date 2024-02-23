import CoTagTypeInfo from "@skeljs/dba/src/net/bodz/lily/concrete/CoTagTypeInfo";
import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { TypeParamType } from "../../meta/TypeParamType";
import _PostTagType_stuff_Validators from "./_PostTagType_stuff_Validators";

export class _PostTagType_stuff_TypeInfo extends CoTagTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "posttag";

    name = "net.bodz.lily.schema.pub.PostTagType"
    icon = "fa-tag"

    static validators = new _PostTagType_stuff_Validators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(_PostTagType_stuff_TypeInfo.declaredProperty);
    }

}

export default _PostTagType_stuff_TypeInfo;
