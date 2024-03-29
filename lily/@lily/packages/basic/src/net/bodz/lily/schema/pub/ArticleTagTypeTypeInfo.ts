import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import ArticleTagTypeValidators from "./ArticleTagTypeValidators";
import _ArticleTagType_stuff_TypeInfo from "./_ArticleTagType_stuff_TypeInfo";

export class ArticleTagTypeTypeInfo extends _ArticleTagType_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.pub.ArticleTagType"; }
    get icon() { return "fa-tag"; }

    validators = new ArticleTagTypeValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default ArticleTagTypeTypeInfo;
