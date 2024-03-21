import { primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import CoCodeTypeInfo from './CoCodeTypeInfo';
import CoParameterValidators from './CoParameterValidators';
import TypeInfo from '@skeljs/core/src/lang/TypeInfo';

export class CoParameterTypeInfo extends CoCodeTypeInfo {

    readonly validators = new CoParameterValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.concrete.CoParameter"; }
    get icon() { return "fa-adjust"; }
    get label() { return "Concrete Parameter"; }
    get description() { return "Definition of a parameter type."; }

    override preamble() {
        super.preamble();
        this.declare({
            // id: primaryKey({ type: 'number', precision: 20, })
        });
    }

    static readonly INSTANCE = new CoParameterTypeInfo();
    
}

export default CoParameterTypeInfo;