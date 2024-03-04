import { EntityPropertyMap, primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import CoCodeTypeInfo from './CoCodeTypeInfo';
import CoPhaseValidators from './CoPhaseValidators';

export class CoPhaseTypeInfo extends CoCodeTypeInfo {

    get name() { return "net.bodz.lily.concrete.CoPhase"; }
    get icon() { return "fa-leaf"; }
    get label() { return "Concrete Phase"; }
    get description() { return "Definition of a phase type."; }

    validators = new CoPhaseValidators(this);

    declaredProperty: EntityPropertyMap = {
        // id: primaryKey({ type: 'number', precision: 20, })
    };

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default CoPhaseTypeInfo;