import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ArticleVoteValidators from "./ArticleVoteValidators";
import _ArticleVote_stuff_TypeInfo from "./_ArticleVote_stuff_TypeInfo";

// Type Info

export class ArticleVoteTypeInfo extends _ArticleVote_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.ArticleVote"
    icon = "fa-tag"

    static validators = new ArticleVoteValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ArticleVoteTypeInfo.declaredProperty);
    }

}

export default ArticleVote;
