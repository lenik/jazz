import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ArticleVoteValidators from "./ArticleVoteValidators";
import _ArticleVote_stuff_Type from "./_ArticleVote_stuff_Type";

// Type Info

export class ArticleVoteType extends _ArticleVote_stuff_Type {

    name = "net.bodz.lily.schema.pub.ArticleVote"
    icon = "fa-tag"

    static validators = new ArticleVoteValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ArticleVoteType.declaredProperty);
    }

}

export default ArticleVote;
