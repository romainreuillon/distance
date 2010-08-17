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

class Matrix(dimension: Int*) {

  //Attributes
  private val mat : Array[Point] = new Array(dimension.product)
  private val dim : Array[Int] = dimension.toArray.reverse

  //Private methods
  private def isOk (coor: Int*) : Boolean =  coor.length==dim.length && List.forall2 (coor.toList.reverse,dim.toList) (_<_)
  private def toIndex (coor: Int*): Int = (for ((arg,i) <- coor.reverse zip (0 until coor.length)) yield arg* dim.slice(0,i).product).sum

  //Methods

  def initBorder: Unit = for(i<- 0 until mat.length) mat(i) = new BorderPoint

  def initBody: Unit = for(i<- 0 until mat.length) mat(i) = new BodyPoint

  def apply(coor: Int*) = {
   // require(isOk(coor: _*))
    mat(toIndex(coor:_*))
  }

  def setPoint (point: Point, coor: Int*) = {
    //require(isOk(coor: _*))
    mat(toIndex(coor:_*)) = point
  }

  def nbDim = dim.length

  def axisRange(axis: Int) = dim(axis)

  def labelToCoordinates(label: Int) = (for(arg <-  0 to dim.length-1) yield ((label/dim.slice(0, arg).product)%dim(arg))).reverse


  class MatIterator () {

    //Attributs
    private var index :Int = 0
    private var end :Boolean = false
    
    //Private methods
    private def axisIsOk(axis: Int) = axis >= 0 && axis < dim.length

    //Methods
    def getAxis(axis: Int) = {
      //require(axisIsOk(axis))
      (index/dim.slice(0, axis).product)%dim(axis)
    }
     
    def setFirst = index = 0

    def setLast = index = mat.length-1

    def setFirstAxis(axis: Int) = {
      //require(axisIsOk(axis))
      index-= dim.slice(0, axis).product*getAxis(axis)
    }

    

    def setLastAxis (axis: Int) = {
      //require(axisIsOk(axis))
      //if(axis==0) {
        //index += dim(0) - getAxis(0) - 1
      //}
      /*else*/ index += dim.slice(0, axis).product*(dim(axis) -getAxis(axis) - 1)
    }

    def incVarAxis(axis: Int) = {
      //require(axisIsOk(axis))
      if(!isAxisLast(axis)) index+=dim.slice(0, axis).product
      else end = true
                               
    }

    def decVarAxis(axis: Int) = {
      //require(axisIsOk(axis))
      if(!isAxisFirst(axis)) index -= dim.slice(0, axis).product
      else end = true
    }

    //this method works only with the axis fixed at 0 (that's what we need for the algorithm)
    def incInvarAxis(axis: Int) = {
      index += 1
      if(getAxis(axis) != 0) {
        val newIndex = index + dim.slice(0,axis).product * (dim(axis) - 1)
        if(newIndex < mat.length) {
          index=newIndex
        }
        else {
          index -= 1
          end = true
        }
     }
   }
    
    def decInvarAxis(axis: Int) = {
      index -= 1
      if(getAxis(axis) != dim(axis) - 1) {
        val newIndex = index - dim.slice(0, axis).product * (dim(axis) - 1)
        if(newIndex > 0) {
          index = newIndex
        }
        else {
          index -= 1
          end = true
        }
     }
   }

    //def isLast(): Boolean= index== mat.length-1
    
    def isAxisLast(axis:Int) = {
      //require(axisIsOk(axis))
      getAxis(axis) == dim(axis) - 1
    }

    def isAxisFirst(axis:Int) = {
     // require(axisIsOk(axis))
      getAxis(axis) == 0
    }
    

    def isEnd(): Boolean = {
      if(end) {
       end=false
       true
      }
      else end
    }
    
    def getCurrent: Point = mat(index)

    def getCoordinates=(for(arg <-  0 to dim.length-1) yield getAxis(arg)).reverse

    def getLabel=index
    
    override def  toString= "(" + (getCoordinates mkString ",") + ")"

  }

}

