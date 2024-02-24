import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ArticleTalkValidators from "./ArticleTalkValidators";
import _ArticleTalk_stuff_TypeInfo from "./_ArticleTalk_stuff_TypeInfo";

export class ArticleTalkTypeInfo extends _ArticleTalk_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.ArticleTalk"
    icon = "fa-tag"

    validators = new ArticleTalkValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default ArticleTalkTypeInfo;
