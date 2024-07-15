import { INT, STRING } from '@skeljs/core/src/lang/baseinfo';
import EntityType from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import { property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import IArtifactExtrasValidators from './IArtifactExtrasValidators';
import IArtifactExtras from './IArtifactExtras';

export class IArtifactExtrasTypeInfo extends EntityType {

    readonly validators = new IArtifactExtrasValidators(this);

    get name() { return "net.bodz.violet.schema.art.IArtifactExtras"; }
    get icon() { return "fa-image"; }
    get label() { return "Artifact Extras"; }
    get description() { return "color/caution/etc."; }

    override create() {
        return new IArtifactExtras();
    }

    override preamble() {
        this.declare({
            caution: property({ type: STRING, icon: "far-tag" }),
            color: property({ type: STRING, icon: "far-tag" }),
            supplyDelay: property({ type: INT, icon: "far-tag" }),
        });
    }

    static readonly INSTANCE = new IArtifactExtrasTypeInfo();

}

export default IArtifactExtrasTypeInfo;

export const IArtifactExtras_TYPE = IArtifactExtrasTypeInfo.INSTANCE;
