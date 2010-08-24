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

class NormEuc extends Norm {
def intersectIndex(currentDist: Int, stackDist: Int, i: Int, l: Int) = 1 + (l*l - i*i + stackDist - currentDist)/(2*(l - i))

def distance(dist: Int, i: Int, l: Int) = {
  val tmp = i-l
  dist + tmp*tmp
}

def preOperations(m: Matrix) = {
    val iter = new m.MatIterator
    var point: Point = new BorderPoint
    var distance=0
    while(!iter.isEnd) {
      while(!iter.isEnd) {
        point = iter.getCurrent
        distance = point.getDistance
        if(distance > 0) {
          point.setDistance(distance*distance)
        }
        iter.incVarAxis(0)
      }
      iter.setFirstAxis(0)
      iter.incInvarAxis(0)
   }
 }

def postOperations(m: Matrix): Unit = {}
}
