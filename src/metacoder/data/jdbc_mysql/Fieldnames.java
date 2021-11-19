package metacoder.data.jdbc_mysql;

/**
 * Field names for Mysql
 * @author Franky Laseure
 */
public class Fieldnames extends metacoder.data.jdbc.Fieldnames {
    
    @Override
    public String getTableschema() {
        return "TABLE_CAT";
    }
    
    public String getPKschema() {
        return "PKTABLE_CAT";
    }

    @Override
    public String getPKcolumnschemaname() {
        return "PKTABLE_SCHEM";
    }

    @Override
    public String getFKschema() {
        return "FKTABLE_CAT";
    }

    @Override
    public String getFKcolumnschemaname() {
        return "FKTABLE_SCHEM";
    }

    @Override
    public int getFKcolumnsequenceoffset() {
        return 1;
    }
    
}
