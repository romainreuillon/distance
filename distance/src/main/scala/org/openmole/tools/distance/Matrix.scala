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

import scala.math.{min,max}

/*object Matrix {
   
  
  def MatrixBody(bodyPoints: Iterable[Array[Int]]): Matrix = {
    val ret = new Matrix(computeDimensions(bodyPoints))
    
    for(p <- bodyPoints) {
      ret(p) = new BodyPoint
    }
    
    for(i <- 0 until ret.mat.length) {
      if(ret.mat(i) == null) ret.mat(i) = new BorderPoint
    }
    
    ret 
  }
  
  private def computeDimensions(points: Iterable[Array[Int]]): Array[Int] = {
    val dim = points.map(_.size).min
    
    val dimensions = new Array[Int](dim)
    
    for(val i <- 0 until dim) {
      val minmax = points.map( elt => (elt(i), elt(i))).reduceLeft((x,y) => (min(x._1,y._1), max(x._2,y._2)))
      dimensions(i) = minmax._2 - minmax._1
    }
    
    dimensions
  }
  
}*/


class Matrix(lowerBorns: Array[Int], upperBorns: Array[Int], steps: Array[Int]) {
  
  //Attributes
  private val dim : Array[Int] = (for(i <- 0 until steps.length) yield (upperBorns(i) - lowerBorns(i) )/steps(i) + 1).toArray
  private val mat : Array[Point] = new Array(dim.product)
  private val weights: Array[Int] = {
    var cumul = 1
    val tmp: Array[Int] = dim.clone
    for(i <- 0 until dim.length) {
      tmp(i) = cumul
      cumul = cumul * dim(i)
    }
    tmp
  }

  //private def isOk (coor: Int*) : Boolean =  coor.length==dim.length && (coor.toList.reverse, dim.toList).zipped.forall (_<_)

  private def toIndex (coor: Int*): Int = {
    val l = coor.length-1
    var sum = 0
    for ( i <- 0 to l) sum += coor(i) * weights(l - i)
    sum
  }

  //Methods
  def initBorder: Unit = for(i <- 0 until mat.length) mat(i) = new BorderPoint

  def initBody: Unit = for(i <- 0 until mat.length) mat(i) = new BodyPoint

  def apply(coor: Int*) = {
    mat(toIndex(coor:_*))
  }
  
  def update(coord: Array[Int], point: Point) = {
      mat(toIndex(coord:_*)) = point
  }
  
  def setPoint (point: Point, coor: Int*) = {
    mat(toIndex(coor:_*)) = point
  }

  def nbDim = dim.length

  def axisRange(axis: Int) = dim(axis)
  
  def stepAtAxis(axis: Int) = steps(axis)

  def labelToCoordinates(label: Int) = (for(arg <- dim.length-1 to (0,-1)) yield ((label / weights(arg)) % dim(arg))* steps(arg) + lowerBorns(arg))

  // An Inner class in order to explore the matrix created
  class MatIterator {

    //Attributs
    private var index: Int = 0
    private var end: Boolean = false
    
    //Private methods
    private def axisIsOk(axis: Int) = axis >= 0 && axis < dim.length

    //Methods
    def getAxis(axis: Int) = {
      (index / weights(axis)) % dim(axis)
    }    
     
    def setFirst = index = 0
    def setLast = index = mat.length-1

    def setFirstAxis(axis: Int) = {
      index -= weights(axis)* getAxis(axis)
    }

    def setLastAxis (axis: Int) = {
      index += weights(axis)*(dim(axis) -getAxis(axis) - 1)
    }

    def incVarAxis(axis: Int) = {
      if(!isAxisLast(axis)) index += weights(axis)
      else end = true                         
    }

    def decVarAxis(axis: Int) = {
      if(!isAxisFirst(axis)) index -= weights(axis)
      else end = true
    }

    //this method works only with the axis fixed at 0 (that's what we need for the algorithm)
    def incInvarAxis(axis: Int) = {
      index += 1
      if(getAxis(axis) != 0) {
        val newIndex = index + weights(axis) * (dim(axis) - 1)
        if(newIndex < mat.length) {
          index=newIndex
        }
        else {
          index -= 1
          end = true
        }
      }
    }

    //this method works only with the axis fixed to its max value (that's what we need for the algorithm)
    def decInvarAxis(axis: Int) = {
      index -= 1
      val lastIndex = dim(axis)-1
      if(getAxis(axis) != lastIndex) {
        val newIndex = index - weights(axis) * (lastIndex)
        if(newIndex > 0) {
          index = newIndex
        }
        else {
          index -= 1
          end = true
        }
      }
    }
   
    def isAxisLast(axis:Int) = {
      getAxis(axis) == dim(axis) - 1
    }

    def isAxisFirst(axis:Int) = {
      getAxis(axis) == 0
    }
    
    def isEnd(): Boolean = {
      if(end) {
        end = false
        true
      }
      else end
    }
    
    def getCurrent: Point = mat(index)
    def getCoordinates = for(arg <- dim.length-1 to (0,-1)) yield getAxis(arg)
    def getLabel = index   
  }

}

