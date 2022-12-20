package message;

/**
 *
 * @author Djordjije
 */
public enum Method {
    LOGIN,
    CREATE,
    READALL,    //get all entities
    FINDWHERE,  //find entities that meet the condition
    GET,    //get the entity that is wanted
    UPDATE,
    DELETE
}
