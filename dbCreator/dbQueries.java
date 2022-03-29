
/**
 * dbCreator
 */
public class dbQueries {

    public String userCreationQuery() {
        String query = "CREATE TABLE UserInfo" +
                       "(mailUser VARCHAR(255)," +
                       " userLastName VARCHAR(255)," +
                       " userfirstName VARCHAR(255)," +
                       " userPassword VARCHAR(255)," +
                       " userWallet FLOAT CHECK (userWallet>=0)," +
                       " PRIMARY KEY (mailUser));";

        return query;

    }


    public String sectionsCreationQuery() {
        String query = "CREATE TABLE Sections" +
                       "(trajectId INT," +
                       " sectionId INT," +
                       " cityArrival VARCHAR(255)," +
                       " latArrival FLOAT," +
                       " longArrival FLOAT," +
                       " cityDeparture VARCHAR(255)," +
                       " latDeparture FLOAT," +
                       " longDeparture FLOAT," +
                       " availableSeats INT CHECK (availableSeats>=0)," +
                       " travelDistance FLOAT CHECK (travelDistance>0)," +
                       " travelDuration FLOAT CHECK (travelDuration>0)," +
                       " sectionWaitingDelay INT CHECK (sectionWaitingDelay>=0)," +
                       " sectionStartDate DATE," +
                       " PRIMARY KEY (trajectId, sectionId));";

        return query;
    }


    public String trajectoryCreationQuery() {
        String query = "CREATE TABLE Trajectory" +
                       "(trajectId INT," +
                       " drivenLicenseCar VARCHAR(255)," +
                       " PRIMARY KEY (trajectId, drivenLicenseCar));";

        return query;
    }


    public String carPoolCreationQuery() {
        String query = "CREATE TABLE carPool" +
                       "(trajectId INT," +
                       " mailUser VARCHAR(255)," +
                       " userStatus ENUM('driver', 'passenger')," +
                       " PRIMARY KEY (trajectId, mailUser, userStatus));";

        return query;
    }


    public String carCreationQuery() {
        String query = "CREATE TABLE car" +
                       "(licensePlate VARCHAR(255)" +
                       " carBrand VARCHAR(255)," +
                       " carEnergy ENUM('essence', 'diesel', 'hybride', 'electrique')," +
                       " carFiscalPower FLOAT CHECK (carFiscalPower>0)," +
                       " intialSeatsNumber INT CHECK (intialSeatsNumber>0)" +
                       " PRIMARY KEY (licensePlate));";

        return query;
    }


    public String tripPlanCreationQuery() {
        String query = "CREATE TABLE carPool" +
                       "(tripId INT," +
                       " trajectId INT," +
                       " sectionId INT," +
                       " PRIMARY KEY (trajectId, tripId, sectionId));";

        return query;
    }

}
