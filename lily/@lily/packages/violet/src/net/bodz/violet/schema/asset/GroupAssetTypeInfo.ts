import GroupAssetValidators from "./GroupAssetValidators";
import _GroupAsset_stuff_TypeInfo from "./_GroupAsset_stuff_TypeInfo";

export class GroupAssetTypeInfo extends _GroupAsset_stuff_TypeInfo {

    readonly validators = new GroupAssetValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.asset.GroupAsset"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new GroupAssetTypeInfo();

}

export default GroupAssetTypeInfo;
