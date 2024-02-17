
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _ArticleTalkVote_stuff_Type } from "./_ArticleTalkVote_stuff_Type";

// Type Info

export class ArticleTalkVoteType extends _ArticleTalkVote_stuff_Type {

    name = "net.bodz.lily.schema.pub.ArticleTalkVote"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(ArticleTalkVoteType.declaredProperty);
    }

}
