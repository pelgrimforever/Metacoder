package metacoder.data.jdbc.types;

/**
 * Database tool
 * @author Franky Laseure
 */
public enum DBtype {
    PostgreSQL("PostgreSQL"),
    MySQL("MySQL"),
    SQLServer("Microsoft SQL Server"),
    Oracle("Oracle"),
    notSupported("notSupported");
    
    private String vendorname;
    private Class columnclass;

    /**
     * @return Database Vendor name
     */
    public String getVendorname() {
        return vendorname;
    }

    /**
     * constructor
     * @param vendorname Database Vendor name
     */
    DBtype(String vendorname) {
        this.vendorname = vendorname;
    }
}
