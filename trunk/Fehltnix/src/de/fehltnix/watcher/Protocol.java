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

package de.fehltnix.watcher;

import de.fehltnix.tools.Localization;

/**
 * Protocol for messages bewteen layers
 * @author johannes
 *
 */
public class Protocol {
	
	// Business variables 
	
	public static final int PROTOCOL_FILENAME = 0; 
	
	public static final int PROTOCOL_EVENT = 1; 
	
	public static final String SEPERATOR_SUBJECT = ";";
	
	
	// File events 
	
	public static final int FILE_DOES_NOT_EXIST = 0;
	
	public static final int FILE_CHANGED = 1;
	
	public static final int DIR_CHANGED_MOREFILES = 2;
	
	public static final int DIR_CHANGED_LESSFILES = 3;
	
	/**
	 * Method encodes the given parameter in a message
	 * 
	 * @param filename
	 * @param event
	 * @return
	 */
	public static String createMessage(String filename, int event){
		
		return filename + SEPERATOR_SUBJECT + event;
		
	}
	
	
	/**
	 * Method splits a message into its single parts
	 * 
	 * @param msg
	 * @return
	 */
	private static String[] parse(String msg){
		
		return msg.split(SEPERATOR_SUBJECT);
		
	}
	
	/**
	 * Method returns the code of the event from a message
	 * 
	 * @param msg
	 * @return
	 */
	public static int getEventCode(Object msg){
		
		return Integer.parseInt(parse((String)msg)[PROTOCOL_EVENT]);
		
	}
	
	/**
	 * Method returns the localized event string from a message 
	 * 
	 * @param msg
	 * @return
	 */
	public static String getEventMessage(Object msg){
		
		return Localization.getInstance().getString(parse((String)msg)[PROTOCOL_EVENT]);
		
	}
	
	/**
	 * Method returns the filename from a message
	 * 
	 * @param msg
	 * @return
	 */
	public static String getFilename(Object msg){
		
		return parse((String)msg)[PROTOCOL_FILENAME];
		
	}
	
}
