import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PostValidators from "./PostValidators";
import _Post_stuff_TypeInfo from "./_Post_stuff_TypeInfo";

export class PostTypeInfo extends _Post_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.Post"
    icon = "fa-tag"

    validators = new PostValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default PostTypeInfo;
