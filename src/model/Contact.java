/**
 * Contact Model Class
 */
package model;

/**
 * Contact Model Class
 */
public class Contact {
    private int id;
    private String contactName;
    private String email;

    public Contact(int id, String contactName, String email) {
        this.id = id;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * Get Id
     *
     * @return id of contact
     * */
    public int getId() {
        return id;
    }

    /**
     * Set Id
     *
     * @param id of contact
     * */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get Name
     *
     * @return contactName of contact
     * */
    public String getContactName() {
        return contactName;
    }

    /**
     * Set Name
     *
     * @param contactName of contact
     * */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Get Email
     *
     * @return email of contact
     * */
    public String getEmail() {
        return email;
    }

    /**
     * Set email
     *
     * @param email of contact
     * */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Override string
     * */
    @Override
    public String toString(){
        return(contactName);
    }
}
