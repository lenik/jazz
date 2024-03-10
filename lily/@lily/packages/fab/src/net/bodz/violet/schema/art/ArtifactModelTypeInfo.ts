import { STRING } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import IValidControl from "@lily/basic/src/net/bodz/lily/concrete/util/IValidControl";
import ArtifactModelValidators from "@lily/violet/src/net/bodz/violet/schema/art/ArtifactModelValidators";
import FabCost from "@lily/violet/src/net/bodz/violet/schema/art/FabCost";
import _ArtifactModel_stuff_TypeInfo from "@lily/violet/src/net/bodz/violet/schema/art/_ArtifactModel_stuff_TypeInfo";

export class ArtifactModelTypeInfo extends _ArtifactModel_stuff_TypeInfo {

    readonly validators = new ArtifactModelValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.art.ArtifactModel"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            cost: property({ type: FabCost.TYPE, validator: this.validators.validateCost }),
            modelName: property({ type: STRING, validator: this.validators.validateModelName }),
            validControl: property({ type: IValidControl.TYPE, validator: this.validators.validateValidControl }),
        });
    }

    static readonly INSTANCE = new ArtifactModelTypeInfo();

}

export default ArtifactModelTypeInfo;
