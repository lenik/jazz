import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PostVoteValidators from "./PostVoteValidators";
import _PostVote_stuff_TypeInfo from "./_PostVote_stuff_TypeInfo";

// Type Info

export class PostVoteTypeInfo extends _PostVote_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.PostVote"
    icon = "fa-tag"

    static validators = new PostVoteValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PostVoteTypeInfo.declaredProperty);
    }

}

export default PostVote;
