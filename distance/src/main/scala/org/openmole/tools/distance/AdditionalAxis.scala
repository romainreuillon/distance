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

object AdditionalAxis {

def AdditionalAxis(m:Matrix )=
{
  for (i <- 0 until m.nbDim)
  {
    val iter = new m.MatIterator
    var s =new Stack[Int]
    var t =new Stack[Int]
    var g =new Stack[Int]
    var label =new Stack[Int]
    while(!iter.isEnd())
    {
      //s=s.push(0)
      //t=t.push(0)
     // g=g.push(iter.getCurrent.getDistance)
      //label=label.push(iter.getLabel)

      while(!iter.isEnd())
      {
        if(iter.getCurrent.getDistance>=0)
        {
          while(!s.isEmpty && ((g.top+(s.top-t.top)*(s.top-t.top))>(iter.getCurrent.getDistance+(iter.getAxis(i)-t.top)*(iter.getAxis(i)-t.top))))
          {
           s=s.pop
           t=t.pop
           g=g.pop
           label=label.pop
          }
          if(s.isEmpty)
          {
                      s=s.push(iter.getAxis(i))
                      t=t.push(0)
                      g=g.push(iter.getCurrent.getDistance)
                      label=label.push(iter.getLabel)
         }
         else
         {
           val w= 1+(s.top*s.top-iter.getAxis(i)*iter.getAxis(i)+g.top-iter.getCurrent.getDistance)/2*(s.top-iter.getAxis(i))
           if(w<m.axisRange(i)){
                      s=s.push(iter.getAxis(i))
                      t=t.push(w)
                      g=g.push(iter.getCurrent.getDistance)
                      label=label.push(iter.getLabel)

                               }

         }
        }
         iter.incVarAxis(i)
       

     }
     if(!s.isEmpty)
     {
      while(!iter.isEnd())
       {
         val point=iter.getCurrent
         point.setDistance(g.top + (iter.getAxis(i)-s.top)*(iter.getAxis(i)-s.top))
         point.clearLabel
         point.addLabel(label.top)
         m.setPoint(point, iter.getCoordinates:_*) 
       
         if(t.top==iter.getAxis(i))
         {
          s=s.pop
          t=t.pop
          g=g.pop
          label=label.pop
        }
        iter.decVarAxis(i)
      }
      
    }
     iter.setFirstAxis(i)
     iter.incInvarAxis(i)
     

     }
   }

}
}