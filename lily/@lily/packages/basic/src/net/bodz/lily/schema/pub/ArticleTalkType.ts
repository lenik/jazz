import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ArticleTalkValidators from "./ArticleTalkValidators";
import _ArticleTalk_stuff_Type from "./_ArticleTalk_stuff_Type";

// Type Info

export class ArticleTalkType extends _ArticleTalk_stuff_Type {

    name = "net.bodz.lily.schema.pub.ArticleTalk"
    icon = "fa-tag"

    static validators = new ArticleTalkValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ArticleTalkType.declaredProperty);
    }

}

export default ArticleTalk;
