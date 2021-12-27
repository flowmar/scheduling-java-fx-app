package models;

/**
 * A subsection of a <code>Country</code>
 */
public class Division {
/**
 * Fields
 */
private int    divisionId;
private String division;
private String date;
private String createdBy;
private String lastUpdate;
private String lastUpdatedBy;
private int    countryId;

/**
 * Constructor for a Division object
 *
 * @param divisionId    The ID number of the <code>Division</code> in the database
 * @param division      The name of the <code>Division</code>
 * @param date          The <code>date</code> that the entry was created
 * @param createdBy     The <code>User</code> that created the <code>Division</code>
 * @param lastUpdate    The last time the <code>Division</code> was updated
 * @param lastUpdatedBy The last user that updated the <code>Division</code>
 * @param countryId     The corresponding ID number of the <code>Country</code> that the <code>Division</code> belongs
 *                      to
 */
public Division( int divisionId, String division, String date, String createdBy,
                 String lastUpdate, String lastUpdatedBy, int countryId ) {
  this.divisionId    = divisionId;
  this.division      = division;
  this.date          = date;
  this.createdBy     = createdBy;
  this.lastUpdate    = lastUpdate;
  this.lastUpdatedBy = lastUpdatedBy;
  this.countryId     = countryId;
}

/**
 * Gets the <code>Division_ID</code> of a <code>Division</code>
 *
 * @return The ID number of the <code>Division</code> in the database
 */
public int getDivisionId( ) {
  return divisionId;
}

/**
 * Gets the name of a <code>Division</code>
 *
 * @return The name of the <code>Division</code> in the database
 */
public String getDivision( ) {
  return division;
}

/**
 * Gets the date that the <code>Division</code> was created
 *
 * @return The date that the <code>Division</code> was created
 */
public String getDate( ) {
  return date;
}

/**
 * Gets the <code>User</code> that created the <code>Division</code>
 *
 * @return The date that the <code>Division</code> was created
 */
public String getCreatedBy( ) {
  return createdBy;
}

/**
 * Gets the last time that the <code>Division</code> was updated
 *
 * @return The last time that the <code>Division</code> was updated
 */
public String getLastUpdate( ) {
  return lastUpdate;
}

/**
 * Gets the last <code>User</code> that updated the <code>Division</code>
 *
 * @return The last <code>User</code> to update the <code>Division</code>
 */
public String getLastUpdatedBy( ) {
  return lastUpdatedBy;
}

/**
 * Gets the ID number of the <code>Country</code> that the <code>Division</code> belongs to
 *
 * @return The ID number of the <code>Country</code> that the <code>Division</code> belongs to
 */
public int getCountryId( ) {
  return countryId;
}

/**
 * Returns <code>division</code> name as a String
 *
 * @return The <code>division</code> name as a String
 */
@Override
public String toString( ) {
  return divisionId + " " + division;
}
}
