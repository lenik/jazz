import RegionLevel from "./RegionLevel";
import RegionLevelValidators from "./RegionLevelValidators";
import _RegionLevel_stuff_TypeInfo from "./_RegionLevel_stuff_TypeInfo";

export class RegionLevelTypeInfo extends _RegionLevel_stuff_TypeInfo {

    readonly validators = new RegionLevelValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.store.RegionLevel"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new RegionLevel();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new RegionLevelTypeInfo();

}

export default RegionLevelTypeInfo;

export const RegionLevel_TYPE = RegionLevelTypeInfo.INSTANCE;
