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

package de.failtnix.watcher;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import de.failtnix.tools.Localization;

public class WatcherManager implements Observer{

	private static WatcherManager instance = new WatcherManager();
	
	private HashMap<Integer, Thread> watcherList;
	
	private ResourceBundle loc = Localization.getInstance();
	
	private WatcherManager(){
	
		watcherList = new HashMap<Integer, Thread>();
		
	}

	public static WatcherManager getInstance(){
		
		return instance;
		
	}
	
	public void add(String filename){
		
		add(filename, 1000);
		
	}
	
	public void add(String filename, long interval){
		
		FileWatcher f = new FileWatcher(filename, interval);
		
		f.addObserver(this);
		
		Thread t = new Thread(f);
		
		watcherList.put(new Integer(f.hashCode()), t);
		
		t.start();
		
	}

	@Override
	public void update(Observable watcher, Object msg){
		
		System.out.println(Protocol.getFilename(msg) + ": " + Protocol.getEventMessage(msg));
		
		switch(Protocol.getEventCode(msg)){
		
			case Protocol.FILE_DOES_NOT_EXIST:
			
				watcherList.remove(new Integer(watcher.hashCode()));
			
				break;
		
			default:
				
				break;
		
		}
		
		
	}

}


