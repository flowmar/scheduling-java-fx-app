package models;

import java.time.LocalDateTime;

/**
 *
 */
public class Appointments {
/**
 *
 */
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private int customerId;
    private int userId;
    private int contactId;

public Appointments( int appointmentId, String title, String description, String location, String type,
                     LocalDateTime start, LocalDateTime end, LocalDateTime createDate, String createdBy,
                     LocalDateTime lastUpdate, String lastUpdatedBy, int customerId, int userId, int contactId ) {
  this.appointmentId = appointmentId;
  this.title         = title;
  this.description   = description;
  this.location      = location;
  this.type          = type;
  this.start         = start;
  this.end           = end;
  this.createDate    = createDate;
  this.createdBy     = createdBy;
  this.lastUpdate    = lastUpdate;
  this.lastUpdatedBy = lastUpdatedBy;
  this.customerId    = customerId;
  this.userId        = userId;
  this.contactId     = contactId;
}

/**
 *
 * @return
 */
public int getAppointmentId( ) {
  return appointmentId;
}

/**
 *
 * @return
 */
public String getTitle( ) {
  return title;
}

/**
 *
 * @return
 */
public String getDescription( ) {
  return description;
}

/**
 *
 * @return
 */
public String getLocation( ) {
  return location;
}

/**
 *
 * @return
 */
public String getType( ) {
  return type;
}

/**
 *
 * @return
 */
public LocalDateTime getStart( ) {
  return start;
}

/**
 *
 * @return
 */
public LocalDateTime getEnd( ) {
  return end;
}

/**
 *
 * @return
 */
public LocalDateTime getCreateDate( ) {
  return createDate;
}

/**
 *
 * @return
 */
public String getCreatedBy( ) {
  return createdBy;
}

/**
 *
 * @return
 */
public LocalDateTime getLastUpdate( ) {
  return lastUpdate;
}

/**
 *
 * @return
 */
public String getLastUpdatedBy( ) {
  return lastUpdatedBy;
}

/**
 *
 * @return
 */
public int getCustomerId( ) {
  return customerId;
}

/**
 *
 * @return
 */
public int getUserId( ) {
  return userId;
}

/**
 *
 * @return
 */
public int getContactId( ) {
  return contactId;
}
}
