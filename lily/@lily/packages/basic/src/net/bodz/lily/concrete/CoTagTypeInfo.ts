import { primaryKey, property } from 'skel01-dba/src/net/bodz/lily/entity/EntityType';
import CoCodeTypeInfo from './CoCodeTypeInfo';
import CoTagValidators from './CoTagValidators';
import TypeInfo from 'skel01-core/src/lang/TypeInfo';

export class CoTagTypeInfo extends CoCodeTypeInfo {

    readonly validators = new CoTagValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.concrete.CoTagType"; }
    get icon() { return "fa-tag"; }
    get label() { return "Concrete Tag Type"; }
    get description() { return "Definition of a tag type."; }

    override preamble() {
        super.preamble();
        this.declare({
            // id: primaryKey({ type: 'number', precision: 20, })
        });
    }

    static readonly INSTANCE = new CoTagTypeInfo();

}

export default CoTagTypeInfo;
export const CoTag_TYPE = CoTagTypeInfo.INSTANCE;
