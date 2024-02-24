import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import GeoLocation from "../../concrete/util/GeoLocation";
import ZoneValidators from "./ZoneValidators";
import _Zone_stuff_TypeInfo from "./_Zone_stuff_TypeInfo";

export class ZoneTypeInfo extends _Zone_stuff_TypeInfo {

    name = "net.bodz.lily.schema.geo.Zone"
    icon = "fa-tag"

    validators = new ZoneValidators(this);

    declaredProperty: EntityPropertyMap = {
        fullPath: property({ type: "string", validator: this.validators.validateFullPath }),
        geo: property({ type: "GeoLocation", validator: this.validators.validateGeo }),
        properties: property({ type: "any", validator: this.validators.validateProperties }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default ZoneTypeInfo;
