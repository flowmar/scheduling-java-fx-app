package models;

/**
 * The <code>Country</code> that a <code>Customer</code> resides in
 */
public class Country
{
/**
 * Fields
 */
private int id;
private String name;

/**
 * Constructor for a <code>Country</code> object
  * @param id The ID number of the <code>Country</code> in the database
 * @param name The <code>name</code> of the <code>Country</code>
 */
public Country( int id, String name)
    {
        this.id = id;
        this.name = name;
    }

/**
 * Gets the ID number of the <code>Country</code> in the database
 * @return The ID number of the <code>Country</code>
 */
public int getId()
    {
        return id;
    }

/**
 * Gets the <code>name</code> of the <code>Country</code>
  * @return The <code>name</code> of the <code>Country</code>
 */
public String getName()
    {
        return name;
    }

/**
 *  Returns the <code>id</code> and the <code>name</code> of the <code>Country</code> as a String in the format "#
 *  (id) :
 *  (name)"
 * @return The <code>id</code> and the <code>name</code> as a String
 */
@Override
    public String toString() {
        return ("#" + Integer.toString(id) + ": " + name);
    }
}
