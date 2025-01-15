import { BIG_DECIMAL, INT } from "skel01-core/src/lang/baseinfo";
import type { int } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import IdEntityTypeInfo from "lily-basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";
import { ArtifactModel_TYPE } from "lily-violet/src/net/bodz/violet/schema/art/ArtifactModelTypeInfo";
import { Artifact_TYPE } from "lily-violet/src/net/bodz/violet/schema/art/ArtifactTypeInfo";

import { FabStdProcess_TYPE } from "./FabStdProcessTypeInfo";
import _FabStdProcessInput_stuff_Validators from "./_FabStdProcessInput_stuff_Validators";

export class _FabStdProcessInput_stuff_TypeInfo extends IdEntityTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "fabstdproc_in";

    static readonly FIELD_PROCESS_ID = "proc";
    static readonly FIELD_MODEL_ID = "model";
    static readonly FIELD_ARTIFACT_ID = "art";
    static readonly FIELD_QUANTITY = "qty";

    static readonly N_PROCESS_ID = 10;
    static readonly N_MODEL_ID = 10;
    static readonly N_ARTIFACT_ID = 10;
    static readonly N_QUANTITY = 20;

    readonly validators = new _FabStdProcessInput_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.violet.schema.fab.FabStdProcessInput"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            quantity: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validateQuantity }),

            process: property({ type: FabStdProcess_TYPE, nullable: false, validator: this.validators.validateProcess }),
            processId: property({ type: INT, nullable: false, precision: 10 }),

            model: property({ type: ArtifactModel_TYPE, validator: this.validators.validateModel }),
            modelId: property({ type: INT, precision: 10 }),

            artifact: property({ type: Artifact_TYPE, validator: this.validators.validateArtifact }),
            artifactId: property({ type: INT, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _FabStdProcessInput_stuff_TypeInfo();

}

export default _FabStdProcessInput_stuff_TypeInfo;

export const _FabStdProcessInput_stuff_TYPE = _FabStdProcessInput_stuff_TypeInfo.INSTANCE;
