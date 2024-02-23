import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PostFavValidators from "./PostFavValidators";
import _PostFav_stuff_Type from "./_PostFav_stuff_Type";

// Type Info

export class PostFavType extends _PostFav_stuff_Type {

    name = "net.bodz.lily.schema.pub.PostFav"
    icon = "fa-tag"

    static validators = new PostFavValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PostFavType.declaredProperty);
    }

}

export default PostFav;
