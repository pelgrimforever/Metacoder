//Auto generated code
//don't change things here, it will be overwritten
/* 
    Created on : Nov 16, 2018, 17:04:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import Fieldsearcher from './fieldsearcher';

class Stringsearcher extends Fieldsearcher {
	constructor(fieldname) { 
    super(fieldname); 
    this.type = this.stringsearcher;
  }

	compareoperator = this.STRING_EQUAL;

  setCompareoperator = (compare) => {
    this.compareoperator = compare;
  }

  reset = () => {
    this.values = [];
  }

  toJSON = () => {
    var json = {
    values: this.valuestoJSON(), 
    compareoperator: this.compareoperator,
    andor: this.andoroperator
    };
    return json;
  }

}

export default Stringsearcher;
