package models;

/**
 *
 */
public class Contact {
/**
 *
 */
private int contactId;
private String contactName;
private String emailAddress;

/**
 *
 * @param contactId
 * @param contactName
 * @param emailAddress
 */
public Contact( int contactId, String contactName, String emailAddress ) {
  this.contactId    = contactId;
  this.contactName  = contactName;
  this.emailAddress = emailAddress;
}

/**
 *
 * @return
 */
public int getContactId( ) {
  return contactId;
}

/**
 *
 * @return
 */
public String getContactName( ) {
  return contactName;
}

/**
 *
 * @return
 */
public String getEmailAddress( ) {
  return emailAddress;
}
}
