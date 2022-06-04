//Auto generated code
//don't change things here, it will be overwritten
/* 
    Created on : Nov 16, 2018, 17:04:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

class Fieldsearcher {
	constructor(fieldname) { 
    this.fieldname = fieldname;
  }

  //configuration constants
  booleansearcher: number = 0;
  datesearcher: number = 1;
  numbersearcher: number = 2;
  stringsearcher: number = 3;
  timesearcher: number = 4;
  
  //Contants
  AND: number = 0;
  OR: number = 1;

  STRING_EQUAL: number = 0;
  STRING_LIKE: number = 1;

  NUMERIC_EQUAL: number = 0;
  NUMERIC_BIGGER: number = 1;
  NUMERIC_BIGGEREQUAL: number = 2;
  NUMERIC_SMALLER: number = 3;
  NUMERIC_SMALLEREQUAL: number = 4;

  DATETIME_EQUAL: number = 0;
  DATETIME_BIGGER: number = 1;
  DATETIME_BIGGEREQUAL: number = 2;
  DATETIME_SMALLER: number = 3;
  DATETIME_SMALLEREQUAL: number = 4;

  //config parameters
  type: number = 0;

  //build parameters
  fieldname: string = "";
  values: any[] = [];
  andoroperator = this.OR;

  init = () => {
    this.values = [];
    this.andoroperator = this.OR;
  }
  getshortname = () => {
    var parts = this.fieldname.split(".");
    return parts[parts.length-1];
  }
  setAndoroperator = (andor) => {
    this.andoroperator = andor;
  }
  used = () => {
    return this.values.length>0;
  }
  valuestoJSON = () => {
    return this.values;
  }
  evaluateselector = (selector) => {
    var result = this.NUMERIC_EQUAL;
    if(selector==">") {
      result = this.NUMERIC_BIGGER;
    } else if(selector==">=") {
      result = this.NUMERIC_BIGGEREQUAL;
    } else if(selector=="<=") {
      result = this.NUMERIC_SMALLEREQUAL;
    } else if(selector=="<") {
      result = this.NUMERIC_SMALLER;
    }
    return result;
  }

  toJSON = () => {};
}

export default Fieldsearcher;
