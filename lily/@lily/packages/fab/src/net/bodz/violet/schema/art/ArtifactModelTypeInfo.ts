import { STRING } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import { IValidControl_TYPE } from "@lily/basic/src/net/bodz/lily/concrete/util/IValidControlTypeInfo";
import ArtifactModelValidators from "@lily/violet/src/net/bodz/violet/schema/art/ArtifactModelValidators";
import { FabCost_TYPE } from "@lily/violet/src/net/bodz/violet/schema/art/FabCostTypeInfo";
import _ArtifactModel_stuff_TypeInfo from "@lily/violet/src/net/bodz/violet/schema/art/_ArtifactModel_stuff_TypeInfo";

import ArtifactModel from "./ArtifactModel";

export class ArtifactModelTypeInfo extends _ArtifactModel_stuff_TypeInfo {

    readonly validators = new ArtifactModelValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.art.ArtifactModel"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new ArtifactModel();
    }

    override preamble() {
        super.preamble();
        this.declare({
            cost: property({ type: FabCost_TYPE, validator: this.validators.validateCost }),
            modelName: property({ type: STRING, precision: 40, validator: this.validators.validateModelName }),
            validControl: property({ type: IValidControl_TYPE, validator: this.validators.validateValidControl }),
        });
    }

    static readonly INSTANCE = new ArtifactModelTypeInfo();

}

export default ArtifactModelTypeInfo;

export const ArtifactModel_TYPE = ArtifactModelTypeInfo.INSTANCE;
