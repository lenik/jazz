import type { integer } from "@skeljs/core/src/lang/type";
import CoEntityTypeInfo from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityTypeInfo";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import _VAppCat_stuff_Validators from "./_VAppCat_stuff_Validators";

export class _VAppCat_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "vappcat";

    name = "net.bodz.lily.schema.vapp.VAppCat"
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

    static validators = new _VAppCat_stuff_Validators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        name: property({ type: "string", precision: 30, validator: this.validators.validateName }),
        depth: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateDepth }),
        refCount: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateRefCount }),

        parent: property({ type: net.bodz.lily.schema.vapp.VAppCatTypeInfo, validator: this.validators.validateParent }),
        parentId: property({ type: "integer", precision: 10 }),
    }

    constructor() {
        super();
        this.declare(_VAppCat_stuff_TypeInfo.declaredProperty);
    }

}

export default _VAppCat_stuff_TypeInfo;
