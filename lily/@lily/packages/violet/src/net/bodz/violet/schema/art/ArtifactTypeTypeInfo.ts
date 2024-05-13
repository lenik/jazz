import ArtifactType from "./ArtifactType";
import ArtifactTypeValidators from "./ArtifactTypeValidators";
import _ArtifactType_stuff_TypeInfo from "./_ArtifactType_stuff_TypeInfo";

export class ArtifactTypeTypeInfo extends _ArtifactType_stuff_TypeInfo {

    readonly validators = new ArtifactTypeValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.art.ArtifactType"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new ArtifactType();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new ArtifactTypeTypeInfo();

}

export default ArtifactTypeTypeInfo;

export const ArtifactType_TYPE = ArtifactTypeTypeInfo.INSTANCE;
