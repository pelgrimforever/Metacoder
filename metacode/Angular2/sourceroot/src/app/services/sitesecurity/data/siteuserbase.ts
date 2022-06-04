//Auto generated code
//don't change things here, it will be overwritten
//extend classes in siteuser.ts for table class definitions
/* 
    Created on : Nov 16, 2018, 17:04:01 PM
    Generated on 21.10.2018 11:19
    Author     : Franky Laseure
*/

export class Siteuserpk {
	constructor() { }
    username: string = '';
    getUsername() {
        return this.username;
    }
    public init() {
        this.username = "";
    }
}

export class Siteuserbase {
	constructor() { }
	tablename: string = 'siteuser';
	PK: Siteuserpk = null;
    public username() {
        if(this.PK===null) return '';
        else return this.PK.username;
    }
    public tostring() {
      let result = "";
      if(this.PK!=null) {
          result += this.PK.getUsername();
      }
      return result;
    }
    userpassword = '';
}
