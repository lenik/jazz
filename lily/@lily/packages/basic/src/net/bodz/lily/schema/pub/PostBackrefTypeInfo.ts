import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import PostBackrefValidators from "./PostBackrefValidators";
import _PostBackref_stuff_TypeInfo from "./_PostBackref_stuff_TypeInfo";

export class PostBackrefTypeInfo extends _PostBackref_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.pub.PostBackref"; }
    get icon() { return "fa-tag"; }

    validators = new PostBackrefValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default PostBackrefTypeInfo;
