import UserAssetValidators from "./UserAssetValidators";
import _UserAsset_stuff_TypeInfo from "./_UserAsset_stuff_TypeInfo";

export class UserAssetTypeInfo extends _UserAsset_stuff_TypeInfo {

    readonly validators = new UserAssetValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.asset.UserAsset"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new UserAssetTypeInfo();

}

export default UserAssetTypeInfo;
