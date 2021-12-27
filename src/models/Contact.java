package models;

/**
 *
 */
public class Contact {
/**
 * Fields
 */
private int    contactId;
private String contactName;
private String emailAddress;


/**
 * Methods
 */

/**
 * The employee who will be in contact with the <code>Customer</code>
 *
 * @param contactId    The employee's identification number
 * @param contactName  The employee's name
 * @param emailAddress The employee's email address
 */
public Contact( int contactId, String contactName, String emailAddress ) {
  this.contactId    = contactId;
  this.contactName  = contactName;
  this.emailAddress = emailAddress;
}

/**
 * Returns the <code>contactId</code>
 *
 * @return The employee's identification number
 */
public int getContactId( ) {
  return contactId;
}

/**
 * Returns the <code>contactName</code>
 *
 * @return The employee's name
 */
public String getContactName( ) {
  return contactName;
}

/**
 * Returns the <code>emailAddress</code>
 *
 * @return The employee's email address
 */
public String getEmailAddress( ) {
  return emailAddress;
}
}
