/*==============================================================================================================================================================
 *   Given a composite systems comprised of randomly distributed insulating and metallic materials: what fraction of the materials
 *  need to be metallic so that the composite system is an electrical conductor? Given a porous landscape with water on the surface
 *   (or oil below), under what conditions will the water be able to drain through to the bottom (or the oil to gush through to the surface)? 
 *   Scientists have defined an abstract process known as percolation to model such situations.
 *   ============================================================================================================================================================*/


public class Percolation
{
				   public boolean[][] grid;																			// grid of n by n size
				   static int sizeOfGrid = 5;																		// size of grid
				   static int count = 0;																			// count means number of blocks in grid ( n * n)
				   static private FindConnectivity connect ;															
				   static boolean isPercolated;																		// checks if grid percolates 
				   static boolean isFirstRowOpen;																	// check if first row is open
				   static boolean isLastRowOpen;																	// check if last row is open
				 
				   
				   public Percolation(int n)              															  // create n-by-n grid, with all sites blocked
				   {
					   		connect = new FindConnectivity(n * n);
					   		isPercolated = false;
					   		isFirstRowOpen = false;
					   		isLastRowOpen = false;
					   		
					   		grid =  new boolean[n][n];
					   		for (int i = 0; i < grid.length; i++)
					   		{
					   			for (int j = 0; j < grid[i].length; j++)
					   			{
					   					grid[i][j] = false;															//boolean value of all blocks of grid is initialized as false
								}
							}
					   		
				   }
				   
				   public static void main(String[] args)
				   {
						      Percolation percolation = new Percolation(sizeOfGrid);
						      while( percolation.numberOfOpenSites() < sizeOfGrid * sizeOfGrid)
						      {
							    	  int row = (int)( Math.random() * sizeOfGrid ) + 1;										// random value of row
							   		  int col = (int) ( Math.random() * sizeOfGrid ) + 1;										// random value of column
							   		  
							   		  if ( row == 1 ) 
							   		  {
							   			  			isFirstRowOpen = true;
							   		  }
							   		 if ( row == sizeOfGrid ) 
							   		  {
							   			  			isLastRowOpen = true;
							   		  }
							   		  percolation.open(row, col);
							   		  
							   		  if (percolation.percolates() == true)
							   		  {
							   			  		break;
							   		  }
						      }
						      
				   }
				   
				   public void open(int row, int col)   															 // open site (row, col) if it is not open already
				   {
					   		row = row - 1 ;
					   		col = col -1;
					   		if(isOpen(row , col ))
					   		{
					   				return;
					   		}
					   		else
					   		{
								   		grid[ row ][ col ] = true;
								   		count++;
										 		
										if (  ( row > 0 ) && (isOpen(row - 1, col)) )
										{
												connect.Union(GetIndex(row, col), GetIndex(row - 1, col));
												
											
										}  
										if (( row < sizeOfGrid - 1) && isOpen(row + 1, col))
										{
												connect.Union(GetIndex(row, col), GetIndex(row + 1, col));
												
											
										}
										 
						   				if (( col > 0) && isOpen(row, col - 1))
						   				{
												connect.Union(GetIndex(row, col), GetIndex(row, col - 1));
												
												
										} 
						   				if (( col < sizeOfGrid - 1) && isOpen(row, col + 1)) 
						   				{
												connect.Union(GetIndex(row, col), GetIndex(row, col + 1));
												
						   				}
								
							}
					
					   		if ( (isFirstRowOpen == true) &&  (isLastRowOpen == true) && (count > sizeOfGrid) )
					   		{
										if (CheckIfPercolated(row , col) == true)
										{
											System.out.println(" Percolates");
											return;
										}
							}
				   }
				   
				   private boolean CheckIfPercolated( int row , int col )															// this method checks if first row and last row is connected or not
				   {
					   	if (row != 0)
					   	{
						   		for (int i = GetIndex(0, 0) ; i <= GetIndex(0, sizeOfGrid-1); i++) 									// Conversion of 2D array in 1D array( first row)
						   		{
								   			if ( connect.IsConnected( GetIndex(row , col), i ) ==  true ) 							////is grid[row][col] is connected to first row
									   		{
								   				for (int j = GetIndex(sizeOfGrid-1, 0) ; j <= GetIndex(sizeOfGrid-1, sizeOfGrid-1); j++) // Conversion of 2D array in 1D array( last row)
										   		{
										   			if ( connect.IsConnected( i, j ) ==  true ) 											// is first row is connected to last row
											   		{
										   						isPercolated = true;
										   						return true;
											   		}
												}
									   		}
						   		}
						}
					   		return false;
				   }
				   
																																// this method converts 2d array into 1d array
				   public int GetIndex(int row, int col) 
				   {		
					   			int index = grid.length * row + col ;
					   			return index;
				   }
				   
				   
				   public boolean isOpen(int row, int col)																		  // is site (row, col) open?
				   {
					   		if (grid[ row  ][ col ] == true) 
					   		{
					   			   return true;
							}
							return false;
				   }
				   
				   
				   public boolean isFull(int row, int col) 																		 // is site (row, col) full?
				   {
					   			if (numberOfOpenSites() == sizeOfGrid * sizeOfGrid) 
					   			{
					   					return true;
								}
					   			return false;
					   
				   }
				   
				   
				   public int numberOfOpenSites()       																		// number of open sites
				   {
					   		return count;
				   }
				   
				   
				   public boolean percolates()            																		  // does the system percolate?
				   {
					   		return isPercolated;
				   }
			
}
