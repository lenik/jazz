
import type { CoParameterType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoParameterType";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { TypeParamType } from "../../meta/TypeParamType";
import { * as validators } from "./PersonValidators";

// Type Info

export class _ArticleParameterType_stuff_Type extends CoParameterType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "articleparm";

    name = "net.bodz.lily.schema.pub.ArticleParameterType"
    icon = "fa-tag"

    static const FIELD_NAME = "name";
    static const FIELD_DUMMY = "dummy";

    static const N_NAME = 30;
    static const N_DUMMY = 10;

    static declaredProperty: EntityPropertyMap = {
        name: property({ type: "string", precision: 30, validator: validators.validate_name }),
        dummy: property({ type: "integer", precision: 10, validator: validators.validate_dummy }),
    }

    constructor() {
        super();
        this.declare(_ArticleParameterType_stuff_Type.declaredProperty);
    }

}
