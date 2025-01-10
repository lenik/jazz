import { STRING } from "skel01-core/src/lang/baseinfo";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import { Dim3d_TYPE } from "../art/Dim3dTypeInfo";
import Region from "./Region";
import RegionValidators from "./RegionValidators";
import _Region_stuff_TypeInfo from "./_Region_stuff_TypeInfo";

export class RegionTypeInfo extends _Region_stuff_TypeInfo {

    readonly validators = new RegionValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.store.Region"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new Region();
    }

    override preamble() {
        super.preamble();
        this.declare({
            bbox: property({ type: Dim3d_TYPE, validator: this.validators.validateBbox }),
            fullPath: property({ type: STRING, validator: this.validators.validateFullPath }),
            position: property({ type: Dim3d_TYPE, validator: this.validators.validatePosition }),
        });
    }

    static readonly INSTANCE = new RegionTypeInfo();

}

export default RegionTypeInfo;

export const Region_TYPE = RegionTypeInfo.INSTANCE;
