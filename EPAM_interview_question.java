import java.util.*;
import java.lang.*;

class EPAM
{
	int heap_size,max1,max2;
	EPAM(int i)
	{
		heap_size=i;
	}
	int[] build_heap(int[] stone)
	{
		int i;
		for(i=heap_size/2;i>=1;i--)
			stone=max_heapify(stone,i);
		return stone;
	}
	int[] max_heapify(int[] stone,int i)
	{
		int l,r,largest;
		int temp;
		l=2*i;
		r=2*i+1;
		if(l<=heap_size && stone[l]>stone[i])
			largest=l;
		else
			largest=i;
		if(r<=heap_size && stone[r]>stone[largest])
			largest=r;
		if(i!=largest)
		{
			temp=stone[i];
			stone[i]=stone[largest];
			stone[largest]=temp;
			
			stone=max_heapify(stone,largest);
		}
		return stone;
	}
	int[] delete_max(int[] stone,int flag)
	{
		if(flag==1)
		{
			max1=stone[1];
			stone[1]=stone[heap_size];
			heap_size--;
			stone=max_heapify(stone,1);
		}
		else
			max2=stone[1];
		return stone;
	}
	int[] insert(int[] stone,int r)
	{
		stone[1]=r;
		stone=max_heapify(stone,1);
		return stone;
	}
	public static void main(String args[])
	{
		Scanner in=new Scanner(System.in);
		int n,i,r;
		System.out.print("Enter no. of stones: ");
		n=in.nextInt();
		int[] stone=new int[n+1];
		for(i=1;i<=n;i++)
		{
			System.out.print("Enter weight of "+(i)+" stone: ");
			stone[i]=in.nextInt();
		}
		EPAM x=new EPAM(n);
		stone=x.build_heap(stone);
		while(x.heap_size>1)
		{
			stone=x.delete_max(stone,1);
			stone=x.delete_max(stone,2);
			r=x.max1-x.max2;
			if(r!=0)
				stone=x.insert(stone,r);
			else
				stone=x.delete_max(stone,1);
		}
		System.out.println("Weight of the stone left = "+stone[1]);
	}
}