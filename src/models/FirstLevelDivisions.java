package models;

import java.time.LocalDateTime;

/**
 *
 */
public class FirstLevelDivisions
{
/**
 *
 */
private int           divisionId;
    private String        division;
    private LocalDateTime date;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private int countryId;

/**
 *
 * @param divisionId
 * @param division
 * @param date
 * @param createdBy
 * @param lastUpdate
 * @param lastUpdatedBy
 * @param countryId
 */
public FirstLevelDivisions( int divisionId, String division, LocalDateTime date, String createdBy,
                                LocalDateTime lastUpdate, String lastUpdatedBy, int countryId )
    {
      this.divisionId = divisionId;
      this.division =division;
      this.date = date;
      this.createdBy = createdBy;
      this.lastUpdate = lastUpdate;
      this.lastUpdatedBy = lastUpdatedBy;
      this.countryId = countryId;
    }

/**
 *
 * @return
 */
public int getDivisionId()
    {
      return divisionId;
    }

/**
 *
 * @return
 */
public String getDivision()
    {
      return division;
    }

/**
 *
 * @return
 */
public LocalDateTime date()
   {
     return date;
   }

/**
 *
 * @return
 */
public String createdBy()
   {
     return createdBy;
   }

/**
 *
 * @return
 */
public LocalDateTime lastUpdate()
   {
     return lastUpdate;
   }

/**
 *
 * @return
 */
public String lastUpdatedBy()
   {
     return lastUpdatedBy;
   }

/**
 *
 * @return
 */
public int countryId()
   {
     return countryId;
   }
   

}
