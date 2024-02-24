import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PostVoteValidators from "./PostVoteValidators";
import _PostVote_stuff_TypeInfo from "./_PostVote_stuff_TypeInfo";

export class PostVoteTypeInfo extends _PostVote_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.PostVote"
    icon = "fa-tag"

    validators = new PostVoteValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default PostVoteTypeInfo;
