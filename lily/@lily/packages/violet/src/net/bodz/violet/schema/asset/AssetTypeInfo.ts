import AssetValidators from "./AssetValidators";
import _Asset_stuff_TypeInfo from "./_Asset_stuff_TypeInfo";

export class AssetTypeInfo extends _Asset_stuff_TypeInfo {

    readonly validators = new AssetValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.asset.Asset"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new AssetTypeInfo();

}

export default AssetTypeInfo;
