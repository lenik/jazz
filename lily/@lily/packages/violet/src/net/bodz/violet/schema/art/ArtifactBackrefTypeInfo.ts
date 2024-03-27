import ArtifactBackref from "./ArtifactBackref";
import ArtifactBackrefValidators from "./ArtifactBackrefValidators";
import _ArtifactBackref_stuff_TypeInfo from "./_ArtifactBackref_stuff_TypeInfo";

export class ArtifactBackrefTypeInfo extends _ArtifactBackref_stuff_TypeInfo {

    readonly validators = new ArtifactBackrefValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.art.ArtifactBackref"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new ArtifactBackref();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new ArtifactBackrefTypeInfo();

}

export default ArtifactBackrefTypeInfo;
