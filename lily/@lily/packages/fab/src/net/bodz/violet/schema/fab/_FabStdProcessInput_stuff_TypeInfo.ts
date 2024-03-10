import { BIG_DECIMAL, INT } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import CoEntityTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoEntityTypeInfo";
import Artifact from "@lily/violet/src/net/bodz/violet/schema/art/Artifact";

import ArtifactModel from "../art/ArtifactModel";
import FabStdProcess from "./FabStdProcess";
import _FabStdProcessInput_stuff_Validators from "./_FabStdProcessInput_stuff_Validators";

export class _FabStdProcessInput_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "fabstdproc_in";

    static readonly FIELD_ID = "id";
    static readonly FIELD_PROCESS_ID = "proc";
    static readonly FIELD_MODEL_ID = "model";
    static readonly FIELD_ARTIFACT_ID = "art";
    static readonly FIELD_QUANTITY = "qty";

    static readonly N_ID = 10;
    static readonly N_PROCESS_ID = 10;
    static readonly N_MODEL_ID = 10;
    static readonly N_ARTIFACT_ID = 10;
    static readonly N_QUANTITY = 20;

    readonly validators = new _FabStdProcessInput_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.fab.FabStdProcessInput"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),
            quantity: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validateQuantity }),

            process: property({ type: FabStdProcess.TYPE, nullable: false, validator: this.validators.validateProcess }),
            processId: property({ type: INT, nullable: false, precision: 10 }),

            model: property({ type: ArtifactModel.TYPE, validator: this.validators.validateModel }),
            modelId: property({ type: INT, precision: 10 }),

            artifact: property({ type: Artifact.TYPE, validator: this.validators.validateArtifact }),
            artifactId: property({ type: INT, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _FabStdProcessInput_stuff_TypeInfo();

}

export default _FabStdProcessInput_stuff_TypeInfo;
