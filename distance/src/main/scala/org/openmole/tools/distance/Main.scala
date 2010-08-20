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

/*package org.openmole.tools.distance
//import scala.io.Source


object Main {

  /**
   * @param args the command line arguments
   */
  def main(args: Array[String]): Unit = {
  /*  println("Welcome to the distance computing program..")
    println("Enter the name of the dimensions file!")
  
    var file = readLine
    
    val arg =((for(arg <- Source.fromFile(file).getLines) yield (arg.toInt)).toArray.reverse)
    val m:Matrix=new Matrix (arg: _*)

    println("press 1 for a border points file 2 for a body points file!")
    var choice=readLine.toInt
    
    println("Enter name of the file containing coordinates!")
    file=readLine
    choice match {
    case 1 => m.initBody
    for(arg<-Source.fromFile(file).getLines) m.setPoint(new BorderPoint, (for(co<-(arg.split(' '))) yield (co.toInt)).toArray:_*)
    
    
    case 2 => m.initBorder
    for(arg<-Source.fromFile(file).getLines) m.setPoint(new BodyPoint, (for(co<-(arg.split(' '))) yield (co.toInt)).toArray:_*)

     case _ => println("Choix incorrect!")
    }
    println("To compute distance with the euclidiean norm press 1 for the Sup norm press 2!")
    choice=readLine.toInt
   // FirstAxis.passage1(m)
   // FirstAxis.passage2(m)
    choice match {
    //case 1=> AdditionalAxis.AdditionalAxis(m,new Norm with NormEuc)
    //case 2=> AdditionalAxis.AdditionalAxis(m,new Norm with NormSup)
    case _ => println("Choix incorrect!")
    }*/

        def fillCarre(m: Matrix) {
     // var i = 0

      //require(m.nbDim==o.length)
      val iter= new m.MatIterator
      //val iter1= new m.MatIterator
      var coor=iter.getCoordinates
      println(coor.toString)
      //iter1.setLast
      //iter.setFirst
      while(!iter.isEnd) {

        for(j <- 0 until m.axisRange(0)/2) {
          println(coor.toString)
          coor=iter.getCoordinates
          println(coor.toString)
          m.setPoint(new BodyPoint, coor:_*)
          iter.incVarAxis(0)
         // coor=iter1.getCoordinates
          println(coor.toString)
          //m.setPoint(new BorderPoint, coor:_*)
          //iter1.decVarAxis(0)
        }
        while(!iter.isEnd) {
          coor=iter.getCoordinates
          m.setPoint(new BorderPoint, coor:_*)
          iter.incVarAxis(0)
         }
       // iter1.setLastAxis(0)
       // iter1.decInvarAxis(0)
        iter.setFirstAxis(0)
        iter.incInvarAxis(0)
      }

    }
    val start : Long = System.currentTimeMillis
    val m :Matrix= new Matrix(10,10,10,10,10,10)
    println(m.weights.mkString(","))
    println("Generating the sphere...")
    fillCarre(m)
    println("Square generated.")
    val norm = new NormEuc


    println("Computing the distance map...")
    val compute= new AllAxis(norm)
    compute.computeDistance(m)
    val end : Long = System.currentTimeMillis
    println("Computed in: " + (end - start)/1000)
  }*/

/*}*/
