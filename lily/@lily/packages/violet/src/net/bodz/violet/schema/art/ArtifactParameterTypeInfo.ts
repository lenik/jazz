import ArtifactParameterValidators from "./ArtifactParameterValidators";
import _ArtifactParameter_stuff_TypeInfo from "./_ArtifactParameter_stuff_TypeInfo";

export class ArtifactParameterTypeInfo extends _ArtifactParameter_stuff_TypeInfo {

    readonly validators = new ArtifactParameterValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.art.ArtifactParameter"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new ArtifactParameterTypeInfo();

}

export default ArtifactParameterTypeInfo;
