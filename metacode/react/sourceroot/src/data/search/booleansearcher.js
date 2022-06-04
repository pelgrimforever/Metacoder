//Auto generated code
//don't change things here, it will be overwritten
/* 
    Created on : Nov 17, 2018, 13:37:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import Fieldsearcher from './fieldsearcher';

class Booleansearcher extends Fieldsearcher {
	constructor(fieldname) { 
    super(fieldname); 
    this.type = this.booleansearcher;
  }

  reset = () => {
    this.values = [];
  }

  toJSON = () => {
    var json = {
	    values: this.valuestoJSON()
    };
    return json;
  }

}

export default Booleansearcher;
