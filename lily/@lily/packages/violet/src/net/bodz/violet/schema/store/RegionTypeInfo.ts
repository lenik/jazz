import { STRING } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import Dim3d from "../art/Dim3d";
import RegionValidators from "./RegionValidators";
import _Region_stuff_TypeInfo from "./_Region_stuff_TypeInfo";

export class RegionTypeInfo extends _Region_stuff_TypeInfo {

    readonly validators = new RegionValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.store.Region"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            bbox: property({ type: Dim3d.TYPE, validator: this.validators.validateBbox }),
            fullPath: property({ type: STRING, validator: this.validators.validateFullPath }),
            position: property({ type: Dim3d.TYPE, validator: this.validators.validatePosition }),
        });
    }

    static readonly INSTANCE = new RegionTypeInfo();

}

export default RegionTypeInfo;