/*
 * :Table:data_base.java
 *
 * Created on Jan 01, 2013, 13:34 PM
 * Generated on :metacoder_date:
 *
 */
package :project:.datahandlers.data.base;

import base.framework.controller.AbstractData;
import data.gis.shape.*;
import :project:.entity.pk.*;
import :project:.interfaces.entity.pk.*;
import :project:.datahandlers.data.:Table:data;
import :project:.interfaces.entity.pk.I:Table:PK;
import :project:.interfaces.logicentity.I:Table:;
import :project:.logicentity.:Table:;
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
public abstract class :Table:data_base extends AbstractData {
    
    protected I:Table: source:table:;
    
    /**
     * Create an empty instance of :Table:data_base
     * @return empty :Table:data
     */
    public static :Table:data create() {
        :Table:data :table:data = new :Table:data(new :Table:(new :Table:PK()));
        :table:data.setClientsidecreated(true);
        return :table:data;
    }
    
    /**
     * Create an instance of :Table:data_base with data from source:table:
     * @return :Table:data
     */
    public :Table:data_base(I:Table: source:table:) {
        set:Table:data(source:table:);
    }
    
    public abstract void set:Table:data(I:Table: :table:);
    
    public abstract void reset();
    
    public void reset_all() {
        //fields
:repeatpkfields:
        set:Column:(source:table:.getPrimaryKey().get:Column:());
:repeatpkfields:
:repeatforeignkeys:
:inpk:
        set:Uniquename:pk((:Pktable:PK)source:table:.getPrimaryKey().get:Uniquename:PK());
:inpk:
:notpk:
        set:Uniquename:pk(source:table:.get:Uniquename:PK());
:notpk:
:repeatforeignkeys:
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
    
    public abstract void update();
    
    public void update_all() {
:repeatforeignkeys:
:inpk:
        source:table:.getPrimaryKey().set:Uniquename:PK(this.get:Uniquename:pk());
:inpk:
:notpk:
        source:table:.set:Uniquename:PK(this.get:Uniquename:pk());
:notpk:
:repeatforeignkeys:
:repeatfields:
:iffieldtype:
:boolean:
        source:table:.set:Column:(this.getIs:column:());
:boolean:
:other:
        source:table:.set:Column:(this.get:Column:());
:other:
:iffieldtype:
:repeatfields:
    }

    public I:Table: getData() {
        return source:table:;
    }
    
    public I:Table: extractData() {
        update();
        return source:table:;
    }
    
    //primary key
    public I:Table:PK get:Table:PK() { return source:table:.getPrimaryKey(); }

    //fields
:repeatforeignkeys:
    private final ObjectProperty<I:Pktable:PK> :uniquename:pk = new SimpleObjectProperty<I:Pktable:PK>();
    public I:Pktable:PK get:Uniquename:pk() { return :uniquename:pk.get(); }
    public void set:Uniquename:pk(I:Pktable:PK newValue) { :uniquename:pk.set(newValue); }
    public ObjectProperty<I:Pktable:PK> :uniquename:pkProperty() { return :uniquename:pk; }

:repeatforeignkeys:    
:repeatpkfields:
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
:customfieldtype:
    private final ObjectProperty<:columntype:> :column: = new SimpleObjectProperty<:columntype:>();
    public :columntype: get:Column:() { return :column:.get(); }
    public void set:Column:(:columntype: newValue) { :column:.set(newValue); }
    public ObjectProperty<:columntype:> :column:Property() { return :column:; }    

:customfieldtype:
:other:
    private final ObjectProperty :column: = new SimpleObjectProperty();
    public Object get:Column:() { return :column:.get(); }
    public void set:Column:(Object newValue) { :column:.set(newValue); }
    public ObjectProperty :column:Property() { return :column:; }    
    
:other:
:iffieldtype:
:repeatpkfields:
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
:customfieldtype:
    private final ObjectProperty<:columntype:> :column: = new SimpleObjectProperty<:columntype:>();
    public :columntype: get:Column:() { return :column:.get(); }
    public void set:Column:(:columntype: newValue) { :column:.set(newValue); }
    public ObjectProperty<:columntype:> :column:Property() { return :column:; }    

:customfieldtype:
:other:
    private final ObjectProperty :column: = new SimpleObjectProperty();
    public Object get:Column:() { return :column:.get(); }
    public void set:Column:(Object newValue) { :column:.set(newValue); }
    public ObjectProperty :column:Property() { return :column:; }    
    
:other:
:iffieldtype:
:repeatfields:
}

