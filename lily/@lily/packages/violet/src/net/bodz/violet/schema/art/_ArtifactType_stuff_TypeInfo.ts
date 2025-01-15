import { INT } from "skel01-core/src/lang/baseinfo";
import type { int } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import IdEntityTypeInfo from "lily-basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";
import { UomRow_TYPE } from "lily-basic/src/net/bodz/lily/schema/util/UomRowTypeInfo";

import { ArtifactCategory_TYPE } from "./ArtifactCategoryTypeInfo";
import { ArtifactType_TYPE } from "./ArtifactTypeTypeInfo";
import _ArtifactType_stuff_Validators from "./_ArtifactType_stuff_Validators";

export class _ArtifactType_stuff_TypeInfo extends IdEntityTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "arttype";

    static readonly FIELD_CATEGORY_ID = "cat";
    static readonly FIELD_PARENT_ID = "parent";
    static readonly FIELD_UOM_ID = "uom";

    static readonly N_CATEGORY_ID = 10;
    static readonly N_PARENT_ID = 10;
    static readonly N_UOM_ID = 10;

    readonly validators = new _ArtifactType_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.violet.schema.art.ArtifactType"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({

            uom: property({ type: UomRow_TYPE, validator: this.validators.validateUom }),
            uomId: property({ type: INT, precision: 10 }),

            parent: property({ type: this, validator: this.validators.validateParent }),
            parentId: property({ type: INT, precision: 10 }),

            category: property({ type: ArtifactCategory_TYPE, validator: this.validators.validateCategory }),
            categoryId: property({ type: INT, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _ArtifactType_stuff_TypeInfo();

}

export default _ArtifactType_stuff_TypeInfo;

export const _ArtifactType_stuff_TYPE = _ArtifactType_stuff_TypeInfo.INSTANCE;
