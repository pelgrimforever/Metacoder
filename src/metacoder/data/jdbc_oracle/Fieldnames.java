package metacoder.data.jdbc_oracle;

/**
 * Oracle fieldnames
 * @author Franky Laseure
 */
public class Fieldnames extends metacoder.data.jdbc.Fieldnames {
    
    @Override
    public String getIsnullable() {
        return "NULLABLE";
    }

}
