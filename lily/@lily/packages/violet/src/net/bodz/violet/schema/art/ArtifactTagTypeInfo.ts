import ArtifactTagValidators from "./ArtifactTagValidators";
import _ArtifactTag_stuff_TypeInfo from "./_ArtifactTag_stuff_TypeInfo";

export class ArtifactTagTypeInfo extends _ArtifactTag_stuff_TypeInfo {

    readonly validators = new ArtifactTagValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.art.ArtifactTag"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new ArtifactTagTypeInfo();

}

export default ArtifactTagTypeInfo;
