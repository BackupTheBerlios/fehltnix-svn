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

import de.fehltnix.tools.Localization;
import de.fehltnix.watcher.WatcherManager;

/**
 * Main class
 *
 */
public class Fehltnix{

	/**
	 * Version number of release
	 */
	public final static String VERSION = "1.0";
	
	/**
	 * Main method expects file names as parameter 
	 * @param args
	 */
	public static void main(String[] args) {

		new Fehltnix(args);

	}
	
	public Fehltnix(String[] files){
		
		ConsolOut.printOutWithDate("Fehltnix v"+ VERSION+" "+Localization.getInstance().getString(Localization.STARTED_KEY));
		
		for(int i = 0; i < files.length; i++){
			
			WatcherManager.getInstance().add(files[i]);
			
		}
					
	}

}
