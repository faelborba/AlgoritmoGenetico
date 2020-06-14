import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AlgoritmoGenetico {
	public static List <Tabela> tabela = new ArrayList<>();
	public static Tabela novoDado = new Tabela();
	public static List <DecimalBinario> db = new ArrayList<>();
	public static DecimalBinario unidade = new DecimalBinario();
	
	public static void main(String[] args) {
		int geracao=0;
		//gerando Decimal binário
		geraDecBin();
		
		//gerando tabela com os dados 1º fit
		geraTabela();
		while(true) {	
			//ordenar tabela pelo fitness		
			ordenaTabela();
			System.out.println(++geracao + "ª Geração");
			if (tabela.get(0).getFit() == 2  || geracao == 500 ) {		
				break;
			}
			
			//remove pop e pondera
			removePopulacao();
			
			//crossover
			crossover();
			
			//mutação
			mutacao();
			
			//decodificar cromossomos mutantes
			decodificar();
		}	
	}
	
	public static void decodificar() {
		for(int i=0; i<10; i++) {
			tabela.get(i).setX(comparaNum(tabela.get(i).getCromossomo().substring(0, 3)));
			tabela.get(i).setY(comparaNum(tabela.get(i).getCromossomo().substring(3, 6)));
			tabela.get(i).setFit(2-((tabela.get(i).getX()-2)*(tabela.get(i).getX()-2))
					-((tabela.get(i).getY()-3)*(tabela.get(i).getY()-3)));
		}
		System.out.println(" Tabela com decodificada com fit --");
		System.out.println("| X | Y | Cromos | F(X,Y) |");
		for(int i=0;i<tabela.size();i++) {
			System.out.println("| "+tabela.get(i).getX()+" | "+tabela.get(i).getY()+" | "
					+tabela.get(i).getCromossomo()+" |  " +tabela.get(i).getFit()+
					"\t  |  "+tabela.get(i).getPoderacao()+"  |"); 
		}
	}
	
	public static void mutacao() {
		List <Integer> listaTest= new ArrayList<>();
		Random rand = new Random();
		for(int i=0; i<5; i++) {		
			int teste = rand.nextInt(10);
			
			if(listaTest.contains(teste)) {// evita repetir linha
				System.out.println(" *** FOI *** --");
				i--;
				continue;
			}
			listaTest.add(teste);
			int teste2 = rand.nextInt(5);
			if(tabela.get(teste).getCromossomo().substring(teste2, teste2+1).equals("0")) {
				StringBuilder troca = new StringBuilder(tabela.get(teste).getCromossomo());
				troca.setCharAt(teste2, '1');
				tabela.get(teste).setCromossomo(troca.toString());
			}else {
				StringBuilder troca = new StringBuilder(tabela.get(teste).getCromossomo());
				troca.setCharAt(teste2, '0');
				tabela.get(teste).setCromossomo(troca.toString());
			}
			System.out.println(" mutacao linha: "+ teste +"\n gene: " +teste2);
			
		}
		System.out.println(" Tabela mutacao --");
		System.out.println("| X | Y | Cromos | F(X,Y) |");
		for(int i=0;i<tabela.size();i++) {
			System.out.println("| "+tabela.get(i).getX()+" | "+tabela.get(i).getY()+" | "
					+tabela.get(i).getCromossomo()+" |  " +tabela.get(i).getFit()+
					"\t  |  "+tabela.get(i).getPoderacao()+"  |"); 
		}
	}
	
	public static void crossover() {
		Random rand = new Random();
		List <Tabela> tabelaNova = new ArrayList<>();
		for(int i=0; i<4; i++) {
			int j=0;
			
			while(j < tabela.get(i).getPoderacao()) {
				novoDado = new Tabela();
				novoDado.setCromossomo(tabela.get(i).getCromossomo().substring(0, 4));
				//System.out.println(" cromossomo --"+ novoDado.getCromossomo());
				novoDado.setCromossomo(novoDado.getCromossomo() + tabela.get(rand.nextInt(4)).getCromossomo().substring(4, 6));
				
				tabelaNova.add(novoDado);
				j++;
			}
		}
		tabela = tabelaNova;
		
		System.out.println(" Tabela nova --");
		System.out.println("| X | Y | Cromos | F(X,Y) |");
		for(int i=0;i<tabela.size();i++) {
			System.out.println("| "+tabela.get(i).getX()+" | "+tabela.get(i).getY()+" | "
					+tabela.get(i).getCromossomo()+" |  " +tabela.get(i).getFit()+
					"\t  |  "+tabela.get(i).getPoderacao()+"  |"); 
		}		 
	}
	
	public static int comparaNum(String comp) {
		for(int i = 0; i<8; i++) {
			if(db.get(i).getBinario().contentEquals(comp)) {
				return i;
			}
			//System.out.println("|  " +db.get(i).getDecimal()+ "  | " +db.get(i).getBinario()+ " |");
		}
		return 0;
	}
	
	public static void removePopulacao() {
		System.out.println("\nTabela sem populacao descartada\n| X | Y | Cromos | F(X,Y) | Pon |");
		for(int i=0; i<10 ;i++) {
			if(i<4) {
				tabela.get(i).setPoderacao(4-i);
			}else {
				tabela.remove(4);
			}
			
		}
		
		for(int i=0;i<tabela.size();i++) {
			System.out.println("| "+tabela.get(i).getX()+" | "+tabela.get(i).getY()+" | "+tabela.get(i).getCromossomo()+" |  " +tabela.get(i).getFit()+ "\t  |  "+tabela.get(i).getPoderacao()+"  |");
		}
	}
	
	public static void ordenaTabela() {
		Collections.sort(tabela);
		System.out.println("\nTabela ordenada\n| X | Y | Cromos | F(X,Y) |");
		for(int i=0;i<10;i++) {
			System.out.println("| "+tabela.get(i).getX()+" | "+tabela.get(i).getY()+" | "+tabela.get(i).getCromossomo()+" |  " +tabela.get(i).getFit()+ "\t  |");
		}
	}
	
	public static void geraTabela() {
		Random random = new Random();
		int randX=0, randY=0; 
		
		for(int i=0; i<10; i++) {
			randX = random.nextInt(8);
			novoDado.setX(randX);
			novoDado.setCromossomo(db.get(randX).getBinario());
			
			randY = random.nextInt(8);
			novoDado.setY(randY);
			novoDado.setCromossomo(novoDado.getCromossomo() + db.get(randY).getBinario());
			
			novoDado.setFit(2-((randX-2)*(randX-2))-((randY-3)*(randY-3)));
			//
			tabela.add(novoDado);
			//
			novoDado = new Tabela();
		}
		
		System.out.println("| X | Y | Cromos | F(X,Y) |");
		for(int i=0;i<10;i++) {
			System.out.println("| "+tabela.get(i).getX()+" | "+tabela.get(i).getY()+" | "+tabela.get(i).getCromossomo()+" |  " +tabela.get(i).getFit()+ "\t  |");
		}
	}
	
	public static void geraDecBin() {		
		for(int i = 0; i<=7; i++) { 
			 unidade.setDecimal(i);
			 
			 if (i<4) {
				 if (i<2) {
					 unidade.setBinario("00"+Integer.toBinaryString(i));
				 }else {
					 unidade.setBinario("0"+Integer.toBinaryString(i));
				 }
			 }else {
				 unidade.setBinario(Integer.toBinaryString(i));
			 }
			  
			 db.add(unidade);
			 unidade = new DecimalBinario();
		}
		System.out.println("| Dec | Bin |");
		for(int i = 0; i<8; i++) {
			System.out.println("|  " +db.get(i).getDecimal()+ "  | " +db.get(i).getBinario()+ " |");
		}
	}
}