import type { int } from "@skeljs/core/src/lang/basetype";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import VAppCatTypeInfo from "./VAppCatTypeInfo";
import _VAppCat_stuff_Validators from "./_VAppCat_stuff_Validators";

export class _VAppCat_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "vappcat";

    get name() { return "net.bodz.lily.schema.vapp.VAppCat"; }
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

    validators = new _VAppCat_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: this.validators.validateId }),
        name: property({ type: "string", precision: 30, validator: this.validators.validateName }),
        depth: property({ type: "int", nullable: false, precision: 10, validator: this.validators.validateDepth }),
        refCount: property({ type: "int", nullable: false, precision: 10, validator: this.validators.validateRefCount }),

        parent: property({ type: VAppCatTypeInfo, validator: this.validators.validateParent }),
        parentId: property({ type: "int", precision: 10 }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _VAppCat_stuff_TypeInfo;
