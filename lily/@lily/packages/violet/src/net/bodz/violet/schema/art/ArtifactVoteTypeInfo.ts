import ArtifactVote from "./ArtifactVote";
import ArtifactVoteValidators from "./ArtifactVoteValidators";
import _ArtifactVote_stuff_TypeInfo from "./_ArtifactVote_stuff_TypeInfo";

export class ArtifactVoteTypeInfo extends _ArtifactVote_stuff_TypeInfo {

    readonly validators = new ArtifactVoteValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.art.ArtifactVote"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new ArtifactVote();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new ArtifactVoteTypeInfo();

}

export default ArtifactVoteTypeInfo;
