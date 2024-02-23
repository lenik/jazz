import CoTagType from "@skeljs/dba/src/net/bodz/lily/concrete/CoTagType";
import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { TypeParamType } from "../../meta/TypeParamType";
import ArticleTagTypeValidators from "./ArticleTagTypeValidators";

export class _ArticleTagType_stuff_Type extends CoTagType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "articletag";

    name = "net.bodz.lily.schema.pub.ArticleTagType"
    icon = "fa-tag"

    static validators = new ArticleTagTypeValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(_ArticleTagType_stuff_Type.declaredProperty);
    }

}

export default _ArticleTagType_stuff_Type;
