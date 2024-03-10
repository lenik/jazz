import CoPhaseTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoPhaseTypeInfo";

import _ArtifactPhase_stuff_Validators from "./_ArtifactPhase_stuff_Validators";

export class _ArtifactPhase_stuff_TypeInfo extends CoPhaseTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "artphase";

    readonly validators = new _ArtifactPhase_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.art.ArtifactPhase"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new _ArtifactPhase_stuff_TypeInfo();

}

export default _ArtifactPhase_stuff_TypeInfo;
