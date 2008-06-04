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

public class Protocol {
	
	public static final int PROTOCOL_FILENAME = 0; 
	
	public static final int PROTOCOL_EVENT = 1; 
	
	public static final String SEPERATOR_SUBJECT = ";";
	
	public static final int FILE_DOES_NOT_EXIST = 0;
	
	public static final int FILE_CHANGED = 1;
	
	public static String createMessage(String filename, int event){
		
		return filename + SEPERATOR_SUBJECT + event;
		
	}
	
	public static String[] parse(String msg){
		
		return msg.split(SEPERATOR_SUBJECT);
		
	}
	
	public static int getEventCode(Object msg){
		
		return Integer.parseInt(parse((String)msg)[PROTOCOL_EVENT]);
		
	}
	
	public static String getEventMessage(Object msg){
		
		return Localization.getInstance().getString(parse((String)msg)[PROTOCOL_EVENT]);
		
	}
	
	public static String getFilename(Object msg){
		
		return parse((String)msg)[PROTOCOL_FILENAME];
		
	}
	
}
