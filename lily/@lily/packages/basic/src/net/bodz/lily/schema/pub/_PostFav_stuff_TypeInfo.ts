import type { long } from "@skeljs/core/src/lang/type";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import FavRecordTypeInfo from "../../concrete/FavRecordTypeInfo";
import PostTypeInfo from "./PostTypeInfo";
import _PostFav_stuff_Validators from "./_PostFav_stuff_Validators";

export class _PostFav_stuff_TypeInfo extends FavRecordTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "post_fav";

    name = "net.bodz.lily.schema.pub.PostFav"
    icon = "fa-tag"

    static FIELD_POST_ID = "post";

    static N_POST_ID = 19;

    validators = new _PostFav_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {

        post: property({ type: PostTypeInfo, nullable: false, validator: this.validators.validatePost }),
        postId: property({ type: "long", nullable: false, precision: 19 }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _PostFav_stuff_TypeInfo;
