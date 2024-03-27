import { STRING } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import GeoLocation from "../../concrete/util/GeoLocation";
import Zone from "./Zone";
import ZoneValidators from "./ZoneValidators";
import _Zone_stuff_TypeInfo from "./_Zone_stuff_TypeInfo";

export class ZoneTypeInfo extends _Zone_stuff_TypeInfo {

    readonly validators = new ZoneValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.geo.Zone"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new Zone();
    }

    override preamble() {
        super.preamble();
        this.declare({
            fullPath: property({ type: STRING, validator: this.validators.validateFullPath }),
            geo: property({ type: GeoLocation.TYPE, validator: this.validators.validateGeo }),
        });
    }

    static readonly INSTANCE = new ZoneTypeInfo();

}

export default ZoneTypeInfo;
