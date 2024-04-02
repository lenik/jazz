import ExternalSite from "./ExternalSite";
import ExternalSiteValidators from "./ExternalSiteValidators";
import _ExternalSite_stuff_TypeInfo from "./_ExternalSite_stuff_TypeInfo";

export class ExternalSiteTypeInfo extends _ExternalSite_stuff_TypeInfo {

    readonly validators = new ExternalSiteValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.inet.ExternalSite"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new ExternalSite();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new ExternalSiteTypeInfo();

}

export default ExternalSiteTypeInfo;

export const ExternalSite_TYPE = ExternalSiteTypeInfo.INSTANCE;
