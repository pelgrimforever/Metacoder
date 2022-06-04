//Auto generated code
//don't change things here, it will be overwritten
/* 
    Created on : Nov 17, 2018, 17:26:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import { Tablesearch } from './tablesearch';

export class Keysearcher {
	constructor() { 
        this.tablesearches = [];
    }

    //configuration constants
    foreignkeysearcher: number = 0;
    primarykeysearcher: number = 1;
    relationalkeysearcher: number = 2;

    //config parameters
    type: number = 0;

    //build parameters
    tablesearches: Tablesearch[] = [];
    andoroperator: number = 1;

    public setTablesearch(tablesearch) {
        //this.tablesearches.clear();
        this.tablesearches.push(tablesearch);
    }
    public getTablesearch() {
        if(this.tablesearches.length>0) {
            return this.tablesearches[0];
        } else {
            return null;
        }
    }
    public resetfields() {
        var i=0;
        var tablesearch = null;
        for(i=0; i<this.tablesearches.length; i++) {
            tablesearch = this.tablesearches[i];
            tablesearch.resetfields();
        }
    }
    public reset() {
        var i=0;
        var tablesearch = null;
        for(i=0; i<this.tablesearches.length; i++) {
            tablesearch = this.tablesearches[i];
            tablesearch.reset();
        }
    }
    public used() {
        var isused = false;
        var i=0;
        var tablesearch = null;
        for(i=0; i<this.tablesearches.length; i++) {
            tablesearch = this.tablesearches[i];
            if(tablesearch.used()) {
                isused = true;
                break;
            }
        }
        return isused;
    }
    public toJSON() {
        var json = {};
        if(this.used()) {
            var tablesearch = null;
            var jsonarray = [];
            for(var i=0; i<this.tablesearches.length; i++) {
                tablesearch = this.tablesearches[i];
                jsonarray.push(tablesearch.toJSON());
            }
            json = jsonarray;
	    }
        return json;
    }
}
