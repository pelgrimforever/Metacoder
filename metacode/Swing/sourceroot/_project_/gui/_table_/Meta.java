package :project:.gui.:table:;

import :project:.interfaces.logicentity.I:Table:;

/**
 *
 * @author Franky Laseure
 */
public class Meta {

:repeatallfields:
    public static byte :COLUMN: = I:Table:.:COLUMN: - 1;
:repeatallfields:
    public static final String[] fieldnames = I:Table:.fieldnames;
    //not static, changable only inside contructor
    public String[] labelsINPUT = I:Table:.fieldnames;
    public boolean[] visibleINPUT = { :repeatallfields:true:,::repeatallfields: };
    public boolean[] readonlyINPUT = { :repeatallfields::inpk:false:inpk::notpk:true:notpk::,::repeatallfields: };
    public boolean[] readonlyNEW = { :repeatallfields:true:,::repeatallfields: };
    public String[] labelsREAD = I:Table:.fieldnames;
    public boolean[] visibleREAD = { :repeatallfields:true:,::repeatallfields: };
    public boolean[] readonlyREAD = { :repeatallfields::infk:false:infk::notfk:true:notfk::,::repeatallfields: };
    
    public Meta() {
//Custom code, do not change this line
//change values of labels and visability where needed between **change labels** comments
//Anything outside //Custom code can be deleted by the code generator

//Custom code, do not change this line
    }

}

