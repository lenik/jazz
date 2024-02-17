
import type { CoTagType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoTagType";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { TypeParamType } from "../../meta/TypeParamType";
import { * as validators } from "./PersonValidators";

// Type Info

export class _PostTagType_stuff_Type extends CoTagType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "posttag";

    name = "net.bodz.lily.schema.pub.PostTagType"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(_PostTagType_stuff_Type.declaredProperty);
    }

}
