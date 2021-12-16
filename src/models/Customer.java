package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 */
public class Customer {

/**
 *
 */
private IntegerProperty customerId;
private StringProperty  customerName;
private StringProperty  address;
private StringProperty postalCode;
private StringProperty phoneNumber;
private StringProperty createDate;
private StringProperty createdBy;
private StringProperty lastUpdate;
private StringProperty lastUpdatedBy;
private IntegerProperty divisionId;
private StringProperty division;
private IntegerProperty countryId;
private StringProperty country;

/**
 * @param customerId
 * @param customerName
 * @param address
 * @param postalCode
 * @param phoneNumber
 * @param createDate
 * @param createdBy
 * @param lastUpdate
 * @param lastUpdatedBy
 * @param divisionId
 * @param division
 * @param countryId
 * @param country
 */
public Customer( IntegerProperty customerId, StringProperty customerName, StringProperty address,
                 StringProperty postalCode, StringProperty phoneNumber, StringProperty createDate,
                 StringProperty createdBy, StringProperty lastUpdate, StringProperty lastUpdatedBy,
                 IntegerProperty divisionId, StringProperty division, IntegerProperty countryId,
                 StringProperty country )
{
  this.customerId    = customerId;
  this.customerName  = customerName;
  this.address       = address;
  this.postalCode    = postalCode;
  this.phoneNumber   = phoneNumber;
  this.createDate    = createDate;
  this.createdBy     = createdBy;
  this.lastUpdate    = lastUpdate;
  this.lastUpdatedBy = lastUpdatedBy;
  this.divisionId    = divisionId;
  this.division      = division;
  this.countryId     = countryId;
  this.country       = country;
}

public Customer(IntegerProperty customerId, StringProperty customerName, StringProperty address,
                StringProperty postalCode, StringProperty division, StringProperty country, StringProperty phoneNumber)
{
  this.customerId = customerId;
  this.customerName = customerName;
  this.address = address;
  this.postalCode = postalCode;
  this.phoneNumber = phoneNumber;
  this.division = division;
  this.country = country;
}

/**
 * @return
 */
public int getCustomerId( ) {
  return customerIdProperty().get();
}

public IntegerProperty customerIdProperty()
{
  if (customerId == null) customerId = new SimpleIntegerProperty(this, "customerId");
  return customerId;
}

/**
 * @return
 */
public String getCustomerName( ) {
  return customerNameProperty().get();
}

public StringProperty customerNameProperty()
{
  if (customerName == null) customerName = new SimpleStringProperty(this, "customerName");
  return customerName;
}

/**
 * @return
 */
public String getAddress( ) {
  return addressProperty().get();
}

public StringProperty addressProperty()
{
  if (address == null) address = new SimpleStringProperty(this, "address");
  return address;
}

/**
 * @return
 */
public String getPostalCode( ) {
  return postalCodeProperty().get();
}

public StringProperty postalCodeProperty()
{
  if (postalCode == null) postalCode = new SimpleStringProperty(this, "postalCode");
  return postalCode;
}

/**
 * @return
 */
public String getPhoneNumber( ) {
  return phoneNumberProperty().get();
}

public StringProperty phoneNumberProperty()
{
  if (phoneNumber == null) phoneNumber = new SimpleStringProperty( this, "phoneNumber" );
  return phoneNumber;
}

/**
 * @return
 */
public StringProperty getCreateDate( ) {return createDate;}

/**
 * @return
 */
public StringProperty getCreatedBy( ) {
  return createdBy;
}

/**
 * @return
 */
public StringProperty getLastUpdate( ) {
  return lastUpdate;
}

/**
 * @return
 */
public StringProperty getLastUpdatedBy( ) {
  return lastUpdatedBy;
}

/**
 * @return
 */
public IntegerProperty getDivisionId( ) {
  return divisionId;
}

/**
 *
 * @return
 */
public String getDivision( ) { return divisionProperty().get(); }

public StringProperty divisionProperty()
{
  if (division == null) division = new SimpleStringProperty( this, "division" );
  return division;
}

/**
 *
 * @return
 */
public IntegerProperty getCountryId( ){ return countryId; }

/**
 *
 * @return
 */
public String getCountry() { return countryProperty().get(); }

public StringProperty countryProperty()
{
  if (country == null) country = new SimpleStringProperty( this, "country" );
  return country;
}

}
