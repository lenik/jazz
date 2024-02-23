import CoTagType from "@skeljs/dba/src/net/bodz/lily/concrete/CoTagType";
import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { TypeParamType } from "../../meta/TypeParamType";
import PostTagTypeValidators from "./PostTagTypeValidators";

export class _PostTagType_stuff_Type extends CoTagType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "posttag";

    name = "net.bodz.lily.schema.pub.PostTagType"
    icon = "fa-tag"

    static validators = new PostTagTypeValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(_PostTagType_stuff_Type.declaredProperty);
    }

}

export default _PostTagType_stuff_Type;
