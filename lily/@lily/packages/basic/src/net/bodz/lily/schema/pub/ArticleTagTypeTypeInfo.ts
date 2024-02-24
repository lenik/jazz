import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ArticleTagTypeValidators from "./ArticleTagTypeValidators";
import _ArticleTagType_stuff_TypeInfo from "./_ArticleTagType_stuff_TypeInfo";

export class ArticleTagTypeTypeInfo extends _ArticleTagType_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.ArticleTagType"
    icon = "fa-tag"

    validators = new ArticleTagTypeValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default ArticleTagTypeTypeInfo;
