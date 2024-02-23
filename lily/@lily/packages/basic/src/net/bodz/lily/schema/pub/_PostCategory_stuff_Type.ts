import type { integer } from "@skeljs/core/src/lang/type";
import CoEntityType from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { PostCategory } from "./PostCategory";
import PostCategoryValidators from "./PostCategoryValidators";

export class _PostCategory_stuff_Type extends CoEntityType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "postcat";

    name = "net.bodz.lily.schema.pub.PostCategory"
    icon = "fa-tag"

    static FIELD_ID = "id";
    static FIELD_NAME = "name";
    static FIELD_PARENT_ID = "parent";
    static FIELD_DEPTH = "depth";
    static FIELD_REF_COUNT = "nref";

    static N_ID = 10;
    static N_NAME = 30;
    static N_PARENT_ID = 10;
    static N_DEPTH = 10;
    static N_REF_COUNT = 10;

    static validators = new PostCategoryValidators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        name: property({ type: "string", precision: 30, validator: this.validators.validateName }),
        depth: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateDepth }),
        refCount: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateRefCount }),

        parent: property({ type: PostCategory.TYPE, validator: this.validators.validateParent }),
        parentId: property({ type: "integer", precision: 10 }),
    }

    constructor() {
        super();
        this.declare(_PostCategory_stuff_Type.declaredProperty);
    }

}

export default _PostCategory_stuff_Type;
