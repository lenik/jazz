import { primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import CoImagedTypeInfo from './CoImagedTypeInfo';
import CoPrincipalValidators from './CoPrincipalValidators';
import { JSON_VARIANT } from '@skeljs/core/src/lang/bas-info';
import { INT, STRING } from '@skeljs/core/src/lang/baseinfo';

export class CoPrincipalTypeInfo extends CoImagedTypeInfo {

    readonly validators = new CoPrincipalValidators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.lily.concrete.CoPrincipal"; }
    get icon() { return "fa-user-lock"; }
    get label() { return "Concrete Principal"; }
    get description() { return "Access control entity."; }

    override preamble() {
        super.preamble();
        this.declare({
            name: property({ type: STRING, precision: 30, }),
            properties: property({ type: JSON_VARIANT, nullable: true, icon: "far-bars" }),
        });
    }

    static readonly INSTANCE = new CoPrincipalTypeInfo();
    
}

export default CoPrincipalTypeInfo;
export const CoPrincipal_TYPE = CoPrincipalTypeInfo.INSTANCE;
