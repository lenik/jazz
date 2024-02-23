import CoTagTypeInfo from "@skeljs/dba/src/net/bodz/lily/concrete/CoTagTypeInfo";
import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { TypeParamType } from "../../meta/TypeParamType";
import _ArticleTagType_stuff_Validators from "./_ArticleTagType_stuff_Validators";

export class _ArticleTagType_stuff_TypeInfo extends CoTagTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "articletag";

    name = "net.bodz.lily.schema.pub.ArticleTagType"
    icon = "fa-tag"

    static validators = new _ArticleTagType_stuff_Validators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(_ArticleTagType_stuff_TypeInfo.declaredProperty);
    }

}

export default _ArticleTagType_stuff_TypeInfo;
