package database.sql.brokers.interfaces;

import models.Building;
import java.sql.Connection;

/**
 *
 * @author Djordjije
 */
public interface IBuildingBroker {
    Building findBuilding(Building building, Connection connection) throws Exception;
}
