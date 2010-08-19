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

abstract class Computable {
  
  def computeDistance(m: Matrix):Unit

  def showResults(m: Matrix)= {
    val iter= new m.MatIterator
    var point: Point = new BorderPoint
    while(!iter.isEnd) {
      while(!iter.isEnd) {
         point=iter.getCurrent
        if(!point.isBorder){
         // println( iter.getCoordinates.mkString("(", ",", ")") +" "+point.getDistance+" "+m.labelToCoordinates(iter.getCurrent.getLabels(0)).mkString("(", ",", ")"))
            println( iter.getCoordinates.mkString("(", ",", ")") +" "+point.getDistance+" "+m.labelToCoordinates(iter.getCurrent.getLabel).mkString("(", ",", ")"))
        }
        iter.incVarAxis(0)
     }
     iter.setFirstAxis(0)
     iter.incInvarAxis(0)
   }
 }
}
