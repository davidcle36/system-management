/**
 * First Level Division Model Class
 */
package model;

import java.sql.Timestamp;

/**
 * First Level Division Model Class
 */
public class FirstLevelDivision {
    private int divisionId;
    private String division;
    private Timestamp Create_Date;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int countryId;

    public FirstLevelDivision(int divisionId, String division, Timestamp create_Date, String createdBy,
                              Timestamp lastUpdate, String lastUpdatedBy, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        Create_Date = create_Date;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryId = countryId;
    }

    /**
     * get DivisionId
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * get Division
     */
    public String getDivision() {
        return division;
    }

    /**
     * get Create_Date
     */
    public Timestamp getCreate_Date() {
        return Create_Date;
    }

    /**
     * get CreatedBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * get LastUpdate
     */
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    /**
     * get LastUpdatedBy
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * get CountryId
     */
    public int getCountryId() {
        return countryId;
    }

    @Override
    public String toString() {
        return (division);
    }
}
