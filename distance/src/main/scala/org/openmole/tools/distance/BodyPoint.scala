/*
 *  Copyright (C) 2010 elbrini
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openmole.tools.distance

class BodyPoint extends Point {

//Attributes
  private var label = -1
  private var distance:Int = -1

  //Getters
  def getDistance: Int = distance
  def getLabel = label

  //Setters
  override def setDistance(dist:Int): Unit = distance = dist

  // Methods
  def addLabel(lab: Int) = label = lab
  def isBorder(): Boolean = false
  
} 
