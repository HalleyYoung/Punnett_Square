//punnett.java
//program for creating punnett squares
import java.util.Scanner;
import java.lang.Math;
import java.util.Arrays;

class PunnettSquare
{
	public static void main (String args[])
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter first parent's genotype, delimiting alleles by / and genes with a space (example: A/a b/b c/C) :");				
		String[] g1 = sc.nextLine().split(" "); 
		String[][] alleles1 = new String[g1.length][2]; //create a 2-dimensional list to hold each of the parent's two alleles for each gene
		for (int i = 0; i < g1.length; i++)
		{
			alleles1[i] = g1[i].split("/"); //alleles1[i] is a list containing two alleles of the same gene
		}
		String[] combos1 = combine(alleles1); //get all possible combinations of genes the parent can transmit
		
		//and do the same thing for the second genotype...
		System.out.println("Enter second parent's genotype, delimiting alleles by / and genes by \" \":");
		
		String[] g2 = sc.nextLine().split(" ");		
		String[][] alleles2 = new String[g2.length][2];
		for (int i = 0; i < g2.length; i++)
		{
			alleles2[i] = g2[i].split("/");
		}
		String[] combos2 = combine(alleles2); //all combos of alleles
		
		String[][] punnett = getpunnett(combos1, combos2);
		printpunnett(punnett, combos1, combos2);
	}
	
	//takes two list of possible genotypes the parents can transmit, returns a 2-d list of all possibilities a child can inherit
	public static String[][] getpunnett(String[] p1, String[] p2)
	{
		String[][] punnett = new String[p1.length][p2.length];
		for (int i = 0; i < p1.length; i++)
		{
			for (int j = 0; j < p2.length; j++)
			{
				String genotype = "";
				for (int k = 0; k < p1[i].length(); k++) //construct the new genotype
				{
					genotype += p1[i].charAt(k);
					genotype += p2[j].charAt(k);
				}
				punnett[i][j] = genotype;
			}
		}
		return punnett;
	}
	
	//takes a [n][2] array of n genes and each of the 2 alleles the parent possesses, and returns a list of all combinations of alleles the parent could give their child
	public static String[] combine (String[][] alleles)
	{
		if (alleles.length == 1) //simplest case
		{
			String[] combos = {alleles[0][0], alleles[0][1]};
			return combos;
		}
		else 
		{
			String[] combos = new String[(int) Math.pow(2, alleles.length)]; //number of combos
			String[] other_traits = combine(Arrays.copyOfRange(alleles, 1, alleles.length)); //recursive step - only combine n-1 alleles
			for (int i = 0; i < combos.length; i++)
			{
				combos[i] = other_traits[i%other_traits.length] + alleles[0][i*2/combos.length];
			}
			return combos;
		}
	}
	
	//print a readout of the punnett square
	public static void printpunnett(String[][] punnett, String[] combos1, String[] combos2)
	{
		System.out.println("Punnett Square:\n" + repeatChar('-', 30));
		String firstParent = repeatChar(' ', punnett[0][0].length()/2 + 1) + "| ";
		for (int i = 0; i < combos1.length; i++) //print first parent's alleles
		{
			firstParent += combos1[i] + repeatChar(' ', punnett[0][0].length()/2 + 1) + "| ";
		}
		System.out.println(firstParent);
		
		for (int i = 0; i < combos2.length; i++)
		{
			String row = combos2[i] + " | ";
			for (int j = 0; j < combos1.length; j++)
			{
				row += punnett[i][j] + " | "; 
			}
			System.out.println(row);
		}
	}

	//just repeating a space n times - this probably already exists somewhere, but I couldn't find it
	public static String repeatChar (char c, int n) 
	{
		String repeated = "";
		for (int i = 0; i < n; i++)
		{
			repeated += c;
		}
		return repeated;
	}

}



