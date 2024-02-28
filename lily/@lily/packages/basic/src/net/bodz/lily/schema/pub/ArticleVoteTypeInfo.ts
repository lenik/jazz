import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import ArticleVoteValidators from "./ArticleVoteValidators";
import _ArticleVote_stuff_TypeInfo from "./_ArticleVote_stuff_TypeInfo";

export class ArticleVoteTypeInfo extends _ArticleVote_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.pub.ArticleVote"; }
    get icon() { return "fa-tag"; }

    validators = new ArticleVoteValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default ArticleVoteTypeInfo;
