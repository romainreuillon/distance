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

import scala.collection.mutable.LinkedList

class BodyPoint extends Point {

//Attributes
private var label: LinkedList [Int] = new LinkedList()
private var distance= -1

 //Getters
 def getDistance : Int = distance

 //Setters
override def setDistance(dist:Int) : Unit = distance=dist

 // Methods
 def dropLabel (lab : Int) = if (label.nonEmpty) label = label.filterNot(_ == lab)
 def clearLabel = label=label.drop(label.length-1)
 def addLabel (lab:Int) = if(!label.exists(_ == lab)) label = label.:+(lab)
 def listLabel(): Unit =  println(label.mkString(", "))
 def isBorder() : Boolean = false




} 
