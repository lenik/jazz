import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import ArticleTalkVoteValidators from "./ArticleTalkVoteValidators";
import _ArticleTalkVote_stuff_TypeInfo from "./_ArticleTalkVote_stuff_TypeInfo";

export class ArticleTalkVoteTypeInfo extends _ArticleTalkVote_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.pub.ArticleTalkVote"; }
    get icon() { return "fa-tag"; }

    validators = new ArticleTalkVoteValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default ArticleTalkVoteTypeInfo;
