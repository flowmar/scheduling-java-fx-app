package models;

import javafx.beans.property.*;

/**
 *
 */
public class Customer {

/**
 *
 */
private IntegerProperty customerIdProperty;
private StringProperty  customerNameProperty;
private StringProperty  addressProperty;
private StringProperty postalCodeProperty;
private StringProperty phoneNumberProperty;
private StringProperty createDateProperty;
private StringProperty createdByProperty;
private StringProperty lastUpdateProperty;
private StringProperty lastUpdatedByProperty;
private IntegerProperty divisionIdProperty;
private StringProperty divisionProperty;
private IntegerProperty countryIdProperty;
private StringProperty countryProperty;
private int customerId;
private String  customerName;
private String  address;
private String postalCode;
private String phoneNumber;
private String createDate;
private String createdBy;
private String lastUpdate;
private String lastUpdatedBy;
private int divisionId;
private String division;
private int countryId;
private String country;

/**
 * @param customerIdProperty
 * @param customerNameProperty
 * @param addressProperty
 * @param postalCodeProperty
 * @param phoneNumberProperty
 * @param createDateProperty
 * @param createdByProperty
 * @param lastUpdateProperty
 * @param lastUpdatedByProperty
 * @param divisionIdProperty
 * @param divisionProperty
 * @param countryIdProperty
 * @param countryProperty
 */
public Customer( IntegerProperty customerIdProperty, StringProperty customerNameProperty,
                 StringProperty addressProperty,
                 StringProperty postalCodeProperty, StringProperty phoneNumberProperty,
                 StringProperty createDateProperty,
                 StringProperty createdByProperty, StringProperty lastUpdateProperty,
                 StringProperty lastUpdatedByProperty,
                 IntegerProperty divisionIdProperty, StringProperty divisionProperty, IntegerProperty countryIdProperty,
                 StringProperty countryProperty )
{
  this.customerIdProperty    = customerIdProperty;
  this.customerNameProperty  = customerNameProperty;
  this.addressProperty       = addressProperty;
  this.postalCodeProperty    = postalCodeProperty;
  this.phoneNumberProperty   = phoneNumberProperty;
  this.createDateProperty    = createDateProperty;
  this.createdByProperty     = createdByProperty;
  this.lastUpdateProperty    = lastUpdateProperty;
  this.lastUpdatedByProperty = lastUpdatedByProperty;
  this.divisionIdProperty    = divisionIdProperty;
  this.divisionProperty      = divisionProperty;
  this.countryIdProperty     = countryIdProperty;
  this.countryProperty       = countryProperty;
}

public Customer(IntegerProperty customerIdProperty, StringProperty customerNameProperty, StringProperty addressProperty,
                StringProperty postalCodeProperty, StringProperty divisionProperty, StringProperty countryProperty,
                StringProperty phoneNumberProperty, IntegerProperty divisionIdProperty,
                IntegerProperty countryIdProperty)
{
  this.customerIdProperty = customerIdProperty;
  this.customerNameProperty = customerNameProperty;
  this.addressProperty = addressProperty;
  this.postalCodeProperty = postalCodeProperty;
  this.phoneNumberProperty = phoneNumberProperty;
  this.divisionProperty = divisionProperty;
  this.countryProperty = countryProperty;
  this.divisionIdProperty = divisionIdProperty;
  this.countryIdProperty = countryIdProperty;
}

public Customer(int customerId, String customerName, String address, String postalCode, String division,
                String country, String phoneNumber, int divisionId, int countryId)
{
  this.customerId = customerId;
  this.customerName = customerName;
  this.address = address;
  this.postalCode = postalCode;
  this.division = division;
  this.country = country;
  this.phoneNumber = phoneNumber;
  this.divisionId = divisionId;
  this.countryId = countryId;
}

public Customer( int customerId, String customerName ) {

this.customerId = customerId;
this.customerName = customerName;

}

/**
 * @return
 */
public int getCustomerId( ) {
  return customerIdProperty().get();
}

public IntegerProperty customerIdProperty()
{
  if (customerIdProperty == null) customerIdProperty = new SimpleIntegerProperty(this, "customerId");
  return customerIdProperty;
}

/**
 * @return
 */
public String getCustomerName( ) {
  return customerNameProperty().get();
}

public StringProperty customerNameProperty()
{
  if (customerNameProperty == null) customerNameProperty = new SimpleStringProperty(this, "customerName");
  return customerNameProperty;
}

/**
 * @return
 */
public String getAddress( ) {
  return addressProperty().get();
}

public StringProperty addressProperty()
{
  if (addressProperty == null) addressProperty = new SimpleStringProperty(this, "address");
  return addressProperty;
}

/**
 * @return
 */
public String getPostalCode( ) {
  return postalCodeProperty().get();
}

public StringProperty postalCodeProperty()
{
  if (postalCodeProperty == null) postalCodeProperty = new SimpleStringProperty(this, "postalCode");
  return postalCodeProperty;
}

/**
 * @return
 */
public String getPhoneNumber( ) {
  return phoneNumberProperty().get();
}

public StringProperty phoneNumberProperty()
{
  if (phoneNumberProperty == null) phoneNumberProperty = new SimpleStringProperty( this, "phoneNumber" );
  return phoneNumberProperty;
}

///**
// * @return
// */
//public StringProperty getCreateDate( ) {return createDate.get();}
//
///**
// * @return
// */
//public StringProperty getCreatedBy( ) {
//  return createdBy;
//}
//
///**
// * @return
// */
//public StringProperty getLastUpdate( ) {
//  return lastUpdate;
//}
//
///**
// * @return
// */
//public StringProperty getLastUpdatedBy( ) {
//  return lastUpdatedBy;
//}

/**
 * @return
 */
public int getDivisionId() { return divisionIdProperty().get(); }

public IntegerProperty divisionIdProperty( )
{
  if (divisionIdProperty == null) divisionIdProperty = new SimpleIntegerProperty( this, "divisionId" );
  return divisionIdProperty;
}

/**
 *
 * @return
 */
public String getDivision( ) { return divisionProperty().get(); }

public StringProperty divisionProperty()
{
  if (divisionProperty == null) divisionProperty = new SimpleStringProperty( this, "division" );
  return divisionProperty;
}

/**
 *
 * @return
 */
public int getCountryId() { return countryIdProperty().get();}

public IntegerProperty countryIdProperty()
{
  if (countryIdProperty == null) countryIdProperty = new SimpleIntegerProperty( this, "countryId" );
  return countryIdProperty; }

/**
 *
 * @return
 */
public String getCountry() { return countryProperty().get(); }

public StringProperty countryProperty()
{
  if (countryProperty == null) countryProperty = new SimpleStringProperty( this, "country" );
  return countryProperty;
}

}
