package models;

import java.time.LocalDateTime;

/**
 *
 */
public class User {
/**
 * Fields
 */
private int           userId;
private String        username;
private String        password;
private LocalDateTime createDate;
private String        createdBy;
private LocalDateTime lastUpdate;
private String        lastUpdatedBy;

/**
 * @param userId
 * @param username
 * @param password
 * @param createDate
 * @param createdBy
 * @param lastUpdate
 * @param lastUpdatedBy
 */
public User( int userId, String username, String password, LocalDateTime createDate, String createdBy,
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
 * Methods
 */

/**
 * Returns the identification number of the <code>User</code>
 *
 * @return The identification number of the <code>User</code>
 */
public int getUserId( ) {
  return userId;
}

/**
 * Returns the <code>username</code> of the <code>User</code>
 *
 * @return The <code>username</code> of the <code>User</code>
 */
public String getUsername( ) {
  return username;
}

/**
 * Returns the <code>password</code> of the <code>User</code>
 *
 * @return The <code>password</code> of the <code>User</code>
 */
public String getPassword( ) {
  return password;
}

}