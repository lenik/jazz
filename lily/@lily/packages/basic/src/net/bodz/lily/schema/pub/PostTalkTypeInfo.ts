import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import PostTalkValidators from "./PostTalkValidators";
import _PostTalk_stuff_TypeInfo from "./_PostTalk_stuff_TypeInfo";

export class PostTalkTypeInfo extends _PostTalk_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.pub.PostTalk"; }
    get icon() { return "fa-tag"; }

    validators = new PostTalkValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default PostTalkTypeInfo;
