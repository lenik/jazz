import PersonAsset from "./PersonAsset";
import PersonAssetValidators from "./PersonAssetValidators";
import _PersonAsset_stuff_TypeInfo from "./_PersonAsset_stuff_TypeInfo";

export class PersonAssetTypeInfo extends _PersonAsset_stuff_TypeInfo {

    readonly validators = new PersonAssetValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.asset.PersonAsset"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new PersonAsset();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new PersonAssetTypeInfo();

}

export default PersonAssetTypeInfo;
