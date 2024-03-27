import OrgAsset from "./OrgAsset";
import OrgAssetValidators from "./OrgAssetValidators";
import _OrgAsset_stuff_TypeInfo from "./_OrgAsset_stuff_TypeInfo";

export class OrgAssetTypeInfo extends _OrgAsset_stuff_TypeInfo {

    readonly validators = new OrgAssetValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.asset.OrgAsset"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new OrgAsset();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new OrgAssetTypeInfo();

}

export default OrgAssetTypeInfo;
