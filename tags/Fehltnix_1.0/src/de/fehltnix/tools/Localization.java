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

package de.fehltnix.tools;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class is the localization tool. Type Localization.getInstance().getString(key)
 * to get a localized string from the property file in your code.
 * The Locale of the os is the default locale. The bundle name is "messages".
 *
 */
public class Localization {

	/**
	 * instance of Localization
	 */
	private static Localization instance = new Localization();
	
	/**
	 * instance of the current locale (default de_DE)
	 */
	private static Locale loc = new Locale("de,DE");

	/**
	 * instance of the bundle
	 */
	private static ResourceBundle myBundle;
	
	/**
	 * private constructor, which initializes the default bundle
	 */
	private Localization(){
		myBundle = ResourceBundle.getBundle("messages");
	}
	
	/**
	 * get an the instance of the current localization bundle
	 * @return
	 */
	public static ResourceBundle getInstance(){
		return myBundle;
	}
	
	/**
	 * set a new locale
	 * @param loc
	 */
	public static void setLocale(Locale loc){
		myBundle = ResourceBundle.getBundle("messages", loc);
	}
	
	//Keys for localization
	
	public final static String STOPPEDWATCHING_KEY = "StoppedWatching";
	public final static String STARTED_KEY = "Started";
	public final static String VERSION_KEY = "Version";
	public final static String WATCHING_KEY = "Watching";

}
