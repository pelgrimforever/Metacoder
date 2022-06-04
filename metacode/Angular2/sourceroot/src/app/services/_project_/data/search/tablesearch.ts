//Auto generated code
//don't change things here, it will be overwritten
/* 
    Created on : Nov 16, 2018, 17:04:01 PM
    Generated on :metacoder_date:
    Author     : Franky Laseure
*/

import { Fieldsearcher } from './fieldsearcher';
import { Foreignkeysearcher } from './foreignkeysearcher';
import { Primarykeysearcher } from './primarykeysearcher';
import { Relationalkeysearcher } from './relationalkeysearcher';

export class Tablesearch {
	constructor() { }

    //Contants
    AND: number = 0;
    OR: number = 1;

    EQUAL: number = 0;
    LIKE: number = 1;

    maxresults: number = 50;
    docount: boolean = false;

    primarykeys: Primarykeysearcher[] = [];
    fieldsearchers: Fieldsearcher[] = [];
    keysearchers: any[] = [];
    andoroperator = 1;

    public init() {
        this.primarykeys = [];
        this.fieldsearchers = [];
        this.keysearchers = [];
        this.andoroperator = this.OR;
    }

    public addFieldsearcher(fieldsearcher) {
        fieldsearcher.reset();
        this.fieldsearchers.push(fieldsearcher);
    }
    public addPrimarykey(primarykey) {
        this.primarykeys.push(primarykey);
    }
    public addKeysearcher(keysearcher) {
        this.keysearchers.push(keysearcher);
    }
    public addStringvalues(stringsearch, values) {
        stringsearch.values.push(values);
    }
    public addLongvalues(numbersearch, values, operators) {
        numbersearch.values.push(values);
        numbersearch.operators.push(operators);
    }
    public addNumbervalues(numbersearch, values, operators) {
        var numvalues = [values.length];
        var i=0;
        for(i=0; i<values.length; i++) {
            numvalues[i] = parseFloat(values[i]);
        }
        numbersearch.values.push(numvalues);
        numbersearch.operators.push(operators);
    }
    public addDatevalues(datesearch, values, operators) {
        datesearch.values.push(values);
        datesearch.operators.push(operators);
    }
    public addTimevalues(timesearch, values, operators) {
        timesearch.values.push(values);
        timesearch.operators.push(operators);
    }
    public addBooleanvalue(booleansearch, value) {
        booleansearch.values.clear();
        booleansearch.values.pushObject(value);
    }
    public setTablesearcher(keysearcher, tablesearch) {
        keysearcher.setTablesearch(tablesearch);
    }
    public used() {
        var used = this.primarykeys.length>0;
        if(!used) {
            var i=0;
            var fieldsearcher = null;
            for(i=0; i<this.fieldsearchers.length; i++) {
                fieldsearcher = this.fieldsearchers[i];
                if(fieldsearcher.used()) {
                    used = true;
                    break;
                }
            }
        }
        if(!used) {
            var i=0;
            var keysearcher = null;
            for(i=0; i<this.keysearchers.length; i++) {
                keysearcher = this.keysearchers[i];
                if(keysearcher.used()) {
                    used = true;
                    break;
                }
            }
        }
        return used;
    }
    public resetfields() {
        var i=0;
        var fieldsearcher = null;
        for(i=0; i<this.fieldsearchers.length; i++) {
            fieldsearcher = this.fieldsearchers[i];
            fieldsearcher.reset();
        }
    }
    public reset() {
        this.resetfields();
        //this.primarykeys.clear();
    }    

}
