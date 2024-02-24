import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ArticleVoteValidators from "./ArticleVoteValidators";
import _ArticleVote_stuff_TypeInfo from "./_ArticleVote_stuff_TypeInfo";

export class ArticleVoteTypeInfo extends _ArticleVote_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.ArticleVote"
    icon = "fa-tag"

    validators = new ArticleVoteValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default ArticleVoteTypeInfo;
