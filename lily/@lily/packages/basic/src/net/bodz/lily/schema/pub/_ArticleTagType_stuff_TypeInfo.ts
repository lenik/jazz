import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import CoTagTypeInfo from "../../concrete/CoTagTypeInfo";
import _ArticleTagType_stuff_Validators from "./_ArticleTagType_stuff_Validators";

export class _ArticleTagType_stuff_TypeInfo extends CoTagTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "articletag";

    name = "net.bodz.lily.schema.pub.ArticleTagType"
    icon = "fa-tag"

    validators = new _ArticleTagType_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _ArticleTagType_stuff_TypeInfo;
