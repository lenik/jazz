import ArtifactPhase from "./ArtifactPhase";
import ArtifactPhaseValidators from "./ArtifactPhaseValidators";
import _ArtifactPhase_stuff_TypeInfo from "./_ArtifactPhase_stuff_TypeInfo";

export class ArtifactPhaseTypeInfo extends _ArtifactPhase_stuff_TypeInfo {

    readonly validators = new ArtifactPhaseValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.art.ArtifactPhase"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new ArtifactPhase();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new ArtifactPhaseTypeInfo();

}

export default ArtifactPhaseTypeInfo;

export const ArtifactPhase_TYPE = ArtifactPhaseTypeInfo.INSTANCE;
