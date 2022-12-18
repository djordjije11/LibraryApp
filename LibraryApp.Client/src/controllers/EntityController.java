/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import models.IEntity;

/**
 *
 * @author Djordjije
 */
public interface IController {
    public void save();
    public void delete();
    public void readAll();
    public void find(IEntity entity);
}
