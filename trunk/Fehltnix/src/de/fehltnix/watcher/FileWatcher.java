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

import java.io.File;
import java.util.Observable;

public class FileWatcher extends Observable implements Runnable{
	
	private String filename;
	
	private long interval;
	
	private long lastModified = -1;
	
	public FileWatcher(String filename){
		
		this.filename = filename;
		
	}
	
	public FileWatcher(String filename, long interval){
		
		this.filename = filename;
		
		this.interval = interval;
		
	}
	
	@Override
	public void run() {

		try
		{
			
			while(true)
			{
				
				Thread.sleep(interval);
				
				File f = new File(filename);
				
				if(!f.exists()){
					
					this.setChanged();
					this.notifyObservers(Protocol.createMessage(filename, Protocol.FILE_DOES_NOT_EXIST));
					
					return;
					
				}
				
				if(lastModified == -1){
					
					lastModified = f.lastModified();
					
				}
				else if(f.lastModified() > lastModified)
				{
					
					lastModified = f.lastModified();
					
					this.setChanged();
					this.notifyObservers(Protocol.createMessage(filename, Protocol.FILE_CHANGED));
					
				}
			
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//return;
			
		}
		
		
	}

}
