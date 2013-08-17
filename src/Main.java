
import java.util.ArrayList;
import java.util.Scanner;
import code.Clustering;
import code.Graph;
import code.Graph.Edge;
import code.Graph.Vertex;
import code.Kruskal;
import code.Prim;

/**
 * The main method
 * @author Roque E. Lopez
 */
public class Main{

	public static void main(String[] args){
		String filePath;
		int option;
		int k = 7;
		Graph graph = null;
		ArrayList<Edge> mst = null;
		ArrayList<Vertex> initials = null;
		
		Scanner in = new Scanner(System.in);
		System.out.println("Digite a direção do arquivo de teste:");
		filePath = in.next();//./resource/data.txt
		
		while(true){
			graph = new Graph();
			graph.generateGraph(filePath);
			System.out.println("Selecione uma opção");
			System.out.println(" 1 Agrupamento com Prim");
			System.out.println(" 2 Agrupamento com Kruskal");
			System.out.println(" 0 Sair");
			option = in.nextInt();
			if(option  == 0)
				break;
			if(option == 1){
				Vertex initial = graph.getRandomVertex();
				Prim prim = new Prim();
				mst = prim.getMST(graph, initial);
				mst = prim.removeEdges(mst, k);
				initials = prim.getInitials();
			}
			else if(option  == 2){
				Kruskal kruskal = new Kruskal();
				mst = kruskal.getMST(graph, k);
				initials = kruskal.getInitials();
			}	

			Clustering c = new Clustering(mst);
			c.clusterization(initials);
			c.plotClusters();
		}
		in.close();
	}
}
