import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import ArticleTalkValidators from "./ArticleTalkValidators";
import _ArticleTalk_stuff_TypeInfo from "./_ArticleTalk_stuff_TypeInfo";

export class ArticleTalkTypeInfo extends _ArticleTalk_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.pub.ArticleTalk"; }
    get icon() { return "fa-tag"; }

    validators = new ArticleTalkValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default ArticleTalkTypeInfo;
