import { primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import CoCodeTypeInfo from './CoCodeTypeInfo';
import CoParameterValidators from './CoParameterValidators';
import TypeInfo from '@skeljs/core/src/lang/TypeInfo';
import { BOOLEAN, INT, STRING } from '@skeljs/core/src/lang/baseinfo';
import UomRow from '../schema/util/UomRow';

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
            type: property({
                type: STRING, nullable: false, precision: 19, icon: "far-cube",
                validator: this.validators.validateType
            }),
            optional: property({
                type: BOOLEAN, nullable: false, icon: "far-key",
            }),
            uom: property({
                type: UomRow.TYPE, icon: "far-bag",
                validator: this.validators.validateUom
            }),
            values: property({
                type: STRING, nullable: true, icon: "far-bars",
                validator: this.validators.validateValues
            }),
        });
    }

    static readonly INSTANCE = new CoParameterTypeInfo();

}

export default CoParameterTypeInfo;
export const CoParameter_TYPE = CoParameterTypeInfo.INSTANCE;
