import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PostVoteValidators from "./PostVoteValidators";
import _PostVote_stuff_Type from "./_PostVote_stuff_Type";

// Type Info

export class PostVoteType extends _PostVote_stuff_Type {

    name = "net.bodz.lily.schema.pub.PostVote"
    icon = "fa-tag"

    static validators = new PostVoteValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PostVoteType.declaredProperty);
    }

}

export default PostVote;
