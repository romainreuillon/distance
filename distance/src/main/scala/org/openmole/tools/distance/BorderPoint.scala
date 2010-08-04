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
class BorderPoint extends Point{
  //Attributes
def isBorder() : Boolean= true
def getDistance = 0
def setDistance(dist:Int) : Unit =println("Cannot set distance for a border point")
def dropLabel (lab : Int) : Unit = println("A border point has no label")
def clearLabel : Unit = println("A border point has no label")
def addLabel (lab:Int) : Unit =println("A border point does not support labels")
def listLabel(): Unit =println("A border point has no label")
}
