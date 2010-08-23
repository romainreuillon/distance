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

    // val arg =((for(arg <- Source.fromFile("/home/elbrini/dim").getLines) yield (arg.toInt)).toArray.reverse)
    //val m:Matrix=new Matrix (arg: _*)
      
    //m.initBorder
    //  println(m(0,0,0).getDistance)
    // for(arg<-Source.fromFile("/home/elbrini/coor").getLines) {
    //val coor=(for(co<-(arg.split(' '))) yield (co.toInt)).toArray
    //  m.setPoint(new BodyPoint, (for(co<-(arg.split('\t'))) yield (co.toInt)).toArray:_*)
    //                                                                                      }


    def fillSphere(m:Matrix, r: Double, o: Int*) {
      var i = 0

      //require(m.nbDim==o.length)
      val iter= new m.MatIterator
      var coor=iter.getCoordinates
      while(!iter.isEnd) {
        while(!iter.isEnd) {
         coor=iter.getCoordinates
          //val distance = sqrt((for ((x,y) <- (o zip coor)) yield ((x-y)*(x-y))).sum)
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
    }

    def fillCarre(m: Matrix) {
     // var i = 0

      //require(m.nbDim==o.length)
      val iter= new m.MatIterator
      val iter1=new m.MatIterator
      iter1.setLast
      //var coor=iter.getCoordinates
     // m(0,0,0,0,0,0) = new BorderPoint
     
      while(!iter.isEnd) {

   
        for(j <- 0 until m.axisRange(0)/2) {
         
          //coor=iter.getCoordinates
          m.setPoint(new BodyPoint, (iter.getCoordinates):_*)
         // println(iter.getCoordinates.toString)
          //println(iter1.index)
          //println(iter1.getAxis(0))
          // println(iter1.getAxis(1))
          // println(iter1.getAxis(2))
         //  println(iter1.getAxis(3))
          // println(iter1.getAxis(4))
          // println(iter1.getAxis(5))
          // println(iter1.getAxis(6))
          // println((for(arg <- m.dim.length-1 to (0,-1)) yield iter1.getAxis(arg)).toString)


          m.setPoint(new BorderPoint, (iter1.getCoordinates):_*)
          
          //println(iter1.getCoordinates.toString)
          // println("Done")
          iter.incVarAxis(0)
          iter1.decVarAxis(0)
         // i += 1
        }
       /* while(!iter.isEnd) {
          //coor=iter.getCoordinates
          
          iter.incVarAxis(0)
         }*/
        iter.setFirstAxis(0)
        iter1.setLastAxis(0)
        iter1.decInvarAxis(0)
        iter.incInvarAxis(0)
      }
     // println(i)
    }
      
    //FirstAxis.passage1(m)
    // println(m(6,5,6).getDistance)
    //FirstAxis.passage2(m)
    // println(m(6,5,6).getDistance)
    //val m = new Matrix(10,10,10,10,10,10,10,3)
    val m = new Matrix(10,10,10,10,10,10,30)
    /* val o  = List(4,3,4,5,6,4,2,2).toSeq
     val iter= new m.MatIterator
     while(!iter.isEnd) {
     while(!iter.isEnd) {
     val coor=iter.getCoordinates
     val distance = sqrt((for ((x,y) <- (o zip coor)) yield ((x-y)*(x-y))).sum)
     if (distance<3.4) m.setPoint(new BodyPoint, coor:_*)
     else m.setPoint(new BorderPoint, coor:_*)
     iter.incVarAxis(0)
     }
     iter.setFirstAxis(0)
     iter.incInvarAxis(0)
     }*/


    
    //val m :Matrix= new Matrix(10,10,10,10,10,10)
    //println(m.dim.mkString(","))
   // println(m.weights.mkString(","))

    println("Generating the sphere...")
   //val start : Long = System.currentTimeMillis
    fillCarre(m)
    //val start : Long = System.currentTimeMillis
    println("Square generated.")
     val start : Long = System.currentTimeMillis
    val norm = new NormEuc


    println("Computing the distance map...")
    val compute= new AllAxis(norm)
    compute.computeDistance(m)
    val end : Long = System.currentTimeMillis
    println("Computed in: " + (end - start)/1000)
    //compute.showResults(m)
    //var point: Point = new BodyPoint()

    /*println("point appartient à la frontière??: "+point.isBorder.toString)
     point.setDistance(15)
     println(point.getDistance)
     point.addLabel(5)
     for (i<-1 to 5) point.addLabel(i)
     point.listLabel()
     point.dropLabel(3)
     point.listLabel()*/

    /*   val m: Matrix = new Matrix(3,3,7)
     // println( "Voila::::"+m.isOk(1,0,2).toString)
     m.init
     //println(m.toInt1(2,1,1))
     /* println(m(2,1,2).getDistance)
      println("the distance of the first element is:" +m(0,1,2).getDistance)
      point= new BodyPoint()*/
     m.setPoint(point,1,1,0)
     m.setPoint(new BodyPoint,1,1,2)
     m.setPoint(new BodyPoint,1,1,3)
     m.setPoint(new BodyPoint,1,1,4)

     m.setPoint(new BodyPoint,0,0,3)
     m.setPoint(new BodyPoint,0,0,4)
     m.setPoint(new BodyPoint,0,0,6)

     m.setPoint(new BodyPoint,1,2,3)
     m.setPoint(new BodyPoint,1,2,4)

     m.setPoint(new BodyPoint,0,1,1)
     m.setPoint(new BodyPoint,0,1,2)
     m.setPoint(new BodyPoint,0,1,3)

     m.setPoint(new BodyPoint,2,1,0)
     m.setPoint(new BodyPoint,2,1,1)
     m.setPoint(new BodyPoint,2,1,2)

     m.setPoint(new BodyPoint,2,2,0)
     m.setPoint(new BodyPoint,2,2,1)
     m.setPoint(new BodyPoint,2,2,2)
     m.setPoint(new BodyPoint,2,2,3)
     m.setPoint(new BodyPoint,2,2,4)
     m.setPoint(new BodyPoint,2,2,5)
     m.setPoint(new BodyPoint,2,2,6)
     FirstAxis.passage1(m)
     FirstAxis.passage2(m)
     println("point du corps (2 2 3) est de distance "+m(2,2,3).getDistance)
     AdditionalAxis.AdditionalAxisSup(m)
     println("point du corps (1 1 1) est de distance "+m(1,1,1).getDistance)
     println("point du corps (1 1 2) est de distance "+m(1,1,2).getDistance)
     println("point du corps (1 1 3) est de distance "+m(1,1,3).getDistance)
     println("point du corps (0 0 3) est de distance "+m(0,0,3).getDistance)
     println("point du corps (0 0 4) est de distance "+m(0,0,4).getDistance)
     println("point du corps (0 0 5) est de distance "+m(0,0,6).getDistance)

     println("point du corps (2 2 0) est de distance "+m(2,2,0).getDistance)
     println("point du corps (2 2 1) est de distance "+m(2,2,1).getDistance)
     println("point du corps (2 2 2) est de distance "+m(2,2,2).getDistance)
     println("point du corps (2 2 3) est de distance "+m(2,2,3).getDistance)
     println("point du corps (2 2 4) est de distance "+m(2,2,4).getDistance)
     println("point du corps (2 2 5) est de distance "+m(2,2,5).getDistance)
     println("point du corps (2 2 6) est de distance "+m(2,2,6).getDistance)*/
    /*val mat: Matrix= new Matrix(3,7)
     mat.init

     mat.setPoint(new BodyPoint,0,1)
     mat.setPoint(new BodyPoint,0,6)
     mat.setPoint(new BodyPoint,1,1)
     mat.setPoint(new BodyPoint,1,2)
     mat.setPoint(new BodyPoint,1,4)
     mat.setPoint(new BodyPoint,1,5)
     mat.setPoint(new BodyPoint,1,6)
     mat.setPoint(new BodyPoint,2,5)
     mat.setPoint(new BodyPoint,2,6)
     FirstAxis.passage1(mat)
     FirstAxis.passage2(mat)
     println("point du corps (1 6) est de distance "+mat(1,6).getDistance)
     AdditionalAxis.AdditionalAxisSup(mat)
     println("point du corps (1 6) est de distance "+mat(1,6).getDistance)*/

    /* var pile=new Stack[Int]
     pile=pile.push(1)
     pile=pile.push(2)
     pile=pile.push(3)
     pile=pile.push(4)
     println("tete: "+pile(0))
     println("tete: "+pile(1))
     println("tete: "+pile(2))*/


    /*val iter = new m.MatIterator()
    
     iter.setFirstAxis(2)
     println(iter.toString)
     iter.setLastAxis(2)
     println(iter.toString)
     iter.setFirst
     println(iter.toString)
     //println(iter.index)
     iter.incVarAxis(0)
     //println(iter.index)
     println(iter.toString)
     iter.incVarAxis(1)
     println(iter.toString)
     iter.incVarAxis(2)
     println(iter.toString)
     iter.setFirstAxis(0)*/

    //iter.setFirst
    /* while(!iter.isEnd()){
                          
     while(!iter.isEnd()) {
     println(":"+iter.toString)
     iter.incVarAxis(0)
     }
     iter.setFirstAxis(0)
     iter.incInvarAxis(0)
     }
     iter.setLast
     while(!iter.isEnd()){

     while(!iter.isEnd()) {
     println("::"+iter.toString)
     iter.decVarAxis(0)
     }
     iter.setLastAxis(0)
     iter.decInvarAxis(0)
     }*/
    //  println(iter.end.toString)
    /*do{
    
     iter.incVarAxis(2)
     println(iter.toString)

     }while(!iter.isAxisLast(2))*/
    /*  iter.setFirstAxis(1)
     while(true) {
     iter.incInvarAxis(1)
     println(iter.toString )
     }*/
    
    /*  while (iter.isAxisInvarInc(2))
     {
     point=iter.getCurrent
     if(!point.isBorder) point.setDistance(5)
     iter.incInvarAxis(2)
     }
     iter.setFirst
     iter.setFirstAxis(2)
     while (iter.isAxisInvarInc(2))
     {
     point=iter.getCurrent
     if(!point.isBorder) println(point.getDistance)
     iter.incInvarAxis(2)
     }*/

  }
   
  //println("Le premier axe est de valeur: "+iter.getAxis(0))
  //println("Le deuxième axe est de valeur: "+iter.getAxis(1))
  //println("Le troisième axe est de valeur: "+iter.getAxis(2))

    



}
