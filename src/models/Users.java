package models;

import java.time.LocalDateTime;

/**
 *
 */
public class Users {
/**
 *
 */
private int userId;
private String username;
private String password;
private LocalDateTime createDate;
private String createdBy;
private LocalDateTime lastUpdate;
private String lastUpdatedBy;

/**
 *
 * @param userId
 * @param username
 * @param password
 * @param createDate
 * @param createdBy
 * @param lastUpdate
 * @param lastUpdatedBy
 */
public Users( int userId, String username, String password, LocalDateTime createDate, String createdBy,
              LocalDateTime lastUpdate, String lastUpdatedBy ) {
  this.userId        = userId;
  this.username      = username;
  this.password      = password;
  this.createDate    = createDate;
  this.createdBy     = createdBy;
  this.lastUpdate    = lastUpdate;
  this.lastUpdatedBy = lastUpdatedBy;
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
public String getUsername( ) {
  return username;
}

/**
 *
 * @return
 */
public String getPassword( ) {
  return password;
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
}
