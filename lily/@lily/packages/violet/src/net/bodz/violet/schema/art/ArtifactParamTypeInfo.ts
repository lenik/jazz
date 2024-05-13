import ArtifactParam from "./ArtifactParam";
import ArtifactParamValidators from "./ArtifactParamValidators";
import _ArtifactParam_stuff_TypeInfo from "./_ArtifactParam_stuff_TypeInfo";

export class ArtifactParamTypeInfo extends _ArtifactParam_stuff_TypeInfo {

    readonly validators = new ArtifactParamValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.art.ArtifactParam"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new ArtifactParam();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new ArtifactParamTypeInfo();

}

export default ArtifactParamTypeInfo;

export const ArtifactParam_TYPE = ArtifactParamTypeInfo.INSTANCE;
