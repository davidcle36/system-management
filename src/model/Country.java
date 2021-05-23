/**
 * Country Model Class
 */
package model;

import java.sql.Timestamp;

/**
 * Country Model Class
 */
public class Country {
    private int countryId;
    private String country;
    private Timestamp Create_Date;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;

    public Country(int countryId, String country, Timestamp create_Date, String createdBy, Timestamp lastUpdate,
                   String lastUpdatedBy) {
        this.countryId = countryId;
        this.country = country;
        Create_Date = create_Date;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * get CountryId
     *
     * */
    public int getCountryId() {
        return countryId;
    }

    /**
     * get Country
     * */
    public String getCountry() {
        return country;
    }

    /**
     * get Create_Date
     * */
    public Timestamp getCreate_Date() {
        return Create_Date;
    }

    /**
     * get CreatedBy
     * */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * get LastUpdate
     * */
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    /**
     * get LastUpdatedBy
     * */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    @Override
    public String toString(){
        return(country);
    }
}
