/*
 * :Table:data_base.java
 *
 * Created on Jan 01, 2013, 13:34 PM
 * Generated on :metacoder_date:
 *
 */
package :project:.datahandlers.data.base;

import :project:.interfaces.logicview.I:Table:;
import :project:.logicview.:Table:;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import javafx.beans.property.*;
import javafx.scene.image.Image;
import text.Conversion;

/**
 *
 * @author Franky Laseure
 */
public abstract class :Table:data_base {
    
    protected I:Table: source:table:;
    
    public :Table:data_base(I:Table: source:table:) {
        set:Table:data(source:table:);
    }
    
    public abstract void set:Table:data(I:Table: :table:);
    
    public abstract void reset();
    
    public void reset_all() {
        //fields
:repeatfields:
:iffieldtype:
:boolean:
        setIs:column:(source:table:.get:Column:());
:boolean:
:other:
        set:Column:(source:table:.get:Column:());
:other:
:iffieldtype:
:repeatfields:
    }
    
    public I:Table: getData() {
        return source:table:;
    }

    //fields
:repeatfields:
:iffieldtype:
:java.lang.String:
    private final StringProperty :column: = new SimpleStringProperty();
    public String get:Column:() { return :column:.get(); }
    public void set:Column:(String newValue) { :column:.set(newValue); }
    public StringProperty :column:Property() { return :column:; }    
    
:java.lang.String: 
:java.sql.Date:
    private final ObjectProperty<Date> :column: = new SimpleObjectProperty<Date>();
    public Date get:Column:() { return :column:.get(); }
    public void set:Column:(Date newValue) { 
        :column:.set(newValue); 
        set:Column:string(Conversion.convertDateToString(newValue));
    }
    public ObjectProperty<Date> :column:Property() { return :column:; }    

    private final StringProperty :column:string = new SimpleStringProperty();
    public String get:Column:string() { return :column:string.get(); }
    public void set:Column:string(String newValue) { :column:string.set(newValue); }
    public StringProperty :column:stringProperty() { return :column:string; }    

:java.sql.Date: 
:java.sql.Time:
    private final ObjectProperty<Time> :column: = new SimpleObjectProperty<Time>();
    public Time get:Column:() { return :column:.get(); }
    public void set:Column:(Time newValue) { 
        :column:.set(newValue); 
        set:Column:string(Conversion.convertTimeToString(newValue));
    }
    public ObjectProperty<Time> :column:Property() { return :column:; }    

    private final StringProperty :column:string = new SimpleStringProperty();
    public String get:Column:string() { return :column:string.get(); }
    public void set:Column:string(String newValue) { :column:string.set(newValue); }
    public StringProperty :column:stringProperty() { return :column:string; }    
    
:java.sql.Time: 
:java.sql.Timestamp:
    private final ObjectProperty<Time> :column: = new SimpleObjectProperty<Time>();
    public Time get:Column:() { return :column:.get(); }
    public void set:Column:(Time newValue) { 
        :column:.set(newValue); 
        set:Column:string(Conversion.convertTimeToString(newValue));
    }
    public ObjectProperty<Time> :column:Property() { return :column:; }    

    private final StringProperty :column:string = new SimpleStringProperty();
    public String get:Column:string() { return :column:string.get(); }
    public void set:Column:string(String newValue) { :column:string.set(newValue); }
    public StringProperty :column:stringProperty() { return :column:string; }    
    
:java.sql.Timestamp: 
:byte:
    private final ObjectProperty<Byte> :column: = new SimpleObjectProperty<Byte>();
    public Byte get:Column:() { return :column:.get(); }
    public void set:Column:(Byte newValue) { :column:.set(newValue); }
    public ObjectProperty<Byte> :column:Property() { return :column:; }    
    
:byte:
:int:
    private final IntegerProperty :column: = new SimpleIntegerProperty();
    public Integer get:Column:() { return :column:.get(); }
    public void set:Column:(Integer newValue) { :column:.set(newValue); }
    public IntegerProperty :column:Property() { return :column:; }    
    
:int: 
:long:
    private final LongProperty :column: = new SimpleLongProperty();
    public Long get:Column:() { return :column:.get(); }
    public void set:Column:(Long newValue) { :column:.set(newValue); }
    public LongProperty :column:Property() { return :column:; }    

:long: 
:double:
    private final DoubleProperty :column: = new SimpleDoubleProperty();
    public Double get:Column:() { return :column:.get(); }
    public void set:Column:(Double newValue) { :column:.set(newValue); }
    public DoubleProperty :column:Property() { return :column:; }    

:double: 
:float:
    private final FloatProperty :column: = new SimpleFloatProperty();
    public Float get:Column:() { return :column:.get(); }
    public void set:Column:(Float newValue) { :column:.set(newValue); }
    public FloatProperty :column:Property() { return :column:; }    

:float: 
:boolean:
    private final BooleanProperty is:column: = new SimpleBooleanProperty();
    public Boolean getIs:column:() { return is:column:.get(); }
    public void setIs:column:(Boolean newValue) { is:column:.set(newValue); }
    public BooleanProperty is:column:Property() { return is:column:; }    
    
:boolean: 
:other:
    private final ObjectProperty :column: = new SimpleObjectProperty();
    public Object get:Column:() { return :column:.get(); }
    public void set:Column:(Object newValue) { :column:.set(newValue); }
    public ObjectProperty :column:Property() { return :column:; }    
    
:other:
:iffieldtype:
:repeatfields:
}

