/**
 * Appointment Model Class
 */
package model;

/**
 * Appointment Model Class
 */
public class Appointment {
    private int id;
    private String title;
    private String description;
    private String location;
    private String type;
    private String start;
    private String end;
    private String createDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;

    public Appointment(int id, String title, String description, String location, String type, String start,
                       String end, String createDate, String createdBy, String lastUpdate, String lastUpdatedBy,
                       int customerId, int userId, int contactId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }


    private int customerId;
    private int userId;
    private int contactId;
    private String contactName;
    private String customerName;
    private int divisionId;
    private String address;

    /**
     * Get Address
     * */
    public String getAddress() {
        return address;
    }

    /**
     * Set Address
     * */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Set Division ID
     *
     * @param divisionId of appointment
     * */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * Get Contact Name
     * */
    public String getContactName() {
        return contactName;
    }

    /**
     * Set Contact Name
     *
     * @param contactName of appointment
     * */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }


    /**
     * Get Customer Name
     * */
    public String getCustomerName() {
        return customerName;
    }


    /**
     * Set Contact Name
     *
     * @param customerName of appointment
     * */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    /**
     * Get Id
     *
     * @return id of appointment*/
    public int getId() {
        return id;
    }

    /**
     * Set ID
     *
     * @param id of appointment
     * */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Get Title
     * */
    public String getTitle() {
        return title;
    }

    /**
     * Set Title
     *
     * @param title of appointment
     * */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get Description
     * */
    public String getDescription() {
        return description;
    }


    /**
     * Set Description
     *
     * @param description of appointment
     * */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * get Location
     * */
    public String getLocation() {
        return location;
    }


    /**
     * set Location.
     *
     * @param location of appointment
     * */
    public void setLocation(String location) {
        this.location = location;
    }


    /**
     * Get Type.
     * */
    public String getType() {
        return type;
    }


    /**
     * set Type
     *
     * @param type of appointment
     * */
    public void setType(String type) {
        this.type = type;
    }


    /**
     * Get Start
     * */
    public String getStart() {
        return start;
    }


    /**
     * set Start
     *
     * @param start of appointment
     * */
    public void setStart(String start) {
        this.start = start;
    }


    /**
     * Get End
     * */
    public String getEnd() {
        return end;
    }


    /**
     * Get End
     *
     * @param end of appointment
     * */
    public void setEnd(String end) {
        this.end = end;
    }


    /**
     * Get CreateDate
     * */
    public String getCreateDate() {
        return createDate;
    }


    /**
     * set CreateDate
     *
     * @param createDate of appointment
     * */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }


    /**
     * Get CreatedBy
     * */
    public String getCreatedBy() {
        return createdBy;
    }


    /**
     * set CreatedBy
     *
     * @param createdBy of appointment
     * */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }


    /**
     * Get LastUpdate
     * */
    public String getLastUpdate() {
        return lastUpdate;
    }


    /**
     * set LastUpdate
     *
     * @param lastUpdate of appointment
     * */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }


    /**
     * Get LastUpdatedBy
     * */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }


    /**
     * set LastUpdatedBy
     *
     * @param lastUpdatedBy of appointment
     * */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }


    /**
     * Get CustomerId
     * */
    public int getCustomerId() {
        return customerId;
    }


    /**
     * set CustomerId
     *
     * @param customerId of appointment
     * */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }


    /**
     * Get UserId
     * */
    public int getUserId() {
        return userId;
    }


    /**
     * set UserId
     *
     * @param userId of appointment
     * */
    public void setUserId(int userId) {
        this.userId = userId;
    }


    /**
     * Get ContactId
     * */
    public int getContactId() {
        return contactId;
    }


    /**
     * set ContactId
     *
     * @param contactId of appointment
     * */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
}
