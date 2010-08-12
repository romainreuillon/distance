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
import scala.Math.{abs,max,min}

object AdditionalAxis {

//La fonction de calcul F pour la norme euclidienne
  private def f(dist:Int,i:Int,l:Int)= dist+(i-l)*(i-l)
//La fonction de calcul V pour la norme sup
  private def v(dist:Int,i:Int,l:Int)=max(dist,abs(i-l))
// fonction de calcul de l'indice d'intersection des paraboles dans le cas de la norme euclidienne
  private def intersectEuc(currentDist:Int,stackDist:Int,i:Int,l:Int)= 1+(l*l-i*i+stackDist-currentDist)/(2*(l-i))
//fonction de calcul de l'indice d'intersection des paraboles dans le cas de la norme sup
  private  def intersectSup(currentDist:Int,stackDist:Int,i:Int,l:Int)= if(l<=i)
                                                                {
                                                                  if(stackDist<=currentDist) 1+max(l+currentDist,(l+i)/2)
                                                                  else 1+min(i-stackDist,(l+i)/2)
                                                                }
                                                                else -1




  private def carre(m:Matrix)= {
    val iter= new m.MatIterator
    while(!iter.isEnd){
      while(!iter.isEnd){
        val point=iter.getCurrent
        if(point.getDistance>0){
          point.setDistance(point.getDistance*point.getDistance)
          m.setPoint(point, iter.getCoordinates:_*)
        }
        iter.incVarAxis(0)
      }
      iter.setFirstAxis(0)
      iter.incInvarAxis(0)
    }

  }

private def AdditionalAxisGeneral(m:Matrix, norme:(Int,Int,Int)=>Int,intersect:(Int,Int,Int,Int)=>Int )=
{
  for (i <- 1 until m.nbDim)
  {
    val iter = new m.MatIterator
    var s =new Stack[Int]
    var t =new Stack[Int]
    var g =new Stack[Int]
    var label =new Stack[Int]
    while(!iter.isEnd())
    {
     /* var s =new Stack[Int]
    var t =new Stack[Int]
    var g =new Stack[Int]
    var label =new Stack[Int]*/
      //s=s.push(0)
      //t=t.push(0)
     // g=g.push(iter.getCurrent.getDistance)
      //label=label.push(iter.getLabel)

      while(!iter.isEnd())
      {
        if(iter.getCurrent.getDistance>=0)
        {
          while(!s.isEmpty && (norme(g.top,s.top,t.top) > norme(iter.getCurrent.getDistance,iter.getAxis(i),t.top)))
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
          // val w= 1+(s.top*s.top-iter.getAxis(i)*iter.getAxis(i)+g.top-iter.getCurrent.getDistance)/2*(s.top-iter.getAxis(i))
               val w=  intersect(iter.getCurrent.getDistance,g.top,iter.getAxis(i),s.top)
           if(w<m.axisRange(i)/*&& w>=0*/){
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
         if(!point.isBorder)
         {
         point.setDistance(norme(g.top, iter.getAxis(i),s.top))
         point.clearLabel
         point.addLabel(label.top)
         m.setPoint(point, iter.getCoordinates:_*)
         }

         if(t.top==iter.getAxis(i) /*&& iter.getAxis(i)!=0*/)
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

def AdditionalAxisEuc(m:Matrix)= {
                                  carre(m)
                                  AdditionalAxisGeneral(m:Matrix, f,intersectEuc)
                                }
def AdditionalAxisSup(m:Matrix)=AdditionalAxisGeneral(m:Matrix, v,intersectSup)
/*def AdditionalAxis(m:Matrix )=
{
  for (i <- 1 until m.nbDim)
  {
    val iter = new m.MatIterator
    var s =new Stack[Int]
    var t =new Stack[Int]
    var g =new Stack[Int]
    var label =new Stack[Int]
    while(!iter.isEnd())
    {
     /* var s =new Stack[Int]
    var t =new Stack[Int]
    var g =new Stack[Int]
    var label =new Stack[Int]*/
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
         if(!point.isBorder)
         {
         point.setDistance(g.top + (iter.getAxis(i)-s.top)*(iter.getAxis(i)-s.top))
         point.clearLabel
         point.addLabel(label.top)
         m.setPoint(point, iter.getCoordinates:_*)
         }
       
         if(t.top==iter.getAxis(i) /*&& iter.getAxis(i)!=0*/)
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

}*/
}