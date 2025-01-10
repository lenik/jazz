import { DOUBLE, MAP } from "skel01-core/src/lang/baseinfo";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import { Uom_TYPE } from "@lily/basic/src/net/bodz/lily/schema/util/UomTypeInfo";

import Artifact from "./Artifact";
import ArtifactValidators from "./ArtifactValidators";
import { IArtifactExtras_TYPE } from "./IArtifactExtrasTypeInfo";
import _Artifact_stuff_TypeInfo from "./_Artifact_stuff_TypeInfo";

export class ArtifactTypeInfo extends _Artifact_stuff_TypeInfo {

    readonly validators = new ArtifactValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.art.Artifact"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new Artifact();
    }

    override preamble() {
        super.preamble();
        this.declare({
            convMap: property({ type: MAP(Uom_TYPE, DOUBLE), validator: this.validators.validateConvMap }),
            extras: property({ type: IArtifactExtras_TYPE, validator: this.validators.validateExtras }),
        });
    }

    static readonly INSTANCE = new ArtifactTypeInfo();

}

export default ArtifactTypeInfo;

export const Artifact_TYPE = ArtifactTypeInfo.INSTANCE;
