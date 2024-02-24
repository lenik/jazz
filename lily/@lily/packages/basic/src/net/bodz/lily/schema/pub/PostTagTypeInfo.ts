import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PostTagValidators from "./PostTagValidators";
import _PostTag_stuff_TypeInfo from "./_PostTag_stuff_TypeInfo";

export class PostTagTypeInfo extends _PostTag_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.PostTag"
    icon = "fa-tag"

    validators = new PostTagValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default PostTagTypeInfo;
