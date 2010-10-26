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
import scala.collection.immutable.Stack
import scala.collection.immutable.Stack.empty

class AdditionalAxis(norm: Norm) extends Computable {

  private def AdditionalAxisGeneral(m:Matrix, norme:(Int,Int,Int)=>Int,intersect:(Int,Int,Int,Int)=>Int ) = {
    var s =new Stack[Int]
    var t =new Stack[Int]
    var g =new Stack[Int]
    var label =new Stack[Int]
    for (i <- 1 until m.nbDim)
    {
      val iter = new m.MatIterator
      s = empty
      t = empty
      g = empty
      label = empty

      while(!iter.isEnd()) {
        while(!iter.isEnd()) {

          val current = iter.getCurrent
          val axisValue = iter.getAxis(i)
          val distance = current.getDistance

          if(current.getDistance >= 0) {
            while(!s.isEmpty && (norme(g.top,s.top,t.top) > norme(distance,axisValue,t.top))) {
              s = s.pop
              t = t.pop
              g = g.pop
              label = label.pop
            }
            if(s.isEmpty) {
              s = s.push(axisValue)
              t = t.push(0)
              g = g.push(distance)
              if (current.isBorder)  label=label.push(iter.getLabel)
              //else label=label.push(iter.getCurrent.getLabels(0))
              else label=label.push(current.getLabel)
            }
            else {

              val w =  intersect(distance,g.top,axisValue,s.top)
              if(w < m.axisRange(i)) {
                s = s.push(axisValue)
                t = t.push(w)
                g = g.push(distance)
                if (current.isBorder)  label=label.push(iter.getLabel)
                //else label=label.push(iter.getCurrent.getLabels(0))
                else label=label.push(current.getLabel)
              }

            }

          }
          iter.incVarAxis(i)
        }

        if(!s.isEmpty) {
          while(!iter.isEnd()) {
            val axisValue = iter.getAxis(i)
            val point=iter.getCurrent
            if(!point.isBorder) {
              point.setDistance(norme(g.top, axisValue, s.top))
              //point.clearLabel
              point.addLabel(label.top)
            }

            if(t.top == axisValue) {
              s = s.pop
              t = t.pop
              g = g.pop
              label = label.pop
            }
            iter.decVarAxis(i)
          }

        }
        iter.setFirstAxis(i)
        iter.incInvarAxis(i)
     
      }
    }
  }

  def computeDistance(m: Matrix) = {
    norm.preOperations(m)
    AdditionalAxisGeneral(m, norm.distance,norm.intersectIndex)
    norm.postOperations(m)

  }

}