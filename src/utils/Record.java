/**
 * Record Class
 * */
package utils;

import com.sun.security.jgss.GSSUtil;
import dao.GeneralCrudDaoImpl;
import javafx.util.Pair;
import model.*;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Record Class
 * */
public class Record extends GeneralCrudDaoImpl {
    private ArrayList<Customer> customerList = new ArrayList<>();
    private ArrayList<Appointment> appointmentList = new ArrayList<>();
    private ArrayList<Contact> contactList = new ArrayList<>();
    private ArrayList<Country> countryList = new ArrayList<>();
    private ArrayList<FirstLevelDivision> divisionList = new ArrayList<>();
    private int[] monthCountArr = new int[12];
    private Map<String, Integer> map = new HashMap<String, Integer>();
    private Map<String, String> mapContact = new HashMap<String, String>();

    public Pair<int[], String> getCustomerAppointmentCountRecord() throws Exception {
        Appointment appointment;
        Contact contact;
        Country country;
        FirstLevelDivision division;
        Customer customer;

        DBConnection.makeConnection();
        ResultSet results = findCustom("SELECT * FROM appointments " +
                "INNER JOIN customers ON appointments.Customer_ID = customers.Customer_ID " +
                "INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID");
        while (results.next()) {
            int id = results.getInt("Appointment_ID");
            String title = results.getString("Title");
            String description = results.getString("Description");
            String location = results.getString("Location");
            String type = results.getString("Type");
            String start = MyTime.getLocaleTime(results.getString("Start"));
            String end = MyTime.getLocaleTime(results.getString("End"));
            String createDate = MyTime.getLocaleTime(results.getString("Create_Date"));
            String createdBy = results.getString("Created_By");
            String lastUpdate = MyTime.getLocaleTime(results.getString("Last_Update"));
            String lastUpdateBy = results.getString("Last_Updated_By");
            int customerId = results.getInt("Customer_ID");
            int userId = results.getInt("USER_ID");
            int contactId = results.getInt("CONTACT_ID");
            String contactName = results.getString("Contact_Name");
            String customerName = results.getString("Customer_Name");
            String divisionId = results.getString("division_ID");
            String address = results.getString("Address");
            String postalCode =results.getString("Postal_Code");

            appointment = new Appointment(id, title, description, location, type, start, end, createDate, createdBy,
                    lastUpdate,
                    lastUpdateBy, customerId, userId, contactId);

            appointment.setContactName(contactName);
            appointment.setCustomerName(customerName);
            appointment.setDivisionId(Integer.parseInt(divisionId));
            appointment.setAddress(address + postalCode);

            appointmentList.add(appointment);
        }

        results = findAll("contacts");
        while (results.next()) {
            int id = results.getInt("Contact_ID");
            String name = results.getString("Contact_Name");
            String email = results.getString("Email");
            contact = new Contact(id, name, email);
            contactList.add(contact);
        }


        results = findAll("countries");
        while (results.next()) {
            int id = results.getInt("Country_ID");
            String countryName = results.getString("Country");
            Timestamp createDate = results.getTimestamp("Create_Date");
            String createdBy = results.getString("Created_By");
            Timestamp lastUpdate = results.getTimestamp("Last_Update");
            String lastUpdatedBy = results.getString("Last_Updated_By");
            country = new Country(id, countryName, createDate, createdBy, lastUpdate, lastUpdatedBy);
            countryList.add(country);
        }

        results = findCustom("SELECT * FROM customers " +
                "INNER JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.DIVISION_ID " +
                "INNER JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID");
        while (results.next()) {
            int id = results.getInt("Customer_ID");
            String name = results.getString("Customer_Name");
            String address = results.getString("Address");
            String postalCode = results.getString("Postal_Code");
            String phone = results.getString("Phone");
            String createDate = MyTime.getLocaleTime(results.getString("Create_Date"));
            String createdBy = results.getString("Created_By");
            String lastUpdate = MyTime.getLocaleTime(results.getString("Last_Update"));
            String lastUpdateBy = results.getString("Last_Updated_By");
            int divisionId = results.getInt("Division_ID");
            String divisionCName = results.getString("Division");
            String countryName = results.getString("Country");
            int countryId = results.getInt("Country_ID");

            customer = new Customer(id, name, address, postalCode, phone, createDate, createdBy, lastUpdate,
                    lastUpdateBy, divisionId);
            customer.setDivision(divisionCName);
            customer.setCountry(countryName);
            customer.setCountryId(countryId);
            customerList.add(customer);
        }

        DBConnection.closeConnection();

        for (Appointment a : appointmentList) {
            if (MyTime.getMonth(a.getStart()) == Month.JANUARY) {
                monthCountArr[0]++;
            } else if (MyTime.getMonth(a.getStart()) == Month.FEBRUARY) {
                monthCountArr[1]++;
            } else if (MyTime.getMonth(a.getStart()) == Month.MARCH) {
                monthCountArr[2]++;
            } else if (MyTime.getMonth(a.getStart()) == Month.APRIL) {
                monthCountArr[3]++;
            } else if (MyTime.getMonth(a.getStart()) == Month.MAY) {
                monthCountArr[4]++;
            } else if (MyTime.getMonth(a.getStart()) == Month.JUNE) {
                monthCountArr[5]++;
            } else if (MyTime.getMonth(a.getStart()) == Month.JULY) {
                monthCountArr[6]++;
            } else if (MyTime.getMonth(a.getStart()) == Month.AUGUST) {
                monthCountArr[7]++;
            } else if (MyTime.getMonth(a.getStart()) == Month.SEPTEMBER) {
                monthCountArr[8]++;
            } else if (MyTime.getMonth(a.getStart()) == Month.OCTOBER) {
                monthCountArr[9]++;
            } else if (MyTime.getMonth(a.getStart()) == Month.NOVEMBER) {
                monthCountArr[10]++;
            } else if (MyTime.getMonth(a.getStart()) == Month.DECEMBER) {
                monthCountArr[11]++;
            }
        }

        for (Appointment a : appointmentList) {
            String s = a.getType() + ": ";
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }
        String cText = "CONTACT'S APPOINTMENT --------------------------------------\n\n";
        for (Contact c : contactList) {
            cText = cText + c.getContactName() + "\n\n";
            for (Appointment a : appointmentList) {
                if (c.getId() == a.getContactId()) {
                    cText = cText + String.format("\t\tAPPOINTMENT ID: %d \n" +
                                    "\tTITLE: %s, \t" +
                                    "TYPE: %s, \t" +
                                    "DESCRIPTION: %s \n" +
                                    "\tSTARTING DATE: and Time: %s, \n" +
                                    "\tENDING DATE AND TIME: %s \n" +
                                    "\tCUSTOMER ID: %d, \t" +
                                    "CUSTOMER NAME: %s \n\n", a.getId(),
                            a.getTitle(), a.getType(), a.getDescription(), a.getStart(),
                            a.getEnd(), a.getCustomerId(), a.getContactName());
                }
            }
        }

        String countryText = "CUSTOMER PER COUNTRY --------------------------------------\n\n";
        for (Country co : countryList) {
            countryText = countryText + co.getCountry() + "\n\n";
            for (Customer c : customerList) {
                if (co.getCountryId() == c.getCountryId()) {
                    countryText = countryText + String.format("\tCUSTOMER ID: %d \n" +
                                    "\tCUSTOMER NAME: %s \n" +
                                    "\tPhone: %s \n " +
                                    "\tADDRESS: %s, " +
                                    "%s, " +
                                    "%s\n\n",
                            c.getId(), c.getName(), c.getPhone(), c.getAddress(),   c.getDivision(),c.getPostalCode()
                            );
                }
            }
        }


        String typeText = "APPOINTMENT TYPE -------------------------------------- \n\n";

        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        for (Map.Entry<String, Integer> entry : entrySet) {
            typeText = typeText + String.format("%s%d %n", entry.getKey(), entry.getValue());
        }

        typeText = typeText + "\n\n" + cText + "\n\n" + countryText;

        return new Pair<int[], String>(monthCountArr, typeText);
    }

    public String getContactAppointmentRecord() {
        return "";
    }


}
