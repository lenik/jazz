import OrgUnitAsset from "./OrgUnitAsset";
import OrgUnitAssetValidators from "./OrgUnitAssetValidators";
import _OrgUnitAsset_stuff_TypeInfo from "./_OrgUnitAsset_stuff_TypeInfo";

export class OrgUnitAssetTypeInfo extends _OrgUnitAsset_stuff_TypeInfo {

    readonly validators = new OrgUnitAssetValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.asset.OrgUnitAsset"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new OrgUnitAsset();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new OrgUnitAssetTypeInfo();

}

export default OrgUnitAssetTypeInfo;

export const OrgUnitAsset_TYPE = OrgUnitAssetTypeInfo.INSTANCE;
