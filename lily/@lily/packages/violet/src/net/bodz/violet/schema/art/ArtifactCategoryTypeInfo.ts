import ArtifactCategoryValidators from "./ArtifactCategoryValidators";
import _ArtifactCategory_stuff_TypeInfo from "./_ArtifactCategory_stuff_TypeInfo";

export class ArtifactCategoryTypeInfo extends _ArtifactCategory_stuff_TypeInfo {

    readonly validators = new ArtifactCategoryValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.art.ArtifactCategory"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new ArtifactCategoryTypeInfo();

}

export default ArtifactCategoryTypeInfo;
