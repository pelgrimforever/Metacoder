/*
 * WSI:Table:.java
 *
 * Created on March 26, 2007, 5:44 PM
 * Generated on :metacoder_date:
 *
 */

package :project:.interfaces.webservice;

import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author Franky Laseure
 */
//Service Endpoint Interface
@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use=SOAPBinding.Use.LITERAL)
public interface WSI:Table: {
    
    @WebMethod String get:Table:s();

    @WebMethod String search(String json);

    @WebMethod String get:Table:(String json);

    @WebMethod void insert:Table:(String json);

    @WebMethod void update:Table:(String json);

    @WebMethod void delete:Table:(String json);

:repeatforeignkeys:
    @WebMethod String get:Table:s4:uniquename:(String json);

    @WebMethod void delete4:uniquename:(String json);

:repeatforeignkeys:
:repeatexternalforeignkeys:
:ifforeignkey:
    @WebMethod String get:Table:s4:uniquename:(String json);

:ifforeignkey:
:repeatexternalforeignkeys:

}

