package models;

/**
 *
 */
public class Division
{
/**
 *
 */
private int           divisionId;
    private String        division;
    private String date;
    private String createdBy;
    private String lastUpdate;
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
public Division( int divisionId, String division, String date, String createdBy,
                 String lastUpdate, String lastUpdatedBy, int countryId )
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
public String date()
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
public String lastUpdate()
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
   
@Override
public String toString()
{
  return (divisionId + " : " + division);
}
}
