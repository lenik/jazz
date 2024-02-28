import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import PostVoteValidators from "./PostVoteValidators";
import _PostVote_stuff_TypeInfo from "./_PostVote_stuff_TypeInfo";

export class PostVoteTypeInfo extends _PostVote_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.pub.PostVote"; }
    get icon() { return "fa-tag"; }

    validators = new PostVoteValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default PostVoteTypeInfo;
