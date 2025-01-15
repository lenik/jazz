import { INT, STRING } from "skel01-core/src/lang/baseinfo";
import type { int } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import CoImagedTypeInfo from "lily-basic/src/net/bodz/lily/concrete/CoImagedTypeInfo";

import { FabStdTestCategory_TYPE } from "./FabStdTestCategoryTypeInfo";
import { FabStdTest_TYPE } from "./FabStdTestTypeInfo";
import _FabStdTest_stuff_Validators from "./_FabStdTest_stuff_Validators";

export class _FabStdTest_stuff_TypeInfo extends CoImagedTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "fabstdtest";

    static readonly FIELD_CODE = "code";
    static readonly FIELD_CATEGORY_ID = "cat";
    static readonly FIELD_PARENT_ID = "parent";
    static readonly FIELD_DEPTH = "depth";
    static readonly FIELD_REF_COUNT = "nref";

    static readonly N_CODE = 20;
    static readonly N_CATEGORY_ID = 10;
    static readonly N_PARENT_ID = 10;
    static readonly N_DEPTH = 10;
    static readonly N_REF_COUNT = 10;

    readonly validators = new _FabStdTest_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.violet.schema.fab.FabStdTest"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            code: property({ type: STRING, precision: 20, validator: this.validators.validateCode }),
            depth: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateDepth }),
            refCount: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateRefCount }),

            category: property({ type: FabStdTestCategory_TYPE, nullable: false, validator: this.validators.validateCategory }),
            categoryId: property({ type: INT, nullable: false, precision: 10 }),

            parent: property({ type: this, validator: this.validators.validateParent }),
            parentId: property({ type: INT, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _FabStdTest_stuff_TypeInfo();

}

export default _FabStdTest_stuff_TypeInfo;

export const _FabStdTest_stuff_TYPE = _FabStdTest_stuff_TypeInfo.INSTANCE;
