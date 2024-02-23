import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ArticleTalkVoteValidators from "./ArticleTalkVoteValidators";
import _ArticleTalkVote_stuff_TypeInfo from "./_ArticleTalkVote_stuff_TypeInfo";

// Type Info

export class ArticleTalkVoteTypeInfo extends _ArticleTalkVote_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.ArticleTalkVote"
    icon = "fa-tag"

    static validators = new ArticleTalkVoteValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ArticleTalkVoteTypeInfo.declaredProperty);
    }

}

export default ArticleTalkVote;
