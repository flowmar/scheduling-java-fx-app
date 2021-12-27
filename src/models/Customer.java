package models;

import javafx.beans.property.*;

/**
 *
 */
public class Customer {

/**
 * Fields
 */
private IntegerProperty customerIdProperty;
private StringProperty  customerNameProperty;
private StringProperty  addressProperty;
private StringProperty  postalCodeProperty;
private StringProperty  phoneNumberProperty;
private StringProperty  createDateProperty;
private StringProperty  createdByProperty;
private StringProperty  lastUpdateProperty;
private StringProperty  lastUpdatedByProperty;
private IntegerProperty divisionIdProperty;
private StringProperty  divisionProperty;
private IntegerProperty countryIdProperty;
private StringProperty  countryProperty;
private int             customerId;
private String          customerName;
private String          address;
private String          postalCode;
private String          phoneNumber;
private String          createDate;
private String          createdBy;
private String          lastUpdate;
private String          lastUpdatedBy;
private int             divisionId;
private String          division;
private int             countryId;
private String          country;

/**
 * Constructor that takes in properties to be shown in a TableView
 *
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
                 StringProperty countryProperty ) {
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

public Customer( IntegerProperty customerIdProperty, StringProperty customerNameProperty,
                 StringProperty addressProperty,
                 StringProperty postalCodeProperty, StringProperty divisionProperty, StringProperty countryProperty,
                 StringProperty phoneNumberProperty, IntegerProperty divisionIdProperty,
                 IntegerProperty countryIdProperty ) {
  this.customerIdProperty   = customerIdProperty;
  this.customerNameProperty = customerNameProperty;
  this.addressProperty      = addressProperty;
  this.postalCodeProperty   = postalCodeProperty;
  this.phoneNumberProperty  = phoneNumberProperty;
  this.divisionProperty     = divisionProperty;
  this.countryProperty      = countryProperty;
  this.divisionIdProperty   = divisionIdProperty;
  this.countryIdProperty    = countryIdProperty;
}

public Customer( int customerId, String customerName, String address, String postalCode, String division,
                 String country, String phoneNumber, int divisionId, int countryId ) {
  this.customerId   = customerId;
  this.customerName = customerName;
  this.address      = address;
  this.postalCode   = postalCode;
  this.division     = division;
  this.country      = country;
  this.phoneNumber  = phoneNumber;
  this.divisionId   = divisionId;
  this.countryId    = countryId;
}

public Customer( int customerId, String customerName ) {
  
  this.customerId   = customerId;
  this.customerName = customerName;
  
}

/**
 * Methods
 */


/**
 * Retrieves the <code>customerId</code>
 * @return The <code>customerId</code>
 */
public int getCustomerId( ) {
  return customerIdProperty( ).get( );
}

/**
 * Retrieves the <code>customerId</code> as a property
 * @return The <code>customerId</code> as a property
 */
public IntegerProperty customerIdProperty( ) {
  if ( customerIdProperty == null ) { customerIdProperty = new SimpleIntegerProperty( this, "customerId" ); }
  return customerIdProperty;
}

/**
 * Retrieves the <code>customerName</code>
 * @return The <code>customerName</code>
 */
public String getCustomerName( ) {
  return customerNameProperty( ).get( );
}

/**
 * Retrieves the <code>customerName</code> as a property
 * @return The <code>customerName</code> as a property
 */
public StringProperty customerNameProperty( ) {
  if ( customerNameProperty == null ) { customerNameProperty = new SimpleStringProperty( this, "customerName" ); }
  return customerNameProperty;
}

/**
 * Retrieves the <code>address</code>
 * @return The <code>address</code>
 */
public String getAddress( ) {
  return addressProperty( ).get( );
}

/**
 * Retrieves the <code>address</code> as a property
 * @return The <code>address</code> as a property
 */
public StringProperty addressProperty( ) {
  if ( addressProperty == null ) { addressProperty = new SimpleStringProperty( this, "address" ); }
  return addressProperty;
}

/**
 * Retrieves the <code>postalCode</code>
 * @return The <code>postalCode</code>
 */
public String getPostalCode( ) {
  return postalCodeProperty( ).get( );
}

/**
 * Retrieves the <code>postalCode</code> as a property
 * @return The <code>postalCode</code> as a property
 */
public StringProperty postalCodeProperty( ) {
  if ( postalCodeProperty == null ) { postalCodeProperty = new SimpleStringProperty( this, "postalCode" ); }
  return postalCodeProperty;
}

/**
 * Retrieves the <code>phoneNumber</code>
 * @return The <code>phoneNumber</code>
 */
public String getPhoneNumber( ) {
  return phoneNumberProperty( ).get( );
}

/**
 * Retrieves the <code>phoneNumber</code> as a property
 * @return The <code>phoneNumber</code> as a property
 */
public StringProperty phoneNumberProperty( ) {
  if ( phoneNumberProperty == null ) { phoneNumberProperty = new SimpleStringProperty( this, "phoneNumber" ); }
  return phoneNumberProperty;
}

/**
 * Retrieves the <code>divisionId</code>
 * @return The <code>divisionId</code>
 */
public int getDivisionId( ) { return divisionIdProperty( ).get( ); }

/**
 * Retrieves the <code>divisionId</code> as a property
 * @return The <code>divisionId</code> as a property
 */
public IntegerProperty divisionIdProperty( ) {
  if ( divisionIdProperty == null ) { divisionIdProperty = new SimpleIntegerProperty( this, "divisionId" ); }
  return divisionIdProperty;
}

/**
 * Retrieves the <code>division</code>
 * @return The <code>division</code>
 */
public String getDivision( ) { return divisionProperty( ).get( ); }

/**
 * Retrieves the <code>division</code> as a property
 * @return The <code>division</code> as a property
 */
public StringProperty divisionProperty( ) {
  if ( divisionProperty == null ) { divisionProperty = new SimpleStringProperty( this, "division" ); }
  return divisionProperty;
}

/**
 * Retrieves the <code>countryId</code>
 * @return The <code>countryId</code>
 */
public int getCountryId( ) { return countryIdProperty( ).get( ); }

/**
 * Retrieves the <code>countryId</code> as a property
 * @return The <code>countryId</code> as a property
 */
public IntegerProperty countryIdProperty( ) {
  if ( countryIdProperty == null ) { countryIdProperty = new SimpleIntegerProperty( this, "countryId" ); }
  return countryIdProperty;
}

/**
 * Retrieves the <code>country</code>
 * @return The <code>country</code>
 */
public String getCountry( ) { return countryProperty( ).get( ); }

/**
 * Retrieves the <code>country</code> as a property
 * @return The <code>country</code> as a property
 */
public StringProperty countryProperty( ) {
  if ( countryProperty == null ) { countryProperty = new SimpleStringProperty( this, "country" ); }
  return countryProperty;
}

}
