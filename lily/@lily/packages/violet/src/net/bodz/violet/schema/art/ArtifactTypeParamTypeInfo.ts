import ArtifactTypeParam from "./ArtifactTypeParam";
import ArtifactTypeParamValidators from "./ArtifactTypeParamValidators";
import _ArtifactTypeParam_stuff_TypeInfo from "./_ArtifactTypeParam_stuff_TypeInfo";

export class ArtifactTypeParamTypeInfo extends _ArtifactTypeParam_stuff_TypeInfo {

    readonly validators = new ArtifactTypeParamValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.art.ArtifactTypeParam"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new ArtifactTypeParam();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new ArtifactTypeParamTypeInfo();

}

export default ArtifactTypeParamTypeInfo;

export const ArtifactTypeParam_TYPE = ArtifactTypeParamTypeInfo.INSTANCE;
