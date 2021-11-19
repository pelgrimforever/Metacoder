package metacoder.data;

/**
 * Customdatatype
 * Defined in the program language xml files
 * used in Class Programlanguage
 * @author Franky Laseure
 */
public class Customdatatype {

    private String databasedriverdatatype;
    private String customdatatype;
    private String datatype;
    
    /**
     * constructor
     * @param databasedriverdatatype jdbc data class name
     * @param customdatatype database dependent custom data class name defined in Project"databasetool" (ex: ProjectPostgresql)
     * @param datatype generic data class name defined in ProjectInterfaces project
     */
    public Customdatatype(String databasedriverdatatype, String customdatatype, String datatype) {
        this.databasedriverdatatype = databasedriverdatatype;
        this.customdatatype = customdatatype;
        this.datatype = datatype;
    }

    /**
     * @return jdbc data class name
     */
    public String getCustomdatatype() {
        return customdatatype;
    }

    /**
     * @return database dependent custom data class name defined in Project"databasetool" (ex: ProjectPostgresql)
     */
    public String getDatabasedriverdatatype() {
        return databasedriverdatatype;
    }

    /**
     * @return generic data class name defined in ProjectInterfaces project
     */
    public String getDatatype() {
        return datatype;
    }
    
}
