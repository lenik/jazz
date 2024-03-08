import ZoneCategoryValidators from "./ZoneCategoryValidators";
import _ZoneCategory_stuff_TypeInfo from "./_ZoneCategory_stuff_TypeInfo";

export class ZoneCategoryTypeInfo extends _ZoneCategory_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.geo.ZoneCategory"; }
    get icon() { return "fa-tag"; }

    validators = new ZoneCategoryValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default ZoneCategoryTypeInfo;
