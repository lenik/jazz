import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import PostCategory from "./PostCategory";
import _PostCategory_stuff_Validators from "./_PostCategory_stuff_Validators";

export class _PostCategory_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "postcat";

    get name() { return "net.bodz.lily.schema.pub.PostCategory"; }
    get icon() { return "fa-tag"; }

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

    validators = new _PostCategory_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),
        name: property({ type: STRING, precision: 30, validator: this.validators.validateName }),
        depth: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateDepth }),
        refCount: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateRefCount }),

        parent: property({ type: this, validator: this.validators.validateParent }),
        parentId: property({ type: INT, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _PostCategory_stuff_TypeInfo;
