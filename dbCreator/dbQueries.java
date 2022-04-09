
/**
 * dbCreator
 */
public class dbQueries {

    public String userCreationQuery() {
        String query = "CREATE TABLE userInfo" +
                       "(mailUser VARCHAR(255)," +
                       " userLastName VARCHAR(255)," +
                       " userfirstName VARCHAR(255)," +
                       " userPassword VARCHAR(255)," +
		               " userCity VARCHAR(255)," +
                       " userWallet FLOAT CHECK (userWallet>=0)," +
                       " PRIMARY KEY (mailUser));";

        return query;

    }

    public String carCreationQuery() {
        String query = "CREATE TABLE carInfo" +
                       "(licensePlate VARCHAR(255)," +
                       " carBrand VARCHAR(255)," +
                       " carModel VARCHAR(255)," +
                       " carEnergy ENUM ('essence', 'diesel', 'hybride', 'electrique')," +
                       " carFiscalPower FLOAT CHECK (carFiscalPower>0)," +
                       " intialSeatsNumber INT CHECK (intialSeatsNumber>0)," +
                       " PRIMARY KEY (licensePlate));";

        return query;
    }

    public String trajectoryCreationQuery() {
        String query = "CREATE TABLE trajectory" +
                       "(trajectId INT," +
                       " drivenLicenseCar VARCHAR(255)," +
                       " driverMail VARCHAR(255)," +
                       " PRIMARY KEY (trajectId)," +
                       " FOREIGN KEY (driverMail) REFERENCES userInfo(mailUser)," +
                       " FOREIGN KEY (drivenLicenseCar) REFERENCES carInfo(licensePlate));";

        return query;
    }

    public String sectionsCreationQuery() {
        String query = "CREATE TABLE sections" +
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
                       " PRIMARY KEY (trajectId, sectionId)," +
                       " FOREIGN KEY (trajectId) REFERENCES trajectory(trajectId));";

        return query;
    }



    public String carPoolCreationQuery() {
        String query = "CREATE TABLE carPool" +
                       "(tripId INT," +
                       " mailUser VARCHAR(255)," +
                       " PRIMARY KEY (tripId)," +
                       " FOREIGN KEY (mailUser) REFERENCES userInfo(mailUser));";

        return query;
    }



    public String carOwnerSHipQuery() {
        String query = "CREATE TABLE carOwnership" +
                       "(licensePlate VARCHAR(255)," +
                       " mailUser VARCHAR(255)," +
                       " FOREIGN KEY (mailUser) REFERENCES userInfo(mailUser)," +
                       " FOREIGN KEY (licensePlate) REFERENCES carInfo(licensePlate));";

        return query;
    }


    public String tripPlanCreationQuery() {
        String query = "CREATE TABLE tripPlan" +
                       "(tripId INT," +
                       " trajectId INT," +
                       " sectionId INT," +
                       " FOREIGN KEY (tripId) REFERENCES carPool(tripId)," +
                       " FOREIGN KEY (trajectId, sectionId) REFERENCES sections(trajectId, sectionId));";

        return query;
    }

}
