package models;

/**
 *
 */
public class Country
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
public Country( int id, String name)
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

/**
 *
 * @return
 */
@Override
    public String toString() {
        return ("#" + Integer.toString(id) + ": " + name);
    }
}
