package message;

import java.io.Serializable;

/**
 *
 * @author Djordjije
 */
public enum Method implements Serializable {
    EXIT,
    LOGIN,
    CREATE,
    READALL,    //get all entities
    FINDWHERE,  //find entities that meet the condition
    GET,    //get the entity that is wanted
    UPDATE,
    DELETE
}
