package database.sql.brokers.impl;

import database.sql.brokers.interfaces.IBuildingBroker;
import database.sql.sqlmodels.SqlBuilding;
import java.sql.Connection;
import models.Building;

/**
 *
 * @author Djordjije
 */
public class SqlBuildingBroker extends SqlEntityBroker<Building> implements IBuildingBroker {
    @Override
    public Building findBuilding(Building building, Connection connection) throws Exception {
        return find(new SqlBuilding(building), connection);
    }
    @Override
    public boolean checkIfBuildingExists(Building building, Connection connection) throws Exception {
        return checkIfExists(new SqlBuilding(building), connection);
    }
}
