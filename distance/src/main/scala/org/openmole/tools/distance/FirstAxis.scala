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

class FirstAxis extends Computable{

def passage1(m: Matrix) = {
  val iter = new m.MatIterator()
  var point: Point = new BorderPoint
  var cpt = 0
  var label = -1
  iter.setFirst
  while(!iter.isEnd()) {
    cpt=0
    while(!iter.isEnd()) {
      point=iter.getCurrent
      if(point.isBorder) {
        label = iter.getLabel
        cpt = 1
      }
      else {
        if(cpt > 0) {
          point.setDistance(cpt)
          point.addLabel(label)
          m.setPoint(point, iter.getCoordinates:_*)
          cpt += 1
        }
      }
      iter.incVarAxis(0)
    }
    iter.setFirstAxis(0)
    iter.incInvarAxis(0)
  }

}

  
def passage2(m: Matrix) = {
  val iter = new m.MatIterator()
  var cpt = 0
  var label = -1
  var point: Point = new BorderPoint
  iter.setLast
  while(!iter.isEnd()) {
    cpt = 0
    while(!iter.isEnd()) {
       point=iter.getCurrent
       if(point.isBorder) {
         label=iter.getLabel
         cpt=1
       }
       else {
         val distance = point.getDistance
         if(cpt>0 && (distance>cpt || distance == -1)) {
           point.setDistance(cpt)
           //point.clearLabel
           point.addLabel(label)
           m.setPoint(point, iter.getCoordinates:_*)
           cpt += 1
         }
       }
       iter.decVarAxis(0)
     }
     iter.setLastAxis(0)
     iter.decInvarAxis(0)
   }
   
 }

  def computeDistance(m:Matrix) = {
    passage1(m)
    passage2(m)
  }

}