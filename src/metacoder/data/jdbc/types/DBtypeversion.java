package metacoder.data.jdbc.types;

/**
 * DBtypeversion
 * Database tool version
 * @author Franky Laseure
 */
public enum DBtypeversion {
    Postgresql7(DBtype.PostgreSQL, "7.", 7),
    Postgresql8(DBtype.PostgreSQL, "8.", 8),
    Postgresql9(DBtype.PostgreSQL, "9.", 9),
    Postgresql10(DBtype.PostgreSQL, "10.", 10),
    Postgresql11(DBtype.PostgreSQL, "11.", 11),
    Postgresql12(DBtype.PostgreSQL, "12.", 12),
    MySQL5(DBtype.MySQL, "5.", 5),
    MySQL8(DBtype.MySQL, "8.", 8),
    SQLServer7(DBtype.SQLServer, "07.", 7),
    SQLServer11(DBtype.SQLServer, "11.", 11),
    SQLServer14(DBtype.SQLServer, "14.", 14),
    Oracle10g(DBtype.Oracle, "Oracle Database 10g", 10),
    notSupported(DBtype.notSupported, "", 0);
    
    private DBtype dbtype;

    /**
     * @return DBtype (Database type)
     */
    public DBtype getDbtype() {
        return dbtype;
    }
    private String versionprefix;
    private int version;
    
    /**
     * constructor
     * @param dbtype DBtype
     * @param versionprefix Version prefix found in full database name
     * @param version Database version number
     */
    DBtypeversion(DBtype dbtype, String versionprefix, int version) {
        this.dbtype = dbtype;
        this.versionprefix = versionprefix;
        this.version = version;
    }
    
    /**
     * find DBtypeversion for vendor name and version string
     * @param vendor Database vendor
     * @param version Database version
     * @return DBtypeversion
     */
    public static DBtypeversion getType(String vendor, String version) {
        DBtypeversion dbtypeversion = notSupported;
        for(DBtypeversion dbtv: DBtypeversion.values()) {
            if(version.startsWith(dbtv.versionprefix) && dbtv.dbtype.getVendorname().indexOf(vendor)!=-1) {
                dbtypeversion = dbtv;
            }
        }
        return dbtypeversion;
    }
    
}
