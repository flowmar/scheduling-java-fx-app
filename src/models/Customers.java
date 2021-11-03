package models;

import java.time.LocalDateTime;

/**
 *
 */
public class Customers {

/**
 *
 */
private int customerId;
private String customerName;
private String address;
private String postalCode;
private String phoneNumber;
private LocalDateTime createDate;
private String createdBy;
private LocalDateTime lastUpdate;
private String lastUpdatedBy;
private int divisionId;

/**
 *
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
 */
public Customers(int customerId, String customerName, String address, String postalCode, String phoneNumber,
                       LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy,
                       int divisionId)
{
  this.customerId = customerId;
  this.customerName = customerName;
  this.address = address;
  this.postalCode = postalCode;
  this.phoneNumber = phoneNumber;
  this.createDate = createDate;
  this.createdBy = createdBy;
  this.lastUpdate = lastUpdate;
  this.lastUpdatedBy = lastUpdatedBy;
  this.divisionId = divisionId;
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
public String getCustomerName( ) {
  return customerName;
}

/**
 *
 * @return
 */
public String getAddress( ) {
  return address;
}

/**
 *
 * @return
 */
public String getPostalCode( ) {
  return postalCode;
}

/**
 *
 * @return
 */
public String getPhoneNumber( ) {
  return phoneNumber;
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
public int getDivisionId( ) {
  return divisionId;
}
}
