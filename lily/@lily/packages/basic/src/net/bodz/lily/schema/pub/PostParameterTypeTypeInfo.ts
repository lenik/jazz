import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import PostParameterTypeValidators from "./PostParameterTypeValidators";
import _PostParameterType_stuff_TypeInfo from "./_PostParameterType_stuff_TypeInfo";

export class PostParameterTypeTypeInfo extends _PostParameterType_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.pub.PostParameterType"; }
    get icon() { return "fa-tag"; }

    validators = new PostParameterTypeValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default PostParameterTypeTypeInfo;
