import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import PostTagTypeValidators from "./PostTagTypeValidators";
import _PostTagType_stuff_TypeInfo from "./_PostTagType_stuff_TypeInfo";

export class PostTagTypeTypeInfo extends _PostTagType_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.pub.PostTagType"; }
    get icon() { return "fa-tag"; }

    validators = new PostTagTypeValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default PostTagTypeTypeInfo;
