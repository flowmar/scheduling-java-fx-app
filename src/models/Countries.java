package models;

/**
 *
 */
public class Countries
{
/**
 *
 */
private int id;
    private String name;

/**
 *
  * @param id
 * @param name
 */
public Countries(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

/**
 *
 * @return
 */
public int getId()
    {
        return id;
    }

/**
 *
  * @return
 */
public String getName()
    {
        return name;
    }
}
