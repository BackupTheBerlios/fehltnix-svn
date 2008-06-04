/*This file is part of Fehltnix.
    
  Fehltnix is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.
  
   Fehltnix is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.
   
   You should have received a copy of the GNU General Public License
   along with Fehltnix.  If not, see <http://www.gnu.org/licenses/>.
*/

package de.fehltnix.ui;

import java.util.Date;

/**
 * Class to manage console output
 * 
 * @author johannes
 *
 */
public class ConsolOut {

	/**
	 * Prints a string out by System.out.println and adds the current date
	 * @param msg
	 */
	public static void printOutWithDate(String msg){
		
		System.out .println(new Date()+" "+msg);
	}
	
	
}
