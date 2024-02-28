import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import PostValidators from "./PostValidators";
import _Post_stuff_TypeInfo from "./_Post_stuff_TypeInfo";

export class PostTypeInfo extends _Post_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.pub.Post"; }
    get icon() { return "fa-tag"; }

    validators = new PostValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default PostTypeInfo;
