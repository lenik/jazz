import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import PostTagValidators from "./PostTagValidators";
import _PostTag_stuff_TypeInfo from "./_PostTag_stuff_TypeInfo";

export class PostTagTypeInfo extends _PostTag_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.pub.PostTag"; }
    get icon() { return "fa-tag"; }

    validators = new PostTagValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default PostTagTypeInfo;
