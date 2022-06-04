//Auto generated code
//don't change things here, it will be overwritten
/* 
    Created on : Nov 18, 2018, 11:43:01 PM
    Generated on 18.10.2018 10:37
    Author     : Franky Laseure
*/

import { Fieldsearcher } from './fieldsearcher';

export class Timesearcher extends Fieldsearcher {
	constructor(fieldname) { 
        super(fieldname); 
        this.type = this.timesearcher;
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
