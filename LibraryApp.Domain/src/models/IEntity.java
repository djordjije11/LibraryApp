package models;

import message.ModelElement;

/**
 *
 * @author Djordjije
 */
public interface IEntity {
    ModelElement getModelElement();
    void setId(Long id);
    Long getId();
}
