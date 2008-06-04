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

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import de.fehltnix.tools.Localization;
import de.fehltnix.ui.ConsolOut;

/**
 * Class manages the watching of the files.
 * 
 * This class is a singleton.
 * 
 * @author johannes
 *
 */
public class WatcherManager implements Observer{

	private static int DEFAULT_TIME_INTERVAL = 1000;
	
	/**
	 * Instance of WatcherManager
	 */
	private static WatcherManager instance = new WatcherManager();
	
	/**
	 * Map with all current running watcher threads
	 */
	private HashMap<Integer, Thread> watcherList;
	
	/**
	 * Private constructor (Singleton) 
	 */
	private WatcherManager(){
	
		watcherList = new HashMap<Integer, Thread>();
		
	}

	/**
	 * Returns instance of WatcherManager
	 * @return
	 */
	public static WatcherManager getInstance(){
		
		return instance;
		
	}
	
	/**
	 * Methods adds a file for watching.
	 * 
	 * Time interval is DEFAULT_TIME_INTERVAL
	 * 
	 * @param filename
	 */
	public void add(String filename){
		
		add(filename, DEFAULT_TIME_INTERVAL);
		
	}
	
	/**
	 * Method adds a file for watching in a specified time interval for watching.
	 * 
	 * @param filename
	 * @param interval
	 */
	public void add(String filename, long interval){
		
		FileWatcher f = new FileWatcher(filename, interval);
		
		f.addObserver(this);
		
		Thread t = new Thread(f);
		
		watcherList.put(new Integer(f.hashCode()), t);
		
		t.start();
		
		ConsolOut.printOutWithDate(Localization.getInstance().getString(Localization.WATCHING_KEY) +" "+ filename);
		
	}

	@Override
	public void update(Observable watcher, Object msg){
		
		ConsolOut.printOutWithDate(Protocol.getFilename(msg) + " " + Protocol.getEventMessage(msg));
		
		switch(Protocol.getEventCode(msg)){
		
			case Protocol.FILE_DOES_NOT_EXIST:
			
				watcherList.remove(new Integer(watcher.hashCode()));
				
				ConsolOut.printOutWithDate(Protocol.getFilename(msg) + " " + Localization.getInstance().getString(Localization.STOPPEDWATCHING_KEY));
				
				break;
		
			default:
				
				break;
		
		}
		
		
	}

}


