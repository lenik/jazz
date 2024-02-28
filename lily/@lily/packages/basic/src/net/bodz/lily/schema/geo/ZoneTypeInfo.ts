import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { STRING } from "@skeljs/core/src/lang/baseinfo";
import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import GeoLocation from "../../concrete/util/GeoLocation";
import ZoneValidators from "./ZoneValidators";
import _Zone_stuff_TypeInfo from "./_Zone_stuff_TypeInfo";

export class ZoneTypeInfo extends _Zone_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.geo.Zone"; }
    get icon() { return "fa-tag"; }

    validators = new ZoneValidators(this);

    declaredProperty: EntityPropertyMap = {
        fullPath: property({ type: STRING, validator: this.validators.validateFullPath }),
        geo: property({ type: GeoLocation.TYPE, validator: this.validators.validateGeo }),
        properties: property({ type: JSON_VARIANT, validator: this.validators.validateProperties }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default ZoneTypeInfo;
