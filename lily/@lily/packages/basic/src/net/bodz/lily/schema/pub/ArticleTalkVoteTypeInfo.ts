import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import ArticleTalkVoteValidators from "./ArticleTalkVoteValidators";
import _ArticleTalkVote_stuff_TypeInfo from "./_ArticleTalkVote_stuff_TypeInfo";

export class ArticleTalkVoteTypeInfo extends _ArticleTalkVote_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.ArticleTalkVote"
    icon = "fa-tag"

    validators = new ArticleTalkVoteValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default ArticleTalkVoteTypeInfo;
