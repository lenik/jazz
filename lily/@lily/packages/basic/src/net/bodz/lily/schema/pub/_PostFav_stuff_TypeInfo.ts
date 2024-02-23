import type { long } from "@skeljs/core/src/lang/type";
import FavRecordTypeInfo from "@skeljs/dba/src/net/bodz/lily/concrete/FavRecordTypeInfo";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import _PostFav_stuff_Validators from "./_PostFav_stuff_Validators";

export class _PostFav_stuff_TypeInfo extends FavRecordTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "post_fav";

    name = "net.bodz.lily.schema.pub.PostFav"
    icon = "fa-tag"

    static FIELD_POST_ID = "post";

    static N_POST_ID = 19;

    static validators = new _PostFav_stuff_Validators();

    static declaredProperty: EntityPropertyMap = {

        post: property({ type: net.bodz.lily.schema.pub.PostTypeInfo, nullable: false, validator: this.validators.validatePost }),
        postId: property({ type: "long", nullable: false, precision: 19 }),
    }

    constructor() {
        super();
        this.declare(_PostFav_stuff_TypeInfo.declaredProperty);
    }

}

export default _PostFav_stuff_TypeInfo;
