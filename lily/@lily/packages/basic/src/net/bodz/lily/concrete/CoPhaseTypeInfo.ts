import { primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import CoCodeTypeInfo from './CoCodeTypeInfo';
import CoPhaseValidators from './CoPhaseValidators';
import TypeInfo from '@skeljs/core/src/lang/TypeInfo';

export class CoPhaseTypeInfo extends CoCodeTypeInfo {

    get name() { return "net.bodz.lily.concrete.CoPhase"; }
    get icon() { return "fa-leaf"; }
    get label() { return "Concrete Phase"; }
    get description() { return "Definition of a phase type."; }

    validators = new CoPhaseValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
            // id: primaryKey({ type: 'number', precision: 20, })
        });
    }

    constructor(selfType: TypeInfo<any>, idType: TypeInfo<any>) {
        super(selfType, idType);
    }

}

export default CoPhaseTypeInfo;