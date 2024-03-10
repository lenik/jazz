import RegionTagValidators from "./RegionTagValidators";
import _RegionTag_stuff_TypeInfo from "./_RegionTag_stuff_TypeInfo";

export class RegionTagTypeInfo extends _RegionTag_stuff_TypeInfo {

    readonly validators = new RegionTagValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.store.RegionTag"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new RegionTagTypeInfo();

}

export default RegionTagTypeInfo;
