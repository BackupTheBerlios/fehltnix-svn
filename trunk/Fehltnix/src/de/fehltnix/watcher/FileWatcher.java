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


/**
 * FileWatcher Thread, which watches the files or directories
 * @author johannes
 *
 */
public class FileWatcher extends Observable implements Runnable{
	
	private String filename;
	
	private long interval;
	
	/**
	 * Variable stores when when file is modified the last time
	 */
	private long lastModified = -1;
	
	/**
	 * File counts from directories  
	 */
	private int directoryFileCountOld = -1;
	
	/**
	 * Constructor with a filename and an tie interval for watching
	 * @param filename
	 * @param interval
	 */
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
				
				if(f.isDirectory()){
					
					this.watchDirectory(f);
					
				}
				else if(f.isFile()){
					
					this.watchFileChangeDate(f);
					
				}

			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//return;
			
		}
		
		
	}
	
	/**
	 * Method counts files of directory and looks if there are more or less files in the given directory
	 * @param f
	 * @return
	 */
	public boolean watchDirectory(File f){
		
		int fileCount = f.listFiles().length;
		
		if(directoryFileCountOld != -1){
			
			if(fileCount > directoryFileCountOld){
				
				this.refreshDirectoryInfo(f);
				
				this.setChanged();
				this.notifyObservers(Protocol.createMessage(f.getAbsolutePath(), Protocol.DIR_CHANGED_MOREFILES));
					
			}
			else if(fileCount < directoryFileCountOld){
				
				this.refreshDirectoryInfo(f);
				
				this.setChanged();
				this.notifyObservers(Protocol.createMessage(f.getAbsolutePath(), Protocol.DIR_CHANGED_LESSFILES));
				
			}
			else{
				
				//nothing happened
				
			}
			
		}
		else {
			
			this.refreshDirectoryInfo(f);
			
		}
		
		return true;
	}
	
	/**
	 * Method store all necessary information about a directory
	 * @param f
	 */
	private void refreshDirectoryInfo(File f){
		
		directoryFileCountOld = f.listFiles().length;
		
	}
	
	/**
	 * Method watches a file date for changes
	 * @param f
	 * @return
	 */
	private boolean watchFileChangeDate(File f){
		
		if(lastModified == -1){
			
			lastModified = f.lastModified();
			
		}
		else if(f.lastModified() > lastModified)
		{
			
			lastModified = f.lastModified();
			
			this.setChanged();
			this.notifyObservers(Protocol.createMessage(filename, Protocol.FILE_CHANGED));
			
		}
		
		return true;
		
	}

}
