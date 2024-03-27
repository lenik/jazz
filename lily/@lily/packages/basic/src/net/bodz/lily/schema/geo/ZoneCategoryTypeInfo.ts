import ZoneCategory from "./ZoneCategory";
import ZoneCategoryValidators from "./ZoneCategoryValidators";
import _ZoneCategory_stuff_TypeInfo from "./_ZoneCategory_stuff_TypeInfo";

export class ZoneCategoryTypeInfo extends _ZoneCategory_stuff_TypeInfo {

    readonly validators = new ZoneCategoryValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.geo.ZoneCategory"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new ZoneCategory();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new ZoneCategoryTypeInfo();

}

export default ZoneCategoryTypeInfo;
