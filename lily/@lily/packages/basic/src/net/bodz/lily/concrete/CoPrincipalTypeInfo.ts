import { primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import IdEntityTypeInfo from './IdEntityTypeInfo';
import CoPrincipalValidators from './CoPrincipalValidators';
import { JSON_VARIANT } from '@skeljs/core/src/lang/bas-info';
import { INT, STRING } from '@skeljs/core/src/lang/baseinfo';

export class CoPrincipalTypeInfo extends IdEntityTypeInfo {

    get name() { return "net.bodz.lily.concrete.CoPrincipal"; }
    get icon() { return "fa-user-lock"; }
    get label() { return "Concrete Principal"; }
    get description() { return "Access control entity."; }

    validators = new CoPrincipalValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
            name: property({ type: STRING, precision: 30, }),
            properties: property({ type: JSON_VARIANT }),
        });
    }

    constructor() {
        super(INT);
    }

}

export default CoPrincipalTypeInfo;