/*
 * Data object for code formatting
 */
package metacoder.codegeneration;

/**
 * Data object for code formatting
 * @author Franky Laseure
 */
public class Codeformatter {
    
    private boolean separatorfound = false;
    private boolean endswithcomma = false;
    private boolean endswithnewline = false;

    /**
     * reset all parameters to false for new code fragment
     */
    public void initialize() {
        this.separatorfound = false;
        this.endswithcomma = false;
        this.endswithnewline = false;
    }
    
    public boolean isSeparatorfound() {
        return separatorfound;
    }

    public void setSeparatorfound(boolean separatorfound) {
        this.separatorfound = separatorfound;
    }

    public boolean isEndswithcomma() {
        return endswithcomma;
    }

    public void setEndswithcomma(boolean endswithcomma) {
        this.endswithcomma = endswithcomma;
    }

    public boolean isEndswithnewline() {
        return endswithnewline;
    }

    public void setEndswithnewline(boolean endswithnewline) {
        this.endswithnewline = endswithnewline;
    }
    
    
}
