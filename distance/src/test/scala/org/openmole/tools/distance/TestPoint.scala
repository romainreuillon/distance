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
import scala.io.Source
import scala.math.sqrt


import org.junit._
import Assert._

class TestPoint {

  @Before
  def setUp: Unit = {
  }

  @After
  def tearDown: Unit = {
  }

  @Test
  def example = {

    
    // val arg =((for(arg <- Source.fromFile("/home/elbrini/dim").getLines) yield (arg.toInt)).toArray.reverse
    // 
    val lower: Array[Int] = Array(0,0,0)
    val upper = Array(10,10,10)
    val steps = Array(1,1,1)
    val m = new Matrix(lower,upper,steps)
    //val m:Matrix=new Matrix (arg: _*)
    m.initBorder
    println(m(0,0,0).getDistance)
    for(arg<-Source.fromFile("/home/elbrini/coor").getLines) {	
    //val coor=(for(co<-(arg.split(' '))) yield (co.toInt)).toArray
    m.setPoint(new BodyPoint, (for(co<-(arg.split('\t'))) yield (co.toInt)).toArray:_*)	
                                                                                  }
   val norm = new NormEuc
   val compute= new AllAxis(norm)
   compute.computeDistance(m)
   compute.showResults(m)

   /* def fillSphere(m:Matrix, r: Double, o: Int*) {
      var i = 0

      //require(m.nbDim==o.length)
      val iter= new m.MatIterator
      var coor=iter.getCoordinates
      while(!iter.isEnd) {
        while(!iter.isEnd) {
         coor=iter.getCoordinates
          
          if ((for ((x,y) <- (o zip coor)) yield ((x-y)*(x-y))).sum < r*r) m.setPoint(new BodyPoint, coor:_*)
          else {
            m.setPoint(new BorderPoint, coor:_*)
            i += 1
          }
          iter.incVarAxis(0)
        }
        iter.setFirstAxis(0)
        iter.incInvarAxis(0)
      }
      println(i)
    }*/
// Attention this function works only with matrix having an even number of values in the first axis
    /*def fillCarre(m: Matrix) {
 
      val iter= new m.MatIterator
      val iter1=new m.MatIterator
      iter1.setLast
    
      while(!iter.isEnd) {
        for(j <- 0 until m.axisRange(0)/2) {
          m.setPoint(new BodyPoint, (iter.getCoordinates):_*)
          m.setPoint(new BorderPoint, (iter1.getCoordinates):_*)  
          iter.incVarAxis(0)
          iter1.decVarAxis(0)
        }
        iter.setFirstAxis(0)
        iter1.setLastAxis(0)
        iter1.decInvarAxis(0)
        iter.incInvarAxis(0)
      }
   
    }*/
      
    /*val lower: Array[Int] = Array(0,0,0)
    val upper = Array(10,10,10)
    val steps = Array(1,1,1)*/
    
   /* val m = new Matrix(lower,upper,steps)
    println("Generating the sphere...")
   val start : Long = System.currentTimeMillis
    fillCarre(m)
    val start1 : Long = System.currentTimeMillis
    println("Square generated in: " + (start1 - start)/1000 + " secondes")
    val norm = new NormEuc
    println("Computing the distance map...")
    val compute= new AllAxis(norm)
    compute.computeDistance(m)
    val end : Long = System.currentTimeMillis
    println("Computed in: " + (end - start1)/1000 + " secondes")*/
    //compute.showResults(m)
    

  }
   
 
    



}
