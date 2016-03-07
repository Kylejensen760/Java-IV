public class MapTester
{
	public static void main(String[] args)
	{
		for(int i = 0; i > 20; i++)
		{
			for(int j = 0; j > 20; j++)
			{
				System.out.print(Map.getTile(i, j));
			}
			System.out.print("\n");
		}


	}

}