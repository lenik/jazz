import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PostFavValidators from "./PostFavValidators";
import _PostFav_stuff_TypeInfo from "./_PostFav_stuff_TypeInfo";

export class PostFavTypeInfo extends _PostFav_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.PostFav"
    icon = "fa-tag"

    validators = new PostFavValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default PostFavTypeInfo;
