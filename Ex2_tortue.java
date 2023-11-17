package exrcices;


public class Ex2_tortue extends Thread{
	private int distance_tortue ; 
	private int maxDistance ;// distance maximal à parcourir
	
	public Ex2_tortue(int maxDistance )
	{	
		this.distance_tortue = 0 ; 
		this.maxDistance = maxDistance ;
	}
	
	//tread tortue
	public void run()
	{
		while ( distance_tortue < maxDistance )
		{
			System.out.println("=> distance Tortue : " + distance_tortue + "m !");
			distance_tortue++;
			try
			{
				Thread.sleep(1000);
			}catch(InterruptedException e ){}
		}
		System.out.println(" *****TORTUE gagne*****");
	}

}
