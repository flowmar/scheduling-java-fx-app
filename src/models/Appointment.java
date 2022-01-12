package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 *
 */
public class Appointment {
/**
 *
 */
private IntegerProperty appointmentIdProperty;
private StringProperty  titleProperty;
private StringProperty  descriptionProperty;
private StringProperty  locationProperty;
private StringProperty  typeProperty;
private StringProperty  startProperty;
private IntegerProperty userIdProperty;
private StringProperty  endProperty;
private IntegerProperty customerIdProperty;
private IntegerProperty contactIdProperty;
private int             appointmentId;
private String          title;
private String          description;
private String          location;
private String          type;
private Timestamp       start;
private Timestamp       end;
private LocalDateTime   createDate;
private String          createdBy;
private LocalDateTime   lastUpdate;
private String          lastUpdatedBy;
private int             customerId;
private int             userId;
private int             contactId;


public Appointment( IntegerProperty appointmentIdProperty,
                    StringProperty titleProperty,
                    StringProperty descriptionProperty,
                    StringProperty locationProperty,
                    StringProperty typeProperty,
                    StringProperty startProperty,
                    StringProperty endProperty,
                    IntegerProperty userIdProperty,
                    IntegerProperty customerIdProperty,
                    IntegerProperty contactIdProperty ) {
  this.appointmentIdProperty = appointmentIdProperty;
  this.titleProperty         = titleProperty;
  this.descriptionProperty   = descriptionProperty;
  this.locationProperty      = locationProperty;
  this.typeProperty          = typeProperty;
  this.startProperty         = startProperty;
  this.userIdProperty        = userIdProperty;
  this.endProperty           = endProperty;
  this.customerIdProperty    = customerIdProperty;
  this.contactIdProperty     = contactIdProperty;
}

public Appointment( int appointmentId, String title, String description, String location, String type,
                    Timestamp start, Timestamp end, int customerId, int userId, int contactId ) {
  this.appointmentId = appointmentId;
  this.title         = title;
  this.description   = description;
  this.location      = location;
  this.type          = type;
  this.start         = start;
  this.end           = end;
  this.customerId    = customerId;
  this.userId        = userId;
  this.contactId     = contactId;
}


/**
 * Returns the <code>appointmentId</code> as an <code>int</code>
 *
 * @return The id number of the <code>Appointment</code>
 */
public int getAppointmentId( ) {
  return appointmentIdProperty( ).get( );
}

/**
 * Returns the <code>appointmentId</code> as a <code>SimpleIntegerProperty</code>
 *
 * @return The <code>ID</code> number of the <code>Appointment</code>
 */
public IntegerProperty appointmentIdProperty( ) {
  if ( appointmentIdProperty == null ) { appointmentIdProperty = new SimpleIntegerProperty( this, "appointmentId" ); }
  return appointmentIdProperty;
}

/**
 * Returns the <code>title</code> of the <code>Appointment</code> as a <code>String</code>
 *
 * @return The <code>title</code> of the <code>Appointment</code>
 */
public String getTitle( ) {
  return titleProperty( ).get( );
}

/**
 * Returns the <code>title</code> of the <code>Appointment</code> as a <code>SimpleStringProperty</code>
 *
 * @return The <code>title</code> of the <code>Appointment</code>
 */
public StringProperty titleProperty( ) {
  if ( titleProperty == null ) { titleProperty = new SimpleStringProperty( this, "title" ); }
  return titleProperty;
}

/**
 * Returns the <code>description</code> of the <code>Appointment</code>
 *
 * @return The <code>description</code> of the <code>Appointment</code>
 */
public String getDescription( ) {
  return descriptionProperty( ).get( );
}

/**
 * Returns the <code>description</code> of the <code>Appointment</code> as a <code>SimpleStringProperty</code>
 *
 * @return The <code>description</code> of the <code>Appointment</code>
 */
public StringProperty descriptionProperty( ) {
  if ( descriptionProperty == null ) { descriptionProperty = new SimpleStringProperty( this, "description" ); }
  return descriptionProperty;
}


/**
 * Returns the <code>location</code> of the <code>Appointment</code>
 *
 * @return The <code>location</code> of the <code>Appointment</code>
 */
public String getLocation( ) {
  return locationProperty( ).get( );
}

/**
 * Returns the <code>location</code> of the <code>Appointment</code> as a <code>SimpleStringProperty</code>
 *
 * @return The <code>location</code> of the <code>Appointment</code>
 */
public StringProperty locationProperty( ) {
  if ( locationProperty == null ) { locationProperty = new SimpleStringProperty( this, "location" ); }
  return locationProperty;
}

/**
 * Returns the <code>type</code> of the <code>Appointment</code>
 *
 * @return The <code>type</code> of the <code>Appointment</code>
 */
public String getType( ) {
  return typeProperty( ).get( );
}

/**
 * Returns the <code>type</code> of the <code>Appointment</code> as a <code>SimpleStringProperty</code>
 *
 * @return The <code>type</code> of the <code>Appointment</code>
 */
public StringProperty typeProperty( ) {
  if ( typeProperty == null ) { typeProperty = new SimpleStringProperty( this, "type" ); }
  return typeProperty;
}



/**
 * Returns the <code>int</code> equivalent of the month of the <code>Appointment</code> start date, with January being 0
 * @return The <code>int</code> equivalent of the month
 */
public int getStartMonthInt() {
  // Get the startProperty
  String startTimestampString =  startProperty().get();
  // Create a DateFormat to be parsed
  SimpleDateFormat dateFormat     = new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss.SSS" );
  
  int month = 0;
  
  try {
    // Create a new Date Object from the Timestamp String using the dateFormat
    Date             parsedDate     = dateFormat.parse(startTimestampString);
    // Get an instance of the Calendar object
    Calendar calendar = Calendar.getInstance();
    // Set the time of the Calendar object to the Date
    calendar.setTime(parsedDate);
    // Get the int equivalent of the month
    month = calendar.get(Calendar.MONTH);
  }
  catch ( ParseException e ) {
    e.printStackTrace( );
  }
  System.out.println("Month: " + month );
  return month;
}

/**
 * Returns the week number that an <code>Appointment</code> is scheduled
 * @return The week number of a scheduled <code>Appointment</code>
 */
public int getStartWeek()
{
  // Get the start property
  String startTimestampString = startProperty().get( );
  // Create a dateFormat
  SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss.SSS");
  
  int week = 0;
  
  try
  {
    // Create a new Date Object from the Timestamp String using the dateFormat
    Date parsedDate = dateFormat.parse(startTimestampString);
    // Get an instance of the Calendar Object
    Calendar calendar = Calendar.getInstance();
    // Set the time of the Calendar object to the Date
    calendar.setTime(parsedDate);
    // Get the week number of the Date set on the Calendar object
    week = calendar.get(Calendar.WEEK_OF_YEAR);
    
  }
  catch (ParseException e) {
    e.printStackTrace();
  }
  System.out.println( "Week: " + week );
  return week;
}


/**
 * Returns the <code>start</code> time of the <code>Appointment</code>
 *
 * @return The <code>start</code> time of the <code>Appointment</code>
 */
public String getStart( ) {
  return startProperty( ).get( );
}


/**
 * Returns the <code>start</code> time of the <code>Appointment</code> as a <code>SimpleStringProperty</code>
 *
 * @return The <code>start</code> time of the <code>Appointment</code>
 */
public StringProperty startProperty( ) {
  if ( startProperty == null ) { startProperty = new SimpleStringProperty( this, "start" ); }
  return startProperty;
}

/**
 * Returns the <code>end</code> time of the <code>Appointment</code>
 *
 * @return The <code>end</code> time of the <code>Appointment</code>
 */
public String getEnd( ) {
  return endProperty( ).get( );
}

/**
 * Returns the <code>end</code> of the <code>Appointment</code> as a <code>SimpleStringProperty</code>
 *
 * @return The <code>end</code> of the <code>Appointment</code>
 */
public StringProperty endProperty( ) {
  if ( endProperty == null ) { endProperty = new SimpleStringProperty( this, "end" ); }
  return endProperty;
}

/**
 * Returns the <code>customerId</code> of the <code>Appointment</code>
 *
 * @return The <code>customerId</code> of the <code>Appointment</code>
 */
public int getCustomerId( ) {
  return customerIdProperty( ).get( );
}

/**
 * Returns the <code>customerId</code> of the <code>Appointment</code> as a <code>SimpleIntegerProperty</code>
 *
 * @return The <code>customerId</code> of the <code>Appointment</code>
 */
public IntegerProperty customerIdProperty( ) {
  if ( customerIdProperty == null ) { customerIdProperty = new SimpleIntegerProperty( this, "customerId" ); }
  return customerIdProperty;
}

/**
 * Returns the <code>userId</code> of the <code>Appointment</code>
 *
 * @return The <code>userId</code> of the person that scheduled the <code>Appointment</code>
 */
public int getUserId( ) {
  return userIdProperty( ).get( );
}

/**
 * Returns the <code>userId</code> of the <code>Appointment</code> as a <code>SimpleIntegerProperty</code>
 *
 * @return The <code>userId</code> of the person that scheduled the <code>Appointment</code>
 */
public IntegerProperty userIdProperty( ) {
  if ( userIdProperty == null ) { userIdProperty = new SimpleIntegerProperty( this, "userId" ); }
  return userIdProperty;
}

/**
 * Returns the <code>contactId</code> of the <code>Appointment</code>
 *
 * @return The <code>contactId</code> of the person assigned to the <code>Appointment</code>
 */
public int getContactId( ) {
  return contactIdProperty( ).get( );
}

/**
 * Returns the <code>contactId</code> of the <code>Appointment</code> as a <code>SimpleIntegerProperty</code>
 *
 * @return The <code>contactId</code> of the person assigned to the <code>Appointment</code>
 */
public IntegerProperty contactIdProperty( ) {
  if ( contactIdProperty == null ) { contactIdProperty = new SimpleIntegerProperty( this, "contactId" ); }
  return contactIdProperty;
}
}