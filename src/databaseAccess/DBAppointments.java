package databaseAccess;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Appointment;

import java.sql.*;

public class DBAppointments {

/**
 * Retrieves all <code>Appointment</code> records from the appointment table.
 *
 * @return Returns an ArrayList containing all the customers from the database.
 *
 * @throws SQLException Throws a SQLException if the SQL is malformed.
 */
public static ObservableList<Appointment> getAllAppointments( ) throws SQLException {
//    ObservableList<Customer> customers = new ArrayList<Customer>();
  ObservableList<Appointment> appointments = FXCollections.observableArrayList( );
  // Retrieve all stored customers from the database
  try {
    
    String sql = "SELECT * FROM appointments";
    
    PreparedStatement preparedStatement = JDBC.getConnection( ).prepareStatement( sql );
    
    ResultSet appointmentsResultSet = preparedStatement.executeQuery( );
    
    while ( appointmentsResultSet.next( ) ) {
      System.out.println( appointmentsResultSet );
      
      // Get the information from the database
      int    appointmentId = appointmentsResultSet.getInt( "Appointment_ID" );
      String title         = appointmentsResultSet.getString( "Title" );
      String description   = appointmentsResultSet.getString( "Description" );
      String location      = appointmentsResultSet.getString( "Location" );
      String type          = appointmentsResultSet.getString( "Type" );
      Timestamp   start         = appointmentsResultSet.getTimestamp( "Start" );
      Timestamp   end           = appointmentsResultSet.getTimestamp( "End" );
      int    customerId    = appointmentsResultSet.getInt( "Customer_ID" );
      int    userId        = appointmentsResultSet.getInt( "User_ID" );
      int    contactId     = appointmentsResultSet.getInt( "Contact_ID" );
      
      // Convert all to Properties to display in the TableView
      IntegerProperty      appointmentIdProperty = new SimpleIntegerProperty( appointmentId );
      StringProperty       titleProperty         = new SimpleStringProperty( title );
      StringProperty       descriptionProperty   = new SimpleStringProperty( description );
      StringProperty       locationProperty      = new SimpleStringProperty( location );
      StringProperty            typeProperty  = new SimpleStringProperty( type );
      ObjectProperty<Timestamp> startProperty = new SimpleObjectProperty<>( start );
      ObjectProperty<Timestamp>      endProperty   = new SimpleObjectProperty<>( end );
      IntegerProperty      customerIdProperty    = new SimpleIntegerProperty( customerId );
      IntegerProperty      userIdProperty        = new SimpleIntegerProperty( userId );
      IntegerProperty      contactIdProperty     = new SimpleIntegerProperty( contactId );
      
      // Create a new Appointment using the data obtained from the database
      Appointment currentAppointment = new Appointment( appointmentIdProperty, titleProperty, descriptionProperty,
          locationProperty, typeProperty, startProperty, endProperty, userIdProperty,
          customerIdProperty, contactIdProperty );
//      Appointment currentAppointment = new Appointment( appointmentId, title, description,location,type,start,end,
//          customerId,userId,contactId );
      
      // Add the customer to the customers ArrayList
      appointments.add( currentAppointment );
    }
  }
  catch ( SQLException e ) {
    e.printStackTrace( );
  }
  
  return appointments;
}

/**
 * Retrieves an <code>ObservableList</code> of <code>Contact</code>s from the database
 * @return The <code>ObservableList</code> of <code>Contact</code>s
 */
public static ObservableList<String> getContacts( )
{
  ObservableList<String> contacts = FXCollections.observableArrayList( );
  try
  {
    String selectContacts = "Select * FROM client_schedule.contacts";
    
    PreparedStatement preparedStatement = JDBC.getConnection( ).prepareStatement( selectContacts );
  
    ResultSet contactsResultSet = preparedStatement.executeQuery( );
    
    while ( contactsResultSet.next( ) )
    {
      // Get the information from the database
      int    contactId   = contactsResultSet.getInt( "Contact_ID" );
      String contactName = contactsResultSet.getString( "Contact_Name" );
      String email       = contactsResultSet.getString( "Email" );
      
      // Convert each column to a Property
      IntegerProperty contactIdProperty   = new SimpleIntegerProperty( contactId );
      StringProperty  contactNameProperty = new SimpleStringProperty( contactName );
      StringProperty  emailProperty       = new SimpleStringProperty( email );
      
      String contact = contactId + " " + contactName;
      
      contacts.add( contact );
    }
  }
  catch ( SQLException e ) {
    e.printStackTrace( );
  }
  
  return contacts;
}


public static ObservableList<String> getCustomers()
{
  
  ObservableList<String> customers = FXCollections.observableArrayList( );
  // Get the data from the database and add it to the customers list
  try
  {
    // Make the SQL query
    String selectCustomers = "SELECT * FROM client_schedule.customers";
    PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement( selectCustomers );
    ResultSet customersResultSet = preparedStatement.executeQuery( );
    
    while(customersResultSet.next())
    {
      // Get the data from the database
      int customerId = customersResultSet.getInt( "Customer_ID" );
      String customerName = customersResultSet.getString( "Customer_Name" );
  
      String customer = customerId + " " + customerName;
      
      customers.add(customer);
    }
  }
  catch ( SQLException e )
  {
    e.printStackTrace();
  }
  return customers;
}
  
  public static ObservableList<String> getUsers()
{
  ObservableList<String> users       = FXCollections.observableArrayList( );
  
  try {
      String                 selectUsers = "SELECT * FROM client_schedule.users";
      PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement( selectUsers );
      ResultSet usersResultSet = preparedStatement.executeQuery();
      
      while(usersResultSet.next())
      {
        int userId = usersResultSet.getInt( "User_ID" );
        String userName = usersResultSet.getString("User_Name");
        
        String user = userId + " " + userName;
        
        users.add(user);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  return users;
}
}