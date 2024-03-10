import ArtifactDocValidators from "./ArtifactDocValidators";
import _ArtifactDoc_stuff_TypeInfo from "./_ArtifactDoc_stuff_TypeInfo";

export class ArtifactDocTypeInfo extends _ArtifactDoc_stuff_TypeInfo {

    readonly validators = new ArtifactDocValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.art.ArtifactDoc"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new ArtifactDocTypeInfo();

}

export default ArtifactDocTypeInfo;
