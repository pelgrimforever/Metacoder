import {Siteuserpk} from './siteuserbase'
import {Siteuserbase} from './siteuserbase'

export class Siteuser extends Siteuserbase {
	constructor() { 
		super();
	}
    auth = null;

    public tostring() {
        let result = super.tostring();
        return result;
    }
}
