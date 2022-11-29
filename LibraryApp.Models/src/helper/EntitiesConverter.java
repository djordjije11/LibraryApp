/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helper;

import java.util.ArrayList;
import java.util.List;
import models.IEntity;

/**
 *
 * @author Djordjije
 */
public class EntitiesConverter  {
    public static <T extends IEntity> List<T> convertList(List<IEntity> entities)
        {
            List<T> list = new ArrayList<>();
            for(IEntity entity : entities)
            {
                list.add((T) entity);
            }
            return list;
        }
}
