//Auto generated code
//don't change things here, it will be overwritten
/* 
    Created on : Nov 17, 2018, 17:05:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import { Fieldsearcher } from './fieldsearcher';

export class Datesearcher extends Fieldsearcher {
	constructor(fieldname) { 
        super(fieldname); 
        this.type = this.datesearcher;
        this.operators = [];
    }

	operators: number[] = [];

    public reset() {
        this.values = [];
        this.operators = [];
    }

    public toJSON() {
        var json = {
	        values: this.valuestoJSON(), 
	        operators: this.operators,
	        andor: this.andoroperator
        };
        return json;
    }

}
